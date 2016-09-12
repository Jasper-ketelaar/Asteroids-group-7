package nl.tudelft.asteroids.model.entity;

import org.newdawn.slick.geom.Vector2f;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Asteroid extends Entity {

	private static final float SPEED = 2f;
	private static final float ROTATION_SPEED = 1.75f;

	private static final int MAX_DEGREES = 360;
	
	private final Animation explosion;
	private final Vector2f velocity;

	public Asteroid(Vector2f position, float rotation) throws SlickException {
		super(new Image("resources/Asteroid.png"), position, rotation);
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
		explosion = new Animation(sprites, 50);
		explosion.setLooping(false);
		
		
		double radian = Math.toRadians(rotation + MAX_DEGREES * new Random().nextFloat());
		float xDelta = (float) Math.cos(radian);
		float yDelta = (float) Math.sin(radian);
		Vector2f direction = new Vector2f(xDelta, yDelta);
		if (direction.length() > 0) {
			direction = direction.normalise();
		}
		velocity = new Vector2f(direction.x * SPEED, direction.y * SPEED);
	}

	public float getMinX() {
		return super.getX();
	}

	public float getMaxX() {
		return super.getX() + super.getSprite().getWidth();
	}

	public float getMinY() {
		return super.getY() + super.getSprite().getHeight();
	}

	public float getMaxY() {
		return super.getY();
	}

	public void update(GameContainer gc) {
		if (explosion.isStopped()) {
			
		}
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
	
	public void playExplosion() {
		setAnimation(explosion);
	}
	
	public Animation getExplosion() {
		return explosion;
	}
	
}