package nl.tudelft.asteroids.game.menu.components;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MenuButton extends MenuComponent {

	private final Image button;

	public MenuButton(int x, int y, Image button) throws SlickException {
		this(null, x, y, button);
	}

	public MenuButton(MenuComponent parent, int x, int y, Image button) throws SlickException {
		super(parent, x, y, button.getWidth(), button.getHeight());
		this.button = button;
	}

	@Override
	public void process(Graphics graphics) {
		//graphics.drawImage(image, x, y, x2, y2, srcx, srcy, srcx2, srcy2, col);
		
	}

}
