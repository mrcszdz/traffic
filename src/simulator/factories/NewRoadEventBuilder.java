package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.Weather;

public abstract class NewRoadEventBuilder extends Builder<Event> {

	protected int _time;
	protected String _id;
	protected String _src;
	protected String _dest;
	protected int _length;
    protected int _co2limit;
    protected int _maxspeed;
    protected Weather _weather;
	
	public NewRoadEventBuilder(String typeTag, String desc) {
		super(typeTag, desc);
	}
	
	 @Override
	    protected void fill_in_data(JSONObject o) {
	        o.put("time", "The time at which the event is executed");
	        o.put("maxspeed", "The vehicle's max speed");
	        o.put("id", "the vehicle id");
	        //... add other fields
	    }
	 
	 protected void parseRoad(JSONObject data) {
	        this._time = data.getInt("time");
	        this._id = data.getString("id");
	        this._src = data.getString("src");
	        this._dest = data.getString("dest");
	        this._length = data.getInt("length");
	        this._co2limit = data.getInt("co2limit");
	        this. _maxspeed = data.getInt("maxspeed");
	        String WString = data.getString("weather");
	        this._weather = Weather.valueOf(WString.toUpperCase());
	 }
}
