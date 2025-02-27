package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.model.DequeuingStrategy;
import simulator.model.Event;
import simulator.model.LightSwitchingStrategy;
import simulator.model.NewJunctionEvent;
import simulator.model.NewVehicleEvent;

public class NewJunctionEventBuilder extends Builder<Event> {

	private Factory<LightSwitchingStrategy> _lssFactory;
	private Factory<DequeuingStrategy> _dqsFactory;
	
    public NewJunctionEventBuilder(Factory<LightSwitchingStrategy> lssFactory, Factory<DequeuingStrategy> dqsFactory) {
        super("new_junction", "A new junction");
        this._lssFactory = lssFactory;
        this._dqsFactory = dqsFactory;
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
        List<Integer> coor = new ArrayList<Integer>();
		JSONArray ja = data.getJSONArray("coor");
		for (int i = 0; i < ja.length(); i++) {
			coor.add(ja.getInt(i)); 
		}
		JSONObject lsstrat = data.getJSONObject("ls_strategy");
		JSONObject dqstrat = data.getJSONObject("dq_strategy");
        return new NewJunctionEvent(time, id, this._lssFactory.create_instance(lsstrat), this._dqsFactory.create_instance(dqstrat), coor.get(0), coor.get(1));
    }
}
