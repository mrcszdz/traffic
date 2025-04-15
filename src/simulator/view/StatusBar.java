package simulator.view;

import java.awt.FlowLayout;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JPanel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class StatusBar extends JPanel implements TrafficSimObserver{
	
	private Controller _ctrl;
	
	public StatusBar(Controller controller){
		_ctrl = controller;
		initGUI();
	}
	private void initGUI() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel statusBar = new JPanel();
		
		statusBar.add(new JLabel("Time:  " + _ctrl.get_sim().get_time()));
		this.add(statusBar);
	}
	@Override
	public void onAdvance(RoadMap map, Collection<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onEventAdded(RoadMap map, Collection<Event> events, Event e, int time) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onReset(RoadMap map, Collection<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onRegister(RoadMap map, Collection<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}
}
