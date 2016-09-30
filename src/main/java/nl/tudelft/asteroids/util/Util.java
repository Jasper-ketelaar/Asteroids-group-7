package nl.tudelft.asteroids.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

import nl.tudelft.asteroids.game.states.DefaultPlayState;
import nl.tudelft.asteroids.model.entity.dyn.explodable.playable.Player;
import nl.tudelft.asteroids.util.Logger.Level;

/**
 * Methods that are often used, providing essential utility.
 * 
 * @author Leroy Velzel, Bernard Bot, Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public class Util {

	private final static String AUDIO_BASE = "sfx/";

	protected final static Logger LOGGER = Logger.getInstance(DefaultPlayState.class.getName());

	private final static Map<String, Audio> AUDIO_CACHE = new HashMap<String, Audio>();

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
	 * 
	 * @param format
	 *            format that the audio file is in
	 * @param name
	 *            of the audio file
	 * @return an Audio instance that is playable, unless an IOException was
	 *         thrown, then null is returned
	 */
	public static Audio loadAudio(String name) {
		if (AUDIO_CACHE.containsKey(name)) {
			return AUDIO_CACHE.get(name);
		} else {
			System.out.println(name);
			String format = name.split("\\.")[1].toUpperCase();
			try {
				AUDIO_CACHE.put(name, AudioLoader.getAudio(format, ResourceLoader.getResourceAsStream(AUDIO_BASE + name)));
				return AUDIO_CACHE.get(name);
			} catch (IOException e) {
				LOGGER.log("IOException thrown audio file not found", Level.ERROR, true);
			}
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

		float randomX = playerLeft ? random.nextFloat() * (gc.getWidth() / 2)
				: random.nextFloat() * (gc.getWidth() / 2) + gc.getWidth() / 2;
		float randomY = playerTop ? random.nextFloat() * (gc.getHeight() / 2)
				: random.nextFloat() * (gc.getHeight() / 2) + gc.getHeight() / 2;

		return new Vector2f(randomX, randomY);
	}

}
