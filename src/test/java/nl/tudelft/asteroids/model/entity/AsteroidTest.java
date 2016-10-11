package nl.tudelft.asteroids.model.entity;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import nl.tudelft.asteroids.TestWithDisplay;
import nl.tudelft.asteroids.model.entity.dyn.explodable.Asteroid;

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class AsteroidTest extends TestWithDisplay {
	
	@Test
	public void testAsteroidConstructor() throws SlickException {
		Asteroid as = new Asteroid(new Vector2f(0, 0), 0, 1);
	}
}

