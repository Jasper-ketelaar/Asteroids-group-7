package nl.tudelft.asteroids.game.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import nl.tudelft.asteroids.game.AsteroidsGame;
import nl.tudelft.asteroids.game.menu.components.Menu;
import nl.tudelft.asteroids.game.menu.components.MenuButton;
import nl.tudelft.asteroids.game.menu.input.InputHandler;
import nl.tudelft.asteroids.util.Logger;

/**
 * The menu state of the Asteroids game.
 * 
 * @author Leroy Velzel, Bernard Bot, Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public class MenuState extends BasicGameState {

	private final static String BACKGROUND = "BG4.jpg";
	private final static Logger LOGGER = Logger.getInstance(MenuState.class.getName());

	private static Image background;

	private Menu menu;
	private InputHandler inputHandler;

	/**
	 * Constructor; sets background sprite.
	 * 
	 * @param background
	 * @throws SlickException
	 */
	public MenuState() throws SlickException {
		if (background == null)
			background = new Image(BACKGROUND);
		LOGGER.log("Background image loaded");
	}

	/**
	 * Empty override method.
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Image singlePlayerImg = new Image("menu/SinglePlayerButton.png");
		this.menu = new Menu(gc.getWidth() / 2 - singlePlayerImg.getWidth() / 2, 150, 500, 500);

		MenuButton singlePlayer = new MenuButton(menu, singlePlayerImg, 0, 0);
		singlePlayer.setAction(() -> {
			try {
				sbg.enterState(1);
				sbg.getState(1).init(gc, sbg);
			} catch (SlickException e) {
				e.printStackTrace();
			}

		});

		MenuButton multiPlayer = new MenuButton(menu, new Image("menu/MultiPlayerButton.png"), 0, 100);
		multiPlayer.setAction(() -> {
			sbg.enterState(2);
			try {
				sbg.getState(2).init(gc, sbg);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		this.menu.append(singlePlayer);
		this.menu.append(multiPlayer);

		this.inputHandler = new InputHandler(sbg);
		this.inputHandler.listen(singlePlayer);
		this.inputHandler.listen(multiPlayer);
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
		inputHandler.update();

	}

	/**
	 * Empty override method.
	 */
	@Override
	public int getID() {
		return 0;
	}

	public Menu getMenu() {
		return menu;
	}

}
