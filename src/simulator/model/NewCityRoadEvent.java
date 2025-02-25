package simulator.model;

public class NewCityRoadEvent extends NewRoadEvent{

	NewCityRoadEvent(int time,String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {
		super(time, id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
		// TODO Auto-generated constructor stub
	}

	@Override
	void execute(RoadMap map) {
		CityRoad road = new CityRoad(_id, _destJunction, _destJunction, _contLimit, _contLimit, _contLimit, _weather);
		map.addRoad(road);
	}
}
