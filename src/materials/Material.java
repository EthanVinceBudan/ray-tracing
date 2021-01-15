package materials;

public class Material {
	private double[] diffuseCoeff;
	private double[] specCoeff;
	private boolean emissive;
	private boolean sink;
	
	public Material(Colour D, Colour S, boolean e, boolean s) {
		this.diffuseCoeff = new double[] { D.getRed(), D.getGreen(), D.getBlue() };
		this.specCoeff = new double[] { S.getRed(), S.getGreen(), S.getBlue() };
		this.emissive = e;
		this.sink = s;
	}
	
	public double[] getDiffuse() {
		return this.diffuseCoeff;
	}
	
	public double[] getSpec() {
		return this.specCoeff;
	}
	
	public boolean isEmissive() {
		return this.emissive;
	}
	
	public boolean isSink() {
		return this.sink;
	}
}
