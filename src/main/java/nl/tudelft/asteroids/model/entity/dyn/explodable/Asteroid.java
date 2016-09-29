package nl.tudelft.asteroids.model.entity.dyn.explodable;

import org.newdawn.slick.geom.Vector2f;

import nl.tudelft.asteroids.util.Logger;

import java.util.ListIterator;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Asteroid moving around the screen, while rotating.
 * 
 * @author Leroy Velzel, Bernard Bot, Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public class Asteroid extends ExplodableEntity {

	private final static Logger LOGGER = Logger.getInstance(Asteroid.class.getName());

	private static final float SPEED = 2f;
	private static final float ROTATION_SPEED = 1.75f;

	private static final int MAX_DEGREES = 360;
	private static final int BASE_POINTS = 100;

	private final Vector2f velocity;

	private int size;

	/**
	 * Constructor. The velocity vector is calculated.
	 * 
	 * @param position
	 *            The position of the Asteroid.
	 * @param rotation
	 *            The rotation of the Asteroid
	 * @throws SlickException
	 */
	public Asteroid(Vector2f position, float rotation, int size) throws SlickException {
		super(new Image(String.format("resources/asteroid/asteroid_%d.png", size)), position, rotation, size);
		this.size = size;

		double radian = Math.toRadians(rotation + MAX_DEGREES * new Random().nextFloat());
		float xDelta = (float) Math.cos(radian);
		float yDelta = (float) Math.sin(radian);
		Vector2f direction = new Vector2f(xDelta, yDelta);
		if (direction.length() > 0) {
			direction = direction.normalise();
		}
		velocity = new Vector2f(direction.x * SPEED, direction.y * SPEED);
		LOGGER.log(String.format("Asteroid spawned in at: (%dx, %dy) with %d deg as rotation and size %d",
				(int) position.getX(), (int) position.getY(), (int) rotation, size));
	}

	/**
	 * Updates the position of the Asteroid. If an Asteroid reaches the border
	 * of the screen, it enters the screen on the opposite side.
	 * 
	 * @param gc
	 */
	public void update(GameContainer gc) {
		setRotation(getRotation() + ROTATION_SPEED);
		super.setPosition(getPosition().add(velocity));
		if (getMaxX() < 0 && getMinX() < 0) {
			setPosition(new Vector2f(gc.getWidth(), getY()));
		} else if (getMaxX() > gc.getWidth() && getMinX() > gc.getWidth()) {
			setPosition(new Vector2f(0.0f - getWidth(), getY()));
		}

		if (getMaxY() < 0 && getMinY() < 0) {
			setPosition(new Vector2f(getX(), gc.getHeight()));
		} else if (getMaxY() > gc.getHeight() && getMinY() > gc.getHeight()) {
			setPosition(new Vector2f(getX(), 0.0f - getHeight()));
		}
	}

	/**
	 * Destroys or split an asteroid, used when an asteroid is hit by a bullet.
	 * 
	 * @param asteroids
	 *            The list containing the asteroids
	 * @throws SlickException
	 */
	public void splitAsteroid(ListIterator<Asteroid> asteroids) throws SlickException {
		if (size == 3) {
			playExplosion();
			return;
		} else {
			float newRot = MAX_DEGREES * new Random().nextFloat();
			asteroids.add(new Asteroid(new Vector2f(getX(), getY()), newRot, size + 1));
			asteroids.add(new Asteroid(new Vector2f(getX(), getY()), Math.abs(newRot - 180), size + 1));
			playExplosion();
		}
	}

	/**
	 * @return The base points of an Asteroid multiplied by the size
	 */
	public int getPoints() {
		return size * BASE_POINTS;
	}

}