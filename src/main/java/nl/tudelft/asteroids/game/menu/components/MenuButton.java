package nl.tudelft.asteroids.game.menu.components;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import nl.tudelft.asteroids.game.menu.input.events.MouseEvent;
import nl.tudelft.asteroids.game.menu.input.interfaces.MouseListener;

public class MenuButton extends MenuComponent implements MouseListener {

	private final static float HOVER = 0.8f;

	boolean hovered = false;

	private Runnable action;

	public MenuButton(MenuComponent parent, Image button, int x, int y) throws SlickException {
		super(parent, button, x, y);
	}

	@Override
	public void process(Graphics graphics) {
		if (hovered)
			canvas.setAlpha(HOVER);
		else
			canvas.setAlpha(1);

	}

	@Override
	public void update(MouseEvent event) {
		hovered = getBoundingBox().contains(event.getX(), event.getY());
		if (hovered) {
			if (event.isLeftMouseDown()) {
				action.run();
			}
		}
	}

	public void setAction(Runnable action) {
		this.action = action;
	}

}
