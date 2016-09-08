package nl.tudelft.asteroids;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import nl.tudelft.asteroids.view.AsteroidsGame;

public class Launch {

	public static void main(String[] args) {
		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new AsteroidsGame("Asteroids"));
			appgc.setDisplayMode(1200, 800, false);
			appgc.setTargetFrameRate(60);
			appgc.start();
		} catch (SlickException ex) {
			ex.printStackTrace();
		}
	}

}
