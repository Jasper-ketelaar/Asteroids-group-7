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
		Menu main = new Menu(new MenuData(new Vector2i(gc.getWidth() / 2 - singlePlayerImg.getWidth() / 2, 150), 500, 500));

		Input input = gc.getInput();

		// Difficulty selector
		List<Difficulty> values = Arrays.asList(Difficulty.values());
		MenuSelector<Difficulty> selector = new MenuSelector<>(main, new MenuData(new Vector2i(45, 0), 200, 20), values);
		main.append(selector);

		// Single player button
		MenuButton singlePlayer = new MenuButton(main, singlePlayerImg, new Vector2i(0, 50));
		singlePlayer.setOnClick(() -> {
			sbg.enterState(AsteroidsGame.STATE_PLAY);
			try {
				((NormalPlayState) sbg.getState(AsteroidsGame.STATE_PLAY)).setMultiplayer(false);
				((DefaultPlayState) sbg.getState(AsteroidsGame.STATE_PLAY)).setDifficulty(selector.getItem());
				sbg.getState(AsteroidsGame.STATE_PLAY).init(gc, sbg);
			} catch (SlickException e) {
				LOGGER.log("Initialization failed", Level.ERROR, true);
			}
		});

		// Multi player button
		MenuButton multiPlayer = new MenuButton(main, new Image("menu/MultiPlayerButton.png"), new Vector2i(0, 150));
		multiPlayer.setOnClick(() -> {
			sbg.enterState(AsteroidsGame.STATE_PLAY);
			try {
				((NormalPlayState) sbg.getState(AsteroidsGame.STATE_PLAY)).setMultiplayer(true);
				((DefaultPlayState) sbg.getState(AsteroidsGame.STATE_PLAY)).setDifficulty(selector.getItem());
				sbg.getState(AsteroidsGame.STATE_PLAY).init(gc, sbg);
			} catch (SlickException e) {
				LOGGER.log("Initialization failed", Level.ERROR, true);
			}
		});
		
		// Rampage button
		MenuButton rampage = new MenuButton(main, new Image("menu/RampageButton.png"), new Vector2i(0, 250));
		rampage.setOnClick(() -> {
			sbg.enterState(AsteroidsGame.STATE_RAMPAGE);
			try {
				((NormalPlayState) sbg.getState(AsteroidsGame.STATE_RAMPAGE)).setMultiplayer(false);
				sbg.getState(AsteroidsGame.STATE_RAMPAGE).init(gc, sbg);
			} catch (SlickException e) {
				LOGGER.log("Initialization failed", Level.ERROR, true);
			}
		});

		// Exit button
		MenuButton exit = new MenuButton(main, new Image("menu/ExitButton.png"), new Vector2i(0, 350));
		exit.setOnClick(() -> {
			LOGGER.log("Game exited by user", Level.INFO, true);
			System.exit(0);
		});

		// Append all MenuComponents to the main menu
		main.append(singlePlayer);
		main.append(multiPlayer);
		main.append(exit);
		main.append(rampage);
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
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {}

	/**
	 * Initial state
	 */
	@Override
	public int getID() {
		return 0;
	}

	/**
	 * @return The menu MenoComponent.
	 */
	public Menu getMenu() {
		return menu;
	}

}
