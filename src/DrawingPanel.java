import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * This class creates a buffered image and displays it.
 * 
 * @author mak1g11
 * 
 */
public class DrawingPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage img;

	/**
	 * This creates a DrawingPanel object. Its size is equal to that of the
	 * BufferedImage.
	 * 
	 * @param bi
	 *            : The BufferedImage which is to be drawn.
	 */
	public DrawingPanel(BufferedImage bi) {
		img = bi;
		setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
	}

	/**
	 * The BufferedImage is drawn.
	 */
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
	}

	/**
	 * This method now replaces the previous BufferedImage with the new one
	 * draws it instead.
	 * 
	 * @param bf
	 *            : The new BufferedImage which is to be drawn.
	 */
	public void updatePanel(BufferedImage bf) {
		img = bf;
		repaint();
	}
}