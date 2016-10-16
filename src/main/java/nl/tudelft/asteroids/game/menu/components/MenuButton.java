package nl.tudelft.asteroids.game.menu.components;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MenuButton extends MenuComponent {

	private final static float HOVER = 0.9f;
	private final static float STANDARD = 1f;

	private boolean hovered;
	private Image button;
	private Runnable action;

	public MenuButton(MenuComponent parent, Image button, int x, int y) throws SlickException {
		super(parent, x - 1, y - 1, button.getWidth() + 1, button.getHeight() + 1);
		this.button = button;
	}

	@Override
	public void process(Graphics graphics) {
		if (hovered)
			button.setAlpha(HOVER);
		else
			button.setAlpha(STANDARD);

		graphics.drawImage(button, 0, 0);
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		hovered = contains(newx, newy);
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		if (hovered) {
			action.run();
		}
	}

	public void setOnClick(Runnable action) {
		this.action = action;
	}

}
