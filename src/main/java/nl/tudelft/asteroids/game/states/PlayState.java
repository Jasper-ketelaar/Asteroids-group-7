package nl.tudelft.asteroids.game.states;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

import nl.tudelft.asteroids.model.entity.Asteroid;
import nl.tudelft.asteroids.model.entity.Bullet;
import nl.tudelft.asteroids.model.entity.Player;
import nl.tudelft.asteroids.model.entity.PowerUp;
import nl.tudelft.asteroids.util.Logger;
import nl.tudelft.asteroids.util.Logger.Level;
import nl.tudelft.asteroids.util.Util;

/**
 * The play state of the Asteroids game. The actual gameplay is executed in this
 * state.
 * 
 * @author Leroy Velzel, Bernard Bot, Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public class PlayState extends BasicGameState {

	private final static Logger LOGGER = Logger.getInstance(PlayState.class.getName());

	private Player player;

	private List<Asteroid> asteroids = new ArrayList<>();
	private List<PowerUp> powerUps = new ArrayList<>();
	private final Image background;

	/**
	 * Constructor; sets background sprite.
	 * 
	 * @param background
	 */
	public PlayState(Image background) {
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
			Audio audio = AudioLoader.getAudio("WAV",
					ResourceLoader.getResourceAsStream("resources/sfx/music_loop.wav"));
			audio.playAsMusic(1, 1, true);
			LOGGER.log("Background music loaded");
		} catch (IOException e) {
			LOGGER.log("IOException occured: music loop file", Level.ERROR, true);
		}

		this.player = new Player(new Vector2f(gc.getWidth() / 2, gc.getHeight() / 2));
		this.player.init();

		LOGGER.log("Game was loaded in " + (System.currentTimeMillis() - curr) + " ms");
	}

	/**
	 * Renders the Player (Bullets are rendered in the Player Class), Asteroids
	 * and background.
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0);
		player.render(g);
		asteroids.stream().forEach(e -> e.render(g));
		powerUps.stream().forEach(p -> p.render(g));
	}

	/**
	 * Updates the Player and Asteroids. Handles Bullet/Asteroid collision.
	 * Plays the explosion sound.
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		/* update player, exit game when player has exploded */
		player.update(gc, delta);
		if (player.getExplosion().isStopped()) {
			LOGGER.log("Player collided with asteroid and died");
			LOGGER.log("Game over! The score was  " + player.getScore());
			gc.exit();
		}

		/*
		 * algorithm for randomly spawning in asteroids when there are too
		 * little asteroids on the screen
		 */
		int max = (int) (2 + Math.floor(player.getScore() / 2000));
		if (asteroids.size() < max) {
			asteroids.add(new Asteroid(Util.randomLocation(player, gc), 0, 1));
		}

		/*
		 * algorithm for randomly spawning in power ups when there are too
		 * little asteroids on the screen
		 */
		int max_power_ups = (int) (1 + Math.floor(player.getScore() / 2000));
		if (powerUps.size() < max_power_ups) {
			powerUps.add(new PowerUp(Util.randomLocation(player, gc)));
		}

		/* update asteroids, play player explode animation, split asteroids, */
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
			 * if the player is colliding with the asteroid or the explosion was
			 * already playing, continue playing the explosion
			 */
			if (player.collide(asteroid) && player.getExplosion().getFrame() == 0) {
				player.playExplosion();
				continue;
			}

			/*
			 * iterate over the bullets and remove them; iterator used to
			 * prevent ConcurrentModificationException
			 */
			Iterator<Bullet> bullets = player.getFiredBullets().iterator();
			while (bullets.hasNext()) {
				Bullet b = bullets.next();
				if (b.collide(asteroid)) {
					player.updateScore(asteroid.getPoints());
					asteroid.splitAsteroid(iterator);
					bullets.remove();
				}
			}
		}

		/* update power ups */
		ListIterator<PowerUp> power_up_iterator = powerUps.listIterator();
		while (power_up_iterator.hasNext()) {
			PowerUp pUp = power_up_iterator.next();
			if (player.collide(pUp)) {
				pUp.setPickupTime();
				player.getPowerUps().add(pUp);
				power_up_iterator.remove();
				LOGGER.log("Power up picked up and removed from screen");
			}
			else if(pUp.creationTimeElapsed() > pUp.creationDuration) {
				power_up_iterator.remove();
				LOGGER.log("Power up despawned after being on screen to long");
			}
		}
		LOGGER.update();
	}

	/**
	 * Override method.
	 */
	@Override
	public int getID() {
		return 0;
	}

}
