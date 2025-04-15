package simulator.view;



import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import simulator.control.Controller;
import simulator.model.Vehicle;

public class ChangeCO2ClassDialog extends JDialog{
	
	private String vehicle;
	private int co2Class;
	private int ticks;
	
	public ChangeCO2ClassDialog(Controller ctrl){
		super();
		this.setTitle("Change CO2 class");
		this.initGUI(ctrl);
	}
	
	private void initGUI(Controller ctrl) {
	     JPanel mainPanel = new JPanel();
	     mainPanel.setLayout(new GridLayout(3,1));
	    
	     JLabel descr = new JLabel("Schedule an event to change the CO2 class of a vehicle after a given number of simulation ticks from now");
	     mainPanel.add(descr);
	     List<Vehicle> vehiculos = ctrl.get_sim().get_roadMap().getVehicles();
	    
	     JPanel midPanel = new JPanel(new FlowLayout());
	     
	     midPanel.add( new JLabel("Vehicle: "));
	     
	     JComboBox<String> botonVehiculo = new JComboBox<String>();
	     for(int i = 0; i< vehiculos.size(); i++) {
	    	 botonVehiculo.addItem(vehiculos.get(i).getId());
	     }
	     midPanel.add(botonVehiculo);
	     
	     midPanel.add(new JLabel("CO2 Class: "));
	     
	     JSpinner CO2Class = new JSpinner(new SpinnerNumberModel(10, 1, 10000, 1));

			ticks.setToolTipText("Simulation steps to run: 1-10000");
			ticks.setMaximumSize(new Dimension(80, 40));
			ticks.setMinimumSize(new Dimension(80, 40));
			ticks.setPreferredSize(new Dimension(80, 40));
			toolBar.add(ticks);
	     midPanel.add(botonVehiculo);
	     
	     midPanel.add(new JLabel("Ticks: "));
	     
	     JComboBox<String> botonVehiculo = new JComboBox<String>();
	     for(int i = 0; i< vehiculos.size(); i++) {
	    	 botonVehiculo.addItem(vehiculos.get(i).getId());
	     }
	     midPanel.add(botonVehiculo);
	}     
}
