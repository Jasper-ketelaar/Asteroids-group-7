package nl.tudelft.asteroids.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.newdawn.slick.GameContainer;

import nl.tudelft.asteroids.TestWithDisplay;
import nl.tudelft.asteroids.model.entity.stat.PowerUp;

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class PowerupFactoryTest extends TestWithDisplay{

	private PowerupFactory powerupFactory;
	private GameContainer gameContainer;
	
	@Before
	public void setUp(){
		powerupFactory = new PowerupFactory();
		gameContainer = Mockito.mock(GameContainer.class);
	}
	
	@Test
	public void testPowerUp(){
		PowerUp powerUp = powerupFactory.create(gameContainer);
		assertEquals(powerUp.getClass(), PowerUp.class);
	}
	
	@Test
	public void testRequiresPowerup(){
		boolean check = powerupFactory.requiresPowerup();
		assertFalse(check);
	}
	
	
	
}
