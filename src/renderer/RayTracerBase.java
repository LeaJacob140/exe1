package renderer;

import java.util.List;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
/** This class is abstract and present the ray trace base
 * @author Lea and Moriya */
public abstract class RayTracerBase {
	/**The scene field*/
	protected Scene scen;
	/**
	 * Constructor to initialize the scene
	 * @param scen for initialize the scene
	 */
	public RayTracerBase(Scene scen) {
		this.scen = scen;
	}
	/**
	 * This method is abstract
	 * @param ray the ray for trace
	 * @return the color of the ray in the scene
	 */
	public abstract Color traceRay(Ray ray);
}
