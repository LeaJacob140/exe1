package renderer;

import java.util.stream.*;
import primitives.*;
import static primitives.Util.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

import static primitives.Util.isZero;
/** This class present camera and the view plane
 * @author Lea and Moriya */

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
	/**field for write an image*/
	private ImageWriter imageWrite;
	/**field for trace ray*/
	private RayTracerBase rayTracBase;
// /**The partition of the pixel*/
//    private double numOfrow;
// /**The partition of the pixel*/
//    private double numOfCol;
    boolean isAntialsin;

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
/**
* Setting the distance of the camera from the view plane
* @param distance of the camera from the plane
*/
public Camera setAntiAlasing(boolean antiAl)
{
	this.isAntialsin=antiAl;
	return this;
}
/**
* set the image writer field
* @return the object by itself (builder pattern)
*/
public Camera setImageWriter(ImageWriter imageWrite)
{
	this.imageWrite=imageWrite;
	return this;
}
/**
* set the ray tracer field
* @return the object by itself (builder pattern)
*/
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
	Point pIJ=pointCen(nX,nY,j,i);
	Vector vIJ=pIJ.subtract(P0).normalize();
	return new Ray(this.P0,vIJ);
}
private Point pointCen(int nX, int nY, int j, int i)
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
	return pIJ;
}
/**
* The method goes through all the pixels, for each pixel it builds a ray
*  and for each ray it gets a color
*/
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

		for (int i = 0; i < nY; ++i)
		{
			for (int j = 0; j < nX; ++j)
				castRay(nX, nY, j, i);
		}
		
	}
	catch (MissingResourceException e)
	{
       throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
	}
	return this;
}
/**
* Creates a grid of lines
* @param interval The space between the lines in the grid that will
*  be printed on the image
* @param color of grid
*/
public void printGrid(int interval,Color color)
{
	if (imageWrite == null) {
       throw new MissingResourceException("ImageWriter is not set", null, null);
   }

	var writer = new ImageWriter("firstImage",imageWrite.getNx(), imageWrite.getNy());
	for (int i = 0; i < imageWrite.getNx(); i++) {
       for (int j = 0; j <imageWrite.getNy(); j++) {
           // Check if the current pixel is on the grid line
           if (i % interval == 0 || j % interval == 0 || i == 0 || j == 0 || i == imageWrite.getNx()-1 || j == imageWrite.getNy()-1) {
        	   imageWrite.writePixel(j, i, color);
           }
           else
               writer.writePixel(j, i, new primitives.Color(0, 0, 255));
       }
   }
}
/**
* Performs delegation for creating the image
*/
public void writeToImage()
{
	if(imageWrite==null){
       throw new MissingResourceException("ImageWriter is not set", null, null);
	}
	imageWrite.writeToImage();
}
/**
* Generates a beam and calculates the beam's color
* @param nX the resolution
* @param nY the resolution
* @param j the place pixel
* @param i the place pixel
*/
private void castRay(int nX, int nY, int j, int i)
{
    Color averageColor = Color.BLACK;
   if(isAntialsin)
   {
    // create the ray
   Ray r = constructRay(nX, nY, j, i);
   // calculate the color of the ray
   averageColor=rayTracBase.traceRay(r);
   }
   else if(!isAntialsin)
   {
	   List<Ray> rays= constructRays(nX, nY,9,9, j, i);
	   for (Ray ray: rays)
	   {
		   Color c=rayTracBase.traceRay(ray);
		   averageColor=averageColor.add(c);
	   }
	   averageColor=averageColor.reduce(rays.size());
   }
   // Write the color to the corresponding pixel in the image
   imageWrite.writePixel(j, i, averageColor);
   //pixelManager.pixelDone();
}

private List<Ray> constructRays(int nX,int nY,int numOfrow,int numOfCol,int j, int i) 
{
	//
	List<Ray> rays=new ArrayList<>();
    Point pIJ=pointCen(nX,nY,j,i);
        //go all over the pixel, by the request
        for (int x = 0; x < numOfrow; x++) {
            for (int y = 0; y <numOfCol; y++) {
                Point point = pIJ;
                double Yii = -(x -(numOfCol - 1) / 2) *this.height/nY;
                double Xjj = -(y -(numOfrow - 1) / 2) * this.width/nX;    
                if (!isZero(Yii))
                    point = point.add(vUp.scale(Yii));
                if (!isZero(Xjj))
                    point = point.add(vRight.scale(Xjj));
                Vector vIJ=point.subtract(P0).normalize();
                rays.add(new Ray(this.P0,vIJ));
            }
        }

   return rays;

}

   
}
