import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;


/**
 * This class basically makes the Julia Panel, in which the corresponding Julia
 * Set to the fractal is produced. It can also be saved and one can also be
 * loaded.
 * 
 * @author mak1g11
 * 
 */
public class JuliaPanel extends Calculations {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Complex userSelectedPoint = new Complex(0, 0);
	private JButton save, open;
	private BufferedImage bfim;
	private boolean burningShip, tricorn, cyan = false;
	private boolean mandelbrot = true;
	private boolean red = true;

	/**
	 * Initiates the layout manager and the dimensions of the image which will
	 * be created later. The initiate() method is also called from the
	 * constructor. An initial Julia Set is drawn at the Complex 0 + 0i.
	 */
	public JuliaPanel() {
		layout = new SpringLayout();
		this.setLayout(layout);
		imgWidth = 300;
		imgHeight = 300;
		initiate();
		showJuliaSet(userSelectedPoint, maxIterations, 1, 1);
	}

	/**
	 * Builds the whole panel, calling all the other methods which separately
	 * work to build it all.
	 */
	public void initiate() {
		addTitle();
		addButtons();
		setPreferredSize(new Dimension(700, 400)); // sets a specific size
	}

	/**
	 * Adds the buttons to the panel, namely, the Save and Open button.
	 */
	public void addButtons() {
		save = new JButton("Save");
		this.add(save);
		Saver svr = new Saver();
		save.addActionListener(svr);
		open = new JButton("Open");
		this.add(open);
		open.addActionListener(svr);
		layout.putConstraint("North", save, 310, "North", this);
		layout.putConstraint("West", save, 50, "West", this);
		layout.putConstraint("North", open, 310, "North", this);
		layout.putConstraint("West", open, 150, "West", this);
	}

	/**
	 * Just adds the title, of the Panel, i.e. 'The Julia Set' above the image
	 * which is to be drawn.
	 */
	public void addTitle() {
		JLabel julia = new JLabel("The Julia Set");
		julia.setFont(new Font(julia.getFont().getFontName(), Font.BOLD, 16));
		this.add(julia);
		layout.putConstraint("North", julia, 20, "North", this);
		layout.putConstraint("West", julia, 80, "West", this);
	}

	/**
	 * This will get the settings of the Julia Set which is to be drawn and then
	 * call the addImage() method on it.
	 * 
	 * @param c
	 *            : The constant Complex at which the Julia Set is to be drawn
	 *            at.
	 * @param iterations
	 *            : The number of iterations that are to be carried out in
	 *            calculating the Set.
	 * @param fractal
	 *            : A number determining to which fractal the Julia Set is to be
	 *            drawn.
	 * @param scheme
	 *            : A number determining the colouring scheme of the Julia Set.
	 */
	public void showJuliaSet(Complex c, int iterations, int fractal, int scheme) {
		// 1 means that the fractal is Mandelbrot set. The values are then
		// changed accordingly.
		if (fractal == 1) {
			mandelbrot = true;
			burningShip = false;
			tricorn = false;
		}
		// 2 means that the fractal is Burning Ship. The values are then changed
		// accordingly.
		else if (fractal == 2) {
			burningShip = true;
			mandelbrot = false;
			tricorn = false;
		}
		// 3 means that the fractal is Tricorn. The values are then changed
		// accordingly.
		else if (fractal == 3) {
			tricorn = true;
			burningShip = false;
			mandelbrot = false;
		}
		// 1 means that the colouring scheme is red. The values are then changed
		// accordingly.
		if (scheme == 1) {
			red = true;
			cyan = false;
		}
		// 1 means that the colouring scheme is cyan. The values are then
		// changed accordingly.
		else if (scheme == 2) {
			cyan = true;
			red = false;
		}
		userSelectedPoint = c;
		maxIterations = iterations;
		bfim = makeImage(); // the image is made by another method and returned
		// here.
		addImage(bfim);
		// pictureShowing = true;

	}

	/**
	 * This creates a DrawingPanel which then paints and displays the Julia Set
	 * 
	 * @param bfim
	 *            : The BufferedImage which is to be drawn.
	 */
	public void addImage(BufferedImage bfim) {
		// this code only executes if there is no image displaying already
		if (dp == null) {
			// if (!pictureShowing) {
			dp = new DrawingPanel(bfim);
			dp.setPreferredSize(new Dimension(250, 250));
			this.add(dp);
		}
		// if there is already an image, it is just updated
		else {
			dp.updatePanel(bfim);
		}

		layout.putConstraint("North", dp, 50, "North", this);
		layout.putConstraint("West", dp, 20, "West", this);

	}

	/**
	 * Scales the coordinates fed to it and scales them. Here, there is no
	 * zooming so the numbers divided by the image dimensions remain the same.
	 * 
	 * @param x
	 *            : The X co-ordinate of the point.
	 * @param y
	 *            : The Y co-ordinate of the point.
	 * @return The Complex represented by the scaled co-ordinates on the panel.
	 */
	public Complex scaleNumber(int x, int y) {
		// as there is no zooming on the Julia Set image, the maximum and
		// minimum values of the axes remain the same
		double realScale = (4.0) / imgWidth;
		double imagScale = (3.2) / imgHeight;
		return new Complex(-2.0 + realScale * x, -1.6 + imagScale * y);
	}

