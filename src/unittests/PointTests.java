/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Point;
/**
 * @author Moriya Haddad Lea Jacob
 *
 */
class PointTests {

	/**
	 * Test method for {@link primitives.Point#subtract(primitives.Point)}.
	 */
	@Test
	void testSubtract() {
		  // ============ Equivalence Partitions Tests ==============
		  // Test subtraction of two different points
		  Point p1 = new Point(1, 2, 3);
		  Point p2 = new Point(2, 3, 4);
		  Point expected = new Point(-1, -1, -1);
		  assertEquals(expected, p1.subtract(p2), "Subtraction of two different points is incorrect");
		  
		  // =============== Boundary Values Tests ==================
		  // Test subtraction of a point from itself
		  Point p3 = new Point(1, 2, 3);
		  assertThrows(IllegalArgumentException.class, () -> p3.subtract(p3),
				  "Subtraction of a point from itself should result in a zero vector");
		  // Test subtraction with itself multiplied -1
		  Point p4 = new Point(-2, -3, -4);
		  Point p5= new Point(4,6,8);
		  assertEquals(p5, p2.subtract(p4), "Subtraction with itself should result in a point plus point");
	}

	/**
	 * Test method for {@link primitives.Point#add(primitives.Vector)}.
	 */
	@Test
	void testAdd() {
		// ============ Equivalence Partitions Tests ==============
		  // Test add of two different points
		  Point p1 = new Point(1, 2, 3);
		  Point p2 = new Point(2, 3, 4);
		  Point expected = new Point(3, 5, 7);
		  assertEquals(expected, p1.add(p2), "addition of two different points is incorrect");

		  // =============== Boundary Values Tests ==================
		  // Test add of a point from itself multiplied -1
		  Point p3 = new Point(1, 2, 3);
		  Point p4 = new Point(-1, -2, -3);
		  assertEquals(new Point(0, 0, 0), p3.add(p4),"ERROR: Point + Vector does not work correctly");}

	/**
	 * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
	 */
	@Test
	void testDistanceSquared() {
		// ============ Equivalence Partitions Tests ==============
		//Test distance for a two points
		Point p1 = new Point(1, 2, 3);
		Point p2 = new Point(4, 5, 6);
		double expected = 27;
		assertEquals(expected, p1.distanceSquared(p2), "Error: Squared distance calculation does not work correctly");
		
		// =============== Boundary Values Tests ==================
		//Test distance for a point with itself
		assertEquals(0, p1.distanceSquared(p1), "Error: Distance calculation with itself does not work correctly");
	}

	/**
	 * Test method for {@link primitives.Point#distance(primitives.Point)}.
	 */
	@Test
	void testDistance() {
		// ============ Equivalence Partitions Tests ==============
		//Test distance for a two points
		Point p1 = new Point(1, 2, 3);
		Point p2 = new Point(4, 5, 6);
		assertEquals(Math.sqrt(27), p1.distance(p2), "Error: Distance calculation does not work correctly");
		
		// =============== Boundary Values Tests ==================
		//Test distance for a point with itself
		assertEquals(0, p1.distance(p1), "Error: Distance calculation with itself does not work correctly");

	}
}
