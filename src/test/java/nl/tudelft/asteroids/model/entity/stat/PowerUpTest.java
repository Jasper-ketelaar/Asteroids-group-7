package nl.tudelft.asteroids.model.entity.stat;

import static org.junit.Assert.assertEquals;

import org.newdawn.slick.Graphics;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;
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
	public void testGetType(){
		assertEquals(testBullet, testPU.getType());
	}
	
	@Test
	public void testPickupTime(){
		long exptectedTime = System.currentTimeMillis();
		testPU.setPickupTime();
		assertEquals(exptectedTime, testPU.getPickupTime());
	}
	
	@Test
	public void testPickupTimeElapsed() throws InterruptedException{
		long exptectedTime = System.currentTimeMillis();
		testPU.setPickupTime();
		Thread.sleep(1);
		long elapsedTime = System.currentTimeMillis() - exptectedTime; 
		assertEquals(elapsedTime, testPU.pickupTimeElapsed());
	}
	
	@Test
	public void testCreationTimeElapsed() {
		long expected = System.currentTimeMillis() - testPU.getCreationTime();
		assertEquals(expected, testPU.creationTimeElapsed());
	}
	
	@Test
	public void testGetBoundingBox(){
		Shape expectedShape = new Ellipse(testPU.getX(), testPU.getY(), 10, 10);
		Shape actualShape = testPU.getBoundingBox();
		// assertEquals(expectedShape, actualShape);
	}
	
	@Test
	public void testRenderInvocations(){
		Graphics graphics = Mockito.mock(Graphics.class);
		testPU.render(graphics);
		Mockito.verify(graphics, Mockito.times(1)).getColor();
		Mockito.verify(graphics, Mockito.times(1)).setColor(testPU.getType().getColor());
		Mockito.verify(graphics, Mockito.times(1)).setAntiAlias(true);
		//Mockito.verify(graphics, Mockito.times(1)).fill(testPU.getBoundingBox());
		Mockito.verify(graphics, Mockito.times(1)).setColor(graphics.getColor());
	}
}
