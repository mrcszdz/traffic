package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class RoadMap {
	private List<Vehicle> _vehicles;
	private List<Road> _roads;
	private List<Junction> _junctions;
	private	Map<String, Vehicle> _vehiclesMap;
	private Map<String, Road> _roadsMap;
	private Map<String, Junction> _junctionsMap;
	
public RoadMap() {
	this._vehicles = new ArrayList<Vehicle>();
	this._roads = new ArrayList<Road>();
	this._junctions = new ArrayList<Junction>();
	this._vehiclesMap = new HashMap<String, Vehicle>();
	this._roadsMap = new HashMap<String, Road>();
	this._junctionsMap = new HashMap<String, Junction>();
}
	
	public void addJunction(Junction j) throws IllegalArgumentException{
		if(this._junctionsMap.get(j.getId()) != null) throw new IllegalArgumentException();
		this._junctions.add(j);
		this._junctionsMap.put(j.getId(), j);
	}
	
	public void addRoad(Road r) throws IllegalArgumentException{
		if(this._roadsMap.get(r.getId()) != null) throw new IllegalArgumentException();
		if(!this._junctions.contains(r.getDest()) || !this._junctions.contains(r.getSrc())) throw new IllegalArgumentException();
		this._roads.add(r);
		this._roadsMap.put(r.getId(), r);		
	}
	
	public void addVehicle(Vehicle v) {
		if(this._vehiclesMap.get(v.getId()) != null) throw new IllegalArgumentException();
		Iterator<Junction> itj = v.getItinerary().iterator();
		Junction J1 = itj.next();
		while(itj.hasNext()) {
			Junction J2 = itj.next();
			if(J1.get_outRoadByJunction().get(J2) == null) throw new IllegalArgumentException();
			J1 = J2;
		}
		this._vehicles.add(v);
		this._vehiclesMap.put(v.getId(), v);
	}
	
	public Vehicle getVehicle(String id) {
		if(this._vehiclesMap.containsKey(id))return this._vehiclesMap.get(id);
		else return null;
	}
	

	public Road getRoad(String id) {
		if(this._roadsMap.containsKey(id))return this._roadsMap.get(id);
		else return null;
	}
	
	public Junction getJunction(String id) {
		if(this._junctionsMap.containsKey(id))return this._junctionsMap.get(id);
		else return null;
	}
	
	public List<Vehicle> getVehicles(){
		return Collections.unmodifiableList(_vehicles);
	}
	
	public List<Road> getRoads(){
		return Collections.unmodifiableList(_roads);
	}
	
	public List<Junction> getJunctions(){
		return Collections.unmodifiableList(_junctions);
	}
	
	public JSONObject report() {
		JSONObject jroad = new JSONObject();
		JSONArray ja = new JSONArray();
		JSONArray ja1 = new JSONArray();
		JSONArray ja2 = new JSONArray();
		Iterator<Vehicle> itv = this._vehicles.iterator();
		Iterator<Road> itr = this._roads.iterator();
		while(itr.hasNext()) {
			ja1.put(itr.next().report());
		}
		jroad.put("roads",ja1);
		
		while(itv.hasNext()) {
			ja.put(itv.next().report());
		}
		jroad.put("vehicles",ja);
		
		Iterator<Junction> itj = this._junctions.iterator();
		while(itj.hasNext()) {
			ja2.put(itj.next().report());
		}
		jroad.put("junctions",ja2);
		return jroad;
	}
	
}
