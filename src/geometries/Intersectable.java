package geometries;
import java.util.ArrayList;
import java.util.List;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

/**
 * This This is an interface for find the interaction with the geometries and the primitives
 * @author Lea and Moriya
 */
public abstract class Intersectable {
	/**
	 * @param ray Ray of the interaction
	 * @return list of points of the interaction
	 */
	//public abstract List<Point> findIntersections(Ray ray);
	public List<Point> findIntersections(Ray ray) {
		 var geoList = findGeoIntersections(ray);
		 return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
	}

	
	public static class GeoPoint {
		
		 public Geometry geometry;
		 public Point point;
		 public GeoPoint(Geometry geometry,Point point)
		 {
			 this.geometry=geometry;
			 this.point=point;
		 }
	}
	
	public  List<GeoPoint> findGeoIntersections(Ray ray)
	{
		return findGeoIntersectionsHelper(ray);
	}
	
	protected  List<GeoPoint> findGeoIntersectionsHelper(Ray ray)
	{
//		List<GeoPoint> intersections = new ArrayList<>();
//		for (Geometries geometry : //) {
//	        List<Point> geometryIntersections = geometry.findIntersections(ray);
//	        if (geometryIntersections != null) {
//	            for (Point intersection : geometryIntersections) {
//	                intersections.add(new GeoPoint(this, intersection));
//	            }
//	        }
//	    }
//
//	    return intersections;
		return null;
	}
	
}
