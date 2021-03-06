package nl.tudelft.asteroids.game.states;

import nl.tudelft.asteroids.game.Difficulty;
import nl.tudelft.asteroids.game.menu.components.*;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
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

	private final static Logger LOGGER = Logger.getInstance(MenuState.class.getName());

	private Menu menu, main;

	/**
	 * Empty override method.
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		main = createMainMenu(gc, sbg);
		menu = main;
	}

	/**
	 * Initializes the main menu
	 */
	public Menu createMainMenu(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Image singlePlayerImg = new Image("menu/SinglePlayerButton.png");
		Menu main = new Menu(
				new MenuData(new Vector2f(gc.getWidth() / 2 - singlePlayerImg.getWidth() / 2, 150), 500, 500));

		Input input = gc.getInput();

		// Difficulty selector
		List<Difficulty> values = Arrays.asList(Difficulty.values());
		MenuSelector<Difficulty> selector = new MenuSelector<>(main, new MenuData(new Vector2f(45, 0), 200, 20),
				values);
		selector.setCurrent(1); // set default to MEDIUM

		// Single player button
		MenuButton singlePlayer = new MenuButton(main, singlePlayerImg, new Vector2f(0, 50));
		singlePlayer.setOnClick(() -> {
			sbg.enterState(AsteroidsGame.STATE_PLAY);
			try {
				NormalPlayState state = (NormalPlayState) sbg.getState(AsteroidsGame.STATE_PLAY);
				state.setDifficulty(selector.getItem());
				sbg.getState(AsteroidsGame.STATE_PLAY).init(gc, sbg);
				state.addPlayer(sbg.getContainer());
			} catch (SlickException e) {
				LOGGER.log("Initialization failed", Level.ERROR, true);
			}
		});

		// Multi player button
		MenuButton multiPlayer = new MenuButton(main, new Image("menu/MultiPlayerButton.png"), new Vector2f(0, 150));
		multiPlayer.setOnClick(() -> {
			sbg.enterState(AsteroidsGame.STATE_PLAY);
			try {
				NormalPlayState state = (NormalPlayState) sbg.getState(AsteroidsGame.STATE_PLAY);
				state.setDifficulty(selector.getItem());
				sbg.getState(AsteroidsGame.STATE_PLAY).init(gc, sbg);
				state.addPlayer(sbg.getContainer());
				state.addPlayer(sbg.getContainer(), Input.KEY_W, Input.KEY_A, Input.KEY_D, Input.KEY_SPACE);
			} catch (SlickException e) {
				LOGGER.log("Initialization failed", Level.ERROR, true);
			}
		});

		// Rampage button
		MenuButton rampage = new MenuButton(main, new Image("menu/RampageButton.png"), new Vector2f(0, 250));
		rampage.setOnClick(() -> {
			sbg.enterState(AsteroidsGame.STATE_RAMPAGE);
			try {
				sbg.getState(AsteroidsGame.STATE_RAMPAGE).init(gc, sbg);
			} catch (SlickException e) {
				LOGGER.log("Initialization failed", Level.ERROR, true);
			}
		});

		// Exit button
		MenuButton exit = new MenuButton(main, new Image("menu/ExitButton.png"), new Vector2f(0, 350));
		exit.setOnClick(() -> {
			LOGGER.log("Game exited by user", Level.INFO, true);
			System.exit(0);
		});

		// Append all MenuComponents to the main menu
		main.append(selector);
		main.append(singlePlayer);
		main.append(multiPlayer);
		main.append(rampage);
		main.append(exit);
		input.addMouseListener(main);
		return main;
	}

	/**
	 * Draws the background image.
	 */
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.drawImage(AsteroidsGame.background, 0, 0);
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

	/**
	 * @return The menu MenuComponent.
	 */
	public Menu getMenu() {
		return menu;
	}

}
