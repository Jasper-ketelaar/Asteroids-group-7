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
		this.test = new MenuSelector<>(new NullComponent(), new MenuData(new Vector2f(0, 0), 20, 20));
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
	
	@Test
	public void testHover() {
		test.mouseMoved(0, 0, 4, 4);
		Assert.assertEquals(0.8f, test.left.getAlpha(), 0);

		test.mouseMoved(0, 0, 16, 4);
		Assert.assertEquals(0.8f, test.right.getAlpha(), 0);
	}
	
	@Test
	public void testClick() {
		test.addItem("Test1");
		test.addItem("Test2");
		
		test.leftHovered = true;
		test.mousePressed(0, 0, 0);
		Assert.assertEquals("Test2", test.getItem());

		test.leftHovered = false;
		test.rightHovered = true;
		test.mousePressed(0, 0, 0);
		Assert.assertEquals("Test1", test.getItem());
	}

}
