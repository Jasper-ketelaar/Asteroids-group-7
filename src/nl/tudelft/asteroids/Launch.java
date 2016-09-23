package nl.tudelft.asteroids;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import nl.tudelft.asteroids.game.AsteroidsGame;
import nl.tudelft.asteroids.util.Logger;
import nl.tudelft.asteroids.util.Logger.Level;

/**
 * Launcher for the game Asteroids.
 * Defines the screen height and width, and sets the target frame rate.
 * 
 * @author Leroy Velzel, Bernard Bot, 
 * Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public class Launch {
	
	private final static Logger LOGGER = Logger.getInstance(Launch.class.getName()); 
	
	/**
	 * Main method for launching the game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LOGGER.registerOutput(System.out);
		try {
			File file = new File(String.format("game%d.log", (int) System.currentTimeMillis()));
			if (!file.exists()) {
				file.createNewFile();
			}
			LOGGER.registerOutput(new FileOutputStream("game.log"));
		} catch (FileNotFoundException e) {
			LOGGER.log("FileNotFoundException thrown", Level.ERROR, true);
		} catch (IOException e) {
			LOGGER.log("IOException thrown", Level.ERROR, true);
		}
		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new AsteroidsGame("Asteroids"));
			appgc.setDisplayMode(1200, 700, false);
			appgc.setTargetFrameRate(100);
			LOGGER.log("Game container fired up", Level.INFO, true);
			appgc.start();
			
		} catch (SlickException ex) {
			LOGGER.log("SlickException thrown", Level.ERROR, true);
		}
	}

}
