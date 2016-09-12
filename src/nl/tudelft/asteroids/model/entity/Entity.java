package nl.tudelft.asteroids.model.entity;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public abstract class Entity {
	
	protected static final int DEGREE_ADJUSTMENT = 90;
	
	private Vector2f pos;
	private Image sprite;
	private Shape box;
	
	public Entity(Image sprite, Vector2f pos, float rotation, Shape box) {
		this.sprite = sprite;
		this.sprite.setRotation(rotation);
		this.pos = pos;
		this.box = box;
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
	
	public float getMaxXShape(){
		return box.getMaxX();
	}
	
	public Shape getBox() {
		return this.box;
	}
	
	public float getMaxYShape(){
		return box.getMaxY();
	}
	
	public float getMinXShape(){
		return box.getMinX();
	}
	
	public float getMinYShape(){
		return box.getMinY();
	}

}