package nl.tudelft.asteroids.model.entity;

import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * Asteroid moving around the screen, while rotating.
 * 
 * @author Bernard
 *
 */
public class Asteroid extends ExplodableEntity {

	private static final float SPEED = 2f;
	private static final float ROTATION_SPEED = 1.75f;

	private static final int MAX_DEGREES = 360;
	
	private final Vector2f velocity;

	private int size;
	/**
	 * Constructor. The velocity vector is calculated.
	 * 
	 * @param 	position
	 * 			The position of the Asteroid.
	 * @param 	rotation
	 * 			The rotation of the Asteroid
	 * @throws SlickException
	 */
	public Asteroid(Vector2f position, float rotation, int size) throws SlickException {
		super(new Image(String.format("resources/asteroid/asteroid_%d.png", size)),position, rotation);		
		this.size = size;

		Image[] sprites = new Image[] {
				new Image("resources/asteroid/Explosion-1.png"),
				new Image("resources/asteroid/Explosion-2.png"),
				new Image("resources/asteroid/Explosion-3.png"),
				new Image("resources/asteroid/Explosion-4.png"),
				new Image("resources/asteroid/Explosion-5.png"),
				new Image("resources/asteroid/Explosion-6.png"),
				new Image("resources/asteroid/Explosion-7.png"),
				new Image("resources/asteroid/Explosion-8.png")
		};
		
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
			setPosition(new Vector2f(0.0f - getWidth(), getY()));
		}

		if (getMaxY() < 0 && getMinY() < 0) {
			setPosition(new Vector2f(getX(), gc.getHeight()));
		} else if (getMaxY() > gc.getHeight() && getMinY() > gc.getHeight()) {
			setPosition(new Vector2f(getX(), 0.0f - getHeight()));
		}

	}
	
	@Override
	public Shape getBoundingBox() {
		final float cX = getX() + getSprite().getWidth() / 2;
		final float cY = getY() + getSprite().getHeight() / 2;
		final float xRad = super.getWidth() / 2;
		final float yRad = super.getHeight() / 2;
		
		return new Ellipse(cX, cY, xRad, yRad).transform(new Transform());
	}

	
	public void destroyAsteroid(ListIterator<Asteroid> asteroids) throws SlickException {
		
		if(size == 3){
			playExplosion();
			return;
		}
		else {
			
			float newRot =  MAX_DEGREES * new Random().nextFloat();
			asteroids.add(new Asteroid(new Vector2f(getX(), getY()), newRot , size+1));
			asteroids.add(new Asteroid(new Vector2f(getX(), getY()), newRot - 180 , size+1));
			playExplosion();
		}
		
	}
	
	
}