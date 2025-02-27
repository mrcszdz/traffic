package simulator.factories;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.SetWeatherEvent;
import simulator.model.Weather;

public class SetWeatherEventBuilder extends Builder<Event> {

	public SetWeatherEventBuilder() {
		super("set_weather", "setWeather");
		// TODO Auto-generated constructor stub
	}

	@Override
	public SetWeatherEvent create_instance(JSONObject data) {
		int time = data.getInt("time");
		List<Pair<String, Weather>> ws = new ArrayList<Pair<String,Weather>>();
		
		JSONArray jRoads = data.getJSONArray("info");
		for (int i = 0; i < jRoads.length(); i++) {
			JSONObject jroad = jRoads.getJSONObject(i);
			ws.add(new Pair<String, Weather>(jroad.getString("road"), Weather.valueOf(jroad.getString("weather"))));
		}
		return new SetWeatherEvent(time, ws);
	}

}
