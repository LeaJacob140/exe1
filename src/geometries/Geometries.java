package geometries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import primitives.*;
/**
 * This class represents collection of shape
 * @author Lea and Moriya
 */
public class Geometries extends Intersectable
{
	/**
	 * List for the shapes collection
	 */
	private List<Intersectable> lstGeo;
	/**
	 * The empty constructor for the shapes collection
	 */
	public Geometries() {
	    lstGeo = new ArrayList<Intersectable>();
	}
	/**
	 * The constructor crate array list for the shapes
	 * @param geometries the collection of the shapes
	 */
	public Geometries(Intersectable... geometries) {
		this.lstGeo=new ArrayList<Intersectable>(Arrays.asList(geometries));
	}
	/**
	 * Adding shape to the collection
	 * @param geometries foe the shapes in the collection
	 */
	public void add(Intersectable... geometries) {
		if (geometries != null) 
		{
			this.lstGeo.addAll(Arrays.asList(geometries));
	    }
	}
	/**
	 * @param ray of the intersection
	 * @return the intersection points between the shapes collection and the ray
	 */
	protected  	List<GeoPoint> findGeoIntersectionsHelper(Ray ray)
	{
		if(this.lstGeo.isEmpty())
			return null;
		List<GeoPoint> intersections = new ArrayList<>();
	    for (Intersectable geometry : lstGeo) {
	        List<GeoPoint> geometryIntersections = geometry.findGeoIntersections(ray);
	        if (geometryIntersections != null)
	        	{intersections.addAll(geometryIntersections);}
	        
	    }
	    if(intersections.isEmpty())
	    	return null;
	    return intersections;
	}

}
