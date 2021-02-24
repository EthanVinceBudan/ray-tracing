package utils;

import java.util.ArrayList;
import objects.Solid;
import main.Collision;

/**
 * Ray class.
 * @author Ethan Vince-Budan
 */
public class Ray {
	private Point3D origin;
	private Vector direction;

	/**
	 * Constructor for ray.
	 * @param or Origin.
	 * @param dir Direciton.
	 */
	public Ray(Point3D or, Vector dir) {
		this.origin = or;
		this.direction = dir;
	}

	/**
	 * Gets a point on the ray based on an offset t and direction.
	 * @param t Offset along ray.
	 * @return Point at that offset.
	 */
	public Point3D extend(double t) {
		return new Point3D(this.origin.getX() + t * this.direction.getX(),
				this.origin.getY() + t * this.direction.getY(), this.origin.getZ() + t * this.direction.getZ());
	}

	/**
	 * Origin.
	 * @return Origin.
	 */
	public Point3D getOrigin() {
		return this.origin;
	}

	/**
	 * Direction.
	 * @return Direction.
	 */
	public Vector getDirection() {
		return this.direction;
	}

	/**
	 * Find first collision point with an object in the scene.
	 * @param scene Objects in the scene.
	 * @return Collision with an object.
	 */
	public Collision getCollision(ArrayList<Solid> scene) {
		Collision result = null;
		double min = Double.MAX_VALUE;
		for (int i = 0; i < scene.size(); i++) {
			Solid obj = scene.get(i);
			Point3D pt = obj.detectCollision(this);
			if (pt != null) {
				Vector norm = obj.normalAt(pt);
				double dist = new Vector(pt).getMagnitude();
				if (dist < min) {
					min = dist;
					result = new Collision(pt, obj, norm);
				}
			}
		}
		return result;
	}
}
