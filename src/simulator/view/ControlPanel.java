package simulator.view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import simulator.control.Controller;


public class ControlPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1206312102543205364L;
    private Controller _ctrl;
	

	ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		 initGUI();
	}

	private void initGUI() {
		setLayout(new BorderLayout());
		JToolBar toolBar = new JToolBar();

		JButton load = new JButton();
		load.setActionCommand("load");
		load.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		       
		    }
		});
		load.setIcon (loadImage("resources/icons/open.png"));
		toolBar.add(load);
		
		toolBar.addSeparator();
		
		JButton co2 = new JButton();
		co2.setActionCommand("co2");
		co2.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		       
		    }
		});
		co2.setIcon(loadImage("resources/icons/co2class.png"));
		toolBar.add(co2);
		
		JButton weather = new JButton();
		weather.setActionCommand("weather");
		weather.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		       
		    }
		});
		weather.setIcon(loadImage("resources/icons/weather.png"));
		toolBar.add(weather);
		
		
		
		toolBar.addSeparator();
		
		JButton run = new JButton();
		run.setActionCommand("run");
		run.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		       
		    }
		});
		run.setIcon(loadImage("resources/icons/run.png"));
		toolBar.add(run);
		
		JButton stop = new JButton();
		stop.setActionCommand("stop");
		stop.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		       
		    }
		});
		stop.setIcon(loadImage("resources/icons/stop.png"));
		toolBar.add(stop);


				
		// Spinner
		toolBar.add(new JLabel(" Ticks:"));
		
		// initial value = 10000 (first parameter)
		// initial range value = 1       (second parameter)
		// final range value   = 10000   (third parameter)
		// increment = 100				 (fourth parameter)
		
		JSpinner ticks = new JSpinner(new SpinnerNumberModel(10, 1, 10000, 1));

		ticks.setToolTipText("Simulation steps to run: 1-10000");
		ticks.setMaximumSize(new Dimension(80, 40));
		ticks.setMinimumSize(new Dimension(80, 40));
		ticks.setPreferredSize(new Dimension(80, 40));
		toolBar.add(ticks);

		
		toolBar.add(Box.createGlue());
		toolBar.addSeparator();
		
		// Exit
		JButton quitButton = new JButton();
		quitButton.setToolTipText("Exit");
		quitButton.setIcon(loadImage("resources/icons/exit.png"));
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});
		toolBar.add(quitButton);
		this.add(toolBar);

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

	
}
