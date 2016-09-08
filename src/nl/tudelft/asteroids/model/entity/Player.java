package nl.tudelft.asteroids.model.entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends Entity {
	
	public Player(float x, float y, float rotation) throws SlickException {
		super(new Image("resources/F-45A a.png"), x, y, rotation);
	}
	

}
