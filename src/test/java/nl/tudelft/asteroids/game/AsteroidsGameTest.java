package nl.tudelft.asteroids.game;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import nl.tudelft.asteroids.TestWithDisplay;
import nl.tudelft.asteroids.game.states.NormalPlayState;

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class AsteroidsGameTest extends TestWithDisplay{

	private AsteroidsGame testGame1;
	private GameContainer gc;
	
	@Before
	public void setup() throws SlickException {
		testGame1 = new AsteroidsGame("Asteroids");
		gc = Mockito.mock(GameContainer.class);
		testGame1.initStatesList(gc);
	}
	
	@Test
	public void testInitStateList1() throws SlickException{
		assertEquals(NormalPlayState.class , testGame1.getState(AsteroidsGame.STATE_PLAY).getClass());
	}

}
