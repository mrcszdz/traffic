package simulator.view;

import java.awt.FlowLayout;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class StatusBar extends JPanel implements TrafficSimObserver{
	
	private JTextField _time;
	
	public StatusBar(Controller controller){
		
		initGUI(controller);
		controller.addObserver(this);
	}
	private void initGUI(Controller ctrl) {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel statusBar = new JPanel();
		_time = new JTextField("Time: " + ctrl.get_sim().get_time());
		statusBar.add(_time);
		_time.setOpaque(false);
		_time.setBorder(null);
		this.add(statusBar);
	}
	@Override
	public void onAdvance(RoadMap map, Collection<Event> events, int time) {
		_time.setText("Time: " + time);
		
	}
	@Override
	public void onEventAdded(RoadMap map, Collection<Event> events, Event e, int time) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onReset(RoadMap map, Collection<Event> events, int time) {
		_time.setText("Time: " + time);
		
	}
	@Override
	public void onRegister(RoadMap map, Collection<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}
}
