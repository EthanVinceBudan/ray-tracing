package utils;

/**
 * Point3D class.
 * @author Ethan Vince-Budan
 */
public class Point3D {
	private double x, y, z;

	/**
	 * Constructor for Point3D.
	 * @param x X-position.
	 * @param y Y-position.
	 * @param z Z-position.
	 */
	public Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Constructor for Point3D.
	 * @param V Vector.
	 */
	public Point3D(Vector V) {
		this.x = V.getX();
		this.y = V.getY();
		this.z = V.getZ();
	}

	/**
	 * X.
	 * @return X.
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * Y.
	 * @return Y.
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * Z. 
	 * @return Z.
	 */
	public double getZ() {
		return this.z;
	}
	
	/**
	 * Parse Point3D from string.
	 * @param S String to be parsed.
	 * @return Point3D.
	 */
	public static Point3D parsePoint(String[] S) {
		double x = Double.parseDouble(S[0]);
		double y = Double.parseDouble(S[1]);
		double z = Double.parseDouble(S[2]);
		return new Point3D(x,y,z);
	}
	
	public String toString() {
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}
}
