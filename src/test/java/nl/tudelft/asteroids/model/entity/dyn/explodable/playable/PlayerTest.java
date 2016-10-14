package nl.tudelft.asteroids.model.entity.dyn.explodable.playable;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Graphics;
import nl.tudelft.asteroids.TestWithDisplay;
import nl.tudelft.asteroids.model.entity.Entity;
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
	private Player testPlayer2;
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
	
	@Test
	public void testGetAnimation() throws SlickException {
		Animation animation = new Animation();
		testPlayer.setAnimation(animation);
		assertEquals(testPlayer.getAnimation(), animation);
	}
	/*
	@Test
	public void testGetBoundingBox(){
		float cX = testPlayer.getX() + testPlayer.getSprite().getWidth() / 2;
		float cY = testPlayer.getY() + testPlayer.getSprite().getHeight() / 2;
		float xRad = testPlayer.getSprite().getWidth() / 2;
		float yRad = testPlayer.getSprite().getHeight() / 2;
		Ellipse ellip = new Ellipse(cX, cY, xRad, yRad);
		
		assertEquals(ellip, testPlayer.getBoundingBox());
	}
	*/
}
