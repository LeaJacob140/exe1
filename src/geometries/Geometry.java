package geometries;

import primitives.Point;
import primitives.Vector;
/**
 * This is an interface for some geometric body
 * @author Lea and Moriya
 */
public interface Geometry extends Intersectable{
/**
 * 
 * @param norPoint Point for finding the normal to it
 * @return the normal vector (perpendicular) to the body at this point.
 */
	public abstract Vector getNormal(Point norPoint);
}
