package nl.tudelft.asteroids.model.entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 * Bullet fired by the player.
 * Used to destroy Asteroids. Travels in a straight line.
 * 
 * @author Bernard
 *
 */
public class Bullet extends Entity{
	
	private final Vector2f direction;
	private static final float SCALE = 12;
	
	/**
	 * Constructor. The direction of the bullet is determined based direction
	 * of the Player.
	 * 
	 * @param x
	 * @param y
	 * @param rotation
	 * @throws SlickException
	 */
	public Bullet(Vector2f location, float rotation) throws SlickException {
		super(new Image("resources/Bullet.png"), location, rotation, null);
		
		double rotationRadians = Math.toRadians(rotation - DEGREE_ADJUSTMENT);
		float xDelta = (float) Math.cos(rotationRadians);
		float yDelta = (float) Math.sin(rotationRadians);
		this.direction = new Vector2f(xDelta, yDelta).normalise().scale(SCALE);
	}
	
	/**
	 * Moves the bullet.
	 */
	public void move() {
		this.setPosition(getPosition().add(direction));
	}
	
}