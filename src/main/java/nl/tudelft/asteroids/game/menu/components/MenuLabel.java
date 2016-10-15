package nl.tudelft.asteroids.game.menu.components;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class MenuLabel extends MenuComponent {

	public final static int TOP_LEFT = 0;
	public final static int TOP_CENTER = 1;
	public final static int TOP_RIGHT = 2;
	public final static int CENTER_LEFT = 3;
	public final static int CENTER = 4;
	public final static int CENTER_RIGHT = 5;
	public final static int BOTTOM_LEFT = 6;
	public final static int BOTTOM_CENTER = 7;
	public final static int BOTTOM_RIGHT = 8;
	public final static int DEFAULT = 9;

	private final String label;
	private final int align;

	public MenuLabel(MenuComponent parent, String label, int x, int y, int width, int height) throws SlickException {
		this(parent, label, DEFAULT, x, y, width, height);

	}

	public MenuLabel(MenuComponent parent, String label, int align, int x, int y, int width, int height)
			throws SlickException {
		super(parent, x, y, width, height);
		this.label = label;
		this.align = align;
	}

	@Override
	public void process(Graphics graphics) {
			graphics.drawString(label, getDrawX(graphics.getFont()), getDrawY(graphics.getFont()));
	}

	public int getDrawX(Font font) {
		switch (align) {

		// Left alignment
		case TOP_LEFT:
		case CENTER_LEFT:
		case BOTTOM_LEFT:
			return 0;

		// Center alignment
		case TOP_CENTER:
		case CENTER:
		case BOTTOM_CENTER:
			return (width - font.getWidth(label)) / 2;

		// Right alignment
		case TOP_RIGHT:
		case CENTER_RIGHT:
		case BOTTOM_RIGHT:
			return (width - font.getWidth(label));

		default:
			return 0;
		}
	}

	public int getDrawY(Font font) {
		switch (align) {

		// Top alignment
		case TOP_LEFT:
		case TOP_CENTER:
		case TOP_RIGHT:
			return 0;

		// Center alignment
		case CENTER_LEFT:
		case CENTER:
		case CENTER_RIGHT:
			return height - font.getHeight(label) / 2;

		// Bottom alignment
		case BOTTOM_LEFT:
		case BOTTOM_CENTER:
		case BOTTOM_RIGHT:
			return height - font.getHeight(label);

		default:
			return 0;
		}
	}

}
