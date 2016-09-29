package nl.tudelft.asteroids.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import nl.tudelft.asteroids.game.states.MultiPlayState;
import nl.tudelft.asteroids.game.states.SinglePlayState;
import nl.tudelft.asteroids.util.Logger;

/**
 * Asteroids game with multiple states.
 * 
 * @author Leroy Velzel, Bernard Bot, Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public class AsteroidsGame extends StateBasedGame {

	private final static Logger LOGGER = Logger.getInstance(AsteroidsGame.class.getName());

	private final static String BACKGROUND = "BG4.jpg";

	private final boolean multiplayer;

	/**
	 * Constructor; specifying the title of the game.
	 * 
	 * @param title
	 *            Title of the game, in this case "Asteroids"
	 */
	public AsteroidsGame(String title, boolean multiplayer) {
		super(title);
		this.multiplayer = multiplayer;
	}

	/**
	 * Initializes the states of the game, and sets the background sprite.
	 */
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		Image background = new Image(BACKGROUND);
		LOGGER.log("Background image loaded");
		if (multiplayer)
			addState(new MultiPlayState(background));
		else
			addState(new SinglePlayState(background));

	}

}
