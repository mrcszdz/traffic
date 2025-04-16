package simulator.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Road;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class RoadsTableModel extends AbstractTableModel implements TrafficSimObserver{

	private String[] _colNames = { "Id", "Length", "Weather", "Max Speed", "SpeedLimit", "Total CO2", "CO2 Limit"};
	private List<Road> _lr;
	
	public RoadsTableModel(Controller ctrl) {
		_lr = new ArrayList<Road>();
		ctrl.addObserver(this);
	}
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	@Override
	public String getColumnName(int col) {
		return _colNames[col];
	}

	public void addVehicle(Road r) {
		_lr.add(r);
		
		fireTableDataChanged();
	}
	
	public void reset() {
		_lr.clear();
		;
		fireTableDataChanged();
	}
	
	@Override
	public int getColumnCount() {
		return _colNames.length;
	}
	
	@Override
	public int getRowCount() {
		return _lr == null ? 0 : _lr.size();
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object s = null;
		switch (columnIndex) {
		case 0:
			s = _lr.get(rowIndex).getId();
			break;
		case 1:
			s = _lr.get(rowIndex).getLength();
			break;
		case 2:
			s = _lr.get(rowIndex).getWeather();
			break;
		case 3:
			s =	_lr.get(rowIndex).getMaxSpeed();
			break;
		case 4:
			s = _lr.get(rowIndex).getSpeedLimit();
			break;
		case 5:
			s = _lr.get(rowIndex).getTotalCO2();
			break;
		case 6:
			s = _lr.get(rowIndex).getContLimit();
			break;
		}
		
		return s;
	}

	@Override
	public void onAdvance(RoadMap map, Collection<Event> events, int time) {
		// TODO Auto-generated method stub
		this.reset();
		_lr.addAll(map.getRoads());
		fireTableDataChanged();
	}

	@Override
	public void onEventAdded(RoadMap map, Collection<Event> events, Event e, int time) {
		this.reset();
		_lr.addAll(map.getRoads());
		fireTableDataChanged();
	}

	@Override
	public void onReset(RoadMap map, Collection<Event> events, int time) {
		this.reset();
		fireTableDataChanged();
	}

	@Override
	public void onRegister(RoadMap map, Collection<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}
}
