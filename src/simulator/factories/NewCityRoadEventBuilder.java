package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewCityRoadEvent;
import simulator.model.NewRoadEvent;
import simulator.model.NewVehicleEvent;
import simulator.model.Weather;

public class NewCityRoadEventBuilder extends Builder<Event> {

    public NewCityRoadEventBuilder() {
        super("new_city_road", "A new city road");
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
        String src = data.getString("src");
        String dest = data.getString("dest");
        int length = data.getInt("length");
        int co2limit = data.getInt("co2limit");
        int maxspeed = data.getInt("maxspeed");
        String WString = data.getString("weather");
        Weather weather = Weather.valueOf(WString);
      
        return new NewCityRoadEvent(time, id, src, dest, length, co2limit, maxspeed, weather);
    }
}