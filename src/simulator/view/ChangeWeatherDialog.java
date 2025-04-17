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
import simulator.model.Road;
import simulator.model.SetContClassEvent;
import simulator.model.SetWeatherEvent;
import simulator.model.Weather;

public class ChangeWeatherDialog extends JDialog{
	
	private boolean _estado;
	public ChangeWeatherDialog(Controller ctrl, List<Road> listaRoad, Frame f){
		super(f, true);
        //this.dialogInit();
		this.setTitle("Change CO2 class");
		this.initGUI(ctrl, listaRoad);
	}
	
	private void initGUI(Controller ctrl, List<Road> lr) {
	     JPanel mainPanel = new JPanel();
	     mainPanel.setLayout(new BoxLayout(mainPanel , BoxLayout.Y_AXIS));
	     
	     JTextArea descr = new JTextArea();
	     descr.setText("Schedule an event to change the weather of a road after a given number of simulation ticks from now");
	     descr.setLineWrap(true);
	     descr.setWrapStyleWord(true);
	     descr.setEditable(false);
	     descr.setOpaque(false);

	     mainPanel.add(descr);
	     //mainPanel.add(topPanel, );
	    
	     JPanel centerPanel = new JPanel(new FlowLayout());
	     centerPanel.add( new JLabel("Road:"));
	     
	     JComboBox<String> botonRoad = new JComboBox<String>();
	     for(int i = 0; i< lr.size(); i++) {
	    	 botonRoad.addItem(lr.get(i).getId());
	     }
	     botonRoad.setPreferredSize(new Dimension(80, 25));
	     centerPanel.add(botonRoad);
	     centerPanel.add(new JLabel("   Weather:"));
	     
	     JComboBox<String> botonWeather = new JComboBox<String>();
	    	 botonWeather.addItem(Weather.CLOUDY.toString());
	    	 botonWeather.addItem(Weather.WINDY.toString());
	    	 botonWeather.addItem(Weather.RAINY.toString());
	    	 botonWeather.addItem(Weather.STORM.toString());
	    	 botonWeather.addItem(Weather.SUNNY.toString());
	     botonWeather.setPreferredSize(new Dimension(80, 25));
	     centerPanel.add(botonWeather);
	     
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
                   ChangeWeatherDialog.this.setVisible(false); 
                }
              });
         botPanel.add(cancel);
		 JButton ok = new JButton("OK");
         ok.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
	            if (botonRoad.getSelectedItem() != null) {
	            	List<Pair<String, Weather>> weather = new ArrayList<Pair<String, Weather>>();
	            	weather.add(new Pair <String, Weather>(botonRoad.getSelectedItem().toString(), Weather.valueOf(botonWeather.getSelectedItem().toString())));
	            	SetWeatherEvent evento = new SetWeatherEvent(ctrl.get_sim().get_time() + Integer.parseInt(ticks.getValue().toString()), weather);
	            	ctrl.addEvent(evento);
	            }
	            else _estado = false;
	            ChangeWeatherDialog.this.setVisible(false);
            }
          });
         botPanel.add(ok);
         this.add(mainPanel);
         this.pack();
         this.setVisible(true);
         
         //this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	}	

	public boolean getEstado() {
		return _estado;
	}
}
