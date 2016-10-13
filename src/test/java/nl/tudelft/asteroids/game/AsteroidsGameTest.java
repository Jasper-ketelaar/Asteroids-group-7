package nl.tudelft.asteroids.game;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class AsteroidsGameTest {

	
	private AsteroidsGame testGame;
	private GameContainer gc;
	
	@Test
	public void setup(){
		testGame = new AsteroidsGame("Asteroids", true);
		gc = Mockito.mock(GameContainer.class);
	}
	
	@Test
	public void testInitStateList() throws SlickException{
		
	}
}
