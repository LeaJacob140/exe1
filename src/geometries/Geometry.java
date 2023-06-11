package geometries;

import primitives.*;

/**
 * This is an interface for some geometric body
 * @author Lea and Moriya
 */
public abstract class Geometry extends Intersectable{
	
	protected Color emission=Color.BLACK;
	private Material material=new Material();
	
	public Material getMaterial() {
		return material;
	}

/**
 * 
 * @param norPoint Point for finding the normal to it
 * @return the normal vector (perpendicular) to the body at this point.
 */
	public abstract Vector getNormal(Point norPoint);
	
	public Color getEmission() {
		return emission;
	}

	public Geometry setEmission(Color emission) {
		this.emission = emission;
		return this;
	}
	
	public Geometry setMaterial(Material material) {
		this.material =material;
		return this;
	}
}
