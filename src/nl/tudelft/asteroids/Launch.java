package nl.tudelft.asteroids;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import nl.tudelft.asteroids.game.AsteroidsGame;

/**
 * Launcher for the game Asteroids.
 * Defines the screen height and width, and sets the target frame rate.
 * 
 * @author Bernard
 *
 */
public class Launch {
	
	/**
	 * Main method for launching the game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new AsteroidsGame("Asteroids"));
			appgc.setDisplayMode(1200, 700, false);
			appgc.setTargetFrameRate(100);
			appgc.start();
		} catch (SlickException ex) {
			ex.printStackTrace();
		}
	}

}
