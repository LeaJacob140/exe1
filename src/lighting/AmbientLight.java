package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight {
	
	Color intensity;
	
	public static AmbientLight NONE=new AmbientLight(Color.BLACK,Double3.ZERO);
	
	public AmbientLight(Color Ia,Double3 Ka)
	{
		this.intensity=Ia.scale(Ka);
	}
	
	public AmbientLight(double Ka)
	{
		this.intensity=this.intensity.scale(Ka);
	}
	
	public Color getIntensity() {
		return intensity;
	}

}
