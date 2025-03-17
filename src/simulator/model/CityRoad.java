package simulator.model;

public class CityRoad extends Road {

	CityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {
		super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
	}

	@Override
	public void reduceTotalContamination() {
		if(this.getWeather() == Weather.WINDY || this.getWeather() == Weather.STORM) {
			this._contAcum -= 10;
		}
		else this._contAcum -= 2;
		if(this._contAcum < 0) this._contAcum = 0;
	}

	@Override
	public void updateSpeedLimit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void calculateVehicleSpeed(Vehicle v) {
		int speed;
		speed = ((11-v.getContClass())*this._limVel)/11;

			v.setSpeed(speed);
		
	}
}
