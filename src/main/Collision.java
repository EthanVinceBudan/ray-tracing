package main;

import objects.*;
import utils.*;

/**
 * Collision class.
 * @author Ethan Vince-Budan
 */
public class Collision {
	private Point3D intersectionPoint;
	private Solid object;
	private Vector collisionNormal;
	
	/**
	 * Constructor for collision.
	 * @param P Point of collision.
	 * @param OB Object that was hit.
	 * @param N Normal at the point of collision.
	 */
	public Collision(Point3D P, Solid OB, Vector N) {
		this.intersectionPoint = P;
		this.object = OB;
		this.collisionNormal = N;
	}
	
	/**
	 * Intersection.
	 * @return Intersection.
	 */
	public Point3D getIntersection() {
		return this.intersectionPoint;
	}
	
	/**
	 * Object.
	 * @return Object.
	 */
	public Solid getObject() {
		return this.object;
	}
	
	/**
	 * Normal.
	 * @return Normal.
	 */
	public Vector getNormal() {
		return this.collisionNormal;
	}
}
