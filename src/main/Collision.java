package main;

import objects.*;
import utils.*;

public class Collision {
	private Point3D intersectionPoint;
	private Solid object;
	private Vector collisionNormal;
	
	public Collision(Point3D P, Solid OB, Vector N) {
		this.intersectionPoint = P;
		this.object = OB;
		this.collisionNormal = N;
	}
	
	public Point3D getIntersection() {
		return this.intersectionPoint;
	}
	
	public Solid getObject() {
		return this.object;
	}
	
	public Vector getNormal() {
		return this.collisionNormal;
	}
}
