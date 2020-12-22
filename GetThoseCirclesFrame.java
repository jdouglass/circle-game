import javax.swing.JFrame;

/**
 * This class is used as the frame for the CirclePanel class.
 * @author Johnathon Douglass
 *
 */
public class GetThoseCirclesFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.setTitle("Get Those Circles");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new CirclePanel());
		frame.pack();
		frame.setVisible(true);
	}
	
	

}
