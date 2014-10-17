import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
/**
 * This Class basically makes the whole Panel for the fractals. It contains
 * buttons to change the fractals and their settings and their colours.
 * 
 * @author mak1g11
 * 
 */
public class FractalPanel extends Calculations {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField realAxisMinVal, realAxisMaxVal, imagAxisMinVal,
			imagAxisMaxVal, no_of_iterations;
	private JLabel point;
	private JButton draw, reset, help;
	private int imgWidth, imgHeight, fractal, scheme, x1, x2, y1, y2;
	private double maxReal = 2.0;
	private double minReal = -2.0;
	private double maxImag = 1.6;
	private double minImag = -1.6;
	private JuliaPanel jp;
	private boolean clicking = false;
	private boolean dragging = false;
	private boolean inPanel = false;
	private JRadioButton burningShip, tricorn, mandelbrot, defaultColour,
			cyanColour;
	private JCheckBox liveUp;
	private boolean mand, burns, tri, red, cyan, jSetShowing, live;
	private Complex clicked, moved;

	/**
	 * Initiates many variables which are to be used in later processes. Also
	 * calls the initiate() method.
	 * 
	 * @param jp
	 *            : A JuliaPanel, which will then update itself in some of the
	 *            methods called later.
	 */
	public FractalPanel(JuliaPanel jp) {
		imgWidth = 400;
		imgHeight = 400;
		this.jp = jp;
		layout = new SpringLayout();
		this.setLayout(layout);
		jSetShowing = false;
		live = false;
		initiate();

	}

	/**
	 * Builds the whole panel, calling all the other methods which separately
	 * work to build it all.
	 */
	public void initiate() {
		addTitle();
		addRealInfo();
		addImaginaryInfo();
		addIterations();
		addButtons();
		addRadioButtons();
		addImage();
		setPreferredSize(new Dimension(710, 400));
	}

	/**
	 * Adds the title of the panel on it.
	 */
	public void addTitle() {
		JLabel fractalViewer = new JLabel("The Fractal Viewer");
		fractalViewer.setFont(new Font(fractalViewer.getFont().getFontName(),
				Font.BOLD, 16));
		this.add(fractalViewer);
		layout.putConstraint("North", fractalViewer, 20, "North", this);
		layout.putConstraint("West", fractalViewer, 50, "West", this);
	}

	/**
	 * Adds the text fields and the labels i.e. the information about the real
	 * axis on the panel.
	 */
	public void addRealInfo() {
		JLabel realAxis = new JLabel("Real Axis");
		realAxis.setFont(new Font(realAxis.getFont().getFontName(), Font.BOLD,
				14));
		realAxisMaxVal = new JTextField(4);
		realAxisMaxVal.setText(String.valueOf(maxReal));
		realAxisMinVal = new JTextField(4);
		realAxisMinVal.setText(String.valueOf(minReal));
		JLabel realAxisMaxDisp = new JLabel("Max Value");
		JLabel realAxisMinDisp = new JLabel("Min Value");
		this.add(realAxis);
		this.add(realAxisMinDisp);
		this.add(realAxisMaxVal);
		this.add(realAxisMaxDisp);
		this.add(realAxisMinVal);
		layout.putConstraint("North", realAxis, 45, "North", this);
		layout.putConstraint("West", realAxis, 80, "West", this);
		layout.putConstraint("North", realAxisMinDisp, 70, "North", this);
		layout.putConstraint("West", realAxisMinDisp, 10, "West", this);
		layout.putConstraint("North", realAxisMinVal, 70, "North", this);
		layout.putConstraint("West", realAxisMinVal, 85, "West", this);
		layout.putConstraint("North", realAxisMaxDisp, 70, "North", this);
		layout.putConstraint("West", realAxisMaxDisp, 140, "West", this);
		layout.putConstraint("North", realAxisMaxVal, 70, "North", this);
		layout.putConstraint("West", realAxisMaxVal, 215, "West", this);
	}

