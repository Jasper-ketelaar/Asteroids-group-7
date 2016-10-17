package nl.tudelft.asteroids.game.states;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import nl.tudelft.asteroids.TestWithDisplay;
import nl.tudelft.asteroids.model.entity.dyn.explodable.playable.Player;

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class SinglePlayStateTest extends TestWithDisplay {

	
	private SinglePlayState playstateTest;
	private Player testPlayer;
	
	
	
	@Before
	public void setup() throws SlickException{
		Image background = new Image("BG4.jpg");
		testPlayer = Mockito.mock(Player.class);
		playstateTest = new SinglePlayState(background, testPlayer);
	}
	
	
	@Test
	public void testInit() throws SlickException {

	}
	

}
