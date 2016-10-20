package nl.tudelft.asteroids.game.menu.components;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

public class MenuSlider extends MenuComponent {
	
	private int value;
	
	private final int min;
	private final int max;
	
	private final double ratio;

	/**
	 * Constructor which also initializes the minimum/maximum values of the slider.
	 * The ratio determines how much the value will increase when the slider is moved.
	 */
	public MenuSlider(MenuComponent parent, int x, int y, int width, int height, int min, int max)
			throws SlickException {
		super(parent, x, y, width, height);
		this.min = min;
		this.max = max;
		this.ratio = width / (max - min);
	}

	/**
	 * Draws a rectangular line (slider rail) and an oval (slider button).
	 */
	@Override
	public void process(Graphics graphics) {
		graphics.fillRect(0, height / 4, width, height / 2);
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
