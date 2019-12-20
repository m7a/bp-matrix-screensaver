package ma.matrix;

import java.awt.Color;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Font;

public class MatrixChar extends JComponent {
	
	private static final long serialVersionUID = -5173930519807887016L;
	
	private static final Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
	
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
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.GREEN);
		g.setFont(font);
		g.drawString(String.valueOf(current), 0, getHeight());
	}
}
