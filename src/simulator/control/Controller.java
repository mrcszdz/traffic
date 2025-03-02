package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Event;
import simulator.model.TrafficSimulator;

public class Controller {
    private TrafficSimulator _sim;
    private Factory<Event> _eventsFactory;

    public Controller(TrafficSimulator sim, Factory<Event> eventsFactory) {
        _sim = sim;
        _eventsFactory = eventsFactory;
    }
    
    public void loadEvents(InputStream in) {
    	//falta la excepcion q nipu de como hacerla
    	JSONObject jo = new JSONObject(new JSONTokener(in));
    	JSONArray ja = jo.getJSONArray("events");
    	for(int i = 0; i < ja.length(); i++) {
    		this._sim.addEvent(this._eventsFactory.create_instance(ja.getJSONObject(i)));
    	}
    }
    
    public void run(int n, OutputStream out) {
    	PrintStream p = new PrintStream(out);
    	JSONObject jo = new JSONObject();
    	JSONArray ja = new JSONArray();
    	
    	for (int i = 0; i < n; i++) {
    	    _sim.advance();
    	    ja.put(_sim.report());
    	}
		jo.put("states", ja);
		p.print(jo);
		System.out.println(jo);
    }
    
    public void reset() {
    	this._sim.reset();
    }
    
}
