package nl.tudelft.asteroids.game.states.menu;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

/**
 * Menu component of the Asteroids game.
 * 
 * @author Bernard
 *
 */
public class Menu extends AbstractComponent {

	private final ArrayList<MenuItem> items = new ArrayList<>();

	/**
	 * Constructor.
	 * 
	 * @param container
	 */
	public Menu(GUIContext container) {
		super(container);
	}

	/**
	 * @return The height of the menu
	 */
	@Override
	public int getHeight() {
		return 800;
	}

	/**
	 * @return The width of the menu
	 */
	@Override
	public int getWidth() {
		return 600;
	}

	/**
	 * @return The 'starting' x-coordinate of the menu
	 */
	@Override
	public int getX() {
		return 200;
	}

	/**
	 * @return The 'starting' y-coordinate of the menu
	 */
	@Override
	public int getY() {
		return 200;
	}

	/**
	 * Renders the different MenuItems.
	 */
	@Override
	public void render(GUIContext c, Graphics g) throws SlickException {
		for (int i = 0; i < items.size(); i++) {
			MenuItem item = items.get(i);
			g.setFont(item.getFont());

		}

	}

	/**
	 * Empty override method.
	 */
	@Override
	public void setLocation(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

}
