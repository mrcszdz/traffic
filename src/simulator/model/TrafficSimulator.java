package simulator.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.json.JSONArray;
import org.json.JSONObject;

public class TrafficSimulator {

    RoadMap _roadMap;
    Queue<Event> _events;
    int _time;

    public TrafficSimulator() {
        _roadMap = new RoadMap();
        _events = new PriorityQueue<>();
        _time = 0;
    }

    
    public void reset() {
    	this._roadMap.clear();
    	this._events.clear();
    	this._time = 0;    	
    }
    
    public void addEvent(Event e) throws IllegalArgumentException{ 
    	if(e.getTime() <= this._time) throw new IllegalArgumentException("Invalid Time");
    	this._events.add(e);
    }
    
    public void advance() {
    	this._time++;
    	Iterator<Event> it = this._events.iterator();
    	boolean tiempoMenor = true;
    	List<Event> listaRemove = new ArrayList<Event>();
    	
    	while(_events.size()>0 && _events.peek().getTime() == this._time) {
    		_events.poll().execute(_roadMap);
    	}
    	
    	/*while(it.hasNext() && tiempoMenor){
    		Event e = it.next();
    		if(e._time > this._time) tiempoMenor = false;
    		else if(e._time == this._time) {
    			e.execute(_roadMap);
    			listaRemove.add(e);
    		}
    	}
    	this._events.removeAll(listaRemove);
    	*/
    	
    	advanceJunctions();
    	advanceRoads();
    }


	private void advanceJunctions() {
		// TODO Auto-generated method stub
		List<Junction> MapaJunctions = new ArrayList<Junction>();
		MapaJunctions = this._roadMap.getJunctions();
		Iterator<Junction> itj = MapaJunctions.iterator();
		while(itj.hasNext()) {
			itj.next().advance(_time);
		}
	}
	
	private void advanceRoads() {
		// TODO Auto-generated method stub
		List<Road> MapaRoads= new ArrayList<Road>();
		MapaRoads= this._roadMap.getRoads();
		Iterator<Road> itr = MapaRoads.iterator();
		while(itr.hasNext()) {
			itr.next().advance(_time);
		}
	}
	
	public JSONObject report() {
		JSONObject jreport = new JSONObject();
		jreport.put("time", this._time);
		jreport.put("state", this._roadMap.report());
		return jreport;
	}
    //...
}
