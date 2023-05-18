/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.Sphere;
import primitives.Vector;
import primitives.Point;
import primitives.Ray;
/**
 * @author Lea Jacob Moriya Haddad
 *
 */
class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
	    // TC01: Test for a simple sphere
	    Sphere sphere = new Sphere(new Point(0, 0, 0), 1);
	    // ensure there are no exceptions
	    assertDoesNotThrow(() -> sphere.getNormal(new Point(0, 0, 1)), "");
	    // generate the test result
	    Vector result = sphere.getNormal(new Point(0, 0, 1));
	    // ensure |result| = 1
	    assertEquals(1, result.length(), 0.00000001, "Sphere's normal is not a unit vector");
	}
	/**
	 * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
	 */
	 @Test
	 public void testFindIntersections() {
	 Sphere sphere = new Sphere(new Point (1, 0, 0),1d);
	 // ============ Equivalence Partitions Tests ==============
	 // TC01: Ray's line is outside the sphere (0 points)
	 assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
	 "Ray's line out of sphere");
	 // TC02: Ray starts before and crosses the sphere (2 points)
	 Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
	 Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
	 List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),
	 new Vector(3, 1, 0)));
	 assertEquals(2, result.size(), "Wrong number of points");
	 result = List.of(result.get(1), result.get(0));
	 assertEquals(List.of(p1, p2), result, "Ray crosses sphere");
	 
	 // TC03: Ray starts inside the sphere (1 point)
	 result = sphere.findIntersections(new Ray(new Point(0.5, 0, 0),
			 new Vector(3, 1, 0)));
	 assertEquals(1, result.size(), "Wrong number of points");
	 result = List.of(result.get(0));
	 Point p3=new Point(1.8867496997597595, 0.4622498999199199, 0.0);
	 assertEquals(List.of(p3), result, "Ray crosses sphere");
	 
	 // TC04: Ray starts after the sphere (0 points)
	 assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0),
			 new Vector(-3, -1, 0)))); 
	 
	 // =============== Boundary Values Tests ==================
	 // **** Group: Ray's line crosses the sphere (but not the center)
	 // TC11: Ray starts at sphere and goes inside (1 points)
	 result=sphere.findIntersections(new Ray(new Point(0.0651530771650466, 0.355051025721682, 0),
			 new Vector(3, 1, 0)));
	 assertEquals(1, result.size(), "Wrong number of points");
	 result = List.of(result.get(0));
	 assertEquals(List.of(p2), result, "Ray crosses sphere");
	 
	 // TC12: Ray starts at sphere and goes outside (0 points)
	 result=sphere.findIntersections(new Ray(new Point(0.0651530771650466, 0.355051025721682, 0),
			 new Vector(-3, -1, 0)));
	 assertNull(result,"Wrong number of points");	
	 
	 // **** Group: Ray's line goes through the center
	 // TC13: Ray starts before the sphere (2 points)
	 Point p4 = new Point(0.1339745962155614,0.0,0.5);
	 Point p5 = new Point(1.8660254037844384,0.0,0.5);
	 result = sphere.findIntersections(new Ray(new Point(-1, 0, 0.5),
			 new Vector(1, 0, 0)));
	 assertEquals(2, result.size(), "Wrong number of points");
	 result = List.of(result.get(1), result.get(0));
	 assertEquals(List.of(p4, p5), result, "Wrong intersection points for TC13");
	 
	 // TC14: Ray starts at sphere and goes inside (1 point)
     result = sphere.findIntersections(new Ray(new Point(0,0,0), new Vector(1,0,0)));
     assertEquals(1, result.size(),"Wrong number of points");
     assertEquals(List.of(new Point(2,0,0)), result, "Bad intersection point");
     
	 // TC15: Ray starts inside (1 point)
	 Ray ray = new Ray(new Point(1.5, 0, 0), new Vector(1, 0, 0));
	 result = sphere.findIntersections(ray);
	 assertEquals(1, result.size(), "Wrong number of points");
	 assertEquals(new Point(2, 0, 0), result.get(0), "Wrong intersection point for TC15");

	 // TC16: Ray starts at the center (1 point)
        try {
        	result = sphere.findIntersections(new Ray(new Point(1,0,0), new Vector(1,0,0)));
            fail("Error when ray starts at center of sphere");}
        catch (IllegalArgumentException exception){}

	 // TC17: Ray starts at sphere and goes outside (0 points)
	 result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),
	 new Vector(1, 1, 0)));
	 assertNull(result, "Wrong number of points");

	 // TC18: Ray starts after sphere (0 points)
	 ray = new Ray(new Point(3, 0, 0), new Vector(1, 0, 0));
	 result = sphere.findIntersections(ray);
	 assertNull(result, "Wrong number of points");

	 // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
	 // TC19: Ray starts before the tangent point
	 ray = new Ray(new Point(0, 1, 0), new Vector(-1, 0, 0));
	 result = sphere.findIntersections(ray);
	 assertNull(result, "Ray should not intersect sphere");

	 // TC20: Ray starts at the tangent point
	 result = sphere.findIntersections(new Ray(new Point(1,1,0), new Vector(1,0,0)));
     assertNull(result, "Wrong number of points");

	 // TC21: Ray starts after the tangent point
	 ray = new Ray(new Point(2, 1, 0), new Vector(1, 0, 0));
	 result = sphere.findIntersections(ray);
	 assertNull(result, "Ray should not intersect sphere");

	 // **** Group: Special cases
	 // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
     result = sphere.findIntersections(new Ray(new Point(3,0,0), new Vector(0,1,0)));
     assertNull(result, "Wrong number of points");
	 }
	 
}
