package main;

import java.util.ArrayList;
import materials.Colour;
import objects.*;

/**
 * Scene class.
 * @author Ethan Vince-Budan
 */
public class Scene {

	/**
	 * Program entry point.
	 * @param args Command line arguments.
	 * @throws InterruptedException ?
	 */
	public static void main(String[] args) throws InterruptedException {
		
		Object[] config = new Object[0];
		if (args.length == 0) {
			config = FileHandler.parseFile("default.scene");
		} else if (args.length == 1) {
			if (args[0].equals("-doc")) {
				FileHandler.open("javadoc/allclasses-index.html");
				System.exit(0);
			}
			config = FileHandler.parseFile(args[0]);
		} else {
			System.out.println("Incorrect number of arguments.");
			System.exit(0);
		}

		int width = (int)config[0];
		int height = (int)config[1];
		int depth = (int)config[2];
		int numSamples = (int)config[3];
		Camera camera = (Camera) config[4];
		String filename = (String)config[5];

		System.out.printf("Rendering at %d x %d, depth %d for %d samples.\n",
				width, height, depth, numSamples);

		// Definition of Solids in scene
		@SuppressWarnings("unchecked")
		ArrayList<Solid> solids = (ArrayList<Solid>)config[6];

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
		System.out.printf("\nFrame completed. Image saved to %s\n", filename);

	}

}
