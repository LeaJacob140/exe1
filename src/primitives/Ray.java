package primitives;

import java.util.List;

/**
 * A class representing a ray, the set of points on a line that are on one side of a given point on the line
called the head of the fund. Defined by point and direction
 * @author Lea and Moriya
 */
public class Ray {
	/**Point field to represent a beam*/
	final Point p0;
	/**A vector field to represent a ray*/
	final Vector dir;
	/**
	 * Constructor to initialize the foundation values
	 * @param p0 the point of the head of the horn
	 * @param dir the direction of the fund
	 */
	public Ray(Point p0, Vector dir) {
		this.p0 = p0;
		this.dir = dir.normalize();
	}
	/**
	 * @return the p0 (The starting point of the fund) value
	 */
	public Point getP0() {
		return p0;
	}
	/**
	 * @return dir,the ray direction vector
	 */
	public Vector getDir() {
		return dir;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj instanceof Ray other)
		return this.p0.equals(other.p0)&& this.dir.equals(other.dir);
		return false;
	}
	@Override
	public String toString() {
		return "Ray [p0=" + p0.toString() + ", dir=" + dir.toString() + "]";
	}	
	
	public Point getPoint(double t)
	{
		return p0.add(dir.scale(t));
	}
	
	public Point findClosesPoint(List<Point> poin)
	{
		if (poin == null || poin.size() < 2) {
	        return null;
	    }
		Point minPoint=poin.get(0);
		for(Point po:poin)
		{
			if(po.distance(p0)<minPoint.distance(p0))
			{
				minPoint=po;
			}
		}
		return minPoint;
	}
}
