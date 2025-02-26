package simulator.model;

public abstract class NewRoadEvent extends Event{

	protected String _id;
	protected String _srcJunction;
	protected String _destJunction;
	protected int _maxSpeed;
	protected int _contLimit;
	protected int _length;
	protected Weather _weather;
	
	NewRoadEvent(int time,String id, String srcJunc, String destJunc, int length, int contLimit, int maxSpeed, Weather weather) {
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
