package simulator.model;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

abstract public class Road extends SimulatedObject{
	private Junction _origin;
	private Junction _destiny;
	private int _length;
	private int _maxVel;
	private int _limVel;
	private int _limCont;
	private int _contAcum;
	private List<Vehicle> _listaCoches;
	private Weather _weatherReport;
	
	
	Road(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {
		  super(id);
		  //Tiene que llamar a addIncomingRoad y a addOutgoingRoad de sus correspondientes Junctions
		  //srcJunc.addIncomingRoad(this)
		  //destJunc.addOutcomingRoad(this)
		  this._origin = srcJunc;
		  this._destiny = destJunc;
		  this._length = length;
		  this._maxVel = maxSpeed;
		  this._limVel = maxSpeed;
		  this._limCont= contLimit;
		  this._contAcum = 0;
		  this._listaCoches = new ArrayList<Vehicle>();
		  this._weatherReport = weather;
		}

	public Junction get_origin() {
		return _origin;
	}

	public Junction get_destiny() {
		return _destiny;
	}

	public int get_length() {
		return _length;
	}

	public int get_maxVel() {
		return _maxVel;
	}

	public int get_limVel() {
		return _limVel;
	}

	public int get_limCont() {
		return _limCont;
	}

	public int get_contAcum() {
		return _contAcum;
	}

	public List<Vehicle> get_listaCoches() {
		return _listaCoches;
	}

	public Weather get_weatherReport() {
		return _weatherReport;
	}
	
	public void enter(Vehicle v) throws Exception{
		if(v.getVActual() != 0 || v.getPos() != 0) throw new Exception("Coche que no toca");
		this._listaCoches.addLast(v);
	}
	
	public void exit(Vehicle v) throws Exception{
		this._listaCoches.remove(v);
	}
	
	@Override
	public void advance(int time){
		this.reduceTotalContamination();
		this.updateSpeedLimit();
		Iterator<Vehicle> it = this._listaCoches.iterator();
		while(it.hasNext()) {
			this.calculateVehicleSpeed(it.next());
			it.next().advance(time);
		}
		this._listaCoches.sort(null);
	}
	
	abstract public void reduceTotalContamination();
	abstract public void updateSpeedLimit();
	abstract public void calculateVehicleSpeed(Vehicle v);
}
