package simulator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class Junction extends SimulatedObject{
	private int _x;
	
	private int _y;
	
	private List<Road> _inRoads;

	private List<List<Vehicle>> _queues; // the i-th queue corresponds to the i-th road in _inRoads

	private Map<Road, List<Vehicle>> _queueByRoad; // for efficient lookup of queues

	private Map<Junction, Road> _outRoadByJunction; // indicates the road to take to reach a given junction

	private int _greenLightIndex; // the index of the road in _inRoads that has a green light (-1 if all lights are red)

	private int _lastSwitchingTime; // the last time the green light was switched from one road to another

	private LightSwitchingStrategy _lss;

	private DequeuingStrategy _dqs;
	
	public Junction(String id, LightSwitchingStrategy lsStrategy, DequeuingStrategy dqStrategy, int xCoor, int yCoor) throws IllegalArgumentException{
		  super(id);
		  
		  if(lsStrategy == null) throw new IllegalArgumentException("Invalid LightSwitching Strategy");
		  if(dqStrategy == null) throw new IllegalArgumentException("Invalid Dequeing Strategy");
		  if(xCoor < 0 || yCoor < 0) throw new IllegalArgumentException("Invalid Coords");
				  
		  _lss = lsStrategy;
		  _dqs = dqStrategy;
		  _x = xCoor;
		  _y = yCoor;
		  _greenLightIndex = -1;
		  _outRoadByJunction = new HashMap<Junction, Road>();
		  _queueByRoad = new HashMap<Road, List<Vehicle>>();
		  _inRoads = new ArrayList<Road>();
		  _queues = new ArrayList<List<Vehicle>>();
		  _lastSwitchingTime = 0;
		  
	}

	public void addIncomingRoad(Road r) throws IllegalArgumentException{
		if(r.getDest() != this) throw new IllegalArgumentException("Invalid Road");
		else {
			List<Vehicle> listaVehicle = new LinkedList<Vehicle>();
			this._inRoads.add(r);
			this._queues.add(listaVehicle);
			this._queueByRoad.put(r, listaVehicle);
		}
	}
	
	public int get_x() {
		return _x;
	}

	public int get_y() {
		return _y;
	}

	public List<Road> get_inRoads() {
		return _inRoads;
	}

	public List<List<Vehicle>> get_queues() {
		return _queues;
	}

	public Map<Road, List<Vehicle>> get_queueByRoad() {
		return _queueByRoad;
	}

	public Map<Junction, Road> get_outRoadByJunction() {
		return _outRoadByJunction;
	}

	public int get_greenLightIndex() {
		return _greenLightIndex;
	}

	public int get_lastSwitchingTime() {
		return _lastSwitchingTime;
	}

	public LightSwitchingStrategy get_lss() {
		return _lss;
	}

	public DequeuingStrategy get_dqs() {
		return _dqs;
	}
	
	public void addOutgoingRoad(Road r) throws IllegalArgumentException{
		if(r.getSrc() != this) throw new IllegalArgumentException("Invalid Road");
		else {
			this._outRoadByJunction.put(r._destiny, r);
		}
	}
	
	public void enter(Vehicle v) {
		this._queueByRoad.get(v.getRoad()).add(v);
	}
	
	public Road roadTo(Junction j) {
		return this._outRoadByJunction.get(j);
	}
	
	
	

	@Override
	void advance(int time) {
		int green = this._greenLightIndex;
		// TODO Auto-generated method stub
		if(green != -1) {
			Road greenRoad = this._inRoads.get(green);
			List<Vehicle> listRoad = this._queueByRoad.get(greenRoad);
			List<Vehicle> l = this._dqs.dequeue(listRoad);
			
			Iterator<Vehicle> lIterator = l.iterator();
			while(lIterator.hasNext()) {
				try {
					Vehicle coche = lIterator.next();
					coche.moveToNextRoad();
					listRoad.remove(coche);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.getMessage();
				}
			}
		}
		int nextGreen = this._lss.chooseNextGreen(_inRoads, _queues, green, this._lastSwitchingTime, time);
		if(green != nextGreen) {
			this._greenLightIndex = nextGreen;
			this._lastSwitchingTime = time;
		}
	}

	@Override
	public JSONObject report() {
		JSONObject jjunction = new JSONObject();
		jjunction.put("id", this._id);
		if(this._greenLightIndex == -1) jjunction.put("green", "none");
		else jjunction.put("green", this._inRoads.get(this._greenLightIndex).getId());
		JSONArray ja = new JSONArray();
		Iterator<Road> itr = this._inRoads.iterator();
		while(itr.hasNext()) {
			JSONObject jqueue = new JSONObject();
			Road road = itr.next();
			jqueue.put("road", road.getId());
			JSONArray ja1 = new JSONArray();
			Iterator<Vehicle> itC = this._queueByRoad.get(road).iterator();
			while(itC.hasNext()) {
				ja1.put(itC.next().getId());
			}
			jqueue.put("vehicles", ja1);
			ja.put(jqueue);
		}
		jjunction.put("queues", ja);
		
		return jjunction;
	}
}