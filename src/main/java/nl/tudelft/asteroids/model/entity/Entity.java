package nl.tudelft.asteroids.model.entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

/**
 * Abstract Class used to represent entities in the game.
 * 
 * @author Leroy Velzel, Bernard Bot, Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public abstract class Entity {

	protected static final int DEGREE_ADJUSTMENT = 90;

	private Vector2f pos;
	private Animation animation;

	/**
	 * Constructor.
	 * 
	 * @param sprite
	 *            The sprite of the Entity
	 * @param pos
	 *            The position of the Entity
	 * @param rotation
	 *            The rotation of the Entity
	 */
	public Entity(Image sprite, Vector2f pos, float rotation) {
		if (sprite != null) {
			this.animation = new Animation(new Image[] { sprite }, 200);
			this.animation.getCurrentFrame().setRotation(rotation);
		}
		this.pos = pos;
	}
	
	/**
	 * Logic for moving through screen borders
	 * @param gc
	 */
	public void positionUpdate(GameContainer gc) {
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
	 * Simple Constructor only taking a position vector.
	 * 
	 * @param pos
	 *            The position of the asteroid.
	 */
	public Entity(Vector2f pos) {
		this.pos = pos;
	}

	/**
	 * @return The x-coordinate of the Entity
	 */
	public float getX() {
		return pos.x;
	}

	/**
	 * @return The y-coordinate of the Entity
	 */
	public float getY() {
		return pos.y;
	}

	/**
	 * @return The position of the Entity
	 */
	public Vector2f getPosition() {
		return pos;
	}

	/**
	 * @param pos
	 *            The position of the Entity
	 */
	public void setPosition(Vector2f pos) {
		this.pos = pos;
	}

	/**
	 * @return The rotation of the Entity
	 */
	public float getRotation() {
		return animation.getCurrentFrame().getRotation();
	}

	/**
	 * @return The sprite of the Entity
	 */
	public Image getSprite() {
		return animation.getCurrentFrame();
	}

	/**
	 * @param rotation
	 *            The rotation of the Entity
	 */
	public void setRotation(float rotation) {
		animation.getCurrentFrame().setRotation(rotation);
	}

	/**
	 * @param animation
	 *            The animation of the sprite
	 */
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	/**
	 * @return The animation of the sprite
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * Draws Entity on specified location.
	 * 
	 * @param g
	 */
	public void render(Graphics g) {
		if (animation != null)
			animation.draw(getX(), getY());
	}

	/**
	 * @return The width of the sprite
	 */
	protected int getWidth() {
		return this.getSprite().getWidth();
	}

	/**
	 * @return The height of the sprite
	 */
	protected int getHeight() {
		return this.getSprite().getHeight();
	}

	/**
	 * @return The minimal x-coordinate of the Asteroid.
	 */
	protected float getMinX() {
		return getX();
	}

	/**
	 * @return The maximal x-coordinate of the Asteroid.
	 */
	protected float getMaxX() {
		return getX() + getSprite().getWidth();
	}

	/**
	 * @return The minimal y-coordinate of the Asteroid.
	 */
	protected float getMinY() {
		return getY() + getSprite().getHeight();
	}

	/**
	 * @return The maximal y-coordinate of the Asteroid.
	 */
	protected float getMaxY() {
		return getY();
	}

	/**
	 * Creates a bounding box based on the height and width of the sprite
	 * 
	 * @return The ellipse shaped bounding box of the asteroid.
	 */
	public Shape getBoundingBox() {
		final float cX = getX() + getSprite().getWidth() / 2;
		final float cY = getY() + getSprite().getHeight() / 2;
		final float xRad = getWidth() / 2;
		final float yRad = getHeight() / 2;

		return new Ellipse(cX, cY, xRad, yRad);
	}

	/**
	 * Generate a direction vector based on the rotation of the Entity.
	 * 
	 * @param rotation
	 *            The rotation of the Entity
	 * @return direction vector
	 */
	public static Vector2f generateDirection(float rotation, int DEGREE_ADJUSTMENT, float SCALE) {
		double rotationRadians = Math.toRadians(rotation - DEGREE_ADJUSTMENT);
		float xDelta = (float) Math.cos(rotationRadians);
		float yDelta = (float) Math.sin(rotationRadians);
		return new Vector2f(xDelta, yDelta).normalise().scale(SCALE);
	}

	/**
	 * Handles collision between Entities.
	 * 
	 * @param entity
	 *            The other Entity
	 * @return True if the sprite collides
	 */
	public boolean collide(Entity entity) {
		return (this.getBoundingBox() != null) && this.getBoundingBox().intersects(entity.getBoundingBox());
	}
}