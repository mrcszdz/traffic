package simulator.model;

import java.util.Iterator;
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
		
		int nextGreen = currGreen + 1;
		for(int i = (nextGreen + 1) % roads.size(); i < roads.size(); i++) {

			if(roads.get(i)._listaCoches.size() > roads.get(nextGreen)._listaCoches.size())
				nextGreen = i;
		}
		return nextGreen;
	}
	
}
