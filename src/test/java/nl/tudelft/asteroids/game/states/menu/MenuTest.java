package nl.tudelft.asteroids.game.states.menu;

import org.junit.Before;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.newdawn.slick.gui.GUIContext;

import nl.tudelft.asteroids.TestWithDisplay;

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class MenuTest extends TestWithDisplay{
	
	private Menu menu;
	
	@Before
	public void setUp(){
		menu = new Menu(Mockito.mock(GUIContext.class));
	}
	
}
