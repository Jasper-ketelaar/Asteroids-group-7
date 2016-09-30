package nl.tudelft.asteroids.game.states;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import nl.tudelft.asteroids.factory.PowerupFactory;
import nl.tudelft.asteroids.model.entity.dyn.explodable.Asteroid;
import nl.tudelft.asteroids.model.entity.stat.PowerUp;

import nl.tudelft.asteroids.util.Logger;
import nl.tudelft.asteroids.util.Logger.Level;

/**
 * The play state of the Asteroids game. The actual gameplay is executed in this
 * state.
 * 
 * @author Leroy Velzel, Bernard Bot, Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public abstract class DefaultPlayState extends BasicGameState {

	protected final static Logger LOGGER = Logger.getInstance(DefaultPlayState.class.getName());

	private final static String MUSIC_LOOP = "sfx/music_loop.wav";

	private final static Vector2f SCORE_LOCATION = new Vector2f(8, 22);

	private Random random = new Random();

	private PowerupFactory powerupFactory = new PowerupFactory();

	protected final List<Asteroid> asteroids = new ArrayList<>();
	protected final List<PowerUp> powerUps = new ArrayList<>();
	private final Image background;

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
		try {
			Audio audio = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(MUSIC_LOOP));
			audio.playAsMusic(1, 1, true);
			LOGGER.log("Background music loaded");
		} catch (IOException e) {
			LOGGER.log("IOException occured: music loop file", Level.ERROR, true);
		}

		LOGGER.log("Game was loaded in " + (System.currentTimeMillis() - curr) + " ms");
	}

	/**
	 * Renders the Player (Bullets are rendered in the Player Class), Asteroids
	 * and background.
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0);
		g.setColor(Color.white);
		asteroids.stream().forEach(e -> e.render(g));
		powerUps.forEach(p -> p.render(g));
		g.drawString("SCORE: " + getScore(), SCORE_LOCATION.x, SCORE_LOCATION.y);
		asteroids.forEach(e -> {
			e.render(g);
		});
	}

	/**
	 * Updates the Player and Asteroids. Handles Bullet/Asteroid collision.
	 * Plays the explosion sound.
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		/*
		 * algorithm for randomly spawning in power ups when there are too
		 * little asteroids on the screen
		 */
		if (powerUps.size() < 3 && powerupFactory.requiresPowerup()) {
			powerUps.add(powerupFactory.create(gc));
			LOGGER.log("A new power up is spawned");
		}

		int max = (int) (2 + Math.floor(getScore() / 2000));
		if (asteroids.size() < max) {
			float randomX = random.nextBoolean() ? random.nextFloat() * (gc.getWidth() / 2)
					: random.nextFloat() * (gc.getWidth() / 2) + gc.getWidth() / 2;
			float randomY = random.nextBoolean() ? random.nextFloat() * (gc.getHeight() / 2)
					: random.nextFloat() * (gc.getHeight() / 2) + gc.getHeight() / 2;

			Vector2f randomPosition = new Vector2f(randomX, randomY);
			Asteroid asteroid = new Asteroid(randomPosition, 0, 1);
			asteroids.add(asteroid);
		}

		/*
		 * update asteroids, play player explode animation, split asteroids,
		 */
		ListIterator<Asteroid> iterator = asteroids.listIterator();
		while (iterator.hasNext()) {
			Asteroid asteroid = iterator.next();
			asteroid.update(gc);
			if (asteroid.getExplosion().isStopped()) {
				LOGGER.log("Asteroid destroyed and instance removed from the game");
				iterator.remove();
				continue;
			}

			/*
			 * if the asteroid explosion is playing, don't make any further
			 * calculations with this asteroid
			 */
			if (asteroid.getExplosion().getFrame() > 0)
				continue;

			/*
			 * if the asteroid explosion is playing, don't make any further
			 * calculations with this asteroid
			 */
			if (asteroid.getExplosion().getFrame() > 0)
				continue;

		}

		LOGGER.update();

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
