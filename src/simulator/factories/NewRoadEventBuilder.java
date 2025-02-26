package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;

public abstract class NewRoadEventBuilder extends Builder<Event> {

    public NewRoadEventBuilder() {
        super("new_road", "A new road");
    }

    @Override
    protected void fill_in_data(JSONObject o) {
        o.put("time", "The time at which the event is executed");
        o.put("maxspeed", "The vehicle's max speed");
        o.put("id", "the vehicle id");
        //... add other fields
    }

}
