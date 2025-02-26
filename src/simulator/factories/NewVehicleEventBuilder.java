package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewVehicleEvent;

public class NewVehicleEventBuilder extends Builder<Event> {

    public NewVehicleEventBuilder() {
        super("new_vehicle", "A new vehicle");
    }

    @Override
    protected void fill_in_data(JSONObject o) {
        o.put("time", "The time at which the event is executed");
        o.put("maxspeed", "The vehicle's max speed");
        o.put("id", "the vehicle id");
        //... add other fields
    }

    @Override
    protected Event create_instance(JSONObject data) {
        int time = data.getInt("time");
        String id = data.getString("id");
        int maxSpeed = data.getInt("maxspeed");
        int contClass = data.getInt("class");
        List<String> itinerary = new ArrayList<String>();
		JSONArray ja = data.getJSONArray("itinerary");
		for (int i = 0; i < ja.length(); i++) {
			itinerary.add(ja.getString(i)); 
		}
        return new NewVehicleEvent(time, id, maxSpeed, contClass, itinerary);
    }
}
