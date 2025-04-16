package simulator.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Junction;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class JunctionsTableModel extends AbstractTableModel implements TrafficSimObserver{

	private String[] _colNames = { "Id", "Green", "Queues"};
	private List<Junction> _lj;
	
	public JunctionsTableModel(Controller ctrl) {
		_lj = new ArrayList<Junction>();
		ctrl.addObserver(this);
	}
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	@Override
	public String getColumnName(int col) {
		return _colNames[col];
	}

	public void addVehicle(Junction j) {
		_lj.add(j);
		
		fireTableDataChanged();
	}
	
	public void reset() {
		_lj.clear();
		;
		fireTableDataChanged();
	}
	
	@Override
	public int getColumnCount() {
		return _colNames.length;
	}
	
	@Override
	public int getRowCount() {
		return _lj == null ? 0 : _lj.size();
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object s = null;
		switch (columnIndex) {
		case 0:
			s = _lj.get(rowIndex).getId();
			break;
		case 1:
			s = _lj.get(rowIndex).get_greenLightIndex();
			break;
		case 2:
			s = _lj.get(rowIndex).get_queueByRoad();
			break;
		}
		
		return s;
	}

	@Override
	public void onAdvance(RoadMap map, Collection<Event> events, int time) {
		// TODO Auto-generated method stub
		this.reset();
		_lj.addAll(map.getJunctions());
		fireTableDataChanged();
	}

	@Override
	public void onEventAdded(RoadMap map, Collection<Event> events, Event e, int time) {
		this.reset();
		_lj.addAll(map.getJunctions());
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
