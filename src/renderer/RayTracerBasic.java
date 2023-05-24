package renderer;
import geometries.Intersectable.GeoPoint;
import java.util.List;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
/** This class extends from the ray tracer base class
 * @author Lea and Moriya */
public class RayTracerBasic extends RayTracerBase{
	/**
	 * Constructor to initialize the scene
	 * @param scen for initialize the scene
	 */
	public RayTracerBasic(Scene scen) {
		super(scen);
		
	}
	/**
	 * Looking for cuts between the ray and the 3D model
       of the scene.
       @param ray With it we will look for a cut
	 */
	public Color traceRay(Ray ray)
	{
		//find the intersection point
		List<GeoPoint> intersections = scen.geometries.findGeoIntersections(ray);
		//if there is no intersection
		if(intersections ==null)
		{
			return scen.background;
		}
		//find the closes point
		GeoPoint closestPoint = ray.findGeoClosestPoint(intersections);
		return calcColor(closestPoint);
	}
	/**
	 * calculating the color.
	 * @param poin
	 * @return  until now, we are just return the intensity of the scene
	 */
	private Color calcColor(GeoPoint poin)
	{
		return (scen.ambientLight.getIntensity()).add(poin.geometry.getEmission());
	}
	
}
