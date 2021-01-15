package main;

import objects.*;
import utils.*;
import java.util.ArrayList;

import materials.Colour;

public class Renderer extends Thread {

	private Thread t;
	private final int MAX_DEPTH;
	private ArrayList<Solid> scene;
	private Camera cam;
	private int numSamples;

	private Colour[] data;
	private volatile double percent;

	public Renderer(ArrayList<Solid> S, Camera C, int n, int m) {
		this.scene = S;
		this.cam = C;
		this.numSamples = n;
		this.MAX_DEPTH = m;
	}

	public Colour[] renderFrame() {
		Colour[] result = new Colour[cam.getWidth() * cam.getHeight()];
		Ray[] rays = cam.getViewRays();
		for (int pixel = 0; pixel < rays.length; pixel++) {
			double[] rawData = new double[] { 0.0, 0.0, 0.0 };
			for (int s = 0; s < numSamples; s++) {
				double[] diffuseData = this.TraceRay(scene, rays[pixel], 0);
				rawData = new double[] { rawData[0] + diffuseData[0], rawData[1] + diffuseData[1],
						rawData[2] + diffuseData[2] };
			}
			result[pixel] = new Colour(rawData[0] / numSamples, rawData[1] / numSamples, rawData[2] / numSamples);
			this.percent = (pixel * 100.0) / (double) rays.length;
		}
		return result;
	}

	public double[] TraceRay(ArrayList<Solid> scene, Ray r, int depth) {
		// TODO Get something specular - BRDF?
		if (depth > MAX_DEPTH) {
			return new double[3];
		}
		Collision c = r.getCollision(scene);
		if (c == null) {
			return new double[3];
		}

		Vector N = c.getNormal();
		Ray L = new Ray(c.getIntersection(), Vector.randomHemi(N));

		double[] incoming = TraceRay(scene, L, depth + 1);

		if (c.getObject().getMaterial().isEmissive()) { // Collided with light source - should always be 100% LIT BRUV
			return new double[] { 1, 1, 1 };
		} else if (c.getObject().getMaterial().isSink()) { // Collided with sink - always dark
			return new double[] { 0, 0, 0 };
		}

		// Lambertian (diffuse)
		double cos_alpha = L.getDirection().dotProduct(N);
		double[] dConsts = c.getObject().getMaterial().getDiffuse();
		double[] outgoingLight = { dConsts[0] * cos_alpha * incoming[0], dConsts[1] * cos_alpha * incoming[1],
				dConsts[2] * cos_alpha * incoming[2] };
		
		return outgoingLight;
	}

	public void start() {
		t = new Thread(this);
		t.start();
	}

	public void run() {
		this.data = this.renderFrame();
	}

	public Colour[] getData() {
		return this.data;
	}

	public Thread getThread() {
		return this.t;
	}

	public double getPercent() {
		return this.percent;
	}
}