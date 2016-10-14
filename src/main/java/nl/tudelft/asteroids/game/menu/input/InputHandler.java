package nl.tudelft.asteroids.game.menu.input;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import nl.tudelft.asteroids.game.menu.input.events.MouseEvent;
import nl.tudelft.asteroids.game.menu.input.interfaces.Listener;
import nl.tudelft.asteroids.game.menu.input.interfaces.MouseListener;

public class InputHandler {

	private int mouseX, mouseY;
	private MouseEvent last;
	private final List<Listener> listeners = new ArrayList<>();
	private final GameContainer game;

	public InputHandler(GameContainer game) {
		this.game = game;
	}

	public void listen(Listener listener) {
		this.listeners.add(listener);
	}

	public void update() {
		Input input = game.getInput();

		boolean update = createMouseEvent(input);

		for (Listener listener : listeners) {
			if (update && listener instanceof MouseListener) {
				((MouseListener) listener).update(last);
			}
		}

	}

	public boolean createMouseEvent(Input input) {
		MouseEvent event = MouseEvent.create().setLocation(input.getMouseX(), input.getMouseY())
				.setPreviousLocation(mouseX, mouseY).setRightButton(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
				.setLeftButton(input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON))
				.setMiddleButton(input.isMouseButtonDown(Input.MOUSE_MIDDLE_BUTTON));
		boolean update = event.equals(last);
		last = event;
		mouseX = input.getMouseX();
		mouseY = input.getMouseY();
		return update;
	}
}
