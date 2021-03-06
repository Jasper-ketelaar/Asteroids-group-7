package nl.tudelft.asteroids.game.menu.components;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class MenuButton extends MenuComponent {

	private final static float HOVER = 0.9f;
	private final static float STANDARD = 1f;

	private boolean hovered;
	private Image button;
	private Runnable action;

	/**
	 * Default constructor
	 * {@link MenuComponent#MenuComponent(MenuComponent, int, int, int, int)}
	 * with added button Image.
	 * 
	 * @param button
	 */
	public MenuButton(MenuComponent parent, Image button, Vector2f coordinates) throws SlickException {
		super(parent, new MenuData(new Vector2f(coordinates.x - 1, coordinates.y - 1), button.getWidth() + 1,
				button.getHeight() + 1));
		this.button = button;
	}

	/**
	 * Override original method to change appearance during mousehover.
	 */
	@Override
	public void process(Graphics graphics) {
		graphics.drawImage(button, 0, 0);
	}

	/**
	 * Check if the new location of the mouse is on the button.
	 */
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		super.mouseMoved(oldx, oldy, newx, newy);
		hovered = contains(newx, newy);
		if (hovered)
			button.setAlpha(HOVER);
		else
			button.setAlpha(STANDARD);
	}

	/**
	 * Check if the button is pressed and action needs to be run.
	 */
	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		if (hovered) {
			action.run();
		}
	}

	/**
	 * @param action
	 *            The Runnable the button should execute when clicked
	 */
	public void setOnClick(Runnable action) {
		this.action = action;
	}

}
