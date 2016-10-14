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
	private AsteroidsGame testGame2;
	private GameContainer gc;
	
	@Before
	public void setup(){
		testGame1 = new AsteroidsGame("Asteroids", true);
		testGame2 = new AsteroidsGame("Asteroids", false);
		gc = Mockito.mock(GameContainer.class);
	}
	
	@Test
	public void testInitStateList1() throws SlickException{
		testGame1.initStatesList(gc);
		assertEquals(MultiPlayState.class , testGame1.getState(0).getClass());
	}
	
	@Test
	public void testInitStateList2() throws SlickException{
		testGame2.initStatesList(gc);
		assertEquals(SinglePlayState.class , testGame2.getState(0).getClass());
	}
}
