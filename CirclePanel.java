import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 * This class is for a game that has a circle that passes the frame
 * from left to right. If the user clicks on the circle, their Hit score will
 * increase by 1 and the circle speed will increase. If the user let's the circle
 * fully pass the frame, then their Misses score will increase by 1. The user
 * can use the up and down arrow keys to increase or decrease the circle size.
 * 
 * @author Johnathon Douglass
 *
 */
public class CirclePanel extends JPanel {
	private int hit = 0, miss = 0;
	private int diameter = 30;
	private Timer t;
	private Point location;
	private Random rand = new Random(); // used to change location of the circle
	private int speed = 60;
	
	/**
	 * Default constructor. Randomly 
	 */
	public CirclePanel() {
		// randomly generate x-point between -80 and -300, and randomly generate y-point between 0 and 400. This is just for the starting circle.
		location = new Point(((rand.nextInt(300) + 80) * -1), rand.nextInt(400));
		addKeyListener(new SizeListener());
		addMouseListener(new ClickListener());
		t = new Timer(speed, new TimerListener());
		t.start();
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(500, 500));
		setFocusable(true);
	}
	
	/**
	 * This method creates a circle and draws it. It also draws the Hit and Miss scoreboard.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Ellipse2D.Double circle = new Ellipse2D.Double(location.x,location.y, diameter, diameter);
		g2.setColor(Color.CYAN);
		g2.fill(circle);
		g2.draw(circle);
		
		Font font = new Font("Arial", Font.BOLD, 20);
		g2.setFont(font);
		g2.setColor(Color.WHITE);
		g2.drawString("Hits: " + hit, 10, 30);
		g2.drawString("Misses: " + miss, 95, 30);
	}
	
	/**
	 * This class implements ActionListener and it changes the location for the next circle
	 * if the user misses and let's it go out of the frame.
	 * @author Johnathon Douglass
	 *
	 */
	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (location.x > 500) { // use 500 because that's the frame width
				location.x = xLocationChanger(); // calls method to change the x position
				location.y = yLocationChanger(); // calls method to change the y position
				miss++;
			}
			location.x += 5; // used for how many pixels the circle travels across the frame.
			repaint();
		}
	}
	
	public int xLocationChanger() {
		return ((rand.nextInt(500) + 80) * -1); // randomly generate a number between -80 and -500
	}
	public int yLocationChanger() {
		return rand.nextInt(400); // randomly generate a number between 0 and 400
	}
	
	/**
	 * This class implements KeyListener to change the diameter of the circle when the user presses
	 * the up or down arrow keys. This works for whether the keys are pressed or held down.
	 * @author Johnathon Douglass
	 *
	 */
	private class SizeListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				if (diameter >= 100) { // check for upper size limit
					break;
				}
				diameter += 5; // diameter increases by 5 pixels
				repaint();
				break;
			case KeyEvent.VK_DOWN:
				if (diameter <= 5) { // check for lower size limit
					break;
				}
				diameter -= 5; // diameter decreases by 5 pixels
				repaint();
				break;
			}
		}
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				if (diameter >= 100) { // check for upper size limit
					break;
				}
				diameter += 5; // diameter increases by 5 pixels
				repaint();
				break;
			case KeyEvent.VK_DOWN:
				if (diameter <= 5) { // check for lower size limit
					break;
				}
				diameter -= 5; // diameter decreases by 5 pixels
				repaint();
				break;
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {}
	}
	
	/**
	 * This class implements a MouseListener which is used to change the location of the circle if the user clicks on it.
	 * It also increases the timer speed each time the user successfully clicks on the circle.
	 * @author Johnathon Douglass
	 *
	 */
	private class ClickListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX(); // gets the user's x position from their mouse click
			int y = e.getY(); // gets the user's y position from their mouse click
			Point p = new Point(x, y); // stores their mouse click as a point
			if (((p.x >= location.x) && (p.x <= (location.x + diameter))) // checks to see if the mouse was clicked inside of the circle's hitbox
			&& ((p.y >= location.y) && (p.y <= (location.y + diameter)))) {
				hit++;
				if (speed >= 0) {
					speed -= 4; // decrease speed by 4 milliseconds
					t.setDelay(speed); // set the new speed
				}
				location.x = xLocationChanger(); // call method to change the x position of the new circle
				location.y = yLocationChanger(); // call method to change the y position of the new circle
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}
}
