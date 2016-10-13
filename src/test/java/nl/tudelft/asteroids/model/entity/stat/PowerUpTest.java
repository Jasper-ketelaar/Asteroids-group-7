package nl.tudelft.asteroids.model.entity.stat;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.newdawn.slick.geom.Vector2f;

import nl.tudelft.asteroids.TestWithDisplay;
import nl.tudelft.asteroids.model.entity.stat.PowerUp.PowerupType;

/**
 * 
 * @author Leroy
 *
 */
@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class PowerUpTest extends TestWithDisplay {
	
	private PowerUp testPU;
	private final PowerupType testBullet = PowerupType.BULLET;
//	private final PowerupType testPoints = PowerupType.POINTS;
//	private final PowerupType testInvinc = PowerupType.INVINCIBILITY;
	
	private final Vector2f testVector = new Vector2f(2,7);
	
	/**
	 * 
	 */
	@Before
	public void setUp() {
		
		testPU = new PowerUp(testVector, testBullet);
		
	}
	
	@Test
	public void getType(){
		
		assertEquals(testBullet, testPU.getType());
		
	}
}
