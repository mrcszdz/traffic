package simulator.factories;

import org.json.JSONObject;

import simulator.model.LightSwitchingStrategy;
import simulator.model.RoundRobinStrategy;

public class RoundRobinStrategyBuilder extends Builder<LightSwitchingStrategy> {

	public RoundRobinStrategyBuilder() {
		super("round_robin_lss", "roundRobinStrategy");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected LightSwitchingStrategy create_instance(JSONObject data) {
		int time = data.optInt("timeslot", 1);
		return new RoundRobinStrategy(time);
	}
	
	
}
