package nl.tudelft.asteroids.game.menu.components;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Menu extends MenuComponent {
	
	public Menu(int x, int y, int width, int height) throws SlickException  {
		super(null, x, y, width, height);
	}

	@Override
	public void process(Graphics graphics) {
		//To show menu, not displayed in release
	}
}
