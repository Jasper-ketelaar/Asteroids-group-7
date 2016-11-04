package nl.tudelft.asteroids.game.menu.components;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import nl.tudelft.asteroids.TestWithDisplay;

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class MenuButtonTest extends TestWithDisplay {

	MenuButton test;
	Image img;

	@Before
	public void init() throws SlickException {
		this.img = new Image("menu/SinglePlayerButton.png");
		this.test = new MenuButton(new NullComponent(), img, new Vector2f(0, 0));
	}
	
	@Test
	public void testSetOnClick() {
		Runnable action = Mockito.mock(Runnable.class);
		
		test.setOnClick(action);
		test.mouseMoved(0, 0, 0, 0);
		test.mousePressed(0, 0, 0);
		
		Mockito.verify(action).run();
	}

}
