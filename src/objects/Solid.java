package objects;

import materials.Material;
import utils.*;

/**
 * Solid class.
 * @author Ethan Vince-Budan
 */
public abstract class Solid {
	
	private Material matl;

	/**
	 * Constructor for Solid.
	 * @param m Material.
	 */
	protected Solid(Material m) {
		this.matl = m;
	}
	
	/**
	 * Material.
	 * @return Material.
	 */
	public Material getMaterial() {
		return this.matl;
	}
	
	/**
	 * Abstract method to detect collision with ray.
	 * @param R Ray.
	 * @return Point of collision.
	 */
	public abstract Point3D detectCollision(Ray R);
	
	/**
	 * Abstract method to calculate surface normal.
	 * @param P Point of interest.
	 * @return Normal at point.
	 */
	public abstract Vector normalAt(Point3D P);
	
}
