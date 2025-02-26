package simulator.model;

public abstract class Event implements Comparable<Event> {

  private static long _counter = 0;

  protected int _time;
  protected long _time_stamp;

  Event(int time) {
    if ( time < 1 )
      throw new IllegalArgumentException("Invalid time: "+time);
    else {
      _time = time;
      _time_stamp = _counter++;
    }
  }

  int getTime() {
    return _time;
  }

  @Override
  public int compareTo(Event o) {
    // TODO complete the method to compare events according to their _time, and when
// _time is equal it compares the _time_stamp;
	  if(this._time > o._time) return 1;
	  if(this._time < o._time) return -1;
	  else {
		  if(this._time_stamp < o._time_stamp) return -1;
		  if(this._time_stamp > o._time_stamp) return 1;
		  else return 0;
	  }
  }

  abstract void execute(RoadMap map);
}