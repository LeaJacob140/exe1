package geometries;

import java.util.List;
import static primitives.Util.*;
import primitives.*;
/**
 * This class represents a plane, which is a point in space and a vertical vector
 * @author Lea and Moriya
 */
public class Plane extends Geometry {
	/**field for a point in space*/
	Point q0;
	
	/**field for a vertical vector*/
	Vector normal;
	/**
	 * The constructor calculates the normal according to what was learned about the normal to the triangle - and the builder will keep one
		The points as the reference point of the plane
	 * @param q0 the point of the plane
	 * @param normal to the plane
	 */
	public Plane(Point q0, Vector normal) {
		this.q0 = q0;
		this.normal = normal.normalize();
	}
	
	/**
	 * The constructor calculates the normal according to what was learned about the normal to the triangle - and the builder will keep one
		The points as the reference point of the plane
	 * @param p1 first point to calculate
	 * @param p2 second point to calculate
	 * @param p3 third point to calculate
	 */
	public Plane(Point p1,Point p2,Point p3) {
		
        Vector v1 =  p2.subtract(p1);
        Vector v2 =  p3.subtract(p1);
        this.normal = (v1.crossProduct(v2)).normalize();
        this.q0 = p1;
	}
	/**
	 * @return the point which represents the plain
	 */
	public Point getQ0() {
		return q0;
	}
	/**
	 * @return the normal which represents the plain
	 */
	public Vector getNormal() {
		return normal;
	}
	@Override
	public Vector getNormal(Point norPoint) {
		return normal;
	}
	/**
	 * @param ray of the intersection
	 * @return the intersection points between the plane and the ray
	 */
	public List<Point> findIntersections(Ray ray)
	{
		if(q0.equals(ray.getP0()))
			return null;
		if(isZero(alignZero(normal.dotProduct(ray.getDir()))))
		{
			return null;
		}
		if(isZero(alignZero(normal.dotProduct(q0.subtract(ray.getP0())))))
			return null;
		double t=alignZero(normal.dotProduct(q0.subtract(ray.getP0())))/(normal.dotProduct(ray.getDir()));
		if(t<=0)
		{
			return null;
		}
		return List.of(ray.getPoint(t));
	}
}
