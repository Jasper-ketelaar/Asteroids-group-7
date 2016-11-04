package nl.tudelft.asteroids.game.menu.components;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import nl.tudelft.asteroids.TestWithDisplay;

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class NullComponentTest extends TestWithDisplay {
	
	NullComponent test;
	
	@Before
	public void init() throws SlickException {
		this.test = new NullComponent();
	}
	
	@Test
	public void testNothingHappens() {
		Graphics mock = Mockito.mock(Graphics.class);
		test.process(mock);
		Mockito.verifyZeroInteractions(mock);
	}
	
	@Test
	public void testAbsoluteX() {
		Assert.assertEquals(0, test.getAbsoluteX(), 0);
	}
	
	@Test
	public void testAbsoluteY() {
		Assert.assertEquals(0, test.getAbsoluteY(), 0);
	}

}
