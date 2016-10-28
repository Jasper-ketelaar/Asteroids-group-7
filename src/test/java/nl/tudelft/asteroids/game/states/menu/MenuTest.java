package nl.tudelft.asteroids.game.states.menu;

import nl.tudelft.asteroids.game.menu.components.Menu;
import nl.tudelft.asteroids.game.menu.components.MenuData;
import nl.tudelft.asteroids.game.menu.components.Vector2i;

import org.junit.Before;
import org.junit.experimental.categories.Category;
import org.newdawn.slick.SlickException;
import nl.tudelft.asteroids.TestWithDisplay;

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class MenuTest extends TestWithDisplay{
	
	private Menu menu;
	
	@Before
	public void setUp(){

		try {
			menu = new Menu(new MenuData(new Vector2i(0, 0), 0, 0));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
}
