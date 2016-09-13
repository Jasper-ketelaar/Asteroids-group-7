package nl.tudelft.asteroids.util;

import org.newdawn.slick.geom.Vector2f;

/**
 * Methods that are often used, providing essential utility.
 * @author Bernard
 *
 */
public class Util {

	/**
	 * Decomposes a radian to a 2D vector.
	 * 
	 * @param radian
	 * @return
	 */
	public static Vector2f decompose(double radian) {
		return new Vector2f((float) Math.cos(radian), (float) Math.sin(radian));
	}

}
