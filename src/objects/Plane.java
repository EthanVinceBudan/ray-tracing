package objects;

import materials.Material;
import utils.*;

/**
 * Plane class
 * @author Ethan Vince-Budan
 */
public class Plane extends Solid {

	private Point3D point;
	private Vector normal;

	/**
	 * Constructor for plane.
	 * @param p Point.
	 * @param N Normal.
	 * @param m Material.
	 */
	public Plane(Point3D p, Vector N, Material m) {
		super(m);
		this.point = p;
		this.normal = N;
		this.normal.normalize();
	}

	public Point3D detectCollision(Ray R) {
		if (R.getDirection().dotProduct(this.normal) == 0) {
			return null;
		}
		
		Vector diff = new Vector(R.getOrigin(), this.point);
		double dot = diff.dotProduct(this.normal);
		
		double t = dot/(R.getDirection().dotProduct(this.normal));
		
		if (t <= 0) {
			return null;
		}
		
		return R.extend(t);
	}

	public Vector normalAt(Point3D P) {
		return this.normal;
	}

}
