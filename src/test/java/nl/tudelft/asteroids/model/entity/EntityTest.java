package nl.tudelft.asteroids.model.entity;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import nl.tudelft.asteroids.TestWithDisplay;
import nl.tudelft.asteroids.model.entity.dyn.explodable.Asteroid;

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class EntityTest extends TestWithDisplay{
	
	private Asteroid asteroid1;
	private Asteroid asteroid2;
	private Vector2f vector;
	
	@Before
	public void setUp() throws SlickException{
		vector = new Vector2f(2.0f, 2.0f);
		asteroid1 = new Asteroid(vector, 2.0f, 2, 2);
		asteroid2 = new Asteroid(vector, 2.0f, 2, 2);
	}
	
	@Test
	public void testCollide(){
		assertTrue(asteroid1.collide(asteroid2));
	}
}
