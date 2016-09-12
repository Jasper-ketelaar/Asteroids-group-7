package nl.tudelft.asteroids.model.entity;

import org.newdawn.slick.geom.Vector2f;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Asteroid moving around the screen, while rotating.
 * 
 * @author Bernard
 *
 */
public class Asteroid extends Entity {

	private static final float SPEED = 2f;
	private static final float ROTATION_SPEED = 1.75f;

	private static final int MAX_DEGREES = 360;

	private final Vector2f velocity;

	/**
	 * Constructor. The velocity vector is calculated.
	 * 
	 * @param 	position
	 * 			The position of the Asteroid.
	 * @param 	rotation
	 * 			The rotation of the Asteroid
	 * @throws SlickException
	 */
	public Asteroid(Vector2f position, float rotation) throws SlickException {
		super(new Image("resources/Asteroid.png"), position, rotation);

		double radian = Math.toRadians(rotation + MAX_DEGREES * new Random().nextFloat());
		float xDelta = (float) Math.cos(radian);
		float yDelta = (float) Math.sin(radian);
		Vector2f direction = new Vector2f(xDelta, yDelta);
		if (direction.length() > 0) {
			direction = direction.normalise();
		}
		velocity = new Vector2f(direction.x * SPEED, direction.y * SPEED);
	}

	/** 
	 * @return The minimal x-coordinate of the Asteroid.
	 */
	public float getMinX() {
		return super.getX();
	}

	/** 
	 * @return The maximal x-coordinate of the Asteroid.
	 */
	public float getMaxX() {
		return super.getX() + super.getSprite().getWidth();
	}

	/** 
	 * @return The minimal y-coordinate of the Asteroid.
	 */
	public float getMinY() {
		return super.getY() + super.getSprite().getHeight();
	}

	/** 
	 * @return The maximal y-coordinate of the Asteroid.
	 */
	public float getMaxY() {
		return super.getY();
	}

	/**
	 * Updates the position of the Asteroid. If an Asteroid reaches
	 * the border of the screen, it enters the screen on the opposite
	 * side.
	 * 
	 * @param gc
	 */
	public void update(GameContainer gc) {
		setRotation(getRotation() + ROTATION_SPEED);
		super.setPosition(getPosition().add(velocity));

		if (getMaxX() < 0 && getMinX() < 0) {
			setPosition(new Vector2f(gc.getWidth(), getY()));
		} else if (getMaxX() > gc.getWidth() && getMinX() > gc.getWidth()) {
			setPosition(new Vector2f(0.0f - getSprite().getWidth(), getY()));
		}

		if (getMaxY() < 0 && getMinY() < 0) {
			setPosition(new Vector2f(getX(), gc.getHeight()));
		} else if (getMaxY() > gc.getHeight() && getMinY() > gc.getHeight()) {
			setPosition(new Vector2f(getX(), 0.0f - getSprite().getHeight()));
		}

	}
}