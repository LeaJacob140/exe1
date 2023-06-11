package lighting;

import primitives.*;

public class SpotLight extends PointLight {
	
	private Vector direction;

	public SpotLight(Color intensity,Point position,Vector direction) {
		super(intensity,position);
		this.direction=direction.normalize();
	}
	
	public Color getIntensity(Point p) {
		return super.getIntensity(p).scale(Math.max(0,direction.dotProduct(getL(p))));
	}

	public Vector getL(Point p) {
		return super.getL(p);
	}
}