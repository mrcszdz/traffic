package simulator.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NewVehicleEvent extends Event{
	private String _id;
	private int _maxSpeed;
	private int _contClass;
	private List<String> _itinerary;
	
	
	public NewVehicleEvent(int time, String id, int maxSpeed, int contClass, List<String> itinerary) {
	    super(time);
	    this._id = id;
	    this._maxSpeed = maxSpeed;
	    this._contClass = contClass;	
	    this._itinerary = new ArrayList<String>();
	    this._itinerary = itinerary;
	}

	@Override
	void execute(RoadMap map) {
		List<Junction> parse = new ArrayList<Junction>();
		Iterator<String> it = this._itinerary.iterator();
		while(it.hasNext()) {
			parse.add(map.getJunction(it.next()));
		}
		Vehicle v = new Vehicle(this._id, this._maxSpeed, this._contClass, parse);
		map.addVehicle(v);
		v.moveToNextRoad();
		// TODO Auto-generated method stub
		
	}
}
