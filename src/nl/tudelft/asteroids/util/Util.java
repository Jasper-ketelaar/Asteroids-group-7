package nl.tudelft.asteroids.util;

import org.newdawn.slick.geom.Vector2f;

public class Util {

	
	public static Vector2f decompose(double radian) {
		return new Vector2f((float) Math.cos(radian), (float) Math.sin(radian));
	}
	
}
