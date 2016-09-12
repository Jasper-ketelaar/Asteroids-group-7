package nl.tudelft.asteroids.model.entity;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public abstract class Entity {
	
	protected static final int DEGREE_ADJUSTMENT = 90;
	
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
	
	public void setPosition(Vector2f pos) {
		this.pos = pos;
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
	
	public void render(Graphics g) {
		g.drawImage(getSprite(), getX(), getY());
	}
	
	protected int getWidth(){
		return this.getSprite().getWidth();
	}
	
	protected int getHeight(){
		return this.getSprite().getHeight();
	}
	
	protected float getMinX() {
		return getX();
	}

	protected float getMaxX() {
		return getX() + getSprite().getWidth();
	}

	protected float getMinY() {
		return getY() + getSprite().getHeight();
	}

	protected float getMaxY() {
		return getY();
	}

	public Shape getBoundingBox() {
		  return new Rectangle(this.getMinX(), this.getMinY(), this.getWidth(), this.getHeight()).transform(new Transform());
	}
	
	public boolean collide(Entity entity) {
	    if (this.getBoundingBox() == null) {
	        return false;
	    }
	    return this.getBoundingBox().intersects(entity.getBoundingBox());
	}
}