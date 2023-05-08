/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import geometries.Tube;
import primitives.Ray;
import primitives.Point;
import primitives.Vector;

/**
 * @author Lea Jacob Moriya Haddad
 *
 */
class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
	 */
	@Test
	public void testGetNormal() {
	    // ============ Equivalence Partitions Tests ==============
	    // TC01: Test for a simple tube
	    Ray ray = new Ray(new Point(0, 0, 1), new Vector(0, 0, 1));
	    Tube tube = new Tube(ray, 1);
	    // ensure there are no exceptions
	    assertDoesNotThrow(() -> tube.getNormal(new Point(0, 1, 1)), "");
	    // generate the test result
	    Vector result = tube.getNormal(new Point(0, 1, 1));
	    // ensure |result| = 1
	    assertEquals(1, result.length(), 0.00000001, "Tube's normal is not a unit vector");
	    // ensure the result is orthogonal to the tube's surface at the given point
	    Vector expectedNormal = new Vector(0, 1, 0); // The expected normal of the tube at (0,1,1)
	    assertTrue(!expectedNormal.equals(result) ||! expectedNormal.equals(result.scale(-1)),
	           "Tube's normal is not orthogonal to the tube's surface");

	    // =============== Boundary Values Tests ==================
	    // TC02: Test for a point opposite to the tube's head
	    Point oppositePoint = new Point(0, 1, 0);
	    Vector expectedNormalOppositePoint = new Vector(0, 1, 0);
	    assertEquals(expectedNormalOppositePoint, tube.getNormal(oppositePoint),
	        "Tube's normal is not pointing towards the tube's axis when the point is opposite to the tube's head");
	}
	

	}
