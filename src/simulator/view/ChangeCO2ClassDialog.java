package simulator.view;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;

import org.json.JSONObject;
import org.json.JSONArray;

import simulator.misc.Pair;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.SetContClassEvent;
import simulator.model.Vehicle;

public class ChangeCO2ClassDialog extends JDialog{
	
	private boolean _estado;
	
	
	public ChangeCO2ClassDialog(Controller ctrl, List<Vehicle> listaVehicle, Frame f){
		super(f, true);
        //this.dialogInit();
		this.setTitle("Change CO2 class");
		this.initGUI(ctrl, listaVehicle);
		this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
	}
	
	private void initGUI(Controller ctrl, List<Vehicle> listaVehicle) {
	     JPanel mainPanel = new JPanel();
	     mainPanel.setLayout(new BoxLayout(mainPanel , BoxLayout.Y_AXIS));
	     
	     JTextArea descr = new JTextArea();
	     descr.setText("Schedule an event to change the CO2 class of a vehicle after a given number of simulation ticks from now");
	     descr.setLineWrap(true);
	     descr.setWrapStyleWord(true);
	     descr.setEditable(false);
	     descr.setOpaque(false);

	     mainPanel.add(descr);
	     //mainPanel.add(topPanel, );
	    
	     JPanel centerPanel = new JPanel(new FlowLayout());
	     centerPanel.add( new JLabel("Vehicle:"));
	     
	     JComboBox<String> botonVehiculo = new JComboBox<String>();
	     for(int i = 0; i< listaVehicle.size(); i++) {
	    	 botonVehiculo.addItem(listaVehicle.get(i).getId());
	     }
	     botonVehiculo.setPreferredSize(new Dimension(80, 25));
	     centerPanel.add(botonVehiculo);
	     centerPanel.add(new JLabel("   CO2 Class:"));
	     
	     JSpinner CO2Class = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));

	     CO2Class.setMaximumSize(new Dimension(50, 25));
	     CO2Class.setMinimumSize(new Dimension(50, 25));
	     CO2Class.setPreferredSize(new Dimension(80, 25));
	     centerPanel.add(CO2Class);
	     
	     centerPanel.add(new JLabel("   Ticks:"));
	     
	     JSpinner ticks = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));

	     ticks.setMaximumSize(new Dimension(50, 25));
	     ticks.setMinimumSize(new Dimension(50, 25));
	     ticks.setPreferredSize(new Dimension(80, 25));
	     centerPanel.add(ticks);
		 
		 mainPanel.add(centerPanel);
		 
		 JPanel botPanel = new JPanel(new FlowLayout());
		 botPanel.setPreferredSize(new Dimension(170, 70));
         mainPanel.add(botPanel);
         
		 JButton cancel = new JButton("Cancel");
         cancel.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent arg0) {
                   _estado = false;
                   ChangeCO2ClassDialog.this.setVisible(false); 
                }
              });
         botPanel.add(cancel);
		 JButton ok = new JButton("OK");
         ok.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
	            if (botonVehiculo.getSelectedItem() != null) {
	            	List<Pair<String, Integer>> vehiculo = new ArrayList<Pair<String, Integer>>();
	            	vehiculo.add(new Pair <String, Integer>(botonVehiculo.getSelectedItem().toString(), Integer.parseInt(CO2Class.getValue().toString())));
	            	SetContClassEvent evento = new SetContClassEvent(Integer.parseInt(ticks.getValue().toString()), vehiculo);
	            	ctrl.addEvent(evento);
	            }
	            else _estado = false;
	            ChangeCO2ClassDialog.this.setVisible(false);
            }
          });
         botPanel.add(ok);

         this.add(mainPanel);
         this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
         this.setVisible(false);
         this.pack();
	}
	public void open() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}

	public boolean getEstado() {
		return _estado;
	}
}
