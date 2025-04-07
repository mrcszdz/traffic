package simulator.view;

import javax.swing.JPanel;

import simulator.control.Controller;
import simulator.model.TrafficSimObserver;

public class ControlPanel extends JPanel implements TrafficSimObserver{
	
	public ControlPanel(Controller ctrl) {
		JPanel layout = new JPanel();
		layout.setOpaque(true);
	}
}
