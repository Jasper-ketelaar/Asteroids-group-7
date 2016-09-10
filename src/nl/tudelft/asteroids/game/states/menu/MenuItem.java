package nl.tudelft.asteroids.game.states.menu;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

public class MenuItem extends AbstractComponent {

	private final String text;
	private final Font font;
	
	public MenuItem(GUIContext container, String text, Font font) {
		super(container);
		this.text = text;
		this.font = font;
	
	}

	@Override
	public int getHeight() {
		return font.getHeight(text);
	}

	@Override
	public int getWidth() {
		return font.getWidth(text);
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void render(GUIContext arg0, Graphics arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLocation(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

}
