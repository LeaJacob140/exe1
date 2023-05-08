package geometries;
import static primitives.Util.*;

import java.util.ArrayList;
import java.util.List;
import primitives.Util;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * This class represents a sphere, a sphere is represented by a point and a radius
 * @author Lea and Moriya
 */
public class Sphere extends RadialGeometry  {
	/**The center point of the sphere*/
	Point center;
	/**
	 * The constructor initializes the values that represent the sphere
	 * @param center the center point value
	 * @param radius the radius value
	 */
	public Sphere(Point center, double radius) {
		super(radius);
		this.center = center;
	}
	/**
	 * @return the center point
	 */
	public Point getCenter() {
		return center;
	}
	/**
	 * @return the radius value
	 */
	public double getRadius() {
		return radius;
	}
	
	public Vector getNormal(Point norPoint) {
		return (norPoint.subtract(center)).normalize();
	}
	
	public 	List<Point> findIntersections(Ray ray)
	{
		double tm=ray.getDir().dotProduct(center.subtract(ray.getP0()));
		double d=Math.sqrt(((center.subtract(ray.getP0())).lengthSquared())-tm*tm);
		if(d>=radius)
			return null;
		double th= Math.sqrt(radius*radius-d*d);
		double t1=alignZero(tm+th);
		double t2=alignZero(tm-th);
		List<Point> interactionPoint = new ArrayList<>();
		if(t1>0&&t2>0)//if there is two pints
		{
			interactionPoint.add(ray.getPoint(t1));
			interactionPoint.add(ray.getPoint(t2));
			return interactionPoint;
		}
		else if(t1>0)//if there is one point
		{
			interactionPoint.add(ray.getPoint(t1));
			return interactionPoint;
		}
		else if(t2>0)//if there is one point
		{
			interactionPoint.add(ray.getPoint(t2));
			return interactionPoint;

		}
		return null;
	}
}