	/**
	 * Adds the text fields and the labels i.e. the information about the
	 * imaginary axis on the panel.
	 */
	public void addImaginaryInfo() {
		JLabel imagAxis = new JLabel("Imaginary Axis");
		imagAxis.setFont(new Font(imagAxis.getFont().getFontName(), Font.BOLD,
				14));
		JLabel imagAxisMaxDisp = new JLabel("Max Value");
		JLabel imagAxisMinDisp = new JLabel("Min Value");
		imagAxisMaxVal = new JTextField(4);
		imagAxisMinVal = new JTextField(4);
		imagAxisMaxVal.setText(String.valueOf(maxImag));
		imagAxisMinVal.setText(String.valueOf(minImag));
		this.add(imagAxis);
		this.add(imagAxisMinDisp);
		this.add(imagAxisMaxVal);
		this.add(imagAxisMaxDisp);
		this.add(imagAxisMinVal);
		layout.putConstraint("North", imagAxis, 95, "North", this);
		layout.putConstraint("West", imagAxis, 80, "West", this);
		layout.putConstraint("North", imagAxisMinDisp, 120, "North", this);
		layout.putConstraint("West", imagAxisMinDisp, 10, "West", this);
		layout.putConstraint("North", imagAxisMinVal, 120, "North", this);
		layout.putConstraint("West", imagAxisMinVal, 85, "West", this);
		layout.putConstraint("North", imagAxisMaxDisp, 120, "North", this);
		layout.putConstraint("West", imagAxisMaxDisp, 140, "West", this);
		layout.putConstraint("North", imagAxisMaxVal, 120, "North", this);
		layout.putConstraint("West", imagAxisMaxVal, 215, "West", this);
	}

	/**
	 * Adds the text fields and the labels i.e. the information about the
	 * iterations of the image on the panel.
	 */
	public void addIterations() {
		JLabel no_of_iterationsDisp = new JLabel("Iterations");
		no_of_iterations = new JTextField(5);
		no_of_iterations.setText(String.valueOf(maxIterations));
		this.add(no_of_iterationsDisp);
		this.add(no_of_iterations);
		layout.putConstraint("North", no_of_iterationsDisp, 150, "North", this);
		layout.putConstraint("West", no_of_iterationsDisp, 10, "West", this);
		layout.putConstraint("North", no_of_iterations, 150, "North", this);
		layout.putConstraint("West", no_of_iterations, 85, "West", this);
	}

	/**
	 * Adds the buttons to the panel, namely, the Help,Draw and Reset button.
	 */
	public void addButtons() {
		draw = new JButton("Draw");
		reset = new JButton("Reset");
		help = new JButton("Help");
		this.add(draw);
		this.add(reset);
		this.add(help);
		// an ActionListener is added to all the three buttons
		ButtonsListener bl = new ButtonsListener();
		draw.addActionListener(bl);
		reset.addActionListener(bl);
		help.setPreferredSize(new Dimension(70, 20));
		help.addActionListener(bl);

		layout.putConstraint("North", draw, 180, "North", this);
		layout.putConstraint("West", draw, 50, "West", this);
		layout.putConstraint("North", reset, 180, "North", this);
		layout.putConstraint("West", reset, 150, "West", this);
		layout.putConstraint("South", help, -35, "South", this);
		layout.putConstraint("West", help, 100, "West", this);
	}

