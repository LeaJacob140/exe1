package renderer;
import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import geometries.Triangle;
import lighting.LightSource;

import java.util.List;
import static primitives.Util.*;
import primitives.*;
import scene.Scene;
/** This class extends from the ray tracer base class
 * @author Lea and Moriya */
public class RayTracerBasic extends RayTracerBase{
	private static final double MIN_CALC_COLOR_K = 0.001;
	private static final Double3 INITIAL_K =new Double3( 1.0);
	private static final int MAX_CALC_COLOR_LEVEL = 10;
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
		GeoPoint closestPoint = findClosestIntersection(ray);
		return closestPoint == null ? scen.background: calcColor(closestPoint, ray);
	}
	/**
	 * calculating the color.
	 * @param poin
	 * @return  until now, we are just return the intensity of the scene
	 */
	private Color calcColor(GeoPoint gp, Ray ray) {
		return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scen.ambientLight.getIntensity());
	}
	private Color calcColor(GeoPoint intersection, Ray ray,int level, Double3 k) 
	{
		Color color = calcLocalEffects(intersection, ray,k);
		return 1 == level ? color: color.add(calcGlobalEffects(intersection, ray, level, k));
	}
	
	private Color calcLocalEffects(GeoPoint gp,Ray ray,Double3 k)
	{
		Color color=gp.geometry.getEmission();
		Vector v=ray.getDir();
		Vector n=gp.geometry.getNormal(gp.point);
		double nv = alignZero(n.dotProduct(v)); 
		if (nv == 0) return color;
		Material material = gp.geometry.getMaterial();
		for (LightSource lightSource : scen.lights)
		{
			Vector l = lightSource.getL(gp.point); 
			double nl = alignZero(n.dotProduct(l));
			if (nl * nv > 0)
			{ // sign(nl) == sing(nv)
				Double3 ktr = transparency(gp, l, n,lightSource);

                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Color lightIntensity = lightSource.getIntensity(gp.point).scale(ktr);
                    color = color.add(lightIntensity.scale(calcDiffusive(material, nl)), lightIntensity.scale(calcSpecular(material, n, l, nl, v)));
                }
			}
		}
			return color;
	}
	
	private Double3 calcDiffusive(Material material,double nl)
	{
		return material.kD.scale(Math.abs(nl));
	}
	
	private Double3 calcSpecular(Material material,Vector n,Vector l,double nl,Vector v)
	{
		Vector r = calcRDirection(n, l,nl);
		return material.kS.scale(Math.pow(Math.max(0, v.scale(-1).dotProduct(r)), material.nShininess));
	}
	
	private Vector calcRDirection(Vector n, Vector l,double nl) {
	    return l.subtract(n.scale(nl*2));
	}
//    private boolean unshaded(GeoPoint geoPoint, Vector l, Vector n, LightSource lightSource) {
//        Ray lightRay = new Ray(geoPoint.point, l.scale(-1), n);
//        List<GeoPoint> intersections = scen.geometries.findGeoIntersections(lightRay);
//
//        if (intersections != null) {
//            double distance = lightSource.getDistance(geoPoint.point);
//            for (GeoPoint intersection : intersections) {
//                if (intersection.point.distance(geoPoint.point) < distance) {
//                    return false;
//                }
//            }
//        }
//        return true;
//
//    }
    private Double3 transparency(GeoPoint gp, Vector l, Vector n, LightSource ls) {
    	 Vector lightDirection = l.scale(-1); // from point to light source
         Ray lightRay = new Ray(gp.point, lightDirection, n);
         List<GeoPoint> intersections = scen.geometries.findGeoIntersections(lightRay);

         Double3 ktr = Double3.ONE;
         if (intersections == null) return ktr;

         double distance = ls.getDistance(gp.point);

         for (GeoPoint intersection : intersections) {

             if (distance > intersection.point.distance(gp.point)) {
                 ktr = ktr.product(intersection.geometry.getMaterial().kT);
             }
         }
         return ktr;

    }
	
	private Color calcGlobalEffects(GeoPoint gp, Ray ray,int level, Double3 k) 
	{
		Color color = Color.BLACK;
		Vector v = ray.getDir();
		Vector n = gp.geometry.getNormal(gp.point);
		Material material = gp.geometry.getMaterial();
		return calcGlobalEffect(constructReflectedRay(gp, v, n),level, k, material.kR).add(calcGlobalEffect(constructRefractedRay(gp, v, n),level, k, material.kT));
	}
	
	private Color calcGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
		Double3 kkx = k.product(kx);
		if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
		GeoPoint gp = findClosestIntersection(ray);
		if (gp == null) return scen.background.scale(kx);
		return isZero(gp.geometry.getNormal(gp.point).dotProduct(ray.getDir()))? Color.BLACK : calcColor(gp, ray, level-1, kkx).scale(kx);
	}
	private Ray constructReflectedRay(GeoPoint gp,Vector v,Vector n)
	{
		Vector r =calcRDirection(n, v,n.dotProduct(v)).normalize();
	    return new Ray(gp.point, r,n);
	}
	private Ray constructRefractedRay(GeoPoint gp,Vector v,Vector n)
	{
		return new Ray(gp.point, v,n);
	}
	private GeoPoint findClosestIntersection(Ray ray)
	{
		List<GeoPoint> intersections = scen.geometries.findGeoIntersections(ray);
		if(intersections==null) return null;
		GeoPoint closestPoint = ray.findGeoClosestPoint(intersections);
		return closestPoint;
	}
}
