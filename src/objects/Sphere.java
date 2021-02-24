package objects;

import materials.Material;
import utils.*;

/**
 * Sphere class.
 * @author Ethan Vince-Budan
 */
public class Sphere extends Solid {
	private Point3D origin;
	private double radius;

	/**
	 * Constructor for sphere.
	 * @param C Point.
	 * @param R Radius.
	 * @param m Material.
	 */
	public Sphere(Point3D C, double R, Material m) {
		super(m);
		this.origin = C;
		this.radius = R;
	}

	/**
	 * Center.
	 * @return Center.
	 */
	public Point3D getCenter() {
		return this.origin;
	}

	/**
	 * Radius.
	 * @return Radius.
	 */
	public double getRadius() {
		return this.radius;
	}

	public Point3D detectCollision(Ray R) {
		Vector OR = new Vector(R.getOrigin());
		Vector C = new Vector(this.getCenter());
		Vector L = C.add(OR.mult(-1));
		double tCA = L.dotProduct(R.getDirection());
		double d = Math.sqrt(L.dotProduct(L) - tCA * tCA);
		if (tCA < 0 || d > this.radius) {
			return null;
		}
		double tCH = Math.sqrt(this.radius * this.radius - d * d);

		double t;
		if (tCH > tCA) {
			t = tCA + tCH;
		} else {
			t = tCA - tCH;
		}

		Point3D result = R.extend(t);

		return result;
	}

	public Vector normalAt(Point3D P) {
		Vector result = new Vector(this.origin, P);
		result.normalize();
		return result;
	}
}
