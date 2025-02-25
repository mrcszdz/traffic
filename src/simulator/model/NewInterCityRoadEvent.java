package simulator.model;

public class NewInterCityRoadEvent extends NewRoadEvent {

	NewInterCityRoadEvent(int time, String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit,
			int length, Weather weather) {
		super(time, id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
		// TODO Auto-generated constructor stub
	}

	@Override
	void execute(RoadMap map) {
		// TODO Auto-generated method stub
		InterCityRoad road = new InterCityRoad(_id, _destJunction, _destJunction, _contLimit, _contLimit, _contLimit, _weather);
		map.addRoad(road);
	}
}
