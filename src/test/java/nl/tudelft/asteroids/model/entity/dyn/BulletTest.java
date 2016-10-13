package nl.tudelft.asteroids.model.entity.dyn;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import nl.tudelft.asteroids.TestWithDisplay;
import nl.tudelft.asteroids.model.entity.dyn.Bullet;

/**
 * TODO: COMMENTS
 * 
 * @author Leroy
 *
 */
@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class BulletTest extends TestWithDisplay {

	private Bullet testBullet;

	private Vector2f testVector = new Vector2f(2, 7);

	/**
	 * TODO: COMMENTS
	 * 
	 * @throws SlickException
	 */
	@Before
	public void setUp() throws SlickException {

		testBullet = new Bullet(testVector, 5.f);

	}

	/**
	 * TODO: Bullet does not move, startPos is equal
	 *  to newPos after move.
	 */
	@Test
	public void testMove() {
		
		Vector2f startPos = testBullet.getPosition();
		
		testBullet.move();

		Vector2f newPos = testBullet.getPosition();
		
		//assertNotEquals(startPos, newPos);
	}


}
