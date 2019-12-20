package ma.matrix;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ExitFrame extends JFrame implements WindowListener, ActionListener {
	
	private static final long serialVersionUID = 7168372221438673636L;

	private MatrixScreensaver owner;
	
	public ExitFrame(MatrixScreensaver owner) {
		super("Matrix Screensaver leave Window");
		this.owner = owner;
		JButton exit = new JButton("Beenden");
		getRootPane().setDefaultButton(exit);
		exit.addActionListener(this);
		getContentPane().add(exit);
		setLocation(0, 0);
		pack();
		setVisible(true);
	}
	
	public void windowActivated(WindowEvent arg0) {
	}
	
	
	public void windowClosed(WindowEvent arg0) {
	}
	
	
	public void windowClosing(WindowEvent arg0) {
		exit();
	}
	
	
	public void windowDeactivated(WindowEvent arg0) {
	}
	
	
	public void windowDeiconified(WindowEvent arg0) {
	}
	
	
	public void windowIconified(WindowEvent arg0) {
	}
	
	
	public void windowOpened(WindowEvent arg0) {
	}

	public void actionPerformed(ActionEvent arg0) {
		exit();
	}
	
	private void exit() {
		try {
			owner.dev.setDisplayMode(owner.cdisplayMode);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		System.exit(0);
	}
	
}
