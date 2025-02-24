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
	
	
	Road(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {
		  super(id);
		  //Tiene que llamar a addIncomingRoad y a addOutgoingRoad de sus correspondientes Junctions
		  this._origin = srcJunc;
		  this._destiny = destJunc;
		  this._length = length;
		  this._maxVel = maxSpeed;
		  this._limVel = maxSpeed;
		  this._limCont= contLimit;
		  this._contAcum = 0;
		  this._listaCoches = new ArrayList<Vehicle>();
		  this._weatherReport = weather;
		  
		  try {
			  destJunc.addIncomingRoad(this);
			  srcJunc.addOutgoingRoad(this);
		  } catch (Exception e) {
			  // TODO Auto-generated catch block
			  e.getMessage();
		  }
		}

	
	public List<Vehicle> getVehicles(){
		return _listaCoches;
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
		this.reduceTotalContamination();
		this.updateSpeedLimit();
		Iterator<Vehicle> it = this._listaCoches.iterator();
		
		while(it.hasNext()) {
			Vehicle vehAux = it.next();
			this.calculateVehicleSpeed(vehAux);
			vehAux.advance(time);
		}
		this._listaCoches.sort(null);
	}
	
	@Override
	public JSONObject report() {
		JSONObject jroad = new JSONObject();
		jroad.put("id", this._id);
		jroad.put("speedlimit", this._limVel);
		jroad.put("weather", this._weatherReport);
		jroad.put("co2", this._contAcum);
		jroad.put("vehicles", this._id);
		JSONArray ja = new JSONArray();
		ja.put(this._listaCoches);
		jroad.put("vehicles",ja);
		
		return jroad;
	}

	abstract public void reduceTotalContamination();
	abstract public void updateSpeedLimit();
	abstract public void calculateVehicleSpeed(Vehicle v);
}
