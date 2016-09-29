package nl.tudelft.asteroids.game;

import org.junit.Test;

import nl.tudelft.asteroids.Launch;

public class AsteroidsGameTest {

	
	private Launch launcher;
	private AsteroidsGame testGame;
	
	@Test
	public void setup(){
		System.out.println("test1");
		launcher = new Launch();
		System.out.println("test2");
		launcher.launch();
		System.out.println("test3");
		testGame = launcher.getAsteroidsGame();
		System.out.println("test4");
	}
}
