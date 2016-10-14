package nl.tudelft.asteroids.game.menu.input.events;

import org.newdawn.slick.state.StateBasedGame;

public class MouseEvent {

	private int x, y, prevX, prevY;
	private boolean left, middle, right;
	
	private MouseEvent() {
	}

	public synchronized static MouseEvent create() {
		return new MouseEvent();
	}

	public MouseEvent setLocation(int x, int y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public MouseEvent setPreviousLocation(int x, int y) {
		this.prevX = x;
		this.prevY = y;
		return this;
	}

	public MouseEvent setMiddleButton(boolean button) {
		this.middle = button;
		return this;
	}

	public MouseEvent setRightButton(boolean button) {
		this.right = button;
		return this;
	}

	public MouseEvent setLeftButton(boolean button) {
		this.left = button;
		return this;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getPreviousX() {
		return prevX;
	}

	public int getPreviousY() {
		return prevY;
	}

	public boolean isLeftMouseDown() {
		return left;
	}

	public boolean isRightMouseDown() {
		return right;
	}

	public boolean isMiddleMouseDown() {
		return middle;
	}
	

	@Override
	public boolean equals(Object other) {
		if (other instanceof MouseEvent) {
			MouseEvent ot = (MouseEvent) other;
			return ot.getX() == getX() && ot.getY() == getY() && ot.getPreviousX() == getPreviousX()
					&& ot.getPreviousY() == getPreviousY() && ot.isLeftMouseDown() == isLeftMouseDown()
					&& ot.isMiddleMouseDown() == isMiddleMouseDown() && ot.isRightMouseDown() == isRightMouseDown();
		}
		return false;
	}

}
