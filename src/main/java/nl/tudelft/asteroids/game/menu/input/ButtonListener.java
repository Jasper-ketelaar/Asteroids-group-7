package nl.tudelft.asteroids.game.menu.input;

import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;

import nl.tudelft.asteroids.game.menu.components.MenuButton;

public class ButtonListener implements MouseListener {

	private Input input;
	
	private final MenuButton button;
	
	public ButtonListener(MenuButton button) {
		this.button = button;
	}
	
	@Override
	public void setInput(Input input) {
		this.input = input;
	}

	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	@Override
	public void inputEnded() {
		// Empty method
		
	}

	@Override
	public void inputStarted() {
		// Empty method
	}

	@Override
	public void mouseWheelMoved(int change) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		
		
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub
		
	}

}
