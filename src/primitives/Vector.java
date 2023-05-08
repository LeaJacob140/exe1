package primitives;
/**The class represents a fundamental object in geometry with a defined direction and size, like a point
By the end point. The actual class inherits from the Point class.
 * @author Lea and Moriya */
public class Vector extends Point {
	/** Constructor to initialize Point based object with its three number values
	 * @param x1 first number value
	 * @param x2 second number value
	 * @param x3 third number value */
	public Vector(double x1,double x2,double x3)
	{
		super(x1,x2,x3);
		if(x1==0&&x2==0&&x3==0)
            throw new IllegalArgumentException("Vector must not be zero vector");
		xyz = new Double3(x1, x2,x3 );
	}
	/** Constructor that initializes the point for the vector direction
	 * @param headVal point value*/
	Vector(Double3 headVal)
	{
		super(headVal);
		if(headVal.equals(Double3.ZERO))
			throw new IllegalArgumentException("Cannot create a zero vector.");
		xyz = headVal;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj instanceof Vector other)
		return xyz.equals(other.xyz);
		return false;
	}
	@Override
	public String toString() {
		return "Vector [head=" + super.toString() + "]";
	}
	/**Vector concatenation
	 *@param  addVec right handle side operand for addition
	 * @return a new vector*/
	public Vector add(Vector addVec)
	{
		return new Vector(xyz.add(addVec.xyz));
	}
	/**Multiply a vector by a number
	 * @param scaleMult A scalar for multiplying a vector
	 * @return a new vector, multiplied by a scalar*/
	public Vector scale(double scaleMult)
	{
		return new Vector(xyz.scale(scaleMult));
	}
	/**scalar product
	 * @param vecDpr Second vector to multiply
	 * @return Scalar, the result of multiplying the two vectors*/
	public double dotProduct(Vector vecDpr)
	{
		return (xyz.d1*vecDpr.xyz.d1)+(xyz.d2*vecDpr.xyz.d2)+(xyz.d3*vecDpr.xyz.d3);
	}
	/**Vector multiplication
	 * @param vecCpr A second vector for the multiplication
	 * @return A new vector perpendicular to the two existing vectors
	 */
	public Vector crossProduct(Vector vecCpr)
	{
		//if(this.dotProduct(vecCpr) == 0)
			//throw new IllegalArgumentException();
		return new Vector((xyz.d2)*(vecCpr.xyz.d3)-(xyz.d3)*(vecCpr.xyz.d2),
				(xyz.d3)*(vecCpr.xyz.d1)-(xyz.d1)*(vecCpr.xyz.d3),
				(xyz.d1)*(vecCpr.xyz.d2)-(xyz.d2)*(vecCpr.xyz.d1));
	}
	/**
	 * Calculation of the squared length of the vector
	 * @return The squared length of the vector
	 */
	public double lengthSquared()
	{
		return (this.xyz.d1*this.xyz.d1)+(this.xyz.d2*this.xyz.d2)+(this.xyz.d3*this.xyz.d3);
	}
	/**
	 * Calculate the length of the vector
	 * @return The length of the vector
	 */
	public double length()
	{
		return Math.sqrt(lengthSquared());
	}
	/**
	 * Vector normalization
	 * @return normalized vector
	 */
	public Vector normalize()
	{
        return new Vector(xyz.scale(1 / length()));
	}
}