	/**
	 * Adds the radio buttons of different colouring schemes and fractals to be
	 * chosen. Also adds a check box to allow for live updates. The Mandelbrot
	 * set is chosen and live updates are checked off initially.
	 */
	public void addRadioButtons() {
		JLabel fractals = new JLabel("Different Fractals");
		JLabel schemes = new JLabel("Different Colours");
		mandelbrot = new JRadioButton("Mandelbrot Set", true);
		burningShip = new JRadioButton("Burning Ship", false);
		tricorn = new JRadioButton("Tricorn", false);
		defaultColour = new JRadioButton("Red Colour Scheme", true);
		cyanColour = new JRadioButton("Cyan Colour Scheme");
		liveUp = new JCheckBox("Live Updates", false);
		this.add(liveUp);
		this.add(fractals);
		this.add(schemes);
		this.add(mandelbrot);
		this.add(burningShip);
		this.add(tricorn);
		this.add(defaultColour);
		this.add(cyanColour);
		ButtonGroup fractls = new ButtonGroup();
		fractls.add(mandelbrot);
		fractls.add(burningShip);
		fractls.add(tricorn);
		ButtonGroup colours = new ButtonGroup();
		colours.add(defaultColour);
		colours.add(cyanColour);
		// a ButtonListener is added to the radio buttons and the check box
		ButtonListener bl = new ButtonListener();
		mandelbrot.addItemListener(bl);
		burningShip.addItemListener(bl);
		tricorn.addItemListener(bl);
		defaultColour.addItemListener(bl);
		cyanColour.addItemListener(bl);
		liveUp.addItemListener(bl);
		layout.putConstraint("North", fractals, 220, "North", this);
		layout.putConstraint("West", fractals, 20, "West", this);
		layout.putConstraint("North", mandelbrot, 235, "North", this);
		layout.putConstraint("West", mandelbrot, 5, "West", this);
		layout.putConstraint("North", burningShip, 255, "North", this);
		layout.putConstraint("West", burningShip, 5, "West", this);
		layout.putConstraint("North", tricorn, 275, "North", this);
		layout.putConstraint("West", tricorn, 5, "West", this);
		layout.putConstraint("North", schemes, 220, "North", this);
		layout.putConstraint("West", schemes, 165, "West", this);
		layout.putConstraint("North", defaultColour, 235, "North", this);
		layout.putConstraint("West", defaultColour, 140, "West", this);
		layout.putConstraint("North", cyanColour, 255, "North", this);
		layout.putConstraint("West", cyanColour, 140, "West", this);
		layout.putConstraint("North", liveUp, 300, "North", this);
		layout.putConstraint("West", liveUp, 5, "West", this);
		mand = true;
		tri = false;
		burns = false;
		cyan = false;
		red = true;
	}

	/**
	 * Adds the image of the fractal on the panel. Calls a few methods on it and
	 * adds a DrawingPanel which then makes the image.
	 */
	public void addImage() {
		BufferedImage bufim = makeImage();
		dp = new DrawingPanel(bufim);
		dp.setPreferredSize(new Dimension(400, 350));
		this.add(dp);
		layout.putConstraint("North", dp, 0, "North", this);
		layout.putConstraint("East", dp, 0, "East", this);
		point = new JLabel(
				"Click the mouse on the drawing to find a specific point!");
		// a MouseListener and a MouseMotionListener is added to the
		// DrawingPanel
		PointDetector pd = new PointDetector();
		dp.addMouseListener(pd);
		dp.addMouseMotionListener(pd);
		this.add(point);
		layout.putConstraint("South", point, -32, "South", this);
		layout.putConstraint("East", point, -10, "East", this);
	}

