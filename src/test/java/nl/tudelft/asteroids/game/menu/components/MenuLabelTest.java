package nl.tudelft.asteroids.game.menu.components;

import java.awt.Font;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;

import nl.tudelft.asteroids.TestWithDisplay;

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class MenuLabelTest extends TestWithDisplay {

	MenuLabel test;
	
	@Before
	public void init() throws SlickException {
		this.test = new MenuLabel(new NullComponent(), "Test", new MenuData(new Vector2f(0, 0), 0, 0));
		
	}
	
	@Test
	public void testGetDrawX() {
		Assert.assertEquals((test.getWidth() - test.getFont().getWidth(test.getText())) / 2, test.getDrawX());
	}
	
	@Test
	public void testGetDrawY() {
		Assert.assertEquals((test.getHeight() - test.getFont().getHeight(test.getText())) / 2, test.getDrawY());
	}
	
	@Test
	public void testGetFont() {
		Assert.assertEquals(new TrueTypeFont(new Font("Verdana", Font.BOLD, 20), true).getHeight(), test.getFont().getHeight());
	}
	
	@Test
	public void testGetText() {
		Assert.assertEquals("Test", test.getText());
	}
}
