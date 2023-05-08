package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Camera {

	private Point P0;
	private Vector vUp;
	private Vector vTo;
	private Vector vRight;
	private double width;
	private double height;
	private double distance;
	public Camera(Point P0,Vector vTo,Vector vUp)
	{
		if(!isZero(vUp.dotProduct(vTo)))
		{
			throw new IllegalArgumentException("The vectors must be orghogoanl");
		}
		this.vUp=vUp.normalize();
		this.vTo=vTo.normalize();
		this.vRight=(vTo.crossProduct(vUp)).normalize();
		this.P0=P0;
	}
	public Point getP0() {
		return P0;
	}
	public Vector getvUp() {
		return vUp;
	}
	public Vector getvTo() {
		return vTo;
	}
	public Vector getvRight() {
		return vRight;
	}
	public double getWidth() {
		return width;
	}
	public double getHeight() {
		return height;
	}
	public double getDistance() {
		return distance;
	}
	public Camera setVPSize(double width, double height)
	{
		this.height=height;
		this.width=width;
		return this;
	}
	public Camera setVPDistance(double distance)
	{
		this.distance=distance;
		return this;
	}
	public Ray constructRay(int nX, int nY, int j, int i)
	{
		//image center
		Point Pc=this.P0.add(this.vTo.scale(this.distance));
		//Ratio (pixel width and height)
		double Ry=this.height/nY;
		double Rx=this.width/nX;
		//Pixel[i,j] center
		Point pIJ=Pc;
		double yI=-1*(i-(nY-1)/2d)*Ry;
		double xJ=(j-(nX-1)/2d)*Rx;
		
		if(!isZero(xJ))
		{
			pIJ=pIJ.add(this.vRight.scale(xJ));
		}
		if(!isZero(yI))
		{
			pIJ=pIJ.add(this.vUp.scale(yI));
		}
		Vector vIJ=pIJ.subtract(P0).normalize();
		Ray ray=new Ray(this.P0,vIJ);
		return ray;
	}

}
