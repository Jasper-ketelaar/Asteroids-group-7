package nl.tudelft.asteroids.game.states.menu;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

public class MenuItem {

	private final String text;
	private final Font font;

	public MenuItem(String text, Font font) {

		this.text = text;
		this.font = font;

	}

	public int getHeight() {
		return font.getHeight(text);
	}

	public int getWidth() {
		return font.getWidth(text);
	}

	public String getText() {
		return text;
	}
	
	public Font getFont() {
		return font;
	}
	
	public void render(Graphics g, int x, int y) {
		g.setFont(getFont());
		g.drawRect(x, y, getWidth(), getHeight());
	}

}
