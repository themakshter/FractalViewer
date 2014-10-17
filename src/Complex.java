public class Complex {
	private double real;
	private double imaginary;

	/**
	 * Creates a Complex number object.
	 * 
	 * @param real
	 *            : The real part of the complex number.
	 * @param imaginary
	 *            : The imaginary part of the complex number.
	 */
	public Complex(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}

	/**
	 * @return The real part of the Complex.
	 */
	public double getRealPart() {
		return real;
	}

	/**
	 * @return The imaginary part of the Complex.
	 */
	public double getImaginaryPart() {
		return imaginary;
	}

	/**
	 * Squares the complex on which the method is called.
	 * 
	 * @return The Complex after it has been squared.
	 */
	public Complex square() {
		double sqReal = getRealPart() * getRealPart() - getImaginaryPart()
				* getImaginaryPart();
		double sqImaginary = 2 * (getRealPart() * getImaginaryPart());
		return new Complex(sqReal, sqImaginary);
	}

	/**
	 * Finds the square of the modulus of the complex.
	 * 
	 * @return The value of the square of the modulus of the Complex
	 */
	public double modulusSquared() {
		return getRealPart() * getRealPart() + getImaginaryPart()
				* getImaginaryPart();
	}

	/**
	 * Adds the Complex in the parameter to the one on which the method is
	 * called.
	 * 
	 * @param d
	 *            : The Complex to be added.
	 * @return The final resulting Complex from the addition of the two.
	 */
	public Complex add(Complex d) {
		double real = getRealPart() + d.getRealPart();
		double imaginary = getImaginaryPart() + d.getImaginaryPart();
		return new Complex(real, imaginary);
	}

	/**
	 * Converts the real part of the Complex to a positive value, if it isn't
	 * one already.
	 * 
	 * @return The positive value of the real part of the Complex.
	 */
	public double getPosReal() {
		if (real < 0) { // it first checks if the value is positive or not
			return -real; // makes it positive if it isn't
		} else {
			return real; // returns it unchanged if it was already positive
		}
	}

	/**
	 * Gets the conjugate of the Complex on which the method is called.
	 * 
	 * @return  The conjugate of the Complex.
	 */
	public Complex getConjugate() {
		imaginary = -imaginary;
		return new Complex(real, imaginary);
	}

	/**
	 * Converts the imaginary part of the Complex to a positive value, if it
	 * isn't one already.
	 * 
	 * @return  The positive value of the imaginary part of the Complex.
	 */
	public double getPosImag() {
		if (imaginary < 0) { // it first checks if the value is positive or not
			imaginary = -imaginary; // makes it positive if it isn't
			return imaginary;
		} else {
			return imaginary; // returns it unchanged if it was already positive
		}
	}

	/**
	 * Converts the Complex to a string representation of its value
	 * 
	 * @return  The string representation of the Complex.
	 */
	public String toString() {
		if (imaginary > 0) {
			return "" + real + " + " + imaginary + "i";
		} else {
			return "" + real + " " + imaginary + "i";
		}
	}
}
