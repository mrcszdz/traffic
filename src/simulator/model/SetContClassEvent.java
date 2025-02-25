package simulator.model;
import java.util.Iterator;
import java.util.List;

import simulator.misc.Pair;

public class SetContClassEvent extends Event {

	private List<Pair<String,Integer>> _cs;
	
	public SetContClassEvent(int time, List<Pair<String,Integer>> cs) throws IllegalArgumentException {
		  super(time);
		  if(cs == null) throw new IllegalArgumentException("Argumento invalido");
		  this._cs = cs;
		}
	
	@Override
	void execute(RoadMap map) throws IllegalArgumentException {
		Iterator<Pair<String, Integer>> it = this._cs.iterator();
		while(it.hasNext()) {
			Pair<String, Integer> par = it.next();
			if(map.getVehicle(par.getFirst()) == null) throw new IllegalArgumentException("Argumento invalido");
			map.getVehicle(par.getFirst()).setContClass(par.getSecond());
		}

	}

}
