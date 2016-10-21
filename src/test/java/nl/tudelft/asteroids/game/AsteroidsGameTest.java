package nl.tudelft.asteroids.game;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import nl.tudelft.asteroids.TestWithDisplay;
import nl.tudelft.asteroids.game.states.MultiPlayState;
import nl.tudelft.asteroids.game.states.SinglePlayState;

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class AsteroidsGameTest extends TestWithDisplay{

	
	private AsteroidsGame testGame1;
	private GameContainer gc;
	
	@Before
	public void setup() throws SlickException {
		testGame1 = new AsteroidsGame("Asteroids", true);
		gc = Mockito.mock(GameContainer.class);
		testGame1.initStatesList(gc);
	}
	
	@Test
	public void testInitStateList1() throws SlickException{
		assertEquals(MultiPlayState.class , testGame1.getState(2).getClass());
	}
	
	@Test
	public void testInitStateList2() throws SlickException{
		assertEquals(SinglePlayState.class , testGame1.getState(1).getClass());
	}
}
