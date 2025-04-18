package simulator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Road;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;
import simulator.model.Vehicle;
import simulator.model.VehicleStatus;
import simulator.model.Weather;

public class MapByRoadComponent extends JComponent implements TrafficSimObserver{

	private static final int _JRADIUS = 10;
	
	private static final Color _IN_JUNCTION_COLOR = Color.BLUE;
	private static final Color _GREEN_LIGHT_COLOR = Color.GREEN;
	private static final Color _RED_LIGHT_COLOR = Color.RED;
	private static final Color _BG_COLOR = Color.WHITE;
	private Controller _ctrl;
	private RoadMap _map;
	
	private Image _car;
	
	MapByRoadComponent(Controller ctrl) {
		initGUI();
		ctrl.addObserver(this);
		_ctrl = ctrl;
		setPreferredSize(new Dimension (300, 200));
	}
	
	
	private void initGUI() {
		// TODO Auto-generated method stub
		_car =  loadImage("car.png");
		
	}
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		// clear with a background color
		g.setColor(_BG_COLOR);
		g.clearRect(0, 0, getWidth(), getHeight());

		if (_map == null || _map.getJunctions().size() == 0) {
			g.setColor(Color.red);
			g.drawString("No map yet!", getWidth() / 2 - 50, getHeight() / 2);
		} else {
			//updatePrefferedSize();
			drawMap(g);
		}
	}
	
	
	private void drawMap(Graphics g) {
		drawRoads(g);
		drawVehicles(g);
		drawJunctions(g);
		drawWeather(g);
		drawCO2(g);
	}
	
	
	private void drawRoads(Graphics g) {
		int i = 0;
		for (Road r : _map.getRoads()) {
			int x1 = 50;
			int y1 = (i+1)*50+5;
			int x2 = getWidth()-100;
			int y2 = (i+1)*50+5;
			g.setColor(Color.BLACK);
			g.drawLine(x1, y1, x2, y2);
			g.drawString(r.getId(), x1 - 30, y1 + 3);
			i++;
		}
		//TODO poner el identificador de la road al lado.
	}
	
	private void drawVehicles(Graphics g) {
		for (Vehicle v : _map.getVehicles()) {
			if (v.getStatus() != VehicleStatus.ARRIVED) {

				Road r = v.getRoad();
				
	
				// The calculation below compute the coordinate (vX,vY) of the vehicle on the
				// corresponding road. It is calculated relatively to the length of the road, and
				// the location on the vehicle.
				int x1 = 50;
				int y1 = (_map.getRoads().indexOf(r)+1)*50; //a revisar jejej
				int x2 = getWidth()-100;

				int vX = x1 + (int) ((x2 - x1) * ((double) v.getLocation() / (double) r.getLength()));
				int vY = y1;				

				g.drawImage(_car, vX, vY - 6, 16, 16, this);
				g.drawString(v.getId(), vX, vY - 8);
				//TODO poner el identificador del vehículo encima de la imagen.
			}
		}
	}
	
	
	private void drawJunctions(Graphics g) {
		int i = 0;
		for (Road r : _map.getRoads()) {
			g.setColor(_IN_JUNCTION_COLOR);
			g.fillOval(50, (i+1)*50, _JRADIUS, _JRADIUS);
			g.drawString(r.getSrc().getId(), 50, ((i+1)*50) - 8);
			g.setColor(_RED_LIGHT_COLOR);
			int idx = r.getDest().get_greenLightIndex();
			if (idx != -1 && r.equals(r.getDest().get_inRoads().get(idx))) {
				g.setColor(_GREEN_LIGHT_COLOR);
			}
			g.fillOval(getWidth()-100, (i+1)*50, _JRADIUS, _JRADIUS);
			g.drawString(r.getDest().getId(), getWidth()-100, ((i+1)*50) - 8);
			i++;
		}
		//TODO poner el nombre d la junction encima.
	}
	
	private void drawWeather(Graphics g) {
		for (Road r : _map.getRoads()) {
			int x1 = getWidth()-70;
			int y1 = (_map.getRoads().indexOf(r)+1)*50; //a revisar jeje
			Weather w = r.getWeather();
			Image weatherImg = loadImage("sun.png");;
			switch (w) {
			case SUNNY:
				weatherImg = loadImage("sun.png");
				break;
			case RAINY: 
				weatherImg = loadImage("rain.png");
				break;
			case CLOUDY:
				weatherImg = loadImage("cloud.png");
				break;
			case WINDY:
				weatherImg = loadImage("wind.png");
				break;
			case STORM:
				weatherImg = loadImage("storm.png");
				break;
			}
			g.drawImage(weatherImg, x1, y1 - 6, 32, 32, this);
		}
	}
	
	
	private void drawCO2(Graphics g) {
		for (Road r : _map.getRoads()) {
			int x1 = getWidth()-35;
			int y1 = (_map.getRoads().indexOf(r)+1)*50; //a revisar jeje
			int C = (int) Math.floor(Math.min((double)r.getTotalCO2()/(1.0 + (double)  r.getContLimit()),1.0) / 0.19);
			
			Image co2Img = loadImage("cont_0.png");;
			switch (C) {
			case 0:
				co2Img = loadImage("cont_0.png");
				break;
			case 1: 
				co2Img = loadImage("cont_1.png");
				break;
			case 2:
				co2Img = loadImage("cont_2.png");
				break;
			case 3:
				co2Img = loadImage("cont_3.png");
				break;
			case 4:
				co2Img = loadImage("cont_4.png");
				break;
			case 5:
				co2Img = loadImage("cont_5.png");
				break;
			}
			g.drawImage(co2Img, x1, y1 - 6, 32, 32, this);
		}
	}
	
	
	private Image loadImage(String img) {
		Image i = null;
		try {
			return ImageIO.read(new File("resources/icons/" + img));
		} catch (IOException e) {
		}
		return i;
	}
	
	public void update(RoadMap map) {
		SwingUtilities.invokeLater(() -> {
			_map = map;
			repaint();
		});
	}
	
	@Override
	public void onAdvance(RoadMap map, Collection<Event> events, int time) {
		update(map);
		
	}

	@Override
	public void onEventAdded(RoadMap map, Collection<Event> events, Event e, int time) {
		update(map);
		
	}

	@Override
	public void onReset(RoadMap map, Collection<Event> events, int time) {
		update(map);
		
	}

	@Override
	public void onRegister(RoadMap map, Collection<Event> events, int time) {
		update(map);
		
	}

}
