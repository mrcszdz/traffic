package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewCityRoadEvent;
import simulator.model.NewInterCityRoadEvent;
import simulator.model.Weather;

public class NewInterCityRoadEventBuilder extends NewRoadEventBuilder {

    public NewInterCityRoadEventBuilder() {
        super("new_inter_city_road", "A new intercity road");
    }

    @Override
    protected Event create_instance(JSONObject data) {
        this.parseRoad(data);
      
        return new NewInterCityRoadEvent(this._time, this._id, this._src, this._dest, this._length, this._co2limit, this._maxspeed, this._weather);
    }
}