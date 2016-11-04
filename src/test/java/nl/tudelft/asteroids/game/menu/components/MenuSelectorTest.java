package nl.tudelft.asteroids.game.menu.components;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import nl.tudelft.asteroids.TestWithDisplay;

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class MenuSelectorTest extends TestWithDisplay {
	
	MenuSelector<String> test;
	
	@Before
	public void init() throws SlickException {
		this.test = new MenuSelector<>(new NullComponent(), new MenuData(new Vector2f(0, 0), 0, 0));
	}
	
	@Test
	public void testAddItem() {
		test.addItem("Test");
		Assert.assertTrue(test.getItem().equals("Test"));
	}
	
	@Test
	public void testSetCurrent() {
		test.addItem("Test1");
		test.addItem("Test2");
		test.setCurrent(1);
		Assert.assertTrue(test.getItem().equals("Test2"));
	}

}
