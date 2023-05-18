/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.Plane;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * @author Lea Jacob Moriya Haddad
 *
 */
class PlaneTests {


	/**
	 * Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}.
	 */
	@Test
	void testConstractor1() {
		//Test when the first and second points converge
		Point v1 = new Point(1, 2, 3);
	    Point v2 = new Point(2, 4, 6);
		assertThrows(IllegalArgumentException.class, () -> new Plane(v1,v1,v2));
	    // Test when the points are on the same line
	    Point p1 = new Point(1, 2, 3);
	    Point p2 = new Point(2, 4, 6);
	    Point p3 = new Point(3, 6, 9);
	    assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p2, p3));
	}

	/**
	 * Test method for {@link geometries.Plane#getNormal()}.
	 */
	@Test
	void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		 Point[] pts =
	         { new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0,1) };
		 // TC01: There is a simple single test here - using a quad
	      Plane pla = new Plane(pts[0],pts[1],pts[2]);
	      // ensure there are no exceptions
	      assertDoesNotThrow(() -> pla.getNormal(pts[0]), "");
	      // generate the test result
	      Vector result = pla.getNormal(pts[0]);
	      // ensure |result| = 1
	      assertEquals(1, result.length(), 0.00000001, "Plane's normal is not a unit vector");
	      // ensure the result is orthogonal to all the edges
	      for (int i = 0; i < 3; ++i)
	         assertTrue(isZero(result.dotProduct(pts[i].subtract(pts[i == 0 ? 2 : i - 1]))),
	                    "Polygon's normal is not orthogonal to one of the edges");
	}
	/**
	 * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
	 */
	 @Test
	 void testFindIntersections() {
	     // Create a plane for testing
	     Plane plane = new Plane(new Point(0, 0, 1), new Vector(0, 0, 1));
	     
	     // ============ Equivalence Partitions Tests ==============
	     // TC1:The ray crosses the plane
	     Ray ray1 = new Ray(new Point(0, 0, -1), new Vector(0, 0, 1));
	     assertEquals(List.of(new Point(0, 0, 1)), plane.findIntersections(ray1),
	            "Wrong intersection point when the ray crosses the plane");
	     
	     // TC2:The ray does not cross the plane
	     Ray ray2 = new Ray(new Point(0, 0, -1), new Vector(0, 1, 0));
	     assertNull(plane.findIntersections(ray2),
	             "Wrong intersection point when the ray doesn't cross the plane");
	     
	     // =============== Boundary Values Tests ==================
	     // Group 1 - the ray is parallel to the plane (2):
	     //TC3:The ray is contained in a plane
	     Ray ray3 = new Ray(new Point(0, 0, 1), new Vector(0, 0, 1));
	     try {
	         plane.findIntersections(ray3);}
	    	 catch (IllegalArgumentException exception){}
	     
	     //TC4:The ray is not contained in the plane
	     Ray ray4 = new Ray(new Point(0, 0, -1), new Vector(0, 0, -1));
	     assertNull(plane.findIntersections(ray4),
	             "Wrong intersection point when the ray is parallel to the plane and doesn't intersect it");

	     // Group 2- the beam is perpendicular to the plane (3), according to P0:
	     // TC5:The ray before the plane
	     Ray ray5 = new Ray(new Point(0, 0, -1), new Vector(0, 1, 1));
	     assertEquals(List.of(new Point(0, 2, 1)), plane.findIntersections(ray5),
	             "Wrong intersection point when the ray is perpendicular to the plane and starts before it");

	     // TC6:The foundation is in the plane
	     Ray ray6 = new Ray(new Point(0, 0, 1), new Vector(0, 1, 0));
	     assertNull(plane.findIntersections(ray6),
	             "Wrong intersection point when the ray is perpendicular to the plane and starts on it");

	     //TC7: The ray after the plane
	     Ray ray7 = new Ray(new Point(0, 0, 2), new Vector(0, 1, 0));
	     assertNull(plane.findIntersections(ray7),
	             "Wrong intersection point when the ray is perpendicular to the plane and starts after it");

	     //TC8: The ray is not perpendicular/parallel, but is on the plane
	     Ray ray8 = new Ray(new Point(0, 1, 0), new Vector(1, 0, 0));
	     assertNull( plane.findIntersections(ray8),
	             "Wrong intersection point when the ray is on the plane but not perpendicular to it");

	     // TC9:The ray is not perpendicular/parallel, but leaves the point of defining the plane
	     Ray ray9 = new Ray(new Point(0, 1, 0), new Vector(1, 1, 0));
	     assertNull(plane.findIntersections(ray9),
	             "Wrong intersection point when the ray is not perpendicular/parallel to the plane and leaves the point of defining the plane");
	 }


}
