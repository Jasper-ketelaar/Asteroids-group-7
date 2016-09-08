package nl.tudelft.asteroids.model.entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public abstract class Entity {
	
	private float x;
	private float y;
	private Image sprite;
	
	public Entity(Image sprite, float x, float y, float rotation) {
		this.sprite = sprite;
		this.sprite.setRotation(rotation);
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
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
	
	public void update(Vector2f direction) {
		this.x += direction.x;
		this.y += direction.y;
	}

}
