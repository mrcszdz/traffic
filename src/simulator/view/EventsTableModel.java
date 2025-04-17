package simulator.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class EventsTableModel extends AbstractTableModel implements TrafficSimObserver{
	
	private List<Event> _events;
	private String[] _colNames = { "Time", "Desc." };
	
	public EventsTableModel(Controller ctrl) {
		_events = new ArrayList<Event>();
		ctrl.addObserver(this);
	}
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	@Override
	public String getColumnName(int col) {
		return _colNames[col];
	}

	public void addEvent(Event e) {
		_events.add(e);
		
		fireTableDataChanged();
	}
	
	public void reset() {
		_events.clear();
		;
		fireTableDataChanged();
	}
	
	@Override
	public int getColumnCount() {
		return _colNames.length;
	}
	
	@Override
	public int getRowCount() {
		return _events == null ? 0 : _events.size();
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object s = null;
		switch (columnIndex) {
		case 0:
			s = _events.get(rowIndex).getTime();
			break;
		case 1:
			s = _events.get(rowIndex).toString();
			break;
		}
		return s;
	}

	@Override
	public void onAdvance(RoadMap map, Collection<Event> events, int time) {
		this.reset();
		_events.addAll(events);
		fireTableDataChanged();
	}

	@Override
	public void onEventAdded(RoadMap map, Collection<Event> events, Event e, int time) {
		//this.reset();
		this.addEvent(e);
		fireTableDataChanged();
	}

	@Override
	public void onReset(RoadMap map, Collection<Event> events, int time) {
		this.reset();
		
	}

	@Override
	public void onRegister(RoadMap map, Collection<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}
	
}
