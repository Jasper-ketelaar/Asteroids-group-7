package nl.tudelft.asteroids.factory;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

import nl.tudelft.asteroids.model.entity.stat.PowerUp;
import nl.tudelft.asteroids.model.entity.stat.PowerUp.PowerupType;

public class PowerupFactory {

	private final Random random = new Random();

	private long last = 0;
	private float build = 0;

	public PowerUp create(GameContainer gc) {
		Vector2f location = new Vector2f(random.nextFloat() * gc.getWidth(), random.nextFloat() * gc.getHeight());
		PowerupType type = PowerupType.values()[(int) Math.ceil(random.nextDouble() * 2)];
		System.out.println((int) Math.ceil(random.nextDouble() * 2));
		PowerUp powerup = new PowerUp(location, type);

		last = System.currentTimeMillis();
		build = 0;

		return powerup;
	}

	public boolean requiresPowerup() {
		long time = System.currentTimeMillis() - last;
		if (last != 0 && time > 20000) {
			return true;
		} else {
			float rand = random.nextFloat();
			if (rand > (0.99999 - build)) {
				return true;
			} else {
				build += 0.000001f;
			}
		}
		return false;
	}

}