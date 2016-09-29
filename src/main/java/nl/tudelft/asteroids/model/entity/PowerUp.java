package nl.tudelft.asteroids.model.entity;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

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
	 * The time in milliseconds that the power up stays on the screen and is
	 * active when picked up.
	 */
	public final long creationDuration = 30_000;
	public final long pickupDuration = 20_000;
	
	private final Type type;
	
	
	/**
	 * Constructor. The rotation of the power up is set to 0, because the
	 * rotation doesn't matter. The type of the power up is 
	 * 
	 * @param sprite
	 * @param pos
	 * @throws SlickException 
	 */
	public PowerUp(Vector2f pos) throws SlickException {
		super(new Image("resources/PowerUp.png"), pos, 0); //TODO: set sprite to according power up
		this.creationTime = System.currentTimeMillis();
		this.type = Type.values()[new Random().nextInt(Type.values().length)];
	}
	
	/**
	 * @return The type of the power up.
	 */
	public Type getType() {
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
	
	/**
	 * The different types of power ups.
	 */
	public enum Type {
		BULLET, POINTS, INVINCIBILITY
	}

}
