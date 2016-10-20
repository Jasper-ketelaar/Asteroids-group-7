package nl.tudelft.asteroids.game.states.menu;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import nl.tudelft.asteroids.TestWithDisplay;

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class MenuItemTest extends TestWithDisplay {
	
	private MenuItem menuItem;
	private String text;
	private Font font;
	private Graphics graphics;
	
	@Before
	public void setUp(){
		text = "test";
		font = Mockito.mock(Font.class);
		menuItem = new MenuItem(text, font);
		graphics = new Graphics();
	}
	
	@Test
	public void testGetHeight(){
		Mockito.when(font.getHeight(text)).thenReturn(4);
		assertEquals(4, menuItem.getHeight());
	}
	
	@Test
	public void testGetWidth(){
		Mockito.when(font.getWidth(text)).thenReturn(4);
		assertEquals(4, menuItem.getWidth());
	}
	
	@Test
	public void testGetText(){
		assertEquals("test", menuItem.getText());
	}
	
	@Test
	public void testGetFont(){
		assertEquals(font, menuItem.getFont());
	}
	
	@Test
	public void testRender() throws SlickException{
		menuItem.render(graphics, 0, 0);
		assertEquals(font, graphics.getFont());
	}
}