	/**
	 * Makes the image of the fractal according to the colour scheme chosen.
	 * 
	 * @return The constructed image of the fractal.
	 */
	public BufferedImage makeImage() {
		Graphics2D g;
		// a BufferedImage is created
		BufferedImage bi = new BufferedImage(imgWidth, imgHeight,
				BufferedImage.TYPE_INT_RGB);
		g = bi.createGraphics();
		// this is for every pixel along the length of the image
		for (int i = 0; i < imgWidth; i++) {
			// this is for every pixel along the height of the image
			for (int j = 0; j < imgHeight; j++) {
				Complex c = scaleNumber(i, j); // a complex is created, scaled
				// to the correct value it is checked if the set of values of
				// the complex, after a number of iterations, diverge or not
				if (inSet(c)) {
					// if they have, the pixel is drawn black
					g.setColor(Color.BLACK);
					g.drawRect(i, j, 1, 1);
				}
				// if they don't, then that pixel is coloured something else
				else {
					// if the red radio button has been selected, then the red
					// colouring scheme is chosen
					if (red) {
						g.setColor(Color.getHSBColor(
								((float) (itrCount / 100.0f)), (float) 1.0f,
								(float) 1.0f));
					}
					// if the cyan radio button has been selected, then the cyan
					// colouring scheme is chosen
					else if (cyan) {
						g.setColor(Color.getHSBColor(((float) 0.525f),
								(float) 1.0f, (float) (itrCount / 100.0f)));
					}
					g.drawRect(i, j, 1, 1);

				}
			}
		}
		// after all the pixel have been coloured accordingly in the
		// BufferedImage, it is returned
		return bi;
	}

	/**
	 * Checks if, after a number of iterations, the sequence of the values of
	 * the Complex diverges or not.
	 * 
	 * @param c
	 *            : The Complex which is to be subjected to the iterative
	 *            formula.
	 * @return True or False depending on the divergence of the values.
	 */
	public boolean inSet(Complex c) {
		Complex z = userSelectedPoint; // the Complex that was selected on the
		// fractal is used
		itrCount = 0;
		// this formula for each iteration will be used only when the Mandelbrot
		// Set boolean is true
		if (mandelbrot) {
			// while the answer to the formula is less than four, it will keep
			// on repeated
			while ((c.square().add(z).modulusSquared() < 4)
					&& (itrCount < maxIterations)) {
				c = c.square().add(z);
				itrCount++;
			}

		}
		// this formula for each iteration will be used only when the Tricorn
		// boolean is true
		else if (tricorn) {
			// while the answer to the formula is less than four, it will keep
			// on repeated
			while ((c.square().getConjugate().add(z).modulusSquared() < 4)
					&& (itrCount < maxIterations)) {
				c = c.square().getConjugate().add(z);
				itrCount++;
			}
		}
		// this formula for each iteration will be used only when the Burning
		// Ship boolean is true
		else if (burningShip) {
			// while the answer to the formula is less than four, it will keep
			// on repeated
			c = new Complex(c.getPosReal(), c.getPosImag()).square().add(z);
			itrCount = 0;
			while ((c.modulusSquared() < 4) && (itrCount < maxIterations)) {
				c = new Complex(c.getPosReal(), c.getPosImag()).square().add(z);
				itrCount++;
			}
		}
		return itrCount == maxIterations; // a boolean is returned if the
		// iteration count reached till the maximum iterations were reached
		// or not. If the two are equal, the value had diverged
	}

	/**
	 * This custom class has been made to perform tasks when the buttons in the
	 * JuliaPanel are pressed.
	 * 
	 * @author mak1g11
	 * 
	 */
	class Saver implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JFileChooser jfc = new JFileChooser();
			int returnVal;
			// this code will be executed if the 'Save' button has been pressed.
			if (e.getSource() == save) {
				// this code will execute if there is a Julia Set being
				// displayed
				returnVal = jfc.showSaveDialog(null);
				File f = jfc.getSelectedFile();
				jfc.setAcceptAllFileFilterUsed(false);
				// Attempt to save the file
				try {
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File outputfile = new File(f.getPath() + ".png"); // adds
						// .png once the user has named the file so that the
						// user doesn't have to do it
						ImageIO.write(bfim, "png", outputfile);
						// A message is generated to inform the user that
						// the file has been saved
						JOptionPane.showMessageDialog(dp,
								"Your file was saved.", "Success!",
								JOptionPane.PLAIN_MESSAGE);
					}
				}
				// if there is an error saving the file, an exception is
				// thrown
				catch (IOException ex) {
					// the user is informed that something went wrong
					JOptionPane
							.showMessageDialog(
									dp,
									"The file could not be saved. We're having our best people look into it.",
									"Houston, we have a probelm",
									JOptionPane.WARNING_MESSAGE);
				}

			}
			// this code executes when the 'Open' button is pressed
			else if (e.getSource() == open) {
				jfc.setAcceptAllFileFilterUsed(false);
				returnVal = jfc.showOpenDialog(null);
				File f = jfc.getSelectedFile();
				try {
					// this is performed if the user opts to open an image
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						bfim = ImageIO.read(f);
						// it is checked if the image selected to be opened is
						// the right format or not
						if ((f.getName().endsWith(".png"))) {
							addImage(bfim);
						}
						// if the file format is incorrect, an error message is
						// generated
						else {
							JOptionPane
									.showMessageDialog(
											dp,
											"The file could not opened. The image you selected was probably not a proper \n"
													+ "PNG format file. Shame on you!",
											"Houston, we have a probelm",
											JOptionPane.WARNING_MESSAGE);
						}
					}

				}
				// if there is an error in loading the file, an exception is
				// thrown
				catch (IOException ioe) {

					JOptionPane
							.showMessageDialog(
									dp,
									"The file could not opened. We're having our best people look into it.",
									"Houston, we have a probelm",
									JOptionPane.WARNING_MESSAGE);
				}
			}
		}

	}

}