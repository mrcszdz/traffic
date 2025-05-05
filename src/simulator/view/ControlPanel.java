package simulator.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.*;

import simulator.control.Controller;
import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.Road;
import simulator.model.RoadMap;
import simulator.model.SetContClassEvent;
import simulator.model.SetWeatherEvent;
import simulator.model.TrafficSimObserver;
import simulator.model.Vehicle;
import simulator.model.Weather;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ControlPanel extends JPanel implements TrafficSimObserver{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1206312102543205364L;
	private int _time;
    private Controller _ctrl;
    private boolean _stopped;
    private JToolBar _toolBar;
    private List<Vehicle> _listaVehicle;
    private List<Road> _listaRoad;
    private JButton _load;
    private JButton _co2;
    private JButton _weather;
    private JSpinner _ticks;
    private JButton _run;
    private JButton _stop;
    private JButton _quit;
    private ChangeCO2ClassDialog _changeCO2Class;
    private ChangeWeatherDialog _changeWeather;

	ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		_listaVehicle = new ArrayList<Vehicle>();
		_listaRoad = new ArrayList<Road>();
		initGUI();
	}

	private void initGUI() {
		
		setLayout(new BorderLayout());
		this._toolBar = new JToolBar();

		
		_changeCO2Class = new ChangeCO2ClassDialog(_listaVehicle, (Frame)SwingUtilities.getWindowAncestor(_toolBar));
		
		_changeWeather = new ChangeWeatherDialog(_listaRoad, (Frame)SwingUtilities.getWindowAncestor(_toolBar));
		
		_load = new JButton();
		_load.setActionCommand("load");
		_load.addActionListener(new ActionListener() {
		    @Override
		public void actionPerformed(ActionEvent e) {
	    	JFileChooser fileChooser = new JFileChooser("resources/examples");
	    	int v = fileChooser.showOpenDialog(null);
    		if (v == JFileChooser.APPROVE_OPTION){
    		   InputStream file;
				try {
					file = new FileInputStream(fileChooser.getSelectedFile());
					System.out.println ("loading selected file");
		    		   _ctrl.reset();
		    		   _ctrl.loadEvents(file);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(fileChooser,"No se ha encontrado el archivo","Error",JOptionPane.WARNING_MESSAGE);
				}
	    	}
	    	   else System.out.println ("load cancelled by user");
		    }
		});
		_load.setIcon (loadImage("resources/icons/open.png"));
		_toolBar.add(_load);
		
		_toolBar.addSeparator();
		
		_co2 = new JButton();
		_co2.setActionCommand("co2");
		_co2.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	Boolean accion = openCO2Dialog();
		    	if(accion) {
		    		addCO2Event();  
		    	}
		    }
		});
		_co2.setIcon(loadImage("resources/icons/co2class.png"));
		_toolBar.add(_co2);
		
		_weather = new JButton();
		_weather.setActionCommand("weather");
		_weather.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	Boolean accion = openWeatherDialog();
		    	if(accion) {
		    		addWeatherEvent();  
		    	}
		    }
		});
		_weather.setIcon(loadImage("resources/icons/weather.png"));
		_toolBar.add(_weather);
		
		
		JSpinner ticks = new JSpinner(new SpinnerNumberModel(10, 1, 10000, 1));
		
		ticks.setToolTipText("Simulation steps to run: 1-10000");
		ticks.setMaximumSize(new Dimension(80, 40));
		ticks.setMinimumSize(new Dimension(80, 40));
		
		//toolBar.addSeparator();
		
