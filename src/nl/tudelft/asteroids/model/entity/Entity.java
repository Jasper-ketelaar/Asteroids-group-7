package nl.tudelft.asteroids.model.entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public abstract class Entity {
	
	private Vector2f pos;
	private Image sprite;
	
	public Entity(Image sprite, Vector2f pos, float rotation) {
		this.sprite = sprite;
		this.sprite.setRotation(rotation);
		this.pos = pos;
	}
	
	public float getX() {
		return pos.x;
	}
	
	public float getY() {
		return pos.y;
	}
	
	public Vector2f getPosition() {
		return pos;
	}
	
	public float getRotation() {
		return sprite.getRotation();
	}
	
	public Image getSprite() {
		return sprite;
	}
	
	public void setRotation(float rotation) {
		sprite.setRotation(rotation);
	}

}
