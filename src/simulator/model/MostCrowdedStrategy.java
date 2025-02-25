package simulator.model;

import java.util.List;

public class MostCrowdedStrategy implements LightSwitchingStrategy {

	private int _timeSlot;
	
	public MostCrowdedStrategy(int time){
		_timeSlot = time;
	}
	
	@Override
	public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime,
			int currTime) {
		if(roads.isEmpty()) return -1;
		if(currGreen != -1  && currTime - lastSwitchingTime < this._timeSlot) return currGreen;
		
		int nextGreen = (currGreen +1) % roads.size();
		if (currGreen == -1) currGreen = 0;
		for(int i = 0; i < roads.size(); i++) {
			int circular = (currGreen + i) % roads.size();
			if(qs.get(nextGreen).size() < qs.get(circular).size()) nextGreen = circular;
		}
		return nextGreen;
	}
}
