package geometries;

/**
 * Abstract class representing a radial geometry, which is a geometry with a specific radius.
 * It extends the Geometry class.
 */
public abstract class RadialGeometry extends Geometry {

    protected double radius;

    /**
     * Constructs a RadialGeometry object with the given radius.
     * @param radius The radius of the radial geometry.
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }

    /**
     * Returns the radius of the radial geometry.
     * @return The radius value.
     */
    public double getRadius() {
        return radius;
    }
}
