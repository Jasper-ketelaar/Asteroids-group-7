package nl.tudelft.asteroids.game.states.menu;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

/**
 * Item used in the menu, consisting of text and a font.
 * 
 * @author Bernard
 *
 */
public class MenuItem {

	private final String text;
	private final Font font;

	/**
	 * Constructor, setting the text and font.
	 * 
	 * @param text
	 * @param font
	 */
	public MenuItem(String text, Font font) {

		this.text = text;
		this.font = font;

	}

	/**
	 * 
	 * @return The height of the MenuItem
	 */
	public int getHeight() {
		return font.getHeight(text);
	}

	/**
	 * 
	 * @return The width of the MenuItem
	 */
	public int getWidth() {
		return font.getWidth(text);
	}

	/**
	 * 
	 * @return The text of the MenuItem
	 */
	public String getText() {
		return text;
	}

	/**
	 * 
	 * @return The font of the MenuItem
	 */	
	public Font getFont() {
		return font;
	}
	
	/**
	 * Renders the MenuItem on specified location.
	 * 
	 * @param g
	 * @param 	x
	 * 			The minimal x-coordinate for rendering.
	 * @param 	y
	 * 			The minimal y-coordinate for rendering
	 */			
	public void render(Graphics g, int x, int y) {
		g.setFont(getFont());
		g.drawRect(x, y, getWidth(), getHeight());
	}

}
