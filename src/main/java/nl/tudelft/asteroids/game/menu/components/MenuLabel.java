package nl.tudelft.asteroids.game.menu.components;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

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
	private final TrueTypeFont font;

	/**
	 * Constructor where everything is default
	 */
	public MenuLabel(MenuComponent parent, String label, int x, int y, int width, int height) throws SlickException {
		this(parent, label, DEFAULT, x, y, width, height);
	}

	/**
	 * Constructor where the font is default
	 */
	public MenuLabel(MenuComponent parent, String label, int align, int x, int y, int width, int height)
			throws SlickException {
		this(parent, label, new TrueTypeFont(new Font("Verdana", Font.BOLD, 20), true), align, x, y, width, height);
	}

	/**
	 * Constructor with no defaults
	 */
	public MenuLabel(MenuComponent parent, String label, TrueTypeFont font, int align, int x, int y, int width,
			int height) throws SlickException {
		super(parent, x, y, width, height);
		this.label = label;
		this.align = align;
		this.font = font;


	}

	@Override
	public void process(Graphics graphics) {
		font.drawString(getDrawX(), getDrawY(), label);
	}

	/**
	 * Gets the text
     */
	public String getText() {
		return this.label;
	}

	/**
	 * Gets the font
     */
	public TrueTypeFont getFont() {
		return this.font;
	}

	/**
	 * Gets the draw x coordinate based on the alignment
	 */
	public int getDrawX() {
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

	/**
	 * Gets the draw y coordinate based on allignment
	 */
	public int getDrawY() {
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
			return (height - font.getHeight(label)) / 2;

		// Bottom alignment
		case BOTTOM_LEFT:
		case BOTTOM_CENTER:
		case BOTTOM_RIGHT:
			return (height - font.getHeight(label));

		default:
			return 0;
		}		
		
	}

}
