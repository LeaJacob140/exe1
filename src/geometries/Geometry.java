package geometries;


import primitives.Color;
import primitives.Point;
import primitives.Vector;
/**
 * This is an interface for some geometric body
 * @author Lea and Moriya
 */
public abstract class Geometry extends Intersectable{
	
	protected Color emission=Color.BLACK;

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
}
