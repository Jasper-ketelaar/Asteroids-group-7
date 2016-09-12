package nl.tudelft.asteroids.model.entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public abstract class Entity {
	
	protected static final int DEGREE_ADJUSTMENT = 90;
	
	private Vector2f pos;
	private Animation sprite;
	
	public Entity(Image sprite, Vector2f pos, float rotation) {
		this.sprite = new Animation(new Image[] {sprite}, 200);
		this.sprite.getCurrentFrame().setRotation(rotation);
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
	
	public void setPosition(Vector2f pos) {
		this.pos = pos;
	}
	
	public float getRotation() {
		return sprite.getCurrentFrame().getRotation();
	}
	
	public Image getSprite() {
		return sprite.getCurrentFrame();
	}
	
	public void setRotation(float rotation) {
		sprite.getCurrentFrame().setRotation(rotation);
	}
	
	public void setAnimation(Animation animation) {
		this.sprite = animation;
	}
	
	public void render(Graphics g) {
		if (sprite != null)
			sprite.draw(getX(), getY());
	}

}
