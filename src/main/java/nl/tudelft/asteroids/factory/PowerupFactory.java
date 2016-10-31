package nl.tudelft.asteroids.factory;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

import nl.tudelft.asteroids.model.entity.stat.PowerUp;
import nl.tudelft.asteroids.model.entity.stat.PowerUp.PowerupType;

/**
 * Factory that creates PowerUps
 * 
 * @author Leroy Velzel, Bernard Bot, Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public class PowerupFactory {

	private final Random random = new Random();

	private long last = 0;
	private float build = 0;

	/**
	 * Method for creating a PowerUp.
	 * 
	 * @param gc
	 *            GameContainer from slick2d
	 * @return Newly created PowerUp
	 */
	public PowerUp create(GameContainer gc) {
		Vector2f location = new Vector2f(random.nextFloat() * gc.getWidth(), random.nextFloat() * gc.getHeight());
		PowerupType type = PowerupType.values()[random.nextInt(3)];
		PowerUp powerup = new PowerUp(location, type);

		last = System.currentTimeMillis();
		build = 0;

		return powerup;
	}

	/**
	 * Algorithm to check if a new PowerUp needs to be spawned.
	 * 
	 * @return Boolean indicating if a new PowerUp needs to be spawned
	 */
	public boolean requiresPowerup(int difficulty) {
		long time = System.currentTimeMillis() - last;
		build += 0.000001f;
		return (last != 0 && time > 15000 || random.nextFloat() > (0.99999 - build));
	}
}
