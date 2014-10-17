import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 * It is a super class to the FractalPanel and JuliaPanel. It contains the
 * shared variables and methods to avoid and reduce code duplication. It
 * contains shared methods as well, but they are abstract as both panels have
 * different code in them.
 */

abstract class Calculations extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int itrCount, imgWidth, imgHeight;
	protected boolean red, cyan;
	protected int maxIterations = 100;
	protected DrawingPanel dp;
	protected SpringLayout layout;

	public abstract boolean inSet(Complex c);

	public abstract Complex scaleNumber(int x, int y);
}
