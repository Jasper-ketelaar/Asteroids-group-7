package nl.tudelft.asteroids.controller;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

import nl.tudelft.asteroids.model.entity.Player;

public class PlayerController {
	
	private static final float SPEED = 1.05f;

	private final Player player;
	private long moveStart = 0;
	private Vector2f velocity = new Vector2f(0, 0);

	public PlayerController(Player player) {
		this.player = player;
	}

	//Messing around with movement
	public void move(Input input) {
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			player.setRotation(player.getRotation() + 1.75f);
			if (input.isKeyDown(Input.KEY_UP)) {
				
			}
		} else if (input.isKeyDown(Input.KEY_LEFT)) {
			player.setRotation(player.getRotation() - 1.75f);
		}

		double rotation = Math.toRadians(player.getRotation() - 90);
		float xDelta = (float) Math.cos(rotation);
		float yDelta = (float) Math.sin(rotation);
		Vector2f direction = new Vector2f(xDelta, yDelta);
		if (direction.length() > 0) {
			direction = direction.normalise();
		}
		
		if (moveStart == 0 && input.isKeyDown(Input.KEY_UP)) {
			moveStart = System.currentTimeMillis();
			velocity = direction;
		} else if (!input.isKeyDown(Input.KEY_UP)){
			moveStart = 0;
		}
		
		if (moveStart > 0 && velocity.length() < 10) {
			velocity = velocity.scale(SPEED);
		} else {
			
			if (velocity.length() >= .01f) {
				velocity.set(velocity.x / (SPEED - .02f), velocity.y / (SPEED - .02f));
		
			} else {
				velocity = new Vector2f(0, 0);
			}
		}
		//player.update(velocity);
	}

	public void render(Graphics graphics) {
		graphics.drawImage(player.getSprite(), player.getX(), player.getY());
	}

}
