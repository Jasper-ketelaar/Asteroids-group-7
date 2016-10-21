package nl.tudelft.asteroids.game.menu.components;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Simple subclass of {@link MenuComponent} that describes a menu. <br />
 * Layouts will be considered in future revisions but is not a main priority now.
 * 
 * @author Jasper
 *
 */
public class Menu extends MenuComponent {
	
	/**
	 * Default constructor refer to {@link MenuComponent#MenuComponent(MenuComponent, int, int, int, int)}
	 * for more information. Parent is set to null as by default a menu should not have a parent.
	 */
	public Menu(int x, int y, int width, int height) throws SlickException  {
		super(null, x, y, width, height);
	}

	/**
	 * Abstract method from {@link MenuComponent}. Only used for debugging in current revision.
	 */
	@Override
	public void process(Graphics graphics) {
		//To show menu, not displayed in release
		//graphics.drawRect(0, 0, width - 1, height - 1);
	}
}
