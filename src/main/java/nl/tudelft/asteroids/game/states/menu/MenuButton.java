package nl.tudelft.asteroids.game.states.menu;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

public class MenuButton extends MenuComponent {

	private final Image button;
	
	public MenuButton(GUIContext container, int x, int y, Image button) {
		super(container, x, y);
		this.button = button;
	}
	
	@Override
	public void render(GUIContext c, Graphics g) throws SlickException {
		super.render(c, g);
	
	}

}
