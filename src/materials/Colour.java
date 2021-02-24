package materials;

/**
 * Colour class
 * @author Ethan Vince-Budan
 */
public class Colour {
	private double red, green, blue;

	/**
	 * Constructor for colour.
	 * @param r Red value.
	 * @param g Green value.
	 * @param b Blue value.
	 */
	public Colour(double r, double g, double b) {
		this.red = r;
		this.green = g;
		this.blue = b;
	}
	
	/**
	 * Combines two images together.
	 * @param img1 First image.
	 * @param img2 Second image.
	 * @return Combined image.
	 */
	public static Colour[] combine(Colour[] img1, Colour[] img2) {
		Colour[] result = new Colour[img1.length];
		for (int i = 0; i < img1.length; i++) {
			result[i] = img1[i].add(img2[i]);
		}
		
		return result;
	}
	
	/**
	 * Adds the colour.
	 * @param c Colour.
	 * @return Colour.
	 */
	public Colour add(Colour c) {
		return new Colour(this.red + c.getRed(), this.green + c.getGreen(), this.blue + c.getBlue());
	}
	
	/**
	 * Divides colour.
	 * @param k Number to divide by.
	 * @return Colour.
	 */
	public Colour divide(double k) {
		return new Colour(this.red/k, this.green/k, this.blue/k);
	}

	/**
	 * Red.
	 * @return Red.
	 */
	public double getRed() {
		return this.red;
	}

	/**
	 * Green.
	 * @return Green.
	 */
	public double getGreen() {
		return this.green;
	}

	/**
	 * Blue.
	 * @return Blue.
	 */
	public double getBlue() {
		return this.blue;
	}

	/**
	 * Red int.
	 * @return Red int.
	 */
	public Integer getRedInt() {
		return (int) (this.red * 255);
	}

	/**
	 * Green int.
	 * @return Green int.
	 */
	public Integer getGreenInt() {
		return (int) (this.green * 255);
	}

	/**
	 * Blue int.
	 * @return Blue int.
	 */
	public Integer getBlueInt() {
		return (int) (this.blue * 255);
	}

	/**
	 * Parses colour into an array.
	 * @return Array of data.
	 */
	public double[] asArray() {
		return new double[] { this.red, this.green, this.blue };
	}
	
	/**
	 * Parses colour from string.
	 * @param S String to parse.
	 * @return Colour.
	 */
	public static Colour parseColour(String[] S) {
		double r = Double.parseDouble(S[0]);
		double g = Double.parseDouble(S[1]);
		double b = Double.parseDouble(S[2]);
		return new Colour(r,g,b);
	}

	public String toString() {
		return "(" + this.red + ", " + this.green + ", " + this.blue + ")";
	}
}
