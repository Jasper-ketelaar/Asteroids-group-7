package nl.tudelft.asteroids.game.states.menu;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

public class Menu extends AbstractComponent {

	private final ArrayList<MenuItem> items = new ArrayList<>();
	
	public Menu(GUIContext container) {
		super(container);
	}

	@Override
	public int getHeight() {
		return 800;
	}

	@Override
	public int getWidth() {
		return 600;
	}

	@Override
	public int getX() {
		return 200;
	}

	@Override
	public int getY() {
		return 200;
	}

	@Override
	public void render(GUIContext c, Graphics g) throws SlickException {
		for (int i = 0; i < items.size(); i++) {
			MenuItem item = items.get(i);
			g.setFont(item.getFont());
			
		}
		
	}

	@Override
	public void setLocation(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

}
