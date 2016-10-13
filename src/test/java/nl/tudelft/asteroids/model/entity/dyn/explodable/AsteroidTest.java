package nl.tudelft.asteroids.model.entity.dyn.explodable;

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
import nl.tudelft.asteroids.util.Util;

/**
 * TODO: COMMENTS
 * 
 * @author Leroy
 *
 */
@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class AsteroidTest extends TestWithDisplay {

	private Asteroid testBigAsteroid;
	// private Asteroid testNormalAsteroid;
	private Asteroid testSmallAsteroid;

	private ListIterator<Asteroid> mockedIterator;

	private Vector2f testVector = new Vector2f(2, 7);
	private Vector2f velocity;

	/**
	 * TODO: COMMENTS
	 * 
	 * @throws SlickException
	 */
	@Before
	public void setUp() throws SlickException {

		testBigAsteroid = new Asteroid(testVector, 7.f, 1);
		// testNormalAsteroid = new Asteroid(testVector, 7.f, 2);
		testSmallAsteroid = new Asteroid(testVector, 7.f, 3);

		mockedIterator = Mockito.mock(ListIterator.class);
		velocity = Util.generateDirection(testBigAsteroid.getRotation(), 90, 2f);
	}

	/**
	 * TODO: COMMENTS
	 */
	@Test
	public void testUpdate1() {
		Vector2f startPos = testBigAsteroid.getPosition();

		testBigAsteroid.update(Mockito.mock(GameContainer.class));

		Vector2f newPos = testBigAsteroid.getPosition();

		assertNotEquals(startPos, newPos);
	}
	
	@Test
	public void testUpdate2() throws SlickException {
		Asteroid testBigAsteroid2 = new Asteroid(testVector, 7.f, 1);
		testBigAsteroid.setPosition(new Vector2f(-150, -130));
		testBigAsteroid.setPosition(testBigAsteroid.getPosition().add(velocity));
		testBigAsteroid.setRotation(testBigAsteroid.getRotation() + 1.75f);
		
		GameContainer gc = Mockito.mock(GameContainer.class);
		testBigAsteroid.setPosition(new Vector2f(gc.getWidth(), testBigAsteroid.getY()));
		Vector2f testPos = new Vector2f(gc.getWidth(),testBigAsteroid.getY());
		
		testBigAsteroid2.setPosition(new Vector2f(-150, -130));
		testBigAsteroid2.update(gc);
		assertEquals(testPos, testBigAsteroid.getPosition());
	}

	/**
	 * @throws SlickException
	 *             Check if the Asteroid is splitted into to Asteroids
	 */
	@Test
	public void testSplitBigAsteroid() throws SlickException {

		testBigAsteroid.splitAsteroid(mockedIterator);

		Mockito.verify(mockedIterator, Mockito.times(2)).add(Mockito.anyObject());

	}

	/**
	 * @throws SlickException
	 *             Check if the Asteroid is not splitted into to Asteroids
	 */
	@Test
	public void testSplitSmallAsteroid() throws SlickException {

		testSmallAsteroid.splitAsteroid(mockedIterator);

		Mockito.verify(mockedIterator, Mockito.times(0)).add(Mockito.anyObject());

	}

	/**
	 * TODO: COMMENTS
	 */
	@Test
	public void testGetPoints() {
		assertEquals(testBigAsteroid.getPoints(), 100);
	}

	/**
	 * TODO: COMMENTS
	 */
	@Test
	public void testGetSize() {
		assertEquals(testBigAsteroid.getSize(), 1);
	}

}
