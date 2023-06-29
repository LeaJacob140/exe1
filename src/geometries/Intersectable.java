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

	/**
     * Constructs a new GeoPoint object with the given geometry and point.
     * @param geometry The associated geometry
     * @param point The intersection point
     */
	public static class GeoPoint {
		
		 public Geometry geometry;
		 public Point point;
		 public GeoPoint(Geometry geometry,Point point)
		 {
			 this.geometry=geometry;
			 this.point=point;
		 }
	}
	/**
     * Finds the geometric intersections between the given ray and the geometry.
     * @param ray The ray for intersection
     * @return A list of GeoPoint objects representing the geometric intersections with the geometry.
     */
	public  List<GeoPoint> findGeoIntersections(Ray ray)
	{
		return findGeoIntersectionsHelper(ray);
	}
	/**
     * Helper method to find the geometric intersections between the given ray and the geometry.
     * @param ray The ray for intersection
     * @return A list of GeoPoint objects representing the geometric intersections with the geometry.
     */
	protected List<Intersectable.GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		Scene mySce = null;
	    List<Intersectable.GeoPoint> intersections = new ArrayList<>();
	    @SuppressWarnings({ "null", "unchecked" })
		Intersectable[] geometriesArray = ((List<Point>) mySce.geometries).toArray(new Intersectable[0]);
	    for (Intersectable geometry : geometriesArray) {
	        List<Intersectable.GeoPoint> geometryIntersections = geometry.findGeoIntersections(ray);
	        if (geometryIntersections != null) {
	            intersections.addAll(geometryIntersections);
	        }
	    }
	    return intersections.isEmpty() ? null : intersections;
	}
}
