package simulator.model;

abstract public class Road extends SimulatedObject{
	private Junction _origin;
	private Junction _destiny;
	private int _lenght;
	private int _maxVel;
	private int _limVel;
	private int _limCont;
	private Weather weatherReport;
	
	
	public Road() {
		
	}
}
