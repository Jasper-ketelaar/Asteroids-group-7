package nl.tudelft.asteroids.model.entity.stat;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import nl.tudelft.asteroids.model.entity.Entity;

/**
 * Abstract power up class from which is extended by concrete power ups.
 * 
 * @author Leroy Velzel, Bernard Bot, Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public class PowerUp extends Entity {

	/**
	 * The pickup time and creation time of the Entity.
	 */
	private long pickupTime;
	private long creationTime;

	/**
	 * The time in milliseconds that the power up stays on the screen
	 */
	public static final long DISAPPEAR_AFTER = 10000;

	private final PowerupType type;

	/**
	 * Constructor. The rotation of the power up is set to 0, because the
	 * rotation doesn't matter. The type of the power up is
	 * 
	 * @param sprite
	 * @param pos
	 * @throws SlickException
	 */
	public PowerUp(Vector2f pos, PowerupType type) {
		super(null, pos, 0);
		this.creationTime = System.currentTimeMillis();
		this.type = type;
	}

	/**
	 * @return The type of the power up.
	 */
	public PowerupType getType() {
		return type;
	}

	/**
	 * @param pickupTime
	 *            The time on which a power up is picked up
	 */
	public void setPickupTime() {
		this.pickupTime = System.currentTimeMillis();
	}

	/**
	 * @return The elapsed time since the power up was picked up.
	 */
	public long pickupTimeElapsed() {
		return System.currentTimeMillis() - pickupTime;
	}

	/**
	 * @return The elapsed time since the power up was created.
	 */
	public long creationTimeElapsed() {
		return System.currentTimeMillis() - creationTime;
	}

	@Override
	public Shape getBoundingBox() {
		return new Ellipse(getX(), getY(), 10, 10);
	}

	@Override
	public void render(Graphics g) {
		Color start = g.getColor();

		g.setColor(getType().getColor());
		g.setAntiAlias(true);
		g.fill(getBoundingBox());

		g.setColor(start);
	}

	/**
	 * The different types of power ups.
	 */
	public enum PowerupType {
		BULLET(Color.red, 10000), POINTS(Color.green, 8000), INVINCIBILITY(Color.yellow, 5000);

		private final Color color;
		private final long duration;

		private PowerupType(Color color, long duration) {
			this.color = color;
			this.duration = duration;
		}

		public Color getColor() {
			return color;
		}

		public long getDuration() {
			return duration;
		}
	}

}