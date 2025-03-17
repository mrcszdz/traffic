package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

public class Vehicle extends SimulatedObject implements Comparable<Vehicle> {
	private List<Junction> _itinerary;
	private int _vMaxima;
	private int _vActual;
	private VehicleStatus _status;
	private Road _carretera;
	private int _pos;
	private int _contClass;
	private int _contamAcum;
	private int _dist;
	private int _currentJunction;
	
	Vehicle(String id, int maxSpeed, int contClass, List<Junction> itinerary) throws IllegalArgumentException {
		  super(id);
		  if(maxSpeed <= 0) throw new IllegalArgumentException("Velocidad maxima negativa");
		 
		  if(0 > contClass || contClass > 10)throw new IllegalArgumentException("Velocidad maxima negativa");
		 
		  if (itinerary.size() < 2) throw new IllegalArgumentException("Velocidad maxima negativa");
		  
		  this._vMaxima = maxSpeed;
			this._vActual = 0;
			this._status = VehicleStatus.PENDING;
			this._pos = 0;
			this._carretera = null;
			this._contClass = contClass;
			this._contamAcum = 0;
			this._dist = 0;
			this._itinerary = Collections.unmodifiableList(new ArrayList<>(itinerary));
			this._currentJunction = 0;
	}
	
	public List<Junction> getItinerary() {
	    return _itinerary;
	}

	public int getMaxSpeed() {
	    return _vMaxima;
	}

	public int getSpeed() {
	    return _vActual;
	}
	
	public VehicleStatus getStatus() {
	    return _status;
	}

	public Road getRoad() {
	    return _carretera;
	}

	public int getLocation() {
	    return _pos;
	}

	public int getContClass() {
	    return _contClass;
	}

	public int getTotalCO2() {
	    return _contamAcum;
	}

	public int getDist() {
	    return _dist;
	}

	public int getCurrentJunction() {
	    return _currentJunction;
	}
		
	public void setSpeed (int s) throws IllegalArgumentException {
		if(this._status== VehicleStatus.TRAVELING) {
			if(s < 0) throw new IllegalArgumentException ("Velocidad no valida");
			else if(s >= this._vMaxima) this._vActual = this._vMaxima;
			else this._vActual = s;
		}
	}
	
	public void setContClass(int c) throws IllegalArgumentException {
		if(0 > c || c > 10) throw new IllegalArgumentException("Contaminacion invalida");
		else this._contClass = c;
	}
	
	public void advance(int time){
	
		if(_status == VehicleStatus.TRAVELING) {
			int posAux = this._pos + this._vActual;
			int length = this._carretera.getLength();
			int distAux;
			int contAux;
			if(posAux >= length) {
				distAux = length - this._pos;
				this._pos =  length;
				this._status = VehicleStatus.WAITING;
				this._vActual = 0;
				this._itinerary.get(this._currentJunction).enter(this);
			}
			else {
				distAux = posAux - this._pos;
				this._pos = posAux;
			}
			this._dist = this._dist + distAux;
			contAux = this._contClass * distAux;
			this._contamAcum += contAux;
			this._carretera.addContamination(contAux);
		}
	}
	
	public void moveToNextRoad() throws IllegalArgumentException {
		if(this._status != VehicleStatus.PENDING && this._status != VehicleStatus.WAITING) 
			throw new IllegalArgumentException ("Estado del coche no correspondiente");
		
		else {
			if(this._carretera != null) {
				this._carretera.exit(this);
				this._pos = 0;
			}
			if(this._currentJunction == this._itinerary.size() - 1) {
				this._status = VehicleStatus.ARRIVED;
				this._carretera = null;
				this._vActual = 0;
			}
			else {
				this._carretera = this._itinerary.get(this._currentJunction).roadTo(this._itinerary.get(this._currentJunction + 1));
				
					this._carretera.enter(this);
				
				this._currentJunction++;
				this._pos = 0;
				this._vActual = 0;
				this._status = VehicleStatus.TRAVELING;		
			}
		}
	}

	@Override
	public JSONObject report() {
		JSONObject jcar = new JSONObject();
		jcar.put("id", this.getId());
		jcar.put("speed", this._vActual);
		jcar.put("distance", this._dist);
		jcar.put("co2", this._contamAcum);
		jcar.put("class", this._contClass);
		jcar.put("status", this._status.toString());
		if(this._status != VehicleStatus.PENDING && this._carretera != null) {
			jcar.put("road", this._carretera.getId());
			jcar.put("location", this._pos);
		}
		return jcar;
	}

	@Override
	public int compareTo(Vehicle o) {
		if(this._pos > o._pos) return -1;
		else if(this._pos < o._pos) return 1;
		return 0;
	}
}
