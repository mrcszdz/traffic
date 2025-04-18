package simulator.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class StatusBar extends JPanel implements TrafficSimObserver{
	
	private JTextField _time;
	private JTextField _eventDescr;
	
	public StatusBar(Controller controller){
		
		initGUI(controller);
		controller.addObserver(this);
	}
	private void initGUI(Controller ctrl) {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel statusBar = new JPanel();
		_time = new JTextField("Time: " + ctrl.get_sim().get_time());
		_time.setOpaque(false);
		_time.setBorder(null);
		_time.setEditable(false);
		_time.setPreferredSize(new Dimension(140, 20));
		statusBar.add(_time);
		
		statusBar.add(new JSeparator(SwingConstants.VERTICAL));
		//statusBar.add();
		
		_eventDescr = new JTextField("Welcome!");
		_eventDescr.setOpaque(false);
		_eventDescr.setBorder(null);
		_eventDescr.setEditable(false);
		_eventDescr.setPreferredSize(new Dimension(400, 20));
		statusBar.add(_eventDescr);
		this.add(statusBar);
	}
	@Override
	public void onAdvance(RoadMap map, Collection<Event> events, int time) {
		_time.setText("Time: " + time);
		_eventDescr.setText("");
	}
	@Override
	public void onEventAdded(RoadMap map, Collection<Event> events, Event e, int time) {
		_eventDescr.setText("Event added (" + e.toString() + ")");
		
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
