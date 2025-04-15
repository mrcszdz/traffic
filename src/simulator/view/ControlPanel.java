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
import java.io.InputStream;

public class ControlPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1206312102543205364L;
    private Controller _ctrl;
    private boolean _stopped;
    private JToolBar toolBar;
    private ChangeCO2ClassDialog co2Dialog;

	ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		 initGUI();
	}

	private void initGUI() {
		co2Dialog = new ChangeCO2ClassDialog(_ctrl, (Frame) SwingUtilities.getWindowAncestor(this));
		setLayout(new BorderLayout());
		this.toolBar = new JToolBar();

		JButton load = new JButton();
		load.setActionCommand("load");
		load.addActionListener(new ActionListener() {
		    @Override
		public void actionPerformed(ActionEvent e) {
	    	JFileChooser fileChooser = new JFileChooser();
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
		load.setIcon (loadImage("resources/icons/open.png"));
		toolBar.add(load);
		
		toolBar.addSeparator();
		
		JButton co2 = new JButton();
		co2.setActionCommand("co2");
		co2.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		       co2Dialog.open();
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
		
		
		JSpinner ticks = new JSpinner(new SpinnerNumberModel(10, 1, 10000, 1));
		
		ticks.setToolTipText("Simulation steps to run: 1-10000");
		ticks.setMaximumSize(new Dimension(80, 40));
		ticks.setMinimumSize(new Dimension(80, 40));
		
		//toolBar.addSeparator();
		
//		JSeparator Gris = new JSeparator(SwingConstants.VERTICAL);
//		Gris.setBackground(Color.GRAY);
//		toolBar.add(Gris);
        
		toolBar.addSeparator();
		
		JButton run = new JButton();
		run.setActionCommand("run");
		run.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	run_sim((Integer)ticks.getValue());
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
	
	protected void run_sim(int n) {
		if (n > 0 && !_stopped) {
			try {
				_ctrl.run(1);
	         		SwingUtilities.invokeLater(() -> run_sim(n - 1));
			} catch (Exception e) {
				// TODO show error message
				_stopped = true;
				// TODO enable the toolbar
			}
		} else {
			_stopped = true;
	                // TODO enable the toolbar
		}
	}

	
}
