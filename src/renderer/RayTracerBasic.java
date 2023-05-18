package renderer;

import java.util.List;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

public class RayTracerBasic extends RayTracerBase{

	public RayTracerBasic(Scene scen) {
		super(scen);
		
	}
	
	public Color traceRay(Ray ray)
	{
		List<Point> intersections = scen.geometries.findIntersections(ray);
		if(intersections ==null)
		{
			return scen.background;
		}
		Point closestPoint = ray.findClosesPoint(intersections);
		return calcColor(closestPoint);
	}
	
	private Color calcColor(Point poin)
	{
		return scen.ambientLight.getIntensity();
	}
	
}
