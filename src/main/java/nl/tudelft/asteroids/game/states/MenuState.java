package nl.tudelft.asteroids.game.states;

import nl.tudelft.asteroids.game.Difficulty;
import nl.tudelft.asteroids.game.menu.components.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import nl.tudelft.asteroids.game.AsteroidsGame;
import nl.tudelft.asteroids.util.Logger;
import nl.tudelft.asteroids.util.Logger.Level;

import java.util.Arrays;
import java.util.List;

/**
 * The menu state of the Asteroids game.
 * 
 * @author Leroy Velzel, Bernard Bot, Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public class MenuState extends BasicGameState {

	
	private final static String MAIN_MENU = "Main menu";
	
	private final static String BACKGROUND = "BG4.jpg";
	private final static Logger LOGGER = Logger.getInstance(MenuState.class.getName());

	private static Image background;

	private Menu menu, main, opt;

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
		opt = createOptionsMenu(gc, sbg);
		main = createMainMenu(gc, sbg);
		menu = main;
	}

	/**
	 * Initializes the main menu
	 */
	public Menu createMainMenu(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Image singlePlayerImg = new Image("menu/SinglePlayerButton.png");
		Menu main = new Menu(gc.getWidth() / 2 - singlePlayerImg.getWidth() / 2, 150, 500, 500);

		Input input = gc.getInput();

		List<Difficulty> values = Arrays.asList(Difficulty.values());
		MenuSelector<Difficulty> selector = new MenuSelector<>(main, 45, 0, 200, 20, values);
		main.append(selector);

		MenuButton singlePlayer = new MenuButton(main, singlePlayerImg, 0, 50);
		singlePlayer.setOnClick(() -> {
			sbg.enterState(AsteroidsGame.STATE_PLAY_SINGLE);
			try {
				sbg.getState(AsteroidsGame.STATE_PLAY_SINGLE).init(gc, sbg);
				((DefaultPlayState)sbg.getState(AsteroidsGame.STATE_PLAY_SINGLE)).setDifficulty(selector.getItem());
			} catch (SlickException e) {
				LOGGER.log("Initialization failed", Level.ERROR, true);
			}
		});

		MenuButton multiPlayer = new MenuButton(main, new Image("menu/MultiPlayerButton.png"), 0, 150);
		multiPlayer.setOnClick(() -> {
			sbg.enterState(AsteroidsGame.STATE_PLAY_MULTI);
			try {
				sbg.getState(AsteroidsGame.STATE_PLAY_MULTI).init(gc, sbg);
				((DefaultPlayState)sbg.getState(AsteroidsGame.STATE_PLAY_MULTI)).setDifficulty(selector.getItem());
			} catch (SlickException e) {
				LOGGER.log("Initialization failed", Level.ERROR, true);
			}
		});

		MenuButton options = new MenuButton(main, new Image("menu/OptionsButton.png"), 0, 250);
		options.setOnClick(() -> {
			input.removeMouseListener(main);
			input.addMouseListener(opt);
			menu = this.opt;
		});
		MenuButton exit = new MenuButton(main, new Image("menu/ExitButton.png"), 0, 350);
		exit.setOnClick(() -> {
			LOGGER.log("Game exited by user", Level.INFO, true);
			System.exit(0);
		});

		main.append(singlePlayer);
		main.append(multiPlayer);
		main.append(exit);
		main.append(options);

		input.addMouseListener(main);
		return main;
	}

	/**
	 * Initializes the options menu
	 */
	public Menu createOptionsMenu(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Image retImage = new Image("menu/ReturnButton.png");
		Menu options = new Menu(gc.getWidth() / 2 - retImage.getWidth() / 2, 150, 500, 500);

		MenuButton ret = new MenuButton(options, retImage, 0, 200);
		ret.setOnClick(() -> {
			gc.getInput().removeMouseListener(opt);
			gc.getInput().addMouseListener(main);
			menu = this.main;
		});

		options.append(ret);
		gc.getInput().addMouseListener(options);
		return options;
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
