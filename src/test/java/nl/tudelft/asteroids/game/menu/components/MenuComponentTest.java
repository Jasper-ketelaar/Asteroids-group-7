package nl.tudelft.asteroids.game.menu.components;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import nl.tudelft.asteroids.TestWithDisplay;

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class MenuComponentTest extends TestWithDisplay {

	MenuComponent test;
	Vector2f location;
	int width;
	int height;
	NullComponent parent;

	@Before
	public void init() throws SlickException {
		this.parent = new NullComponent();
		this.test = new MenuLabel(parent, "Test", new MenuData(new Vector2f(0, 0), 0, 0));
		this.width = 0;
		this.height = 0;
		this.location = new Vector2f(0, 0);
	}

	@Test
	public void testConstructorX() {
		Assert.assertEquals(location.x, test.getX(), 0);
	}

	@Test
	public void testConstructorY() {
		Assert.assertEquals(location.y, test.getY(), 0);
	}

	@Test
	public void testConstructorWidth() {
		Assert.assertEquals(width, test.getWidth());
	}

	@Test
	public void testConstructorHeight() {
		Assert.assertEquals(width, test.getHeight());
	}

	@Test
	public void testConstructorParent() {
		Assert.assertEquals(parent, test.getParent());
	}

	@Test
	public void testAbsoluteX() {
		Assert.assertEquals(0, test.getAbsoluteX(), 0);
	}

	@Test
	public void testAbsoluteY() {
		Assert.assertEquals(0, test.getAbsoluteY(), 0);
	}

	@Test
	public void testIsAcceptingInput() {
		Assert.assertFalse(test.isAcceptingInput());
	}

	@Test
	public void testSetLocation() {
		test.setLocation(1, 1);
		Assert.assertEquals(1, test.getX(), 0);
		Assert.assertEquals(1, test.getY(), 0);
	}

	@Test
	public void testAppend() throws SlickException {
		NullComponent test2 = new NullComponent();
		test.append(test2);
		Assert.assertTrue(test.getChildren().contains(test2));
	}

	@Test
	public void testBounds() {
		Rectangle rect = new Rectangle(location.x, location.y, 0, 0);
		Assert.assertEquals(rect.toString(), test.getBoundingBox().toString());
	}

	@Test
	public void testContains() {
		Assert.assertFalse(test.contains(4, 4));
	}

	@Test
	public void testRender() throws SlickException {
		Graphics mock = Mockito.mock(Graphics.class);

		test.render(mock);
		Mockito.verify(mock).drawImage(test.canvas, 0, 0);
	}

}
