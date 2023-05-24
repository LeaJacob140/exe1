package primitives;
/** This class present a basic object in geometry - a point with 3 coordinates, contains an object of three
Numbers as above in the field of type D
 * @author Lea and Moriya */
public class Point {
	/**A field of the coordinates of type Double3 */
	Double3 xyz;
	/** Constructor to initialize Point based object with its three number values
	 * @param x1 first number value
	 * @param x2 second number value
	 * @param x3 third number value */
	public Point(double x1,double x2,double x3)
	{
		this.xyz=new Double3(x1,x2,x3);
	}
	/** Constructor to initialize Point based object the same number values
	 * @param xyzVal point value*/
	public Point(Double3 xyzVal)
	{
		this.xyz=xyzVal;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj instanceof Point other)
		return this.xyz.equals(other.xyz);
		return false;
	}
	public Double3 getXyz() {
		return xyz;
	}
	@Override
	public String toString() {
		return "Point [xyz=" + xyz.toString() + "]";
	}
	/** Vector subtraction, returns a vector from the second point to the point on which it is carried out
		the action
	 * @param  for a subtraction operation from it to a point
	 * @return result of sub */
	public Vector subtract(Point subPoint)
	{
		return new Vector(xyz.subtract(subPoint.xyz));
	}
	/** Adding a vector to a point returns 
	 * @param  addVec to add
	 * @return a new point */
	public Point add(Point addVec)
	{
		return new Point(xyz.add(addVec.xyz));
	}
	/** The distance between two points in a square
	 * @param  disPoint a second point belonging to the distance
	 * @return the distance in a square */
	 public double distanceSquared(Point disPoint)
	{
		 return (((this.xyz.d1)-(disPoint.xyz.d1))*((this.xyz.d1)-(disPoint.xyz.d1)))+
				(((this.xyz.d2)-(disPoint.xyz.d2))*((this.xyz.d2)-(disPoint.xyz.d2)))+
				(((this.xyz.d3)-(disPoint.xyz.d3))*((this.xyz.d3)-(disPoint.xyz.d3)));
	}
	/** The distance between two points
	 * @param  disPoint a second point belonging to the distance
	 * @return the distance*/
	public double distance(Point disPoint)
	{
		return Math.sqrt(distanceSquared(disPoint));
	}
}