package simulator.model;

import java.util.List;

public class Vehicle extends SimulatedObject {
	private List<Junction> _itinerary;
	private int _maxima;
	private int _actual;
	private VehicleStatus _status;
	private Road _carretera;
	private int _pos;
	private int _contClass;
	private int _contamAcum;
	private int _dist;
	
	Vehicle(String id, int maxSpeed, int contClass, List<Junction> itinerary) {
		  super(id);
		  if(maxSpeed < 0) throw new Exception("Velocidad maxima negativa");
		 
		  else if(0 > _contClass || _contClass > 10) throw new Exception("Contaminacion invalida");
		 
		  else if (_itinerary.size() < 2) throw new Exception("Itinerario invalido");
		  
		  this._maxima = maxSpeed;
			this._actual = 0;
			this._status = VehicleStatus.PENDING;
			this._pos = 0;
			this._carretera = null;
			this._contClass = contClass;
			this._contamAcum = 0;
			this._dist = 0;
			this._itinerary = Collections.unmodifiableList(new ArrayList<>(itinerary));
	}
		
	public void setSpeed (int s) {
		if(s < 0) throw new Exception ("Velocidad no valida");
		else if(s >= this._maxima) this._actual = this._maxima;
		else this._actual = s;
	}
	
	public void setContaminationClass(int c) {
		if(0 > _contClass || _contClass > 10) throw new Exception("Contaminacion invalida");
		else this._contClass = c;
	}
	
	public void advance(int time){
	
		if(_status == VehicleStatus.TRAVELING) {
			int posAux = this._pos + this._actual;
			int length = this._carretera.getLength();
			int distAux;
			int contAux;
			if(posAux >= length) {
				distAux = length - this._pos;
				this._pos = length;
				this._status = VehicleStatus.WAITING;
				this._actual = 0;
			}
			else {
				distAux = posAux - this._pos;
				this._pos = posAux;
			}
			
			contAux = this._contClass * distAux;
			this._contamAcum += contAux;
			
		}
	}
	
	public void moveToNextRoad() {
		if(this._status != VehicleStatus.PENDING && this._status != VehicleStatus.WAITING) 
			throw new Exception ("Estado del coche no correspondiente");
		
		else {
			if(this._carretera != null) this._carretera.exit();
			else if(this._status == VehicleStatus.WAITING &&
					!this._itinerary.hasNext()) {
				this._status = VehicleStatus.ARRIVED;
				this._carretera = null;
				this._actual = 0;
			}
		}
	}
}
