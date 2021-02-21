package materials;

public class Material {
	private String name;
	private double[] diffuseCoeff;
	private boolean emissive;
	private boolean sink;
	
	public Material(String n, Colour D, boolean e, boolean s) {
		this.name = n;
		this.diffuseCoeff = new double[] { D.getRed(), D.getGreen(), D.getBlue() };
		this.emissive = e;
		this.sink = s;
	}
	
	public double[] getDiffuse() {
		return this.diffuseCoeff;
	}
	
	public boolean isEmissive() {
		return this.emissive;
	}
	
	public boolean isSink() {
		return this.sink;
	}
	
	public String getName() {
		return this.name;
	}
}
