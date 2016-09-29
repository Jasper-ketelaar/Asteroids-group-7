package nl.tudelft.asteroids.model.entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import nl.tudelft.asteroids.util.Logger;

/**
 * Bullet fired by the player. Used to destroy Asteroids. Travels in a straight
 * line.
 * 
 * @author Leroy Velzel, Bernard Bot, 
 * Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public class Bullet extends Entity {
	
	private final static Logger LOGGER = Logger.getInstance(Asteroid.class.getName());

	private final Vector2f direction;
	private static float SCALE = 12;

	/**
	 * Constructor. The direction of the bullet is determined based on the 
	 * direction of the Player.
	 * 
	 * @param x
	 * @param y
	 * @param rotation
	 * @throws SlickException
	 */
	public Bullet(Vector2f location, float rotation) throws SlickException {
		super(new Image("resources/Bullet.png"), location, rotation);
		double rotationRadians = Math.toRadians(rotation - DEGREE_ADJUSTMENT);
		float xDelta = (float) Math.cos(rotationRadians);
		float yDelta = (float) Math.sin(rotationRadians);
		this.direction = new Vector2f(xDelta, yDelta).normalise().scale(SCALE);
		LOGGER.log(String.format("Bullet fired at (%dx, %dy) with a rotation of %d deg", (int) location.getX(), (int) location.getY(), (int) rotation));
	}
	
	/**
	 * @param SCALE The speed at which a bullet moves.
	 */
	public void setScale(float SCALE) {
		Bullet.SCALE = SCALE;
	}

	/**
	 * Moves the bullet.
	 */
	public void move() {
		this.setPosition(getPosition().add(direction));
	}

}