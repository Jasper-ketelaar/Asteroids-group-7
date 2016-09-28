package nl.tudelft.asteroids.util;

import java.io.IOException;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 * Methods that are often used, providing essential utility.
 * @author Leroy Velzel, Bernard Bot, 
 * Jasper Ketelaar, Emre Ilgin, Bryan Doerga
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
	
	/**
	 * Loads an audio file based on the format and name
	 * @param format format that the audio file is in
	 * @param name of the audio file
	 * @return an Audio instance that is playable, unless an IOException was thrown, then null is returned
	 */
	public static Audio load(String format, String name) {
		try {
			return AudioLoader.getAudio(format, ResourceLoader.getResourceAsStream("resources/sfx/" + name));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
