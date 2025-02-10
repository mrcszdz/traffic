package simulator.model;
import java.util.List;
import java.util.ArrayList;

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
}
