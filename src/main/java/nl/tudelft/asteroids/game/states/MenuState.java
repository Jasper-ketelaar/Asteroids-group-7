package nl.tudelft.asteroids.game.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import nl.tudelft.asteroids.game.menu.components.Menu;
import nl.tudelft.asteroids.game.menu.components.MenuButton;

/**
 * The menu state of the Asteroids game.
 * 
 * @author Leroy Velzel, Bernard Bot, Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public class MenuState extends BasicGameState {

	private final Image background;
	private Menu menu;
	private Image test;

	/**
	 * Constructor; sets background sprite.
	 * 
	 * @param background
	 */
	public MenuState(Image background) {
		this.background = background;
	}

	/**
	 * Empty override method.
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame arg1) throws SlickException {
		
		MenuButton singlePlayer = new MenuButton(new Image("menu/SinglePlayerButton.png"), 0, 0);
		this.menu = new Menu(gc.getWidth() / 2 - singlePlayer.getWidth() / 2, 150, 500, 500);
		this.menu.append(new MenuButton(new Image("menu/SinglePlayerButton.png"), 0, 0));
		this.menu.append(new MenuButton(new Image("menu/MultiPlayerButton.png"), 0, 100));
	}

	/**
	 * Draws the background image.
	 */
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0);
		menu.render(g);
	}

	/**
	 * Empty override method.
	 */
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// TODO Auto-generated method stub

	}

	/**
	 * Empty override method.
	 */
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
