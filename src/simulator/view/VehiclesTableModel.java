package simulator.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;
import simulator.model.Vehicle;

public class VehiclesTableModel extends AbstractTableModel implements TrafficSimObserver{

	private String[] _colNames = { "Id", "Location", "Itinerary", "CO2 class", "Max Speed", "Speed", "Total CO2", "Distance"};
	private List<Vehicle> _lv;
	
	public VehiclesTableModel(Controller ctrl) {
		_lv = new ArrayList<Vehicle>();
		ctrl.addObserver(this);
	}
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	@Override
	public String getColumnName(int col) {
		return _colNames[col];
	}

	public void addVehicle(Vehicle v) {
		_lv.add(v);
		
		fireTableDataChanged();
	}
	
	public void reset() {
		_lv.clear();
		;
		fireTableDataChanged();
	}
	
	@Override
	public int getColumnCount() {
		return _colNames.length;
	}
	
	@Override
	public int getRowCount() {
		return _lv == null ? 0 : _lv.size();
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object s = null;
		switch (columnIndex) {
		case 0:
			s = _lv.get(rowIndex).getId();
			break;
		case 1:
			s = _lv.get(rowIndex).getRoad() + ":" + _lv.get(rowIndex).getLocation();
			break;
		case 2:
			s = _lv.get(rowIndex).getItinerary();
			break;
		case 3:
			s = _lv.get(rowIndex).getContClass();
			break;
		case 4:
			s = _lv.get(rowIndex).getMaxSpeed();
			break;
		case 5:
			s = _lv.get(rowIndex).getSpeed();
			break;
		case 6:
			s = _lv.get(rowIndex).getTotalCO2();
			break;
		case 7:
			s = _lv.get(rowIndex).getDist();
			break;
		}
		
		return s;
	}

	@Override
	public void onAdvance(RoadMap map, Collection<Event> events, int time) {
		// TODO Auto-generated method stub
		this.reset();
		_lv.addAll(map.getVehicles());
		fireTableDataChanged();
	}

	@Override
	public void onEventAdded(RoadMap map, Collection<Event> events, Event e, int time) {
		this.reset();
		_lv.addAll(map.getVehicles());
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
