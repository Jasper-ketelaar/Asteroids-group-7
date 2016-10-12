package nl.tudelft.asteroids.model.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import nl.tudelft.asteroids.TestWithDisplay;
import nl.tudelft.asteroids.model.entity.dyn.explodable.Asteroid;

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class AsteroidTest extends TestWithDisplay {

	private Asteroid testBigAsteroid;
	// private Asteroid testNormalAsteroid;
	// private Asteroid testSmallAsteroid;


	private Vector2f testVector = new Vector2f(2, 7);

	@Before
	public void setUp() throws SlickException {
		testBigAsteroid = new Asteroid(testVector, 7.f, 1);
		// testNormalAsteroid = new Asteroid(testVector, 7.f, 2);
		// testSmallAsteroid = new Asteroid(testVector, 7.f, 3);

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
