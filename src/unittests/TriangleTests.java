package unittests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import geometries.Triangle;
import primitives.*;



/**
 * @author Lea Jacob Moriya Haddad
 *
 */
class TriangleTests {
	/**
	 * Test method for {@link geometries.Polygon#getNormal(primitives.Point)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
	      // TC01: There is a simple single test here - using a quad
	      Point[] pts =
	         { new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1) };
	      Triangle trian = new Triangle(pts[0],pts[1], pts[2]);
	      // ensure there are no exceptions
	      assertDoesNotThrow(() -> trian.getNormal(pts[0]), "");
	      // generate the test result
	      Vector result = trian.getNormal(pts[0]);
	      // ensure |result| = 1
	      assertEquals(1, result.length(), 0.00000001, "Triangle's normal is not a unit vector");
	}
	/**
	 * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections()
	{
		Triangle triangle =  new Triangle(new Point(0, 3, -3),new Point(3, 0, -3),new Point(-3, 0, -3));
	    Ray ray ;
	    // ============ Equivalence Partitions Tests ==============
	    // TC01: The point of intersection is opposite the vertex of the triangle
	    try {
	    	   ray = new Ray(new Point(1, 1, -2),new Vector(-2, 0.5, -1));
	    	   assertEquals(((new Point(-1, 1.5, -3))), triangle.findIntersections(ray).get(0),"the ray goes through the triangle");
	    	}
	    catch(IllegalArgumentException e) {}

	    // TC02: The point of intersection is opposite the side of the triangle
	    ray = new Ray(new Point(0, -1, -1), new Vector(0, 1, 1));
	    assertNull(triangle.findIntersections(ray), "TC02: Wrong intersection");

	    // TC03: The point of intersection is inside the triangle
	    ray = new Ray(new Point(0, 0.1, -1), new Vector(0, 0.9, 1));
	    assertNull(triangle.findIntersections(ray),
	            "TC03: Wrong intersection point");

	    // =============== Boundary Values Tests ==================
	    // TC04: The point of intersection is on the vertex of the triangle
	    ray = new Ray(new Point(0, 1, -1), new Vector(0, 0, 1));
	    assertNull(triangle.findIntersections(ray), "TC04: Wrong intersection");

	    // TC05: The point of intersection is on the side of the triangle
	    ray = new Ray(new Point(-1, 0, -1), new Vector(1, 0, 1));
	    assertNull(triangle.findIntersections(ray), "TC05: Wrong intersection");

	    // TC06: The point of intersection is next to the ray of the triangle contained in the plane
	    ray = new Ray(new Point(0, 0, -1), new Vector(0, 1, 1));
	    assertNull(triangle.findIntersections(ray), "TC06: Wrong intersection");
	}
}
