package simulator.model;

import java.util.LinkedList;
import java.util.List;

public class MoveAllStrategy implements DequeuingStrategy{

	@Override
	public List<Vehicle> dequeue(List<Vehicle> q) {
		List<Vehicle> listaCoches = new LinkedList<Vehicle>();
		listaCoches.addAll(q);
		return listaCoches;
	}

}
