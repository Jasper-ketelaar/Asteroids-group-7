package nl.tudelft.asteroids.game.states.menu;

import nl.tudelft.asteroids.game.menu.components.Menu;
import org.junit.Before;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

import nl.tudelft.asteroids.TestWithDisplay;

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class MenuTest extends TestWithDisplay{
	
	private Menu menu;
	
	@Before
	public void setUp(){

		try {
			menu = new Menu(0, 0, 0, 0);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
}
