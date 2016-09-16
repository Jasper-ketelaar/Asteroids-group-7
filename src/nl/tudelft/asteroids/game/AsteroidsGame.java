package nl.tudelft.asteroids.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import nl.tudelft.asteroids.game.states.PlayState;

/**
 * Asteroids game with multiple states.
 * @author Leroy Velzel, Bernard Bot, 
 * Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public class AsteroidsGame extends StateBasedGame {
	
	/**
	 * Constructor; specifying the title of the game.
	 * 
	 * @param	title 
	 * 			Title of the game, in this case "Asteroids"
	 */
	public AsteroidsGame(String title) {
		super(title);
	}
	
	/**
	 * Initializes the states of the game, and sets the background sprite.
	 */
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		Image background = new Image("resources/BG4.jpg");
		//addState(new MenuState(background));
		addState(new PlayState(background));
	}

}
