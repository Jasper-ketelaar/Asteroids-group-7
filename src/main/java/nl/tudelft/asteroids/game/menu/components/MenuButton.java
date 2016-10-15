package nl.tudelft.asteroids.game.menu.components;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MenuButton extends MenuComponent {

	private final static float HOVER = 0.8f;

	private boolean hovered;

	private Image button;

	public MenuButton(MenuComponent parent, Image button, int x, int y) throws SlickException {
		super(parent, x, y, button.getWidth(), button.getHeight());
		this.button = button;
	}

	@Override
	public void process(Graphics graphics) {
		if (hovered)
			button.setAlpha(HOVER);
		else
			button.setAlpha(1);
		
		graphics.drawImage(button, 0, 0);
		

	}

}
