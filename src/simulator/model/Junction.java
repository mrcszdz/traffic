package simulator.model;

import java.util.List;
import java.util.Map;

public class Junction extends SimulatedObject implements DequeuingStrategy{
	private int _x;
	
	private int _y;
	
	List<Road> _inRoads;

	List<List<Vehicle>> _queues; // the i-th queue corresponds to the i-th road in _inRoads

	Map<Road, List<Vehicle>> _queueByRoad; // for efficient lookup of queues

	Map<Junction, Road> _outRoadByJunction; // indicates the road to take to reach a given junction

	int _greenLightIndex; // the index of the road in _inRoads that has a green light (-1 if all lights are red)

	int _lastSwitchingTime; // the last time the green light was switched from one road to another

	LightSwitchingStrategy _lss;

	DequeuingStrategy _dqs;
	
	Junction(String id, LightSwitchStrategy lsStrategy, DequeingStrategy dqStrategy, int xCoor, int yCoor) {
		  super(id);
		  
		  _lss = lsStrategy;
		  _dqs = dqStrategy;
		  _x = xCoor;
		  _y = yCoor;
	}

	@Override
	public List<Vehicle> dequeue(List<Vehicle> q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void advance(int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JSONObject report() {
		// TODO Auto-generated method stub
		return null;
	}
}
