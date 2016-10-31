package nl.tudelft.asteroids.game.menu.components;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Component representing a MenuSlider.
 * Not used for now
 */
public class MenuSlider extends MenuComponent {
	
	private int value;
	
	private final double ratio;

	/**
	 * Constructor which also initializes the minimum/maximum values of the slider.
	 * The ratio determines how much the value will increase when the slider is moved.
	 */
	public MenuSlider(MenuComponent parent, MenuData menudata, int min, int max)
			throws SlickException {
		super(parent, menudata);
		this.ratio = menudata.width / (max - min);
	}

	/**
	 * Draws a rectangular line (slider rail) and an oval (slider button).
	 */
	@Override
	public void process(Graphics graphics) {
		graphics.fillRect(0, menudata.height / 4, menudata.width, menudata.height / 2);
		graphics.fillOval((float) (ratio * value), 0, 10, 10);
	}
	
	/**
	 * Not yet implemented.
	 */
	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		//i
	}

}
