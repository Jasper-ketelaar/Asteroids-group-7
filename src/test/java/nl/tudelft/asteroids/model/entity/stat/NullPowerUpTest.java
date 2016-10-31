package nl.tudelft.asteroids.model.entity.stat;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import nl.tudelft.asteroids.TestWithDisplay;

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class NullPowerUpTest extends TestWithDisplay {
	
	@Test
	public void testpickupTimeElapsed(){
		PowerUp p = new NullPowerUp();
		long expected = 0;
		assertEquals(expected, p.pickupTimeElapsed());
	}
	
	@Test
	public void testcreationTimeElapsed(){
		PowerUp p = new NullPowerUp();
		long expected = 0;
		assertEquals(expected, p.creationTimeElapsed());
	}
	
	@Test
	public void testSetPickupTime(){
		PowerUp p = new NullPowerUp();
		long expected = 0;
		p.setPickupTime();
		assertEquals(expected, p.getPickupTime());
	}
	
}
