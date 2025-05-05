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
import org.json.JSONArray;

import simulator.misc.Pair;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Road;
import simulator.model.SetContClassEvent;
import simulator.model.SetWeatherEvent;
import simulator.model.Vehicle;
import simulator.model.Weather;

public class ChangeWeatherDialog extends JDialog{
	
	
	private JSpinner _ticks;
	JComboBox<String> _botonWeather;
	private boolean _estado;
	private DefaultComboBoxModel<Road> _roadsModel;
	private JComboBox<Road> _botonRoads;
	
	public ChangeWeatherDialog(List<Road> listaRoad, Frame f){
		super(f, true);
        //this.dialogInit();
		this.setTitle("Change CO2 class");
		this.initGUI(listaRoad);
	}
	
	private void initGUI(List<Road> lr) {
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
	     
	     _roadsModel = new DefaultComboBoxModel<>();
	     _botonRoads = new JComboBox<Road>(_roadsModel);
	     

	     for(int i = 0; i< lr.size(); i++) {
	    	 _botonRoads.addItem(lr.get(i));
	     }
	     _botonRoads.setPreferredSize(new Dimension(80, 25));
	     centerPanel.add(_botonRoads);
	     centerPanel.add(new JLabel("   Weather:"));
	     
	     _botonWeather = new JComboBox<String>();
	    	 _botonWeather.addItem(Weather.CLOUDY.toString());
	    	 _botonWeather.addItem(Weather.WINDY.toString());
	    	 _botonWeather.addItem(Weather.RAINY.toString());
	    	 _botonWeather.addItem(Weather.STORM.toString());
	    	 _botonWeather.addItem(Weather.SUNNY.toString());
	     _botonWeather.setPreferredSize(new Dimension(80, 25));
	     centerPanel.add(_botonWeather);
	     
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
                   ChangeWeatherDialog.this.setVisible(false); 
                }
              });
         botPanel.add(cancel);
		 JButton ok = new JButton("OK");
         ok.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
	         
	            _estado = true;
	            ChangeWeatherDialog.this.setVisible(false);
            }
          });
         botPanel.add(ok);
         this.add(mainPanel);
         this.pack();
         this.setVisible(false);
         
         //this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	}	
	
	public Boolean open(Frame parent, List<Road> roads) {

		// update the comboxBox model -- if you always use the same no
		// need to update it, you can initialize it in the constructor.
		//
		_roadsModel.removeAllElements();
		for (Road r : roads)
			_roadsModel.addElement(r);

		// You can change this to place the dialog in the middle of the parent window.
		//
		setLocation(parent.getLocation().x + 10, parent.getLocation().y + 10);

		setVisible(true);
		return _estado && _botonRoads.getSelectedItem() != null;
	}

	public boolean getEstado() {
		return _estado;
	}
	
	public int getTicks() {
		return Integer.parseInt(_ticks.getValue().toString());
	}
	
	public String getRoad() {
		return _botonRoads.getSelectedItem().toString();
	}
	
	public Weather getWeather() {
		return Weather.valueOf(_botonWeather.getSelectedItem().toString());
	}
	
}
