/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

class GeometriesTests 
{

/**
 * @author Lea Jacob Moriya Haddad
 *
 */
@Test
	void testFindIntersections() 
	{
	try {
		
		 Geometries geometries= new Geometries();
		 Sphere sphere = new Sphere(new Point(1, 0, 0),1);
         Triangle triangle = new Triangle(new Point(-4,0,0), new Point(0, 0, 5), new Point(0, -5, 0));
         Plane plane = new Plane (new Point(0, 0, 1), new Point(1, 0, 0), new Point(4, 0, 2));
         geometries.add(sphere, triangle, plane);
         
		// ============ Equivalence Partitions Tests ==============
		//A few (but not all of them) in interaction
        int numOfIntersections = geometries.findIntersections(new Ray(new Point(-4, -3, 2), new Vector(9,5,-1))).size();
		assertEquals(1, numOfIntersections, "Wrong number of intersection points");

		// =============== Boundary Values Tests ==================
		//Empty collection of objects
		assertNull(geometries.findIntersections(new Ray(new Point(-9, -9, -9),new Vector(-8,-8,-8))), "Empty collection of objects - wrong intersection points");
		
		//There is no shape with interaction
		assertNull(geometries.findIntersections(new Ray(new Point(0, -8, 0),new Vector(-10,-1,0))), "There is no shape with interaction - wrong intersection points");

		//There is one object with interaction
		numOfIntersections = geometries.findIntersections(new Ray(new Point(-0.8, -3, 1),new Vector(3.4,3,1.57))).size();
		assertEquals(1, numOfIntersections, "Wrong number of intersection points");

		//The all shapes with interaction
		numOfIntersections = geometries.findIntersections(new Ray(new Point(-4, -3, 0),new Vector(6,3,0.5))).size();
		assertEquals(3, numOfIntersections, "Wrong number of intersection points");
	}
	catch(Exception e)
	{
		 e.printStackTrace();
	}
	}
}