	/**
	 * Scales the coordinates fed to it and scales them according to the Real
	 * and Imaginary Axes set.
	 * 
	 * @param x
	 *            : The X co-ordinate of the point.
	 * @param y
	 *            : The Y co-ordinate of the point.
	 * @return The Complex represented by the scaled co-ordinates on the panel.
	 */
	public Complex scaleNumber(int x, int y) {
		double realScale = (maxReal - minReal) / imgWidth; // the real part is
		// scaled according to the image dimensions
		double imagScale = (maxImag - minImag) / imgHeight; // the imaginary
		// part is scaled according to the image dimensions
		return new Complex(minReal + realScale * x, minImag + imagScale * y);
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
						g.setColor(Color.getHSBColor(((itrCount / 100.0f)),
								1.0f, 1.0f));
					}
					// if the cyan radio button has been selected, then the cyan
					// colouring scheme is chosen
					else if (cyan) {
						g.setColor(Color.getHSBColor((0.525f), 1.0f,
								(itrCount / 100.0f)));
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
		Complex z = new Complex(0, 0);
		itrCount = 0;
		// this formula for each iteration will be used only when the Mandelbrot
		// Set radio button has been selected
		if (mand) {
			// while the answer to the formula is less than four, it will keep
			// on repeated
			while ((z.square().add(c).modulusSquared() < 4)
					&& (itrCount < maxIterations)) {
				z = z.square().add(c);
				itrCount++;
			}
		}
		// this formula for each iteration will be used only when the Tricorn
		// radio button has been selected
		else if (tri) {
			// while the answer to the formula is less than four, it will keep
			// on repeated
			while ((z.square().getConjugate().add(c).modulusSquared() < 4)
					&& (itrCount < maxIterations)) {
				z = z.square().getConjugate().add(c);
				itrCount++;
			}
		}
		// this formula for each iteration will be used only when the Burning
		// Ship radio button has been selected
		else if (burns) {
			// while the answer to the formula is less than four, it will keep
			// on repeated
			z = new Complex(z.getRealPart(), z.getPosImag()).square().add(c);
			itrCount = 0;
			while ((z.modulusSquared() < 4) && (itrCount < maxIterations)) {
				z = new Complex(z.getPosReal(), z.getPosImag()).square().add(c);
				itrCount++;
			}
		}
		return itrCount == maxIterations; // a boolean is returned if the
		// iteration count reached till the maximum iterations were reached
		// or not. If the two are equal, the value had diverged
	}

	/**
	 * Draws the rectangle inside the DrawingPanel to zoom into the fractal.
	 */
	public void paint(Graphics g) {
		super.paint(g);
		// this code only executes if the user is dragging the mouse to make a
		// rectangle
		if (dragging) {
			// this checks if the user is dragging the mouse inside the
			// DrawingPanel and only then is the rectangle drawn
			if (inPanel) {
				g.setColor(Color.WHITE);
				g.drawRect(Math.min(x1, x2) + 311, Math.min(y1, y2),
						Math.abs(x2 - x1), Math.abs(y2 - y1)); // this ensures
				// that the code is drawn in all directions
			}
		}

	}

	/**
	 * This custom class is made to handle the changes made to the radio buttons
	 * and the check box on the FractalPanel.
	 */
	class ButtonListener implements ItemListener {

		public void itemStateChanged(ItemEvent e) {
			// if the mandelbrot radio button is selected, the mand value is set
			// to true, and the others set to false. The fractal value is of the
			// Mandelbrot Set.
			if (mandelbrot.isSelected()) {
				mand = true;
				burns = false;
				tri = false;
				fractal = 1;
			}
			// if the burningShip radio button is selected, the burns value is
			// set to true, and the others set to false. The fractal value is of
			// the Burning Ship.
			else if (burningShip.isSelected()) {
				mand = false;
				burns = true;
				tri = false;
				fractal = 2;
			}
			// if the tricorn radio button is selected, the tri value is set to
			// true, and the others set to false. The fractal value is of the
			// Tricorn.

			else if (tricorn.isSelected()) {
				mand = false;
				burns = false;
				tri = true;
				fractal = 3;
			}
			// if the defaultColour radio button is selected, the red value is
			// set to true and the other set to false. The scheme value is for
			// the red (original) colour.
			if (defaultColour.isSelected()) {
				red = true;
				cyan = false;
				scheme = 1;
			}
			// if the cyanColour radio button is selected, the cyan value is set
			// to true and the other set to false. The scheme value is for the
			// cyan colour.
			if (cyanColour.isSelected()) {
				red = false;
				cyan = true;
				scheme = 2;
			}
			// if mouse has clicked and the Julia Set is showing, the clicked
			// image being displayed on the JuliaPanel is changed and updated.
			if (clicking && jSetShowing) {
				jp.showJuliaSet(clicked, maxIterations, fractal, scheme);
			}
			// if the mouse hasn't clicked and the Julia Set is showing and the
			// LiveUpdates are on, the image on the JuliaPanel is updated
			// constantly.
			else if (!clicking && jSetShowing && inPanel) {
				jp.showJuliaSet(moved, maxIterations, fractal, scheme);
			} else if (!clicking && !jSetShowing) {
				jp.showJuliaSet(new Complex(0, 0), maxIterations, fractal,
						scheme);
			}
			// sets the value of live to be true and clicking to be false so
			// that image on the JuliaPanel can be updated constantly.
			if ((liveUp.isSelected())) {
				live = true;
				clicking = false;
			}
			// sets the value of live to false,and the image is not constantly
			// updated.
			else if (!(liveUp.isSelected())) {
				live = false;
			}
			dp.updatePanel(makeImage()); // the fractal is updated after all the
			// changes to its style and colour
			// have been made.

		}

	}

