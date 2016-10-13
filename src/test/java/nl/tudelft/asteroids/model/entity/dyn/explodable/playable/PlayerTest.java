package nl.tudelft.asteroids.model.entity.dyn.explodable.playable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ListIterator;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.mockito.Mockito;

import nl.tudelft.asteroids.TestWithDisplay;
import nl.tudelft.asteroids.model.entity.dyn.explodable.playable.Player;

/**
 * TODO: COMMENTS
 * 
 * @author Leroy
 *
 */
@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class PlayerTest extends TestWithDisplay {
	
	private Player testPlayer;

	private Vector2f testVector = new Vector2f(2, 7);
	
	
	/**
	 * @throws SlickException 
	 *  
	 *  TODO: COMMENTS
	 */
	@Before
	public void setUp() throws SlickException {
		testPlayer = new Player(testVector);
	}
	
	/**
	 * TODO: COMMENTS
	 */
	@Test
	public void testUpdateScore() { 
		final int score = 700;
		testPlayer.updateScore(score);
		
		assertEquals(score, testPlayer.getScore());
		
	}

}
