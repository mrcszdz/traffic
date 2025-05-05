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
import javax.swing.DefaultComboBoxModel;
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

import extra.jdialog.ex1.Dish;

import org.json.JSONArray;

import simulator.misc.Pair;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.SetContClassEvent;
import simulator.model.Vehicle;

public class ChangeCO2ClassDialog extends JDialog{
	
	private JSpinner _ticks;
	private JSpinner _CO2Class;
	private boolean _estado;
	private DefaultComboBoxModel<Vehicle> _vehiclesModel;
	private JComboBox<Vehicle> _botonVehiculo;
	public ChangeCO2ClassDialog(List<Vehicle> listaVehicle, Frame f){
		super(f, true);
        //this.dialogInit();
		this.setTitle("Change CO2 class");
		this.initGUI(listaVehicle);
	}
	
	private void initGUI( List<Vehicle> lv) {
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
	     
	     _vehiclesModel = new DefaultComboBoxModel<>();
	     _botonVehiculo = new JComboBox<Vehicle>(_vehiclesModel);
	     _botonVehiculo.setPreferredSize(new Dimension(80, 25));
	     centerPanel.add(_botonVehiculo);
	     centerPanel.add(new JLabel("   CO2 Class:"));
	     
	     _CO2Class = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));

	     _CO2Class.setMaximumSize(new Dimension(50, 25));
	     _CO2Class.setMinimumSize(new Dimension(50, 25));
	     _CO2Class.setPreferredSize(new Dimension(80, 25));
	     centerPanel.add(_CO2Class);
	     
	     centerPanel.add(new JLabel("   Ticks:"));
	     
	     _ticks = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));

	     _ticks.setMaximumSize(new Dimension(50, 25));
	     _ticks.setMinimumSize(new Dimension(50, 25));
	     _ticks.setPreferredSize(new Dimension(80, 25));
	     centerPanel.add(_ticks);
		 
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
	           
	            _estado = true;
	            ChangeCO2ClassDialog.this.setVisible(false);
            }
          });
         botPanel.add(ok);
         this.add(mainPanel);
         this.pack();
         this.setVisible(false);
         
         //this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	}	
	
	public Boolean open(Frame parent, List<Vehicle> vehicles) {

		// update the comboxBox model -- if you always use the same no
		// need to update it, you can initialize it in the constructor.
		//
		_vehiclesModel.removeAllElements();
		for (Vehicle v : vehicles)
			_vehiclesModel.addElement(v);

		// You can change this to place the dialog in the middle of the parent window.
		//
		setLocation(parent.getLocation().x + 10, parent.getLocation().y + 10);

		setVisible(true);
		return _estado && _botonVehiculo.getSelectedItem() != null;
	}
	
	public boolean getEstado() {
		return _estado;
	}
	
	public Integer getInt() {
		return Integer.parseInt(_CO2Class.getValue().toString());
	}
	
	public Integer getTicks() {
		return Integer.parseInt(_ticks.getValue().toString());
	}
	
	public String getBoton() {
		return _botonVehiculo.getSelectedItem().toString();
	}
}
