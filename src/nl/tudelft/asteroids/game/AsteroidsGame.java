package nl.tudelft.asteroids.game;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

import nl.tudelft.asteroids.controller.PlayerController;
import nl.tudelft.asteroids.game.states.MenuState;
import nl.tudelft.asteroids.game.states.PlayState;
import nl.tudelft.asteroids.model.entity.Player;

public class AsteroidsGame extends StateBasedGame {

	private Player player;
	private PlayerController playerController;
	
	public AsteroidsGame(String title) {
		super(title);
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		Image background = new Image("resources/BG4.jpg");
		addState(new MenuState(background));
		addState(new PlayState(background));
	}

}
