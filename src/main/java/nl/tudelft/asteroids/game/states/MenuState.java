package nl.tudelft.asteroids.game.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import nl.tudelft.asteroids.game.AsteroidsGame;
import nl.tudelft.asteroids.game.menu.components.Menu;
import nl.tudelft.asteroids.game.menu.components.MenuButton;
import nl.tudelft.asteroids.util.Logger;
import nl.tudelft.asteroids.util.Logger.Level;

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

	private Menu menu, main, options;

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
		main = createMainMenu(gc, sbg);
		menu = main;
	}
	
	public Menu createMainMenu(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Image singlePlayerImg = new Image("menu/SinglePlayerButton.png");
		Menu main = new Menu(gc.getWidth() / 2 - singlePlayerImg.getWidth() / 2, 150, 500, 500);

		Input input = gc.getInput();
		
		MenuButton singlePlayer = new MenuButton(main, singlePlayerImg, 0, 0);
		singlePlayer.setOnClick(() -> {
			sbg.enterState(AsteroidsGame.STATE_PLAY_SINGLE);
			try {
				sbg.getState(AsteroidsGame.STATE_PLAY_SINGLE).init(gc, sbg);;
			} catch (SlickException e) {
				LOGGER.log("Initialization failed", Level.ERROR, true);
			}
		});
		input.addMouseListener(singlePlayer);
		
		
		MenuButton multiPlayer = new MenuButton(main, new Image("menu/MultiPlayerButton.png"), 0, 100);
		multiPlayer.setOnClick(() -> {
			sbg.enterState(AsteroidsGame.STATE_PLAY_MULTI);
			try {
				sbg.getState(AsteroidsGame.STATE_PLAY_MULTI).init(gc, sbg);
			} catch (SlickException e) {
				LOGGER.log("Initialization failed", Level.ERROR, true);
			}
		});
		input.addMouseListener(multiPlayer);
		
		main.append(singlePlayer);
		main.append(multiPlayer);
		return main;
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

	}

	/**
	 * Initial state
	 */
	@Override
	public int getID() {
		return 0;
	}

	public Menu getMenu() {
		return menu;
	}

}
