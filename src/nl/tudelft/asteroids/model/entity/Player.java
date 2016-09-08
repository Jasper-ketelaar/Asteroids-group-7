package nl.tudelft.asteroids.model.entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends Entity {
	
	public Player(float x, float y, float rotation) throws SlickException {
		super(new Image("resources/Plane.png").getScaledCopy(0.3f), x, y, rotation);
	}
	

}
