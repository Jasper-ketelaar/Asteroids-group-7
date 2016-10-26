package nl.tudelft.asteroids.model.entity;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import nl.tudelft.asteroids.model.entity.dyn.explodable.Asteroid;

public class EntityTest {
	
	private Asteroid asteroid;
	private Vector2f vector;
	
	@Before
	public void setUp() throws SlickException{
		vector = new Vector2f(2.0f, 2.0f);
		asteroid = new Asteroid(vector, 2.0f, 2, 2);
	}
	
	@Test
	public void testCollide(){
		assertTrue(asteroid.collide(asteroid));
	}
}
