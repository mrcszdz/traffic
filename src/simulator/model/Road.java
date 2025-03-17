package simulator.model;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

abstract public class Road extends SimulatedObject{
	protected Junction _origin;
	protected Junction _destiny;
	protected int _length;
	protected int _maxVel;
	protected int _limVel;
	protected int _limCont;
	protected int _contAcum;
	protected List<Vehicle> _listaCoches;
	protected Weather _weatherReport;
	
	
	Road(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather)throws IllegalArgumentException {
		  super(id);
		  
		  if(maxSpeed <= 0) throw new IllegalArgumentException("Velocidad Maxima invalida");
		  if(contLimit < 0) throw new IllegalArgumentException("Contaminacion maxima invalida");
		  if(length <= 0) throw new IllegalArgumentException("Tamaño invalido");
		  if(weather == null) throw new IllegalArgumentException("Tiempo invalido");
		  
		  this._origin = srcJunc;
		  this._destiny = destJunc;
		  this._length = length;
		  this._maxVel = maxSpeed;
		  this._limVel = maxSpeed;
		  this._limCont= contLimit;
		  this._contAcum = 0;
		  this._listaCoches = new ArrayList<Vehicle>();
		  this._weatherReport = weather;
		  
		
			  destJunc.addIncomingRoad(this);
			  srcJunc.addOutgoingRoad(this);
		 
		}

	
	public List<Vehicle> getVehicles(){
		return Collections.unmodifiableList(_listaCoches);
	}
	
	
	public Junction getSrc() {
		return _origin;
	}

	public Junction getDest() {
		return _destiny;
	}

	public int getLength() {
		return _length;
	}

	public int getMaxSpeed() {
		return _maxVel;
	}

	public int getSpeedLimit() {
		return _limVel;
	}

	public int getContLimit() {
		return _limCont;
	}

	public int getTotalCO2() {
		return _contAcum;
	}

	public List<Vehicle> get_listaCoches() {
		return Collections.unmodifiableList(this._listaCoches);
	}

	public Weather getWeather() {
		return _weatherReport;
	}
	
	public void setWeather(Weather weather) throws IllegalArgumentException{
		if (weather == null) throw new IllegalArgumentException ("Invalid weather");
		this._weatherReport = weather;
	}
	
	public void enter(Vehicle v) throws IllegalArgumentException{
		if(v.getSpeed() != 0 || v.getLocation() != 0) throw new IllegalArgumentException("Coche que no toca");
		this._listaCoches.add(v);
	}
	
	public void exit(Vehicle v){
		this._listaCoches.remove(v);
	}
	
	@Override
	public void advance(int time){
		this.updateSpeedLimit();
		Iterator<Vehicle> it = this._listaCoches.iterator();
		this.reduceTotalContamination();		
		while(it.hasNext()) {
			Vehicle vehAux = it.next();
			this.calculateVehicleSpeed(vehAux);
			vehAux.advance(time);
		}
		Collections.sort(this._listaCoches); 
	}
	
	@Override
	public JSONObject report() {
		JSONObject jroad = new JSONObject();
		jroad.put("id", this._id);
		jroad.put("speedlimit", this._limVel);
		jroad.put("weather", this._weatherReport.name());
		jroad.put("co2", this._contAcum);
		JSONArray ja = new JSONArray();
		Iterator<Vehicle> it = this._listaCoches.iterator();
		while(it.hasNext()) {
			ja.put(it.next().getId());
		}
		jroad.put("vehicles",ja);
		return jroad;
	}
	
	public void addContamination(int c) throws IllegalArgumentException{
		if(c < 0) throw new IllegalArgumentException("Coche que no toca");
		this._contAcum = this._contAcum + c;
	}

	abstract public void reduceTotalContamination();
	abstract public void updateSpeedLimit();
	abstract public void calculateVehicleSpeed(Vehicle v);
}
