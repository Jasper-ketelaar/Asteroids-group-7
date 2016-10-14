package nl.tudelft.asteroids.game.menu.input;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import nl.tudelft.asteroids.game.menu.input.interfaces.Listener;

public class InputHandler {

	private int mouseX, mouseY;
	
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
		
		for (Listener listener : listeners) {
			
		}
		
		mouseX = input.getMouseX();
		mouseY = input.getMouseY();
	}
}
