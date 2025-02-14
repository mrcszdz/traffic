package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	
	Vehicle(String id, int maxSpeed, int contClass, List<Junction> itinerary) throws Exception {
		  super(id);
		  if(maxSpeed < 0) throw new Exception("Velocidad maxima negativa");
		 
		  else if(0 > _contClass || _contClass > 10) throw new Exception("Contaminacion invalida");
		 
		  else if (_itinerary.size() < 2) throw new Exception("Itinerario invalido");
		  
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

	public int getVMaxima() {
	    return _vMaxima;
	}

	public int getVActual() {
	    return _vActual;
	}

	public VehicleStatus getStatus() {
	    return _status;
	}

	public Road getCarretera() {
	    return _carretera;
	}

	public int getPos() {
	    return _pos;
	}

	public int getContClass() {
	    return _contClass;
	}

	public int getContamAcum() {
	    return _contamAcum;
	}

	public int getDist() {
	    return _dist;
	}

	public int getCurrentJunction() {
	    return _currentJunction;
	}
		
	public void setSpeed (int s) throws Exception {
		if(s < 0) throw new Exception ("Velocidad no valida");
		else if(s >= this._vMaxima) this._vActual = this._vMaxima;
		else this._vActual = s;
	}
	
	public void setContaminationClass(int c) throws Exception {
		if(0 > _contClass || _contClass > 10) throw new Exception("Contaminacion invalida");
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
				this._pos = length;
				this._status = VehicleStatus.WAITING;
				this._vActual = 0;
			}
			else {
				distAux = posAux - this._pos;
				this._pos = posAux;
			}
			
			contAux = this._contClass * distAux;
			this._contamAcum += contAux;
			
		}
	}
	
	public void moveToNextRoad() throws Exception {
		if(this._status != VehicleStatus.PENDING && this._status != VehicleStatus.WAITING) 
			throw new Exception ("Estado del coche no correspondiente");
		
		else {
			if(this._carretera != null) this._carretera.exit(this);
			else if(this._currentJunction == this._itinerary.size() - 1) {
				this._status = VehicleStatus.ARRIVED;
				this._carretera = null;
				this._vActual = 0;
			}
			else {
				//añadir coche a la siguiente carretera del itinerario
				this._currentJunction++;
				this._pos = 0;
				this._vActual = 0;
				this._status = VehicleStatus.TRAVELING;
			}
		}
	}

	@Override
	public JSONObject report() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int compareTo(Vehicle o) {
		if(this._pos > o._pos) return 1;
		else if(this._pos < o._pos) return -1;
		return 0;
	}
}
