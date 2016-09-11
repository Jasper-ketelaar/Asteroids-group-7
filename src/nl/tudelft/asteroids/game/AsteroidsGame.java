package nl.tudelft.asteroids.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import nl.tudelft.asteroids.game.states.PlayState;

public class AsteroidsGame extends StateBasedGame {
	
	public AsteroidsGame(String title) {
		super(title);
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		Image background = new Image("resources/BG4.jpg");
		//addState(new MenuState(background));
		addState(new PlayState(background));
	}

}
