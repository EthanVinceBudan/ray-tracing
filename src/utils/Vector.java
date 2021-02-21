package utils;

import java.util.Random;

public class Vector {
	private double dx, dy, dz;

	public Vector(double x, double y, double z) {
		this.dx = x;
		this.dy = y;
		this.dz = z;
	}

	public Vector(Point3D P) {
		this.dx = P.getX();
		this.dy = P.getY();
		this.dz = P.getZ();
	}

	public Vector(Point3D start, Point3D end) {
		this.dx = end.getX() - start.getX();
		this.dy = end.getY() - start.getY();
		this.dz = end.getZ() - start.getZ();
	}

	public static Vector reflected(Vector incidence, Vector normal) {
		incidence.normalize();
		normal.normalize();
		double dot = -2 * incidence.dotProduct(normal);
		Vector result = incidence.add(normal.mult(dot));
		result.normalize();
		return result;
		
	}
	
	public static Vector randomHemi(Vector V1) {
		Random rand = new Random();
		Vector temp = new Vector(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());
		temp.normalize();

		Vector V2 = temp.crossProd(V1);
		Vector V3 = V1.crossProd(V2);
		Vector result = V1.mult(rand.nextDouble()).add(V2.mult(rand.nextDouble() - 0.5))
				.add(V3.mult(rand.nextDouble() - 0.5));
		result.normalize();
		return result;
	}

	public void normalize() {
		double mag = Math.sqrt(dx * dx + dy * dy + dz * dz);
		if (mag != 0) {
			this.dx /= mag;
			this.dy /= mag;
			this.dz /= mag;
		}
	}

	public Vector mult(double n) {
		return new Vector(this.dx * n, this.dy * n, this.dz * n);
	}

	public Vector add(Vector V) {
		return new Vector(this.dx + V.getX(), this.dy + V.getY(), this.dz + V.getZ());
	}

	public Vector crossProd(Vector V) {
		double x = this.dy * V.getZ() - this.dz * V.getY();
		double y = this.dz * V.getX() - this.dx * V.getZ();
		double z = this.dx * V.getY() - this.dy * V.getX();
		return new Vector(x, y, z);
	}

	public double dotProduct(Vector V) {
		return this.dx * V.getX() + this.dy * V.getY() + this.dz * V.getZ();
	}

	public double getX() {
		return this.dx;
	}

	public double getY() {
		return this.dy;
	}

	public double getZ() {
		return this.dz;
	}

	public double getMagnitude() {
		return Math.sqrt(this.dx * this.dx + this.dy * this.dy + this.dz * this.dz);
	}
	
	public static Vector parseVector(String[] S) {
		double dx = Double.parseDouble(S[0]);
		double dy = Double.parseDouble(S[1]);
		double dz = Double.parseDouble(S[2]);
		return new Vector(dx,dy,dz);
	}

	public String toString() {
		return this.dx + " " + this.dy + " " + this.dz;
	}
}
