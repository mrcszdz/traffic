package simulator.model;

public abstract class NewRoadEvent extends Event{

	protected String _id;
	protected Junction _srcJunction;
	protected Junction _destJunction;
	protected int _maxSpeed;
	protected int _contLimit;
	protected int _length;
	protected Weather _weather;
	
	NewRoadEvent(int time,String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {
		super(time);
		this._id = id;
		this._srcJunction = srcJunc;
		this._destJunction = destJunc;
		this._maxSpeed = maxSpeed;
		this._contLimit = contLimit;
		this._length = length;
		this._weather = weather;
	}
	
}
