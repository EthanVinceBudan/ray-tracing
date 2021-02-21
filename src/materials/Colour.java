package materials;

public class Colour {
	private double red, green, blue;

	public Colour(double r, double g, double b) {
		this.red = r;
		this.green = g;
		this.blue = b;
	}
	
	public static Colour[] combine(Colour[] img1, Colour[] img2) {
		Colour[] result = new Colour[img1.length];
		for (int i = 0; i < img1.length; i++) {
			result[i] = img1[i].add(img2[i]);
		}
		
		return result;
	}
	
	public Colour add(Colour c) {
		return new Colour(this.red + c.getRed(), this.green + c.getGreen(), this.blue + c.getBlue());
	}
	
	public Colour divide(double k) {
		return new Colour(this.red/k, this.green/k, this.blue/k);
	}

	public double getRed() {
		return this.red;
	}

	public double getGreen() {
		return this.green;
	}

	public double getBlue() {
		return this.blue;
	}

	public Integer getRedInt() {
		return (int) (this.red * 255);
	}

	public Integer getGreenInt() {
		return (int) (this.green * 255);
	}

	public Integer getBlueInt() {
		return (int) (this.blue * 255);
	}

	public double[] asArray() {
		return new double[] { this.red, this.green, this.blue };
	}
	
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
