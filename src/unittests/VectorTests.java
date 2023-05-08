/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Double3;
import primitives.Vector;

/**
 * @author Lea Jacob Moriya Haddad
 *
 */
class VectorTests {

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	void testAddVector() {
	      // ============ Equivalence Partitions Tests ==============
		  // Test add of two different vectors
		  Vector p1 = new Vector(1, 2, 3);
		  Vector p2 = new Vector(2, 3, 4);
		  Vector expected = new Vector(3, 5, 7);
		  assertEquals(expected, p1.add(p2), "addition of two different vectors is incorrect");
		  
	      // =============== Boundary Values Tests ==================
		  // Test add of two vectors which are in opposite direction
		  assertThrows(IllegalArgumentException.class, () -> p2.add(new Vector(-2, -3, -4)),
				  "addition of two vectors which are in opposite direction is incorrect");
		
	}
	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	void testSubVector() {
	      // ============ Equivalence Partitions Tests ==============
		  // Test subtraction of two different vectors
		  Vector p1 = new Vector(1, 2, 3);
		  Vector p2 = new Vector(2, 3, 4);
		  Vector expected = new Vector(-1, -1, -1);
		  assertEquals(expected, p1.subtract(p2), "subtraction of two different points is incorrect");
		
	      // =============== Boundary Values Tests ==================
		  // Test add of two vectors which are in the same direction and in the same size
		  assertThrows(IllegalArgumentException.class, () -> p2.subtract(p2),
				  "subtraction of two vectors which are in the same direction and in the same size is incorrect");
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	void testScale() {
	      // ============ Equivalence Partitions Tests ==============
		  // Test of scalar vector multiplication
		  double scalar=3;
		  Vector p1 = new Vector(1, 2, 3);
		  Vector expected = new Vector(3, 6, 9);
		  assertEquals(expected, p1.scale(scalar), "Multiplication of a vector by a scalar is incorrect");
		
	      // =============== Boundary Values Tests ==================
		  // Test for vector multiplication by scalar 0
		  Vector p2 = new Vector(1, 2, 3);
		  assertThrows(IllegalArgumentException.class, () -> p2.scale(0),
				  "Multiplying a vector by a scalar 0 is incorrect");		
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	void testDotProduct() {
	    // ============ Equivalence Partitions Tests ==============
		//Test if dot product works
		Vector v1 =new Vector(1,2,3);
		Vector v2=new Vector(4,5,6);
		assertEquals(32, v1.dotProduct(v2), "Multiplying a vector other vector is incorrect");

	    // =============== Boundary Values Tests ==================
		//Test for two orthogonal vectors
		Vector p1 =new Vector(3,0,-3);
		Vector p2=new Vector(1,0,1);
		assertEquals(0, p1.dotProduct(p2), "Multiplication of a vector by a scalar is incorrect");

		//Test when one of the vectors is a unit vector
		Vector p3 =new Vector(1,1,1);
		assertEquals(2, p2.dotProduct(p3), "Multiplying a vector by a unit vector is incorrect");
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	void testCrossProduct() {
		// ============ Equivalence Partitions Tests ==============
	    //Test if cross product works
		Vector v1 = new Vector(1, 2, 3);
		Vector v2 = new Vector(5, 0, 2);
		assertEquals(new Vector(4,13, -10), v1.crossProduct(v2), "cross product of two vectors is incorrect");
		
		// =============== Boundary Values Tests ==================
		// Test cross product of two vectors which are in opposite direction
		Vector p1 =new Vector(3,0,-3);
		Vector p2 = new Vector(-3, 0, 3);
		assertThrows(IllegalArgumentException.class, () -> p1.crossProduct(p2),
				  "cross product of two vectors in opposite direction is incorrect");
		// Test cross product of two vectors which are in the same direction
		assertThrows(IllegalArgumentException.class, () -> p2.crossProduct(p2),
				  "cross product of two vectors in the same direction is incorrect");
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	void testLengthSquared() {
		// ============ Equivalence Partitions Tests ==============
		//Test length squared
		Vector p1 = new Vector(0, 3, 4);
		assertEquals(25, p1.lengthSquared(), "lengthSquared() wrong value");

	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	void testLength() {
		// ============ Equivalence Partitions Tests ==============
		//Test length
		assertEquals(5, new Vector(0, 3, 4).length(), "length() wrong value");
	}

}
