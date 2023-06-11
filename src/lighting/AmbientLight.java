package lighting;

import primitives.Color;
import primitives.Double3;
/**
 * This class represents ambient light class
 * @author Lea and Moriya
 */
public class AmbientLight extends Light{
	
	/**The static field for default ambient light,initialized to an
	 *ambient lighting object with black color and and for an attenuation coefficient of 0
	 */
	public static AmbientLight NONE=new AmbientLight(Color.BLACK,Double3.ZERO);
	/**
	 * Constructor for initializing ambient light. we are scale the color by scalar
	 * @param Ia original fill light
	 * @param Ka fill light attenuation coefficient
	 */
	public AmbientLight(Color Ia,Double3 Ka)
	{
		super(Ia.scale(Ka));
	}
	/**
	 * Constructor for initializing ambient light. Changing the intensity of the
	 * light with a scalar
	 * @param Kafill light attenuation coefficient
	 */
	public AmbientLight(Color Ia,double Ka)
	{
		super(Ia.scale(Ka));
	}

	
}
