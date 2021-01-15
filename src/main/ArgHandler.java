package main;

public class ArgHandler {
	
	public static String[] getArgs(String[] args) {
		String width = "256";
		String height = "256";
		String depth = "4";
		String samples = "10";
		String fileName = "preview.bmp";
		
		if (args.length > 5) {
			return null;
		}
		
		switch (args.length) {
		case 1:
			break;
		case 5:
			width = args[0];
			height = args[1];
			depth = args[2];
			samples = args[3];
			fileName = args[4];
		}
		return new String[] { width, height, depth, samples, fileName };
	}
}
