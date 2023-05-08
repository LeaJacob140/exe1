package geometries;
import java.util.List;
import primitives.Point;
import primitives.Ray;

/**
 * This This is an interface for find the interaction wuth the geometries and the primitives
 * @author Lea and Moriya
 */
public interface Intersectable {
	/**
	 * @param ray Ray of the interaction
	 * @return list of points of the interaction
	 */
	List<Point> findIntersections(Ray ray);
}
