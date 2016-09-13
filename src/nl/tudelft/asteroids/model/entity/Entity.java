package nl.tudelft.asteroids.model.entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

/**
 * Abstract Class used to represent entities in the game.
 * 
 * @author Bernard
 *
 */
public abstract class Entity {

	protected static final int DEGREE_ADJUSTMENT = 90;

	private Vector2f pos;
	private Animation sprite;

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
			this.sprite = new Animation(new Image[] {sprite}, 200);
			this.sprite.getCurrentFrame().setRotation(rotation);
		}
		this.pos = pos;

	}
	
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
	 * @param pos The position of the Entity
	 */
	public void setPosition(Vector2f pos) {
		this.pos = pos;
	}

	/**
	 * @return The rotation of the Entity
	 */
	public float getRotation() {
		return sprite.getCurrentFrame().getRotation();
	}

	/**
	 * @return The sprite of the Entity
	 */
	public Image getSprite() {
		return sprite.getCurrentFrame();
	}

	/**
	 * @param The rotation of the Entity
	 */
	public void setRotation(float rotation) {
		sprite.getCurrentFrame().setRotation(rotation);
	}
	
	public void setAnimation(Animation animation) {
		this.sprite = animation;
	}

	public Animation getAnimation() {
		return sprite;
	}
	
	/**
	 * Draws Entity on specified location.
	 * 
	 * @param g
	 */
	public void render(Graphics g) {
		if (sprite != null)
			sprite.draw(getX(), getY());
	}
	
	
	protected int getWidth(){
		return this.getSprite().getWidth();
	}
	
	protected int getHeight(){
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

	public Shape getBoundingBox() {
		  return new Rectangle(getMinX(), getMinY(), getWidth(), getHeight()).transform(new Transform());
	}
	
	public boolean collide(Entity entity) {
	    if (this.getBoundingBox() == null) {
	        return false;
	    }
	    return this.getBoundingBox().intersects(entity.getBoundingBox());
	}
}