import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

/**
 * This class adds the JuliaPanel and the FractalPanel to its frame.
 * 
 * @author mak1g11
 * 
 */
public class FractalViewerFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SpringLayout layout;

	/**
	 * Creates a FractalViewerFrame object. The initiate() method is called from
	 * within the constructor.
	 */
	public FractalViewerFrame() {
		super("The Fractal Viewer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initiate();
	}
	/**
	 * Adds the JuliaPanel and the FractalPanel to the frame.
	 */
	public void initiate() {
		layout = new SpringLayout();
		JuliaPanel jp = new JuliaPanel(); // a JuliaPanel is created
		FractalPanel fp = new FractalPanel(jp); // a FractalPanel is created
		Container c = getContentPane();
		c.setLayout(layout);
		c.add(fp); // adds the FractalPanel
		c.add(jp); // adds the JuliaPanel
		layout.putConstraint("North", fp, 0, "North", this);
		layout.putConstraint("West", fp, 0, "West", this);
		layout.putConstraint("North", jp, 0, "North", this);
		layout.putConstraint("West", jp, 700, "West", this);
		setVisible(true);
		setSize(1000, 410); // a specific size is set
		setLocationRelativeTo(null); // the frame appears in the centre of the
										// screen
	}

}