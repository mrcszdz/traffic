package simulator.model;

public class NewJunctionEvent extends Event{

	private String _id;
	private LightSwitchingStrategy _lsStrat;
	private DequeuingStrategy _dqStrat;
	private int _xCoor;
	private int _yCoor;
	
	public NewJunctionEvent(int time, String id, LightSwitchingStrategy lsStrategy, DequeuingStrategy dqStrategy, int xCoor, int yCoor) {
		  super(time);
		 this._id = id;
		 this._lsStrat = lsStrategy;
		 this._dqStrat = dqStrategy;
		 this._xCoor = xCoor;
		 this._yCoor = yCoor;
		}

	@Override
	void execute(RoadMap map) {
		Junction junct = new Junction(_id, _lsStrat, _dqStrat, _xCoor, _xCoor);
		map.addJunction(junct);
	}
}
