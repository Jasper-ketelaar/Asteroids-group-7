package nl.tudelft.asteroids.game.menu.components;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;

public class MenuButton extends MenuComponent implements MouseListener {

	private final static float HOVER = 0.9f;

	private boolean hovered;
	private Image button;
	private Runnable action;

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

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		hovered = contains(newx, newy);
		System.out.println(newx + ", " + newy + ", " + getAbsoluteX() + ", " + getAbsoluteY());
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
