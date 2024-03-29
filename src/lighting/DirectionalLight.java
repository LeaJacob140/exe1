package lighting;

import primitives.*;

public class DirectionalLight extends Light implements LightSource{
	private Vector direction;
	
	public DirectionalLight(Color intensity,Vector direction) {
		super(intensity);
		this.direction=direction.normalize();
	}

	@Override
	public Color getIntensity(Point p) {
		return this.getIntensity();
	}

	@Override
	public Vector getL(Point p) {
		return direction.normalize();
	}
	@Override
	public double getDistance(Point point)
	{
		return Double.POSITIVE_INFINITY;
	}

}
