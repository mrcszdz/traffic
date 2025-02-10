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
		if(currGreen == -1) {
			Iterator<List<Vehicle>> it = qs.iterator();
			int max = 0;
			int i = 0;
			while(it.hasNext()) {
				i++;
				if(it.next().size() > qs.get(max).size()) max = i;
			}
			return max;
		}
		if (currTime-lastSwitchingTime < _timeSlot) return currGreen;
		/*
		int aux, k;
		if (currGreen + 1 >= roads.size()) aux = 0;
		else aux = currGreen + 1;
		
		if(aux >= roads.size()) k = 0;
		else k = aux + 1;
		
		for(int j = roads.size(); j > 0; j--) {
			if(qs.get(k).size() > qs.get(aux).size()) aux = k;
			if(k >= qs.size()) k = 0;
			else k++;			
		}
		return aux;
		*/
		return 0;
	}
	
}
