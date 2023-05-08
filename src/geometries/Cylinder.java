package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
/**
 * This class represents a cylinder which is represented by height, inherited from the tube class
 * @author Lea and Moriya
 */
public class Cylinder extends Tube{
	/**This field represents the height of the cylinder*/
	double height;
	/**
	 * This constructor initializes the values of the cylinder
	 * @param axisRay for the ray of the cylinder
	 * @param radius for the radius of the cylinder
	 * @param height for the height of the cylinder
	 */
	public Cylinder(Ray axisRay, double radius, double height) {
		super(axisRay,radius);
		this.height = height;
	}
	/**
	 * @return the height value
	 */
	public double getHeight() {
	return height;
	}
	public 	List<Point> findIntersections(Ray ray)
	{
		return null;
	}

}
