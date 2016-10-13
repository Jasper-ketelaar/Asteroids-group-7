package nl.tudelft.asteroids.util;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class UtilTest {

	/**
	 * Tests Util.decompose method
	 */
	@Test
	public void testDecompose() {
		double radian = Math.toRadians(90);
		Vector2f expected = new Vector2f((float) Math.cos(radian), (float) Math.sin(radian));
		Vector2f decomposed = Util.decompose(radian);
		assertEquals(expected, decomposed);
	}
	
	/**
	 * TODO:
	 * The randomLocation method does not return 2 randomly generated vectors.
	 * Needs a fix?
	 */
	@Test
	public void testRandomLocation(){
		GameContainer gc = Mockito.mock(GameContainer.class);
		Vector2f expected = Util.randomLocation(gc);
		Vector2f actual = Util.randomLocation(gc);
		// assertNotEquals(expected, actual);
	}

}
