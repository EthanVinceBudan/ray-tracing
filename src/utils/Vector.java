package utils;

import java.util.Random;

/**
 * The {@code Vector} class is used to perform operations using 3-dimensional vectors.
 * @author Ethan Vince-Budan
 */
public class Vector {
	private double dx, dy, dz;

	/**
	 * Creates a {@code Vector} object with the given dimensions.
	 * @param x Dimension in x.
	 * @param y Dimension in y.
	 * @param z Dimension in z.
	 */
	public Vector(double x, double y, double z) {
		this.dx = x;
		this.dy = y;
		this.dz = z;
	}

	/**
	 * Creates a {@code Vector} object using the given {@code Point}.
	 * The starting point of the {@code Vector} is the origin of the 3D space,
	 * and the endpoint is the position of the {@code Point}.
	 * @param P The endpoint of the desired vector.
	 */
	public Vector(Point3D P) {
		this.dx = P.getX();
		this.dy = P.getY();
		this.dz = P.getZ();
	}

	/**
	 * Creates a {@code Vector} object using two {@code Point} objects as the
	 * starting and ending positions. The resulting vector will always point
	 * TOWARDS the endpoint.
	 * @param start The origin of the vector.
	 * @param end The endpoint of the vector.
	 */
	public Vector(Point3D start, Point3D end) {
		this.dx = end.getX() - start.getX();
		this.dy = end.getY() - start.getY();
		this.dz = end.getZ() - start.getZ();
	}

	/**
	 * Reflects the given incidence vector about the given normal vector.
	 * @param incidence The incoming {@code Vector} to be reflected.
	 * @param normal The {@code Vector} perpendicular to a surface, that the
	 * incidence {@code Vector} will reflect across.
	 * @return Normalized {@code Vector} representing the result of the reflection.
	 * @see Vector#normalize()
	 * @throws IllegalArgumentException if either of the incidence or normal
	 * 		vectors have a magnitude of zero.
	 */
	public static Vector reflected(Vector incidence, Vector normal) {
		if (incidence.getMagnitude() == 0 || normal.getMagnitude() == 0) {
			throw new IllegalArgumentException("Cannot reflect about/on a vector of magnitude zero.");
		}
		incidence.normalize();
		normal.normalize();
		double dot = -2 * incidence.dotProduct(normal);
		Vector reflection = incidence.add(normal.mult(dot));
		reflection.normalize();
		return reflection;
		
	}
	
	/**
	 * Generates a random {@code Vector} that is within the hemisphere surrounding
	 * the given {@code Vector}. Specifically, the new {@code Vector} will be
	 * within 90 degrees of the given {@code Vector}, in a random direction.
	 * @param VZ The {@code Vector} from which the hemisphere will be constructed.
	 * @return A random {@code Vector} within the constructed hemisphere.
	 */
	public static Vector randomHemi(Vector VZ) {
		// Create a random vector
		Random rand = new Random();
		Vector temp = new Vector(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());
		temp.normalize();

		// Use cross product to find a dimension perpendicular to VZ
		Vector VX = temp.crossProd(VZ);
		// Use cross product again to find the second dimension
		Vector VY = VZ.crossProd(VX);
		
		// Add all three dimensions together to make a new random vector; normalize
		Vector result = VZ.mult(rand.nextDouble()).add(VX.mult(2 * (rand.nextDouble() - 0.5)))
				.add(VY.mult(2 * (rand.nextDouble() - 0.5)));
		result.normalize();
		
		return result;
	}

	/**
	 * Updates the current {@code Vector} to have a magnitude of 1, while maintaining
	 * its original direction. If the magnitude of the current {@code Vector} is zero,
	 * nothing will change.
	 * @see Vector#getMagnitude()
	 */
	public void normalize() {
		double mag = Math.sqrt(dx * dx + dy * dy + dz * dz);
		if (mag != 0) {
			this.dx /= mag;
			this.dy /= mag;
			this.dz /= mag;
		}
	}

	/**
	 * Multiplies the current {@code Vector} by a scalar number.
	 * @param n Scalar value to multiply by.
	 * @return New {@code Vector} holding the result of the multiplication.
	 */
	public Vector mult(double n) {
		return new Vector(this.dx * n, this.dy * n, this.dz * n);
	}

	/**
	 * Adds the given {@code Vector} to the current {@code Vector}.
	 * @param V The {@code Vector} to add.
	 * @return New {@code Vector} holding the result of the addition.
	 */
	public Vector add(Vector V) {
		return new Vector(this.dx + V.getX(), this.dy + V.getY(), this.dz + V.getZ());
	}

	/**
	 * Performs the cross product between the current and given {@code Vector}s.
	 * This product is always in the form (current X given).
	 * @param V The {@code Vector} to take the cross product with.
	 * @return New {@code Vector} holding the result of the operation.
	 */
	public Vector crossProd(Vector V) {
		double x = this.dy * V.getZ() - this.dz * V.getY();
		double y = this.dz * V.getX() - this.dx * V.getZ();
		double z = this.dx * V.getY() - this.dy * V.getX();
		return new Vector(x, y, z);
	}

	/**
	 * Performs the dot product between the current and given {@code Vector}s.
	 * @param V The {@code Vector} to take the dot product with.
	 * @return Scalar value holding the result of the operation.
	 */
	public double dotProduct(Vector V) {
		return this.dx * V.getX() + this.dy * V.getY() + this.dz * V.getZ();
	}

	/**
	 * Returns the x dimension of the {@code Vector}.
	 * @return The x dimension of the {@code Vector}.
	 */
	public double getX() {
		return this.dx;
	}

	/**
	 * Returns the y dimension of the {@code Vector}.
	 * @return The y dimension of the {@code Vector}.
	 */
	public double getY() {
		return this.dy;
	}

	/**
	 * Returns the z dimension of the {@code Vector}.
	 * @return The z dimension of the {@code Vector}.
	 */
	public double getZ() {
		return this.dz;
	}

	/**
	 * Calculates the magnitude of the current {@code Vector}.
	 * @return The magnitude of the {@code Vector}. 
	 */
	public double getMagnitude() {
		return Math.sqrt(this.dx * this.dx + this.dy * this.dy + this.dz * this.dz);
	}
	
	/**
	 * Returns a {@code Vector} with the dimensions specified in the {@code String[]} argument.
	 * @param S {@code String[]} array to be parsed.
	 * @return {@code Vector} with (x, y, z) dimensions extracted from the given {@code String[]}.
	 * @throws IllegalArgumentException if the given {@code String[]} is not of length 3.
	 */
	public static Vector parseVector(String[] S) {
		if (S.length != 3) {
			throw new IllegalArgumentException("Array length must be 3.");
		}
		double dx = Double.parseDouble(S[0]);
		double dy = Double.parseDouble(S[1]);
		double dz = Double.parseDouble(S[2]);
		return new Vector(dx,dy,dz);
	}

	/**
	 * Returns a {@code String} representation of the current {@code Vector}. This representation
	 * consists of the x, y and z dimensions of the {@code Vector} separated by spaces.
	 * @return The {@code String} representation of the current {@code Vector}.
	 */
	public String toString() {
		return this.dx + " " + this.dy + " " + this.dz;
	}
}
