package nl.tudelft.asteroids.game.states.menu;

import nl.tudelft.asteroids.TestWithDisplay;
import nl.tudelft.asteroids.game.menu.components.MenuData;
import nl.tudelft.asteroids.game.menu.components.MenuLabel;
import nl.tudelft.asteroids.game.menu.components.Vector2i;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class MenuLabelTest extends TestWithDisplay {

    private MenuLabel menuItem;
    private String text;
    private TrueTypeFont font;
    private Graphics graphics;

    @Before
    public void setUp() {
        text = "test";
        font = Mockito.mock(TrueTypeFont.class);
        try {
            menuItem = new MenuLabel(null, "test", font, MenuLabel.CENTER, new MenuData(new Vector2i(0, 0), 50, 50));
        } catch (SlickException e) {
            e.printStackTrace();
        }
        graphics = new Graphics();
    }

    @Test
    public void testGetHeight() {
        assertEquals(50, menuItem.getHeight());
    }

    @Test
    public void testGetWidth() {
        assertEquals(50, menuItem.getWidth());
    }

    @Test
    public void testGetText() {
        assertEquals("test", menuItem.getText());
    }

    @Test
    public void testGetFont() {
        assertEquals(font, menuItem.getFont());
    }

    @Test
    public void testRender() throws SlickException {
        //menuItem.render(graphics);
        //assertNotEquals(graphics, inst);
    }
}
