package simulator.model;

public class InterCityRoad extends Road {
	
	InterCityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length,
			Weather weather) {
		super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void reduceTotalContamination() {
		int x = 20;
		switch(this._weatherReport) {
		case SUNNY:
			x = 2;
			break;
		case CLOUDY:
			x = 3;
			break;
		case RAINY:
			x = 10;
			break;
		case WINDY:
			x = 15;
			break;
		default:
			break;
		}
		this._contAcum = ((100-x)*this._contAcum)/100;
	}

	@Override
	public void updateSpeedLimit() {
		if(this._limCont < this._contAcum) {
			this._limVel = this._maxVel/2;
		}
		else this._limVel = this._maxVel;
	}

	@Override
	public void calculateVehicleSpeed(Vehicle v) {
		int speed;
		if(this._weatherReport == Weather.STORM) speed = (this._limVel*8)/10;
		else speed = this._limVel;
		
			v.setSpeed(speed);
		
	}
}
