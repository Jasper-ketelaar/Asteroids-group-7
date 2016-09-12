package nl.tudelft.asteroids.model.entity;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
	private Image sprite;

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
		this.sprite = sprite;
		this.sprite.setRotation(rotation);
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
		return sprite.getRotation();
	}

	/**
	 * @return The sprite of the Entity
	 */
	public Image getSprite() {
		return sprite;
	}

	/**
	 * @param The rotation of the Entity
	 */
	public void setRotation(float rotation) {
		sprite.setRotation(rotation);
	}

	/**
	 * Draws Entity on specified location.
	 * 
	 * @param g
	 */
	public void render(Graphics g) {
		g.drawImage(getSprite(), getX(), getY());
	}

}
