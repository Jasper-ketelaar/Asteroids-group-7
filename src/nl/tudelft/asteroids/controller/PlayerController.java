package nl.tudelft.asteroids.controller;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import nl.tudelft.asteroids.model.entity.Player;

public class PlayerController {
	
	private final Player player;
	
	public PlayerController(Player player) {
		this.player = player;
	}
	
	public void move(Input input) {
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			player.setRotation(player.getRotation() + 1.75f);
		} 
	}
	
	public void render(Graphics graphics) {
		graphics.drawImage(player.getSprite(), player.getX(), player.getY());
	}

}
