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
	
	public String toString() {
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}
}
