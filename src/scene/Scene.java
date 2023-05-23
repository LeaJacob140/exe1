package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;
/** This class present scene
 * @author Lea and Moriya */
public class Scene {
	/**The name of the scene*/
	public String name;
	/**background color*/
	public Color background=Color.BLACK;
	/**ambient lighting*/
	public AmbientLight ambientLight=AmbientLight.NONE;
	/**The 3D model*/
	public Geometries geometries=new Geometries();
	/**
	 * Constructor for initializing the name of the scene.
	 * @param name the name of the scene
	 */
	public Scene(String name)
	{
		this.name=name;
	}
	/**
	 * set the background
	 * @return the object by itself (builder pattern)
	 */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}
	/**
	 * set the ambient light
	 * @return the object by itself (builder pattern)
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}
	/**
	 * set the geometries
	 * @return the object by itself (builder pattern)
	 */
	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;
	}
	
}
