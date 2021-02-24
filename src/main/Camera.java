package main;

import utils.*;

/**
 * Camera class.
 * @author Ethan Vince-Budan
 */
public class Camera {
	private Point3D location;
	private Vector tn;
	private Vector vn;
	private double fovAngle;
	private int width, height;

	/**
	 * Constructor for camera.
	 * @param L Position in space.
	 * @param w Horizontal resolution in pixels.
	 * @param h Vertical resolution in pixels.
	 * @param FOV Field of view in radians.
	 */
	public Camera(Point3D L, int w, int h, double FOV) {
		this.location = L;
		this.width = w;
		this.height = h;
		this.fovAngle = FOV;
		
		// TODO stop hardcoding all of this lmao
		this.tn = new Vector(1, 0, 0);
		tn.normalize();
		this.vn = new Vector(0, 0, 1);
		vn.normalize();
	}

	/**
	 * Gets all the rays needed to get all pixels.
	 * @return View rays corresponding to pixels on the image.
	 */
	public Ray[] getViewRays() {
		Ray[] result = new Ray[this.width * this.height];

		// Getting observable width and height - SCALAR
		double gx = Math.tan(this.fovAngle / 2);
		double gy = gx * ((this.height - 1.0) / (this.width - 1.0));
		//System.out.println(gx + ", " + gy);

		// Vector perpenedicular to view plane and vn
		Vector bn = vn.crossProd(tn);

		// Small vectors used to displace the final ray
		Vector qx = bn.mult(2 * gx / (this.width - 1));
		Vector qy = vn.mult(2 * gy / (this.height - 1));

		// Common origin for all rays in the extreme corner of the view plane
		Vector botLeft = this.tn.add(bn.mult(-gx).add(vn.mult(-gy)));

		for (int y = 1; y <= this.height; y++) {
			for (int x = 1; x <= this.width; x++) {

				// Offset origin vector by small vectors; normalize to unit vector
				Vector dir = botLeft.add(qx.mult(x-1)).add(qy.mult(y-1));
				dir.normalize();

				// Add to ray array
				result[(y - 1) * this.width + (x - 1)] = new Ray(this.location, dir);

			}
		}

		return result;
	}
	
	/**
	 * Location.
	 * @return Location.
	 */
	public Point3D getLocation() {
		return this.location;
	}
	
	/**
	 * Width.
	 * @return Width.
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Height
	 * @return Height.
	 */
	public int getHeight() {
		return this.height;
	}
}
