package geometries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import primitives.Point;
import primitives.Ray;

public class Geometries implements Intersectable
{
	private List<Intersectable> lstGeo;
	
	public Geometries() {
	    lstGeo = new ArrayList<Intersectable>();
	}
	
	public Geometries(Intersectable... geometries) {
		this.lstGeo=new ArrayList<Intersectable>(Arrays.asList(geometries));
	}

	public void add(Intersectable... geometries) {
		if (geometries != null) 
		{
			this.lstGeo.addAll(Arrays.asList(geometries));
	    }
	}
	
	public 	List<Point> findIntersections(Ray ray)
	{
		if(this.lstGeo.isEmpty())
			return null;
		List<Point> intersections = new ArrayList<>();
	    for (Intersectable geometry : lstGeo) {
	        List<Point> geometryIntersections = geometry.findIntersections(ray);
	        if (geometryIntersections != null)
	        	{intersections.addAll(geometryIntersections);}
	        
	    }
	    if(intersections.isEmpty())
	    	return null;
	    return intersections;
	}

}
