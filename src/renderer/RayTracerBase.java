package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

public abstract class RayTracerBase {

	protected Scene scen;

	public RayTracerBase(Scene scen) {
		this.scen = scen;
	}
	
	public abstract Color traceRay(Ray ray);
	
	
}
