package lighting;

import primitives.*;

public class PointLight extends Light implements LightSource{
	
	private Point position;
	private double kC=1;
	private double kL=0;
	private double kQ=0;
	
	public PointLight(Color intensity,Point position) {
		super(intensity);
		this.position=position;
	}
	public PointLight setKc(double kC) {
		this.kC = kC;
		return this;
	}
	public PointLight setKl(double kL) {
		this.kL = kL;
		return this;
	}
	public PointLight setKq(double kQ) {
		this.kQ = kQ;
		return this;
	}
	@Override
	public Color getIntensity(Point p) {
		return this.getIntensity().reduce(kC+(position.distance(p)*kL)+(position.distance(p)*position.distance(p)*kQ));
	}
	@Override
	public Vector getL(Point p) {
		return (p.subtract(position)).normalize();
	}
	@Override
	public double getDistance(Point point) {
		return position.distance(point);
	}


}
