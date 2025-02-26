package simulator.factories;

import org.json.JSONObject;

import simulator.model.DequeuingStrategy;
import simulator.model.MoveAllStrategy;

public class MoveAllStrategyBuilder extends Builder<DequeuingStrategy> {

	public MoveAllStrategyBuilder() {
		super("move_all_dqs", "MoveAllStrategy");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected DequeuingStrategy create_instance(JSONObject data) {
		// TODO Auto-generated method stub
		return new MoveAllStrategy();
	}

}