package main;

import java.io.*;

import materials.Colour;

public class FileHandler {

	public FileHandler() {
	}

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
				allData[3*i] = data[i].getBlueInt().byteValue();
				allData[3*i+1] = data[i].getGreenInt().byteValue();
				allData[3*i+2] = data[i].getRedInt().byteValue();
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

	private static byte[] intToByteArray(int n) {
		return new byte[] { (byte) (n >> 0 & 0xff), (byte) (n >> 8 & 0xff), (byte) (n >> 16 & 0xff),
				(byte) (n >> 24 & 0xff) };
	}

}
