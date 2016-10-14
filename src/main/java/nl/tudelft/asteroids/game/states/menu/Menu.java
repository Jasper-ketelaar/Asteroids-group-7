package nl.tudelft.asteroids.game.states.menu;

import org.newdawn.slick.gui.GUIContext;

public class Menu extends MenuComponent {

	private final int width;
	private final int height;
	
	public Menu(GUIContext container, int x, int y, int width, int height) {
		super(container, x, y);
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() { 
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}
}
