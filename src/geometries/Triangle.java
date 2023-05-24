package geometries;

import static primitives.Util.*;
import primitives.*;
import java.util.List;

/**
 * This class represents a triangle-polygon with 3 vertices, the class inherits from the polygon class
 * @author Lea and Moriya
 *
 */
public class Triangle extends Polygon {
	/**
	 * Constructor for initializing the points of the triangle
	 * @param p1 first point of the triangle
	 * @param p2 second point of the triangle
	 * @param p3 third point of the triangle
	 */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }
    
    /**
	 * @param ray of the intersection
	 * @return the intersection points between the triangle and the ray
	 */
    protected 	List<GeoPoint> findGeoIntersectionsHelper(Ray ray)
	{
		List<Point> pPoint = plane.findIntersections(ray);
		
		if(pPoint == null)
			return null;
		
		Point point=pPoint.get(0);
		
	    Vector v=ray.getDir();
	    Point p0=ray.getP0();
	    
	    Point p1=this.vertices.get(0);
	    Point p2=this.vertices.get(1);
	    Point p3=this.vertices.get(2);
	    
	    Vector v1=(p1).subtract(p0);
	    Vector v2=(p2.subtract(p0));
	    Vector v3=(p3.subtract(p0));
	    
	    Vector n1=v1.crossProduct(v2).normalize();
	    Vector n2=v2.crossProduct(v3).normalize();
	    Vector n3=v3.crossProduct(v1).normalize();
		
	    double s1 = alignZero(n1.dotProduct(v));
    	double s2 = alignZero(n2.dotProduct(v));;
    	double s3 = alignZero(n3.dotProduct(v));
    	if (s1==0|| s2==0|| s3 ==0) 
        {
    		return null;    
    	}
    	
        if ((s1<0&& s2<0&& s3 <0)||(s1>0&& s2>0&& s3 >0)) 
        {
        	return List.of(new GeoPoint(this,point)); 
    	}
        return null;
	}
		
}
