package simulator.model;

public class NewCityRoadEvent extends NewRoadEvent{

	public NewCityRoadEvent(int time,String id, String srcJunc, String destJunc, int length, int contLimit, int maxSpeed, Weather weather) {
		super(time, id, srcJunc, destJunc, length, contLimit, maxSpeed, weather);
		// TODO Auto-generated constructor stub
	}

	@Override
	void execute(RoadMap map) {
		CityRoad road = new CityRoad(_id, map.getJunction(this._srcJunction), map.getJunction(this._destJunction), this._maxSpeed, this._contLimit, this._length, this._weather);
		map.addRoad(road);
	}
}
