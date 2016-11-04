package nl.tudelft.asteroids.game.menu.components;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class NullComponent extends MenuComponent {

	public NullComponent() throws SlickException {
		super(null, new MenuData(new Vector2f(0,0), 0, 0));
	}
	
	/**
	 * The absolute x value is the x value of this component and all it's parents component's x values </br>
	 * thus creating the x value where this element is painted on the display.
	 * 
	 * @return the absolute x value
	 */
	@Override
	public float getAbsoluteX() {
		return super.getX();
	}

	/**
	 * The absolute y value is the y value of this component and all it's parents component's y values </br>
	 * thus creating the y value where this element is painted on the display.
	 * 
	 * @return the absolute y value
	 */
	@Override
	public float getAbsoluteY() {
		return super.getY();
	}

	/**
	 * Overrides to check parent as well
	 */
	@Override
	public boolean isAcceptingInput() {
		return true;
	}
	@Override
	public void process(Graphics graphics) {

	}

}