	/**
	 * This custom class has been made to make necessary changes as the buttons
	 * on the FractalPanel are pressed.
	 * 
	 * @author mak1g11
	 * 
	 */
	class ButtonsListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// this code will execute if the draw button has been pressed.
			if (e.getSource() == draw) {
				// it will try to get the values from the text fields and then
				// re-draw the image. The point is to zoom into the fractal
				// according to the values entered by the user in the real and
				// imaginary axis limits. The number of iterations can be
				// adjusted as well to change the sharpness and detail.
				try {
					maxReal = Double.parseDouble(realAxisMaxVal.getText()); // gets
					// the
					minReal = Double.parseDouble(realAxisMinVal.getText());
					maxImag = Double.parseDouble(imagAxisMaxVal.getText());
					minImag = Double.parseDouble(imagAxisMinVal.getText());
					maxIterations = Integer
							.parseInt(no_of_iterations.getText());
					clicking = false;
					dp.updatePanel(makeImage());
				}
				// an exception is thrown if the text fields don't have numbers
				// entered in the correct format and an error message is
				// generated.
				catch (NumberFormatException nfe) {
					JOptionPane
							.showMessageDialog(
									dp,
									"One or more of the inputs are either not numbers or have been left empty!",
									"Error D:", JOptionPane.ERROR_MESSAGE);
				}
			}
			// the following code is executed if the reset button is pressed.
			// All the values of the axes and iterations are set their original
			// ones and the image is updated.
			else if (e.getSource() == reset) {
				maxReal = 2.0;
				minReal = -2.0;
				maxImag = 1.6;
				minImag = -1.6;
				maxIterations = 100;
				realAxisMaxVal.setText(String.valueOf(maxReal));
				realAxisMinVal.setText(String.valueOf(minReal));
				imagAxisMaxVal.setText(String.valueOf(maxImag));
				imagAxisMinVal.setText(String.valueOf(minImag));
				no_of_iterations.setText(String.valueOf(maxIterations));
				clicking = false;
				dp.updatePanel(makeImage());
			}
			// this code is executed if the help button is pressed. This will
			// provide some guideline as how to properly use the Fractal Viewer.
			else if (e.getSource() == help) {
				JOptionPane
						.showMessageDialog(
								dp,
								"Behold, the fractal viewer! Its sole purpose it to satisfy your weird urges to look at beautiful mathematics \n"
										+ " ( we understand it; we have it too, hence we made this!). You can manually adjust the values of the real and  \n"
										+ "imaginary axis to your needs(try keeping it b/w 2,-2 for real and 1.6,-1.6 for imaginary. Else the image \n"
										+ " won't be in focus). Or, if you're lazy(like us),you can drag your mouse on the image and make a window\n"
										+ "  to zoom on. Moreover, we have provided you with three different fractals and two colour schemes so you can \n"
										+ "play around with the viewer until it satisfies you completeley. Click anywhere on the fractal image to\n"
										+ "  its corresponding Julia Set. Or activate live updating and see the image as your mouse moves around the\n"
										+ " image. If you find something you really like, save it! You can also load any image you saved previously to show \n"
										+ "your friends or whatever you want to do.\n "
										+ "\n"
										+ " Now go forth, padawan, and continue your journey. May it be a satisfying and enjoyable one.",
								"Fractal Viewer for Dummies",
								JOptionPane.PLAIN_MESSAGE);
			}
		}

	}

	/**
	 * This custom class has been made to handle the mouse movements made on the
	 * FractalPanel.
	 * 
	 * @author mak1g11
	 * 
	 */
	class PointDetector implements MouseListener, MouseMotionListener {
		private Complex init, fin;

		/**
		 * Displays the corresponding Julia Set to the point clicked in the
		 * fractal.
		 */
		public void mouseClicked(MouseEvent e) {
			clicking = true; // it is set to true to stop the stop the image
			// from changing if Live Updates has been
			// checked
			int real = e.getX(); // stores the x-coordinate of the point clicked
			// in a variable
			int imag = e.getY(); // stores the y-coordinate of the point clicked
			// in a variable
			clicked = scaleNumber(real, imag); // creates the corresponding
			// Complex to the point clicked in the Real and Imaginary Axes
			point.setText("Point selected: " + clicked.toString()); // displays
			// the Complex represented on the label below the image
			jp.showJuliaSet(clicked, maxIterations, fractal, scheme); // shows
			// the Julia Set corresponding to the Complex/point.

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// sets the value of inPanel to true when the mouse enters the
			// DrawingPanel
			inPanel = true;
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// the value of inPanel is set to false when the mouse exits the
			// DrawingPanel
			inPanel = false;
			// the code only executes if the user hasn't clicked to display a
			// point
			if (!clicking) {
				// Displays on the label below the image text to signify that
				// the mouse is not inside the image anymore.
				point.setText("Click the mouse on the drawing to find a specific point!");
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			x1 = e.getX(); // stores the x-coordinate of the point clicked
			// in a variable
			y1 = e.getY(); // stores the x-coordinate of the point clicked
			// in a variable
			init = scaleNumber(x1, y1); // creates the corresponding
			// Complex to the point clicked in the Real and Imaginary
			// Axes

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// only performs this code if the user had been dragging the mouse
			// over the image, trying to create the rectangle.
			if (dragging) {
				// gets the x and y coordinates of the mouse when it is released
				// and scales them according to the axes and creates the
				// corresponding Complex
				x2 = e.getX(); // gets the X coordinate of the final position of
								// the mouse when it is released
				y2 = e.getY(); // gets the Y coordinate of the final position of
								// the mouse when it is released
				fin = scaleNumber(x2, y2);
				// of the initial coordinates and the final ones, the bigger of
				// the two are chosen as the maximum real and imaginary values.
				// The same formula is applied to get the minimum ones. The new
				// image is drawn accordingly and the new values are displayed
				// on the text fields
				maxReal = Math.max(init.getRealPart(), fin.getRealPart());
				minReal = Math.min(init.getRealPart(), fin.getRealPart());
				maxImag = Math.max(init.getImaginaryPart(),
						fin.getImaginaryPart());
				minImag = Math.min(init.getImaginaryPart(),
						fin.getImaginaryPart());
				realAxisMaxVal.setText(String.valueOf(maxReal));
				realAxisMinVal.setText(String.valueOf(minReal));
				imagAxisMaxVal.setText(String.valueOf(maxImag));
				imagAxisMinVal.setText(String.valueOf(minImag));
				no_of_iterations.setText(String.valueOf(maxIterations));
				dp.updatePanel(makeImage());
				// the value of dragging is set to false to indicate the user is
				// not dragging to zoom in anymore. The value of jSetShowing is
				// set to true to show that the corresponding Julia Set is
				// showing.
				dragging = false;
				jSetShowing = true;
				repaint();
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// while the mouse is dragged, the new coordinates are continually
			// being updated.
			dragging = true;
			x2 = e.getX();
			y2 = e.getY();
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// this code is only executed if the Live Updating check box is
			// ticked
			if (live) {
				// this is done only if the user hasn't clicked to display an
				// image yet
				if (!clicking) {
					// as the mouse moves, the Julia Set is updated to its
					// current point
					int real = e.getX();
					int imag = e.getY();
					moved = scaleNumber(real, imag);
					point.setText(moved.toString());
					jp.showJuliaSet(moved, maxIterations, fractal, scheme);
					jSetShowing = true;
				}
			}
		}

	}
}