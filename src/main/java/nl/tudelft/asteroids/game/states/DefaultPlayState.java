package nl.tudelft.asteroids.game.states;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import nl.tudelft.asteroids.game.Difficulty;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import nl.tudelft.asteroids.factory.PowerupFactory;
import nl.tudelft.asteroids.model.entity.dyn.explodable.Asteroid;
import nl.tudelft.asteroids.model.entity.stat.PowerUp;

import nl.tudelft.asteroids.util.Logger;
import nl.tudelft.asteroids.util.Util;

/**
 * The play state of the Asteroids game. The actual gameplay is executed in this
 * state. This is an abstract class extended by the multiplayer and single
 * player play states.
 * 
 * @author Leroy Velzel, Bernard Bot, Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public abstract class DefaultPlayState extends BasicGameState {

	protected final static Logger LOGGER = Logger.getInstance(DefaultPlayState.class.getName());

	private final static String MUSIC_LOOP = "music_loop.wav";
	private final static Vector2f SCORE_LOCATION = new Vector2f(8, 22);

	private PowerupFactory powerupFactory = new PowerupFactory();

	protected final List<Asteroid> asteroids = new ArrayList<>();
	protected final List<PowerUp> powerUps = new ArrayList<>();

	private final Image background;

	private Difficulty difficulty = Difficulty.MEDIUM;

	/**
	 * Constructor; sets background sprite.
	 * 
	 * @param background
	 */
	public DefaultPlayState(Image background) {
		this.background = background;
	}

	/**
	 * Initializes the PlayState. The Player, Asteroids and sound are added to
	 * the game. Prints load time to console.
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame arg1) throws SlickException {
		long curr = System.currentTimeMillis(); // measure load time

		Audio audio = Util.loadAudio(MUSIC_LOOP);
		System.out.println(arg1.getCurrentStateID());
		if (!audio.isPlaying())
			audio.playAsMusic(1, 1, true);
		System.out.println("True");

		LOGGER.log("Background music loaded");

		LOGGER.log("Game was loaded in " + (System.currentTimeMillis() - curr) + " ms");
	}

	/**
	 * Renders the Player (Bullets are rendered in the Player Class), Asteroids
	 * and background.
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g) throws SlickException {
		// draw background
		g.drawImage(background, 0, 0);

		g.drawString(difficulty.toString(), gc.getWidth() - g.getFont().getWidth(difficulty.toString()), 0);

		// render Asteroids, PowerUps
		powerUps.forEach(p -> p.render(g));
		asteroids.forEach(a -> a.render(g));

		// set color of font and draw SCORE
		g.setColor(Color.white);
		g.drawString("SCORE: " + getScore(), SCORE_LOCATION.x, SCORE_LOCATION.y);

	}

	/**
	 * Updates the Player and Asteroids. Handles Bullet/Asteroid collision.
	 * Plays the explosion sound.
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		/*
		 * Algorithm for randomly spawning in power ups when there are too
		 * little power ups on the screen.
		 */
		if (powerUps.size() < 3 && powerupFactory.requiresPowerup(difficulty.getDifficulty())) {
			powerUps.add(powerupFactory.create(gc));
			LOGGER.log("A new PowerUp spawned");
		}

		/*
		 * Algorithm for randomly spawning in asteroids when there are too
		 * little asteroids on the screen. A higher score means that more
		 * asteroids can be spawned.
		 */
		int max = (int) (difficulty.getDifficulty() + Math.floor(getScore() / 2000));
		if (asteroids.size() < max) {
			asteroids.add(new Asteroid(Util.randomLocation(gc), 0, 1, difficulty.getDifficulty()));
		}

		/*
		 * Update asteroids
		 */
		ListIterator<Asteroid> iterator = asteroids.listIterator();
		while (iterator.hasNext()) {
			Asteroid asteroid = iterator.next();
			asteroid.update(gc);

			// remove asteroid when it has exploded
			if (asteroid.getExplosion().isStopped()) {
				iterator.remove();
				LOGGER.log("Asteroid destroyed and instance removed from the game");
				continue;
			}
		}
		LOGGER.update();
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public abstract int getScore();

	/**
	 * Override method.
	 */
	@Override
	public int getID() {
		return 0;
	}

}
