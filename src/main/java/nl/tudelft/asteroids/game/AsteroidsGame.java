package nl.tudelft.asteroids.game;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import nl.tudelft.asteroids.game.states.MenuState;
import nl.tudelft.asteroids.game.states.NormalPlayState;
import nl.tudelft.asteroids.game.states.RampagePlayState;
import nl.tudelft.asteroids.util.Logger;
import nl.tudelft.asteroids.util.Logger.Level;

/**
 * Asteroids game with multiple states.
 * 
 * @author Leroy Velzel, Bernard Bot, Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public class AsteroidsGame extends StateBasedGame {

	public final static int STATE_MENU_MAIN = 0;
	public final static int STATE_PLAY = 1;
	public final static int STATE_RAMPAGE = 2;
	
	private final static String AUDIO_BASE = "sfx/";
	private final static Map<String, Audio> AUDIO_CACHE = new HashMap<String, Audio>();
	
	private final static Logger LOGGER = Logger.getInstance(AsteroidsGame.class.getName());

	private final static String BACKGROUND = "BG4.jpg";
	public static Image background;


	/**
	 * Constructor; specifying the title of the game.
	 * 
	 * @param title
	 *            Title of the game, in this case "Asteroids"
	 */
	public AsteroidsGame(String title) {
		super(title);
	}

	/**
	 * Initializes the states of the game, and sets the background sprite.
	 * Checks if a multi player or single player game (PlayState) needs to bes
	 * started.
	 */
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		this.background = new Image(BACKGROUND);
		
		LOGGER.log("Background image loaded");
		
		addState(new MenuState());
		addState(new NormalPlayState());
		addState(new RampagePlayState());
	}
	
	
	/**
	 * Loads an audio file based on the format and name
	 * 
	 * @param format
	 *            format that the audio file is in
	 * @param name
	 *            of the audio file
	 * @return an Audio instance that is playable, unless an IOException was
	 *         thrown, then null is returned
	 */
	public static Audio loadAudio(String name) {
		if (AUDIO_CACHE.containsKey(name)) {
			return AUDIO_CACHE.get(name);
		} else {
			System.out.println(name);
			String format = name.split("\\.")[1].toUpperCase();
			try {
				AUDIO_CACHE.put(name, AudioLoader.getAudio(format, ResourceLoader.getResourceAsStream(AUDIO_BASE + name)));
				return AUDIO_CACHE.get(name);
			} catch (IOException e) {
				LOGGER.log("IOException thrown audio file not found", Level.ERROR, true);
			}
		}
		return null;
	}

}
