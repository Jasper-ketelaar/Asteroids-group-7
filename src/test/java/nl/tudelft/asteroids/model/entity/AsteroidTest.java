package nl.tudelft.asteroids.model.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ListIterator;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.mockito.Mockito;

import nl.tudelft.asteroids.TestWithDisplay;
import nl.tudelft.asteroids.model.entity.dyn.explodable.Asteroid;

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class AsteroidTest extends TestWithDisplay {

	private Asteroid testBigAsteroid;
	// private Asteroid testNormalAsteroid;
	private Asteroid testSmallAsteroid;
	
	private ListIterator<Asteroid> mockedIterator;

	private Vector2f testVector = new Vector2f(2, 7);

	@Before
	public void setUp() throws SlickException {

		testBigAsteroid = new Asteroid(testVector, 7.f, 1);
		// testNormalAsteroid = new Asteroid(testVector, 7.f, 2);
		testSmallAsteroid = new Asteroid(testVector, 7.f, 3);
		
		mockedIterator = Mockito.mock(ListIterator.class);

	}

	/**
	 * Check if the Asteroid moves after the update
	 */
	@Test
	public void testUpdate() {
		Vector2f startPos = testBigAsteroid.getPosition();

		testBigAsteroid.update(Mockito.mock(GameContainer.class));

		Vector2f newPos = testBigAsteroid.getPosition();

		assertNotEquals(startPos, newPos);
	}

	/**
	 * @throws SlickException 
	 * Check if the Asteroid is splitted into to Asteroids
	 */
	@Test
	public void testSplitBigAsteroid() throws SlickException {
		
		testBigAsteroid.splitAsteroid(mockedIterator);
		
		Mockito.verify(mockedIterator, Mockito.times(2)).add(Mockito.anyObject());

	}
	
	/**
	 * @throws SlickException 
	 * Check if the Asteroid is not splitted into to Asteroids
	 */
	@Test
	public void testSplitSmallAsteroid() throws SlickException {		
		
		testSmallAsteroid.splitAsteroid(mockedIterator);
		
		Mockito.verify(mockedIterator, Mockito.times(0)).add(Mockito.anyObject());

	}

	@Test
	public void testGetPoints() {
		assertEquals(testBigAsteroid.getPoints(), 100);
	}

	@Test
	public void testGetSize() {
		assertEquals(testBigAsteroid.getSize(), 1);
	}

}
