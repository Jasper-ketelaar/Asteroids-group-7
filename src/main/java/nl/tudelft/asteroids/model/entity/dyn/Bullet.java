package nl.tudelft.asteroids.model.entity.dyn;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import nl.tudelft.asteroids.model.entity.Entity;
import nl.tudelft.asteroids.model.entity.dyn.explodable.Asteroid;
import nl.tudelft.asteroids.util.Logger;
import nl.tudelft.asteroids.util.Util;

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
	
	private final static String BULLET = "Bullet.png";

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
		super(new Image(BULLET), location, rotation);

		this.direction = Util.generateDirection(rotation, DEGREE_ADJUSTMENT, SCALE);
		
		LOGGER.log(String.format("Bullet fired at (%dx, %dy) with a rotation of %d deg", (int) location.getX(), (int) location.getY(), (int) rotation));
	}
	
	/**
	 * @param SCALE The speed at which a bullet moves.
	 */
	public void setScale(float SCALE) {
		Bullet.SCALE = SCALE;
	}
	
	/**
	 * Return the SCALE of the bullet.
	 * @return SCALE
	 */
	public float getScale(){
		return Bullet.SCALE;
	}

	/**
	 * Moves the bullet.
	 */
	public void move() {
		super.setPosition(getPosition().add(direction));
	}

}