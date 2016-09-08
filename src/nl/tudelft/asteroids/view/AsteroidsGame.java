package nl.tudelft.asteroids.view;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import nl.tudelft.asteroids.controller.PlayerController;
import nl.tudelft.asteroids.model.entity.Player;

public class AsteroidsGame extends BasicGame {

	private Player player;
	private PlayerController playerController;
	
	public AsteroidsGame(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		playerController.render(g);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		this.player = new Player(300, 300, 0);
		this.playerController = new PlayerController(player);
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		playerController.move(gc.getInput());
	}

}
