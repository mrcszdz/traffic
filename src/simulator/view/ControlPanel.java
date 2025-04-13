package simulator.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import simulator.control.Controller;
import simulator.model.TrafficSimObserver;

public class ControlPanel extends JPanel implements TrafficSimObserver{
	
	private Controller _ctrl;
	private int _ticks;
	
	public ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		initGUI();
	}

	private void initGUI() {
		JPanel layout = new JPanel();
		layout.setOpaque(true);

		
		JButton open = new JButton();
		open.setBounds(20,20,150,100); //esto es prueba error xq ni idea djfsabsha
		open.setIcon (new ImageIcon("icons/open.png"));
		open.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//lo que tiene q hacer cuando pulsas open
			}
			});
		layout.add(open); 
		
		
		JButton co2 = new JButton();
		co2.setBounds(220,220,150,100); //lo mismo
		co2.setIcon (new ImageIcon("icons/co2class.png"));
		co2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//lo que tiene q hacer cuando pulsas open
			}
			});
		layout.add(co2); 
		
		
		JButton weather = new JButton();
		weather.setBounds(420,420,150,100); //lo mismo
		weather.setIcon (new ImageIcon("icons/weather.png"));
		weather.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//lo que tiene q hacer cuando pulsas open
			}
			});
		layout.add(weather); 
		
		
		JButton play= new JButton();
		play.setBounds(620,620,150,100); //lo mismo
		play.setIcon (new ImageIcon("icons/run.png"));
		play.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//lo que tiene q hacer cuando pulsas open
			}
			});
		layout.add(play); 
		
		JButton stop= new JButton();
		stop.setBounds(820,820,150,100); //lo mismo
		stop.setIcon (new ImageIcon("icons/stop.png"));
		stop.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//lo que tiene q hacer cuando pulsas open
			}
			});
		layout.add(stop);
		
		
		JLabel ticks = new JLabel("Ticks"); //ESTO HAY Q REVISAR XQ ESTA EN UNA CAJA BLANCA
		ticks.setLocation(1000, 1000);
		ticks.setSize (50, 40);
		ticks.setHorizontalAlignment (JLabel.CENTER);
		layout.add(ticks);
		
		JLabel ticksNum = new JLabel( Integer.toString(this._ticks));
		ticksNum.setLocation(1100, 1100);
		ticksNum.setSize (50, 40);
		ticksNum.setHorizontalAlignment (JLabel.CENTER);
		layout.add(ticksNum);

		
		//LUEGO VA EL BOTON ESE Q NO SE COMO SE HACE
		
		JButton exit= new JButton();
		exit.setBounds(5020,5020,150,100); //lo mismo
		exit.setIcon (new ImageIcon("icons/exit.png"));
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//lo que tiene q hacer cuando pulsas open
			}
			});
		layout.add(exit);
		
	}

}
