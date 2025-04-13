package simulator.view;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import simulator.control.Controller;
import simulator.model.TrafficSimObserver;

public class StatusBar extends JPanel implements TrafficSimObserver{
	
	private Controller _ctrl;
	
	public StatusBar(Controller controller){
		_ctrl = controller;
		initGUI();
	}
	private void initGUI() {
		JPanel statusBar = new JPanel(new FlowLayout());
		
		statusBar.add(new JLabel("Time:" + ));
	}
}
