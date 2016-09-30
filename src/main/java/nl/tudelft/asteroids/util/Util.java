package nl.tudelft.asteroids.util;

import java.io.IOException;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

import nl.tudelft.asteroids.model.entity.dyn.explodable.playable.Player;

/**
 * Methods that are often used, providing essential utility.
 * @author Leroy Velzel, Bernard Bot, 
 * Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public class Util {

	private static final String AUDIO_BASE = "sfx/";
	
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
			return AudioLoader.getAudio(format, ResourceLoader.getResourceAsStream(AUDIO_BASE + name));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Generates a random location vector based on the Players position.
	 * 
	 * @param player
	 * @param gc
	 * @return
	 */
	public static Vector2f randomLocation(Player player, GameContainer gc) {
		Random random = new Random();
		boolean playerLeft = player.getX() < gc.getWidth() / 2;
		boolean playerTop = player.getY() < gc.getHeight() / 2;
		
		float randomX = playerLeft ? random.nextFloat() * (gc.getWidth() / 2) : random.nextFloat() * (gc.getWidth() / 2) + gc.getWidth() / 2;
		float randomY = playerTop ? random.nextFloat() * (gc.getHeight() / 2) : random.nextFloat() * (gc.getHeight() / 2) + gc.getHeight() / 2;
		
		return new Vector2f(randomX, randomY);
	}

}
