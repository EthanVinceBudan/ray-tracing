package objects;

import materials.Material;
import utils.*;

public abstract class Solid {
	
	private Material matl;

	protected Solid(Material m) {
		this.matl = m;
	}
	
	public Material getMaterial() {
		return this.matl;
	}
	
	public abstract Point3D detectCollision(Ray R);
	
	public abstract Vector normalAt(Point3D P);
	
}
