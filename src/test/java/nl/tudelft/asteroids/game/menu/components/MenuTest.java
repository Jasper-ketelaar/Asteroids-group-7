package nl.tudelft.asteroids.game.menu.components;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import nl.tudelft.asteroids.TestWithDisplay;

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class MenuTest extends TestWithDisplay {

	MenuComponent test;
	
	@Before
	public void init() throws SlickException {
		this.test = new Menu(new MenuData(new Vector2f(0, 0), 0, 0));
	}
	
	@Test
	public void testNothingHappens() {
		Graphics mock = Mockito.mock(Graphics.class);
		test.process(mock);
		Mockito.verifyZeroInteractions(mock);
	}
}
