package nl.tudelft.asteroids.game.menu.components;

import java.awt.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class MenuLabel extends MenuComponent {

	private final String label;
	private final TrueTypeFont font;

	/**
	 * Constructor where the font is default
	 */
	public MenuLabel(MenuComponent parent, String label, MenuData menudata) throws SlickException {
		this(parent, label, new TrueTypeFont(new Font("Verdana", Font.BOLD, 20), true), menudata);
	}

	/**
	 * Constructor with no defaults
	 */
	public MenuLabel(MenuComponent parent, String label, TrueTypeFont font, MenuData menudata)
			throws SlickException {
		super(parent, menudata);
		this.label = label;
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
		return (menudata.width - font.getWidth(label)) / 2;
	}

	/**
	 * Gets the draw y coordinate based on allignment
	 */
	public int getDrawY() {
		return (menudata.height - font.getHeight(label)) / 2;
	}

}
