package simulator.factories;



import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewCityRoadEvent;
import simulator.model.NewInterCityRoadEvent;
import simulator.model.NewRoadEvent;
import simulator.model.NewVehicleEvent;
import simulator.model.Weather;

public class NewCityRoadEventBuilder extends NewRoadEventBuilder {

    public NewCityRoadEventBuilder() {
        super("new_city_road", "A new city road");
    }

    @Override
    protected Event create_instance(JSONObject data) {
        this.parseRoad(data);
      
        return new NewCityRoadEvent(this._time, this._id, this._src, this._dest, this._length, this._co2limit, this._maxspeed, this._weather);
    }
}