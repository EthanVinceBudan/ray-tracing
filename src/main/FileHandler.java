package main;

import java.io.*;
import utils.*;
import materials.*;
import java.util.ArrayList;
import objects.*;
import materials.Colour;

public class FileHandler {

	public static void saveImage(Colour[] data, int W, int H, String f) {
		File file = new File(f);

		int padding = (W * 3) % 4;
		int lineLength = (W * 3) + padding;
		int dataSize = lineLength * H;
		int size = 54 + dataSize;
		byte[] bWidth = FileHandler.intToByteArray(W);
		byte[] bHeight = FileHandler.intToByteArray(H);
		byte[] bSize = FileHandler.intToByteArray(size);
		byte[] bDataSize = FileHandler.intToByteArray(dataSize);

		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(new byte[] { 0x42, 0x4d });
			fos.write(bSize);
			fos.write(new byte[] { 0x00, 0x00, 0x00, 0x00, 0x36, 0x00, 0x00, 0x00 });
			fos.write(new byte[] { 0x28, 0x00, 0x00, 0x00 });
			fos.write(bWidth);
			fos.write(bHeight);
			fos.write(new byte[] { 0x01, 0x00, 0x18, 0x00, 0x00, 0x00, 0x00, 0x00 });
			fos.write(bDataSize);
			fos.write(new byte[] { 0x13, 0x0B, 0x00, 0x00, 0x13, 0x0B, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
					0x00, 0x00 });

			byte[] allData = new byte[dataSize];
			for (int i = 0; i < data.length; i++) {
				allData[3 * i] = data[i].getBlueInt().byteValue();
				allData[3 * i + 1] = data[i].getGreenInt().byteValue();
				allData[3 * i + 2] = data[i].getRedInt().byteValue();
			}

			fos.write(allData);

			fos.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Object[] parseFile(String fileName) {
		File file = new File(fileName);
		// width, height, depth, samples, Camera, filename, solids
		Object[] result = new Object[7];
		ArrayList<Material> matls = new ArrayList<Material>();
		ArrayList<Solid> solids = new ArrayList<Solid>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String line;
			while ((line = br.readLine()) != null) {
				String[] words = line.strip().split(" ");

				if (words[0].equals("size:")) {
					result[0] = Integer.parseInt(words[1]);
					result[1] = Integer.parseInt(words[2]);
				} else if (words[0].equals("depth:")) {
					result[2] = Integer.parseInt(words[1]);
				} else if (words[0].equals("samples:")) {
					result[3] = Integer.parseInt(words[1]);
				} else if (words[0].equals("camera:")) {
					String[] location = words[1].strip().split(",");
					Point3D L = new Point3D(Double.parseDouble(location[0]), Double.parseDouble(location[1]),
							Double.parseDouble(location[2]));
					Camera c = new Camera(L, (int) result[0], (int) result[1], Double.parseDouble(words[2]));
					result[4] = c;
				} else if (words[0].equals("out:")) {
					result[5] = words[1];
				} else if (words[0].equals("materials")) {
					String subLine = br.readLine();
					while (!subLine.equals("}")) {
						String[] subWords = subLine.strip().split(" ");
						if (!subWords[0].equals("//")) {
							String name = subWords[0].substring(0, subWords[0].length() - 1);
							String[] sCol = subWords[1].strip().split(",");
							Colour C = new Colour(Double.parseDouble(sCol[0]), Double.parseDouble(sCol[1]),
									Double.parseDouble(sCol[2]));
							boolean emitter = Boolean.parseBoolean(subWords[2]);
							boolean sink = Boolean.parseBoolean(subWords[3]);
							matls.add(new Material(name, C, emitter, sink));
						}
						subLine = br.readLine();
					}
				} else if (words[0].equals("scene")) {
					String subLine = br.readLine();
					while (!subLine.equals("}")) {
						String[] subWords = subLine.strip().split(" ");
						if (subWords[0].equals("sphere:")) {
							String[] sLocation = subWords[1].strip().split(",");
							Point3D L = new Point3D(Double.parseDouble(sLocation[0]), Double.parseDouble(sLocation[1]),
									Double.parseDouble(sLocation[2]));
							double r = Double.parseDouble(subWords[2]);
							Material M = null;
							for (int i = 0; i < matls.size(); i++) {
								if (matls.get(i).getName().equals(subWords[3])) {
									M = matls.get(i);
								}
							}
							if (M == null) {
								throw new NullPointerException("Undeclared material");
							}
							solids.add(new Sphere(L, r, M));
						} else if (subWords[0].equals("plane:")) {
							String[] sPoint = subWords[1].strip().split(",");
							Point3D P = new Point3D(Double.parseDouble(sPoint[0]), Double.parseDouble(sPoint[1]),
									Double.parseDouble(sPoint[2]));
							String[] sNormal = subWords[2].strip().split(",");
							Vector V = new Vector(Double.parseDouble(sNormal[0]), Double.parseDouble(sNormal[1]),
									Double.parseDouble(sNormal[2]));
							Material M = null;
							for (int i = 0; i < matls.size(); i++) {
								if (matls.get(i).getName().equals(subWords[3])) {
									M = matls.get(i);
								}
							}
							if (M == null) {
								throw new NullPointerException("Undeclared material");
							}
							solids.add(new Plane(P, V, M));
						}
						subLine = br.readLine();
					}
					result[6] = solids;
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private static byte[] intToByteArray(int n) {
		return new byte[] { (byte) (n >> 0 & 0xff), (byte) (n >> 8 & 0xff), (byte) (n >> 16 & 0xff),
				(byte) (n >> 24 & 0xff) };
	}

}
