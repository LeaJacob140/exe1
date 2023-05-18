package renderer;
/** This class present camera and the view plane
 * @author Lea and Moriya */
import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import primitives.*;

import java.util.MissingResourceException;

import static primitives.Util.isZero;


public class Camera {
	/**The position point of the camera*/
	private Point P0;
	/**The vector of the up direction of the camera*/
	private Vector vUp;
	/**The vector of the left/"To" direction of the camera*/
	private Vector vTo;
	/**The vector of the right direction of the camera*/
	private Vector vRight;
	/**The width of the view plane*/
	private double width;
	/**The center height of the view plane*/
	private double height;
	/**The center distance between the ray and the view plane*/
	private double distance;
	
	private ImageWriter imageWrite;
	
	private RayTracerBase rayTracBase;

	/**
	 * Constructor for initializing the points of the triangle
	 * @param P0 the position point of the camera
	 * @param vUp the vector of the up direction of the camera
	 * @param vTo the vector of the right direction of the camera
	 */
	public Camera(Point P0,Vector vTo,Vector vUp)
	{
		if(vUp.dotProduct(vTo)!=0)
		{
			throw new IllegalArgumentException("The vectors must be orghogoanl");
		}
		this.vUp=vUp.normalize();
		this.vTo=vTo.normalize();
		this.vRight=(vTo.crossProduct(vUp)).normalize();
		this.P0=P0;
	}
	/**
	 * @return the position of the camera
	 */
	public Point getP0() {
		return P0;
	}
	/**
	 * @return the position of the camera
	 */
	public Vector getvUp() {
		return vUp;
	}
	/**
	 * @return the vector of the up direction of the camera
	 */
	public Vector getvTo() {
		return vTo;
	}
	/**
	 * @return vector of the left/"To" direction of the camera
	 */
	public Vector getvRight() {
		return vRight;
	}
	/**
	 * @return width of the view plane
	 */
	public double getWidth() {
		return width;
	}
	/**
	 * @return center height of the view plane
	 */
	public double getHeight() {
		return height;
	}
	/**
	 * @return center distance between the ray and the view plane
	 */
	public double getDistance() {
		return distance;
	}
	/**
	 * Setting the size of the view plane
	 * @param width for the width of the view plane
	 * @param height for the height of the view plane
	 */
	public Camera setVPSize(double width, double height)
	{
		this.height=height;
		this.width=width;
		return this;
	}
	/**
	 * Setting the distance of the camera from the view plane
	 * @param distance of the camera from the plane
	 */
	public Camera setVPDistance(double distance)
	{
		this.distance=distance;
		return this;
	}
	
	public Camera setImageWriter(ImageWriter imageWrite)
	{
		this.imageWrite=imageWrite;
		return this;
	}
	
	public Camera setRayTracer(RayTracerBase rayTracBase)
	{
		this.rayTracBase=rayTracBase;
		return this;
	}
	/**
	 * Constructor for initializing the points of the triangle
	 * @param nX Resolution of the viewing plane
	 * @param nY Resolution of the viewing plane
	 * @param j the index in the view plane
	 * @param i the index in the view plane
	 */
	public Ray constructRay(int nX, int nY, int j, int i)
	{
		//image center
		Point Pc=this.P0.add(this.vTo.scale(this.distance));
		//Ratio (pixel width and height)
		double Ry=this.height/nY;
		double Rx=this.width/nX;
		//Pixel[i,j] center
		double yI=-1*(i-(nY-1)/2d)*Ry;
		double xJ=(j-(nX-1)/2d)*Rx;
		Point pIJ=Pc;
		if(!isZero(xJ))
		{
			pIJ=pIJ.add(this.vRight.scale(xJ));
		}
		if(!isZero(yI))
		{
			pIJ=pIJ.add(this.vUp.scale(yI));
		}
		Vector vIJ=pIJ.subtract(P0).normalize();
		return new Ray(this.P0,vIJ);
	}
	
	public Camera renderImage() 
	{
		try {
			if (imageWrite == null) 
		        throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
		    
			if (rayTracBase == null) 
	                throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");
	                
			if (P0 == null)
				throw new MissingResourceException("missing resource", Point.class.getName(), "");

		    if (vUp == null || vTo == null || vRight == null)
		    	throw new MissingResourceException("missing resource", Vector.class.getName(), "");	
 
		    int nX = imageWrite.getNx(); 
		    int nY = imageWrite.getNy(); 

		    // Iterate over the pixels in the image
		    for (int i = 0; i < nY; i++)
		    {
		        for (int j = 0; j < nX; j++) 
		        {
		            //Ray ray = constructRay(nX, nY, j, i); 

		            // Use the ray to get the color using the ray tracing algorithm
		            Color pixelColor = castRay(nX, nY, j, i);

		            // Write the color to the corresponding pixel in the image
		            imageWrite.writePixel(j, i, pixelColor);
		        }
		    }
		}
		catch (MissingResourceException e)
		{
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
		}
		return this;
	}
	
	public void printGrid(int interval,Color color) 
	{
		if (imageWrite == null) {
	        throw new MissingResourceException("ImageWriter is not set", null, null);
	    }
		for (int i = 0; i < height; i++) {
	        for (int j = 0; j < width; j++) {
	            // Check if the current pixel is on the grid line
	            if (i % interval == 0 || j % interval == 0) {
	                imageWrite.writePixel(j, i, color);
	            }
	        }
	    }
	}
	
	public void writeToImage() //throws MissingResourcesException
	{
		if(imageWrite==null)
		{
	        throw new MissingResourceException("ImageWriter is not set", null, null);
		}
		imageWrite.writeToImage();
	}
	
	private Color castRay(int nX,int nY,int j,int i)
	{
		 Ray r=constructRay(nX, nY, j, i);
		 Color c=rayTracBase.traceRay(r);
		 return c;
	}
	
	
}
