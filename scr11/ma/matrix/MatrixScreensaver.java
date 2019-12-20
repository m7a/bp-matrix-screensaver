package ma.matrix;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MatrixScreensaver extends Window {
	
	public int Y; // Höhe   (60 bei 1024)
	public int X; // Breite (80 bei 1280)
	public static final String space = "                       ";
	public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	public static final String numbers = "01234567890123456789012345678901234567890123456789012345678901234567890123456789";
	public static final String special = "@ł€¶ŧ←↓→øþĸjħŋđðßææ|«»¢“”nµ·¹²³¼½¬{[]}]\\¬!\"§$%&/()=?";
	public final DisplayMode cdisplayMode;
	public final GraphicsDevice dev;
	
	public MatrixScreensaver(boolean useCurrentResolution) {
		super(null);
		new ExitFrame(this);
		dev = getGraphicsConfiguration().getDevice();
		cdisplayMode = dev.getDisplayMode();
		
		if(dev.getType() != GraphicsDevice.TYPE_RASTER_SCREEN) {
			System.out.println("WARNING : Render not direct on screen.");
		}
		
		dev.setFullScreenWindow(this);
		
		boolean svsa = false;
		
		if(!useCurrentResolution) {
			try {
				DisplayMode[] modes = dev.getDisplayModes();
				DisplayMode nw = null;
				for(int i = 0; i < modes.length; i++) {
					// 1024x768, 640x480
					if(modes[i].getWidth() == 640 && modes[i].getHeight() == 480) {
						nw = modes[i];
						break;
					}
				}
				if(nw != null) {
					svsa = true;
					dev.setDisplayMode(nw);
				} else {
					System.out.println("WARNING : No 640x480 Resolution available.");
				}
			} catch(Exception ex) {
				System.out.println("WARNING : Unalbe to set display Mode.");
				ex.printStackTrace();
			}
		} else {
			System.out.println("MESSAGE : Using current Resolution.");
		}
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		if(svsa) {
			X = (int)(640/(1280.00d/80.00d));
			Y = (int)(480/(1024.00d/60.00d));
		} else {
			X = (int)(dim.width/(1280.00d/80.00d));
			Y = (int)(dim.height/(1024.00d/60.00d));
		}
			
		System.out.println("MESSAGE : Matrix-Size : X=" + X + " Y=" + Y);
		
		final MatrixChar[][] streifen;
		
		final char[] chars = ((letters + numbers + space + special).toCharArray());
		final Random r = new Random();
		
		final JPanel content = new JPanel(new GridLayout(Y, X));
		content.setBackground(Color.BLACK);
		streifen = new MatrixChar[Y][X];
		for(int i = 0; i < Y; i++) {
			for(int j = 0; j < X; j++) {
				streifen[i][j] = new MatrixChar(chars[r.nextInt(chars.length)]);
				content.add(streifen[i][j]);
			}
		}
		
		Thread changingThread = new Thread() {
			public void run() {
				setPriority(Thread.MIN_PRIORITY);
				setName("Matrix Changing Thread");
				while(true) {
					char[] sicherung = new char[X];
					char cchar;
					int[] cnotrunning = { 
						r.nextInt(X), r.nextInt(X), r.nextInt(X), 
						r.nextInt(X), r.nextInt(X), r.nextInt(X), 
						r.nextInt(X), r.nextInt(X), r.nextInt(X), 
						r.nextInt(X), r.nextInt(X), r.nextInt(X), 
						r.nextInt(X), r.nextInt(X), r.nextInt(X), 
						r.nextInt(X), r.nextInt(X), r.nextInt(X), 
						r.nextInt(X), r.nextInt(X), r.nextInt(X), 
						r.nextInt(X), r.nextInt(X), r.nextInt(X), 
					};
					
					for(int j = 0; j < X; j++) {
						boolean weiter = true;
						for(int i2 = 0; i2 < cnotrunning.length; i2++) {
							if(j == cnotrunning[i2]) {
								weiter = false;
								break;
							}
						}
						if(weiter) {
							sicherung[j] = streifen[0][j].getCurrentChar();
							streifen[0][j].setCurrentChar(chars[r.nextInt(chars.length)]);
						}
					}
					
					for(int i = 1; i < Y; i++) {
						for(int j = 0; j < X; j++) {
							boolean weiter = true;
							for(int i2 = 0; i2 < cnotrunning.length; i2++) {
								if(j == cnotrunning[i2]) {
									weiter = false;
									break;
								}
							}
							if(weiter) {
								cchar = streifen[i][j].getCurrentChar();
								streifen[i][j].setCurrentChar(sicherung[j]);
								sicherung[j] = cchar;
							}
						}
					}
					
					SwingUtilities.updateComponentTreeUI(content);
					
					try {
						sleep(20);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
						System.exit(0);
					}
				}
			}
		};
		changingThread.start();

		add(content);
		if(!dev.isFullScreenSupported()) {
			setSize(dim.width, dim.height);
			System.out.println("WARNING : Your platform dosen't support fullscren mode, you may see windows.");
		}
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		boolean current = false;
		if(args.length == 1) {
			if(args[0].equals("-c")) {
				current = true;
			}
		}
		new MatrixScreensaver(current);
	}
	
}
