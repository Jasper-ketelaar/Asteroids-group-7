package nl.tudelft.asteroids.model.entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Player extends Entity {
	
	public Player(Vector2f position, float rotation) throws SlickException {
		super(new Image("resources/Plane.png").getScaledCopy(0.3f), position, rotation);
	}
	
	
}
