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
		double sqReal = (real * real) - (imaginary * imaginary);
		double sqImaginary = 2 * real * imaginary;
		return new Complex(sqReal, sqImaginary);
	}

	/**
	 * Finds the square of the modulus of the complex.
	 * 
	 * @return The value of the square of the modulus of the Complex
	 */
	public double modulusSquared() {
		return (real * real) + (imaginary * imaginary);
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
		return new Complex(real + d.getRealPart(), imaginary + d.getImaginaryPart());
	}

	/**
	 * Converts the real part of the Complex to a positive value, if it isn't
	 * one already.
	 * 
	 * @return The positive value of the real part of the Complex.
	 */
	public double getPosReal() {
	    return (real < 0) ? -real : real;
	}

	/**
	 * Gets the conjugate of the Complex on which the method is called.
	 * 
	 * @return  The conjugate of the Complex.
	 */
	public Complex getConjugate() {
		return new Complex(real, -imaginary);
	}

	/**
	 * Converts the imaginary part of the Complex to a positive value, if it
	 * isn't one already.
	 * 
	 * @return  The positive value of the imaginary part of the Complex.
	 */
	public double getPosImag() {
	    return (imaginary < 0) ? -imaginary : imaginary;
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
