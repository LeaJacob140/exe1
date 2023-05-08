package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
/**
 * This class represents a tube, represented by a radius and a beam
 * @author Lea and Moriya
 */
public class Tube extends RadialGeometry {
	/**field for the ray*/
	Ray axisRay;
	/**
	 * A constructor for initializing the values that represent the tube
	 * @param axisRay the ray of the tube
	 * @param radius the radius of the tube
	 */
	public Tube(Ray axisRay, double radius) {
		super(radius);
		this.axisRay = axisRay;
	}
	/**
	 * @return the ray value
	 */
	public Ray getAxisRay() {
		return axisRay;
	}
	/**
	 * @return the radius value
	 */
	public double getRadius() {
		return radius;
	}
	public Vector getNormal(Point norPoint) {
		double t=(axisRay.getDir().dotProduct(norPoint.subtract(axisRay.getP0())));
		if (t==0) { // the point is at the head of the tube
	        return axisRay.getDir().scale(-1);
	    }
		Point O=axisRay.getP0().add(axisRay.getDir().scale(t));
		return (norPoint.subtract(O)).normalize();
	}
	public 	List<Point> findIntersections(Ray ray)
	{
		return null;
	}
}
