package simulator.model;

import java.util.Iterator;
import java.util.List;

import simulator.misc.Pair;

public class SetWeatherEvent extends Event {

	private List<Pair<String, Weather>> _listaWeathers;
	
	public SetWeatherEvent(int time, List<Pair<String, Weather>> ws) throws IllegalArgumentException {
		super(time);
		
		if(ws == null) throw new IllegalArgumentException("Argumento invalido");
	    this._listaWeathers = ws;
	}

	@Override
	void execute(RoadMap map) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Iterator<Pair<String, Weather>> it = this._listaWeathers.iterator();
		while(it.hasNext()) {
			Pair<String, Weather> par = it.next();
			if(map.getRoad(par.getFirst()) == null) throw new IllegalArgumentException("Argumento invalido");
			map.getRoad(par.getFirst()).setWeather(par.getSecond());
		}
	}

	@Override
	public String toString() {
		String descr;
		descr = "Change Weather: [";
		for(int i = 0; i < _listaWeathers.size() - 1; i++) {
			descr += "(" + _listaWeathers.get(i).getFirst() + "," + _listaWeathers.get(i).getSecond()+ "),";
		}
		descr +="(" + _listaWeathers.get(_listaWeathers.size() - 1).getFirst() + "," + _listaWeathers.get(_listaWeathers.size() - 1).getSecond()+ ")]";
		return descr;
	}
	
}
