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
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;

import simulator.control.Controller;
import simulator.model.Vehicle;

public class ChangeCO2ClassDialog extends JDialog{
	
	private String vehicle;
	private int co2Class;
	private int ticks;
	private boolean estado;
	private int indexComboBox;
	
	public ChangeCO2ClassDialog(Controller ctrl, Frame f){
		super(f, true);
        //this.dialogInit();
		this.setTitle("Change CO2 class");
		this.initGUI(ctrl);
		//this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
	}
	
	private void initGUI(Controller ctrl) {
	     JPanel mainPanel = new JPanel();
//	     mainPanel.setLayout(new BoxLayout(mainPanel , BoxLayout.Y_AXIS));
//	     mainPanel.setPreferredSize(new Dimension(500, 200));
	     
	     mainPanel.setLayout(new BorderLayout(10 , 5));
	     mainPanel.setPreferredSize(new Dimension(510, 210));
	     
	     JTextArea descr = new JTextArea();
	     descr.setText("Schedule an event to change the CO2 class of a vehicle after a given number of simulation ticks from now");
	     descr.setLineWrap(true);
	     descr.setWrapStyleWord(true);
	     descr.setEditable(false);
	     descr.setPreferredSize(new Dimension(170, 210));
	     
	     
	     JPanel topPanel = new JPanel(new FlowLayout());
	     topPanel.setPreferredSize(new Dimension(170, 70));
	  
	     topPanel.add(descr);
	     
	     mainPanel.add(topPanel, BorderLayout.NORTH);
	     //mainPanel.add(topPanel, );
	     List<Vehicle> vehiculos = ctrl.get_sim().get_roadMap().getVehicles();
	    
	     JPanel leftPanel = new JPanel(new FlowLayout());
	     JPanel centerPanel = new JPanel(new FlowLayout());
	     JPanel rightPanel = new JPanel(new FlowLayout());
	     
	     leftPanel.setPreferredSize(new Dimension(170, 70));
	     centerPanel.setPreferredSize(new Dimension(170, 70));
	     rightPanel.setPreferredSize(new Dimension(170, 70));
	     
	     leftPanel.add( new JLabel("Vehicle: "));
	     
	     JComboBox<String> botonVehiculo = new JComboBox<String>();
	     for(int i = 0; i< vehiculos.size(); i++) {
	    	 botonVehiculo.addItem(vehiculos.get(i).getId());
	     }
	     leftPanel.add(botonVehiculo);
	     
	     centerPanel.add(new JLabel("CO2 Class: "));
	     
	     JSpinner CO2Class = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));

	     CO2Class.setMaximumSize(new Dimension(50, 25));
	     CO2Class.setMinimumSize(new Dimension(50, 25));
	     CO2Class.setPreferredSize(new Dimension(50, 25));
	     centerPanel.add(CO2Class);
	     
	     rightPanel.add(new JLabel("Ticks: "));
	     
	     JSpinner ticks = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));

	     ticks.setMaximumSize(new Dimension(50, 25));
	     ticks.setMinimumSize(new Dimension(50, 25));
	     ticks.setPreferredSize(new Dimension(50, 25));
	     rightPanel.add(ticks);
		 
		 mainPanel.add(leftPanel, BorderLayout.WEST);
		 mainPanel.add(centerPanel, BorderLayout.CENTER);
		 mainPanel.add(rightPanel, BorderLayout.EAST);
		 
		 JPanel botPanel = new JPanel(new FlowLayout());
		 botPanel.setPreferredSize(new Dimension(170, 70));
		 
		 JButton ok = new JButton("OK");
         ok.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e ) {
                if (botonVehiculo.getSelectedItem() != null) {
                    estado = true;
                    indexComboBox = botonVehiculo.getSelectedIndex();
                    ChangeCO2ClassDialog.this.setVisible(false);
                }
            }

          });
         botPanel.add(ok);

         JButton cancel = new JButton("Cancel");
         cancel.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent arg0) {
                   estado = false;
                   ChangeCO2ClassDialog.this.setVisible(false); 
                }
              });
         botPanel.add(cancel);
        
         mainPanel.add(botPanel, BorderLayout.SOUTH);
         this.add(mainPanel);
         this.setVisible(false);
         this.pack();
	}

	public void open() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}     
}
