package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.SetContClassEvent;

public class SetContClassEventBuilder extends Builder<Event> {

	public SetContClassEventBuilder() {
		super("set_cont_class", "SetContClass");
		// TODO Auto-generated constructor stub
	}

	@Override
	public SetContClassEvent create_instance(JSONObject data) {
		int time = data.getInt("time");
		List<Pair<String, Integer>> ws = new ArrayList<Pair<String, Integer>>();
		
		JSONArray jVehicles = data.getJSONArray("info");
		for (int i = 0; i < jVehicles.length(); i++) {
			JSONObject jvehicle = jVehicles.getJSONObject(i);
			ws.add(new Pair<String, Integer>(jvehicle.getString("vehicle"), jvehicle.getInt("class"))); 
		}
		return new SetContClassEvent(time, ws);
	}
}
