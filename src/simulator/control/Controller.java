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
    	
    	p.println("{");
    	p.println("states:[");
    	
    	for (int i = 0; i < n-1; i++) {
    	    _sim.advance();
    	    p.println(_sim.report()+",");
    	}
        _sim.advance();
	    p.println(_sim.report());
    
		p.print("]}");
		//System.out.println(jo);
    }
    
    public void reset() {
    	this._sim.reset();
    }
    
}
