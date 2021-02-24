package materials;

/**
 * Material class.
 * @author Ethan Vince-Budan
 */
public class Material {
	private String name;
	private double[] diffuseCoeff;
	private boolean emissive;
	private boolean sink;
	
	/**
	 * Constructor for material.
	 * @param n Name.
	 * @param D Diffuse colour.
	 * @param e Emitter.
	 * @param s Sink.
	 */
	public Material(String n, Colour D, boolean e, boolean s) {
		this.name = n;
		this.diffuseCoeff = new double[] { D.getRed(), D.getGreen(), D.getBlue() };
		this.emissive = e;
		this.sink = s;
	}
	
	/**
	 * Diffuse.
	 * @return Diffuse.
	 */
	public double[] getDiffuse() {
		return this.diffuseCoeff;
	}
	
	/**
	 * Emissive.
	 * @return Emissive.
	 */
	public boolean isEmissive() {
		return this.emissive;
	}
	
	/**
	 * Sink.
	 * @return Sink.
	 */
	public boolean isSink() {
		return this.sink;
	}
	
	/**
	 * Name.
	 * @return Name.
	 */
	public String getName() {
		return this.name;
	}
}
