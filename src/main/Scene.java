package main;

import java.util.ArrayList;
import materials.Colour;
import materials.Material;
import objects.*;
import utils.*;

public class Scene {

	public static void main(String[] args) throws InterruptedException {

		String[] parsedArgs = ArgHandler.getArgs(args);
		if (parsedArgs == null) {
			System.out.println("Too many arguments");
			return;
		}

		int width = Integer.parseInt(parsedArgs[0]);
		int height = Integer.parseInt(parsedArgs[1]);
		int depth = Integer.parseInt(parsedArgs[2]);
		int numSamples = Integer.parseInt(parsedArgs[3]);
		String filename = parsedArgs[4];

		System.out.println(
				"Rendering at " + width + " x " + height + ", depth " + depth + ", for " + numSamples + " samples.");

		Camera camera = new Camera(new Point3D(0, -0.3, 0), width, height, Math.PI/2);

		// Definition of materials used
		Material light = new Material(new Colour(1,1,1), new Colour(1, 1, 1), true, false);
		Material red_shiny = new Material(new Colour(1, 0, 0), new Colour(1, 1, 1), false, false);
		Material white_shiny = new Material(new Colour(1, 1, 1), new Colour(1, 1, 1), false, false);
		Material blue_shiny = new Material(new Colour(1, 0.2, 1), new Colour(1,1,1), false, false);

		// Definition of Solids in scene
		ArrayList<Solid> solids = new ArrayList<Solid>();

		// Center sphere
		solids.add(new Sphere(new Point3D(3, 0, 0), 1, red_shiny));
		// Bottom plane
		solids.add(new Plane(new Point3D(0, 0, -1), new Vector(0, 0, 1), white_shiny));
		// Right Plane
		solids.add(new Plane(new Point3D(0, 1.5, 0), new Vector(0, -1, 0), blue_shiny));
		// Back Plane
		solids.add(new Plane(new Point3D(4.5, 0, 0), new Vector(-1, 0, 0), white_shiny));
		// Smaller Sphere
		solids.add(new Sphere(new Point3D(2.5, -1.3, -0.6), 0.4, white_shiny));
		// Light
		solids.add(new Sphere(new Point3D(-2,-2,12), 11, light));

		Runtime env = Runtime.getRuntime();
		Renderer[] render = new Renderer[env.availableProcessors()];
		for (int i = 0; i < render.length; i++) {
			render[i] = new Renderer(solids, camera, numSamples, depth);
		}

		Colour[] imageData = new Colour[width * height];
		for (int i = 0; i < imageData.length; i++) {
			imageData[i] = new Colour(0, 0, 0);
		}

		System.out.printf("Using all %d cores detected. ", env.availableProcessors());
		System.out.println("Starting frame...");
		for (Renderer r : render) {
			r.start();
		}

		boolean running = true;
		while (running) {
			running = false;
			double percent = 0.0;
			for (Renderer r : render) {
				if (r.getThread().isAlive()) {
					running = true;
				}
				percent += r.getPercent();
			}

			if (System.currentTimeMillis() % 500 == 0) {
				System.out.print("\r" + (int) (percent / render.length) + "%");
			}
		}

		for (int i = 0; i < render.length; i++) {
			imageData = Colour.combine(imageData, render[i].getData());
		}

		for (int i = 0; i < imageData.length; i++) {
			imageData[i] = imageData[i].divide(render.length);
		}

		FileHandler.saveImage(imageData, width, height, filename);
		System.out.println("\nFrame completed.");

	}

}
