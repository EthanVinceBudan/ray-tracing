package utils;

public class Point3D {
	private double x, y, z;

	public Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Point3D(Vector V) {
		this.x = V.getX();
		this.y = V.getY();
		this.z = V.getZ();
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public double getZ() {
		return this.z;
	}
	
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
