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
import java.awt.image.BufferedImage; 
import java.io.File; 
import javax.imageio.ImageIO; 
import java.awt.*; 
import java.awt.event.*;

public class MatrixScreensaverImage extends Window {
	
	public final int Y;
	public final int X;
	public static final String space = "                   ";
	public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	public static final String numbers = "01234567890123456789012345678901234567890123456789012345678901234567890123456789";
	public static final String special = "@ł€¶ŧ←↓→øþĸjħŋđðßææ|«»¢“”nµ·¹²³¼½¬{[]}]\\¬!\"§$%&/()=?";
	public final DisplayMode cdisplayMode;
	public final GraphicsDevice dev;
	private Window wnd;
	
	public MatrixScreensaverImage() {
		super(null);
		wnd = this;
		System.out.println("MESSAGE : Creating Matrix...");
		dev = getGraphicsConfiguration().getDevice();
		cdisplayMode = dev.getDisplayMode();
		if(dev.getType() != GraphicsDevice.TYPE_RASTER_SCREEN) {
			System.out.println("WARNING : Render not direct on screen.");
		}
		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		X = (int)(dim.width/(1280.00d/80.00d));
		Y = (int)(dim.height/(1024.00d/60.00d));
			
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
		add(content);
		if(!dev.isFullScreenSupported()) {
			setSize(dim.width, dim.height);
			System.out.println("WARNING : You're Plattform dosen't support fullscren Mode, you may see windows.");
		}
		
		addWindowListener(new WindowAdapter() {
				public void windowOpened(WindowEvent event) {
					Thread t = new Thread() {
						public void run() {
							try {
								System.out.println("MESSAGE : Getting Screenshot...");
								Thread.sleep(2000);
								BufferedImage bi = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
								System.out.println("MESSAGE : Creating image...");
								MediaTracker track = new MediaTracker(wnd);
								Image masysmalogo = Toolkit.getDefaultToolkit().createImage("splash.png");
								track.addImage(masysmalogo, 1);
								track.waitForAll();
								int img_x = (dim.width-480-16);
								int img_y = (dim.height-80-40);
								bi.createGraphics().drawImage(masysmalogo, img_x, img_y, wnd);
								System.out.println("MESSAGE : Saving image...");
								ImageIO.write(bi, "png", new File("matrix-desktop.png")); 
								System.out.println("MESSAGE : Closing Window...");
								setVisible(false);
								dispose();
							} catch(Exception ex) {
								System.out.println("ERROR : Cold not write screenshot.");
								System.out.println("        " + ex.toString());
							}
							
							System.exit(0);
						}
					};
					t.start();
				}
		});
		
		dev.setFullScreenWindow(this);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MatrixScreensaverImage();
	}
}

class MatrixChar extends Component {
	
	private static final long serialVersionUID = -5173930519807887016L;
	
	private char current;
	
	public MatrixChar() {
		current = 'X';
	}
	
	public MatrixChar(char current) {
		this.current = current;
	}
	
	public void setCurrentChar(char c) {
		current = c;
	}
	
	public char getCurrentChar() {
		return current;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawString(String.valueOf(current), 0, getHeight());
	}
}
