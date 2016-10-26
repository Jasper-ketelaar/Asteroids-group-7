package nl.tudelft.asteroids;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class LaunchTest extends TestWithDisplay {

	private Launch launcher;
	
	@Before
	public void setUp() {
		launcher = new Launch();
	}
	
	
	@Test
	public void getGame() {
		
	}
}
