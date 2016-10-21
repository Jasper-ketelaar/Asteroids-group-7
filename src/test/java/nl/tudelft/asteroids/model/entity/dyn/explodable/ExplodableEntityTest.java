package nl.tudelft.asteroids.model.entity.dyn.explodable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ListIterator;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.mockito.Mockito;

import nl.tudelft.asteroids.TestWithDisplay;
import nl.tudelft.asteroids.model.entity.dyn.explodable.ExplodableEntity;

/**
 * TODO: COMMENTS
 * 
 * @author Leroy
 *
 */
@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class ExplodableEntityTest extends TestWithDisplay {

	private ExplodableEntity testMockedExplosion;
	// private ExplodableEntity testNormal;
	// private ExplodableEntity testSmall;

	private Vector2f testVector = new Vector2f(2, 7);

	private Animation explosion;

	/**
	 * TODO: COMMENTS
	 * @throws SlickException
	 */
	@Before
	public void setUp() throws SlickException {

		// testNormal = new ExplodableEntity(testVector, 7.f, 2);
		// testSmall = new ExplodableEntity(testVector, 7.f, 3);

		explosion = Mockito.mock(Animation.class);
		testMockedExplosion = new ExplodableEntity(testVector, explosion);

	}

	/**
	 * TODO: COMMENTS
	 */
	@Test
	public void testConstructor() {
		Mockito.verify(explosion, Mockito.times(1)).setLooping(false);
	}
	
	/**
	 * TODO: COMMENTS
	 */
	@Test
	public void testPlayExplosion() {
		testMockedExplosion.playExplosion();
		
		Mockito.verify(explosion, Mockito.times(1)).update(0);
	}
	
	/**
	 * TODO: COMMENTS
	 */
	@Test
	public void testGetExplosion() {
		Animation recievedExplosion = testMockedExplosion.getExplosion();
		
		assertEquals(recievedExplosion, explosion);
	}

}