//		JSeparator Gris = new JSeparator(SwingConstants.VERTICAL);
//		Gris.setBackground(Color.GRAY);
//		toolBar.add(Gris);
        
		_toolBar.addSeparator();
		
		_run = new JButton();
		_run.setActionCommand("run");
		_run.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	enableToolbar(false);
		    	run_sim(Integer.parseInt(ticks.getValue().toString()));
		    }
		});
		_run.setIcon(loadImage("resources/icons/run.png"));
		_toolBar.add(_run);
		
		_stop = new JButton();
		_stop.setActionCommand("stop");
		_stop.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		       _stopped = true;
		    }
		});
		_stop.setIcon(loadImage("resources/icons/stop.png"));
		_toolBar.add(_stop);


				
		// Spinner
		_toolBar.add(new JLabel(" Ticks:"));
		
		// initial value = 10000 (first parameter)
		// initial range value = 1       (second parameter)
		// final range value   = 10000   (third parameter)
		// increment = 100				 (fourth parameter)
		
		_toolBar.add(ticks);

		
		_toolBar.add(Box.createGlue());
		_toolBar.addSeparator();
		
		// Exit
		_quit = new JButton();
		_quit.setToolTipText("Exit");
		_quit.setIcon(loadImage("resources/icons/exit.png"));
		_quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});
		_toolBar.add(_quit);
		this.add(_toolBar);
		_ctrl.addObserver(this);

	}
	
	protected ImageIcon loadImage(String path) {
		return new ImageIcon(Toolkit.getDefaultToolkit().createImage(path));
	}
	
	protected void quit() {
		int n = JOptionPane.showOptionDialog(this.getParent(), "Are sure you want to quit?", "Quit",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

		if (n == 0) {
			System.exit(0);
		}
	}
	
	protected void run_sim(int n) {
		if (n > 0 && !_stopped) {
			try {
				_ctrl.run(1);
				Thread.sleep(100);
	         		SwingUtilities.invokeLater(() -> run_sim(n - 1));
	         		
			} catch (Exception e) {
				// TODO show error message
				System.out.println("Error: " + e.getMessage());
				_stopped = true;
			}
		} else {
			_stopped = false;
			enableToolbar(true);
	                // TODO enable the toolbar
		}
	}
	
	private void enableToolbar(boolean b) {
		
		_load.setEnabled(b);
		_co2.setEnabled(b);
		_weather.setEnabled(b);
		_run.setEnabled(b);
		_quit.setEnabled(b);
	}

	@Override
	public void onAdvance(RoadMap map, Collection<Event> events, int time) {
		_time = time;
		_listaVehicle.clear();
		for	(int i = 0; i < map.getVehicles().size(); i++) {
			_listaVehicle.add(map.getVehicles().get(i));
		}
		_listaRoad.clear();
		for	(int i = 0; i < map.getRoads().size(); i++) {
			_listaRoad.add(map.getRoads().get(i));
		}
		
	}

	@Override
	public void onEventAdded(RoadMap map, Collection<Event> events, Event e, int time) {
		_time = time;
		_listaVehicle.clear();
		for	(int i = 0; i < map.getVehicles().size(); i++) {
			_listaVehicle.add(map.getVehicles().get(i));
		}
		_listaRoad.clear();
		for	(int i = 0; i < map.getRoads().size(); i++) {
			_listaRoad.add(map.getRoads().get(i));
		}
	}

	@Override
	public void onReset(RoadMap map, Collection<Event> events, int time) {
		_time = time;
		_listaVehicle.clear();
		_listaRoad.clear();
		
	}

	@Override
	public void onRegister(RoadMap map, Collection<Event> events, int time) {
		_time = time;
		// TODO Auto-generated method stub
		
	}

	
	private Boolean openCO2Dialog() {
    	return _changeCO2Class.open(simulator.view.ViewUtils.getWindow(this), _listaVehicle);
	}
	
	private Boolean openWeatherDialog() {
    	return _changeWeather.open(simulator.view.ViewUtils.getWindow(this), _listaRoad);
	}
	
	
	private void addWeatherEvent() {
		   
        List<Pair<String, Weather>> weather = new ArrayList<Pair<String, Weather>>();
        weather.add(new Pair <String, Weather>(_changeWeather.getRoad(), _changeWeather.getWeather()) );
        SetWeatherEvent evento = new SetWeatherEvent(_time + _changeWeather.getTicks(), weather);
        _ctrl.addEvent(evento);

	}
	
	private void addCO2Event() {
		List<Pair<String, Integer>> vehiculo = new ArrayList<Pair<String, Integer>>();
        vehiculo.add(new Pair <String, Integer>(_changeCO2Class.getBoton(), _changeCO2Class.getInt()));
        SetContClassEvent evento = new SetContClassEvent((_time + _changeCO2Class.getTicks()), vehiculo);
        	_ctrl.addEvent(evento);
	}
}
