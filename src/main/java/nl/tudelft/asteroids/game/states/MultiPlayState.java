package nl.tudelft.asteroids.game.states;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import nl.tudelft.asteroids.model.entity.Asteroid;
import nl.tudelft.asteroids.model.entity.Bullet;
import nl.tudelft.asteroids.model.entity.Player;

/**
 * The play state of the Asteroids game. The actual gameplay is executed in this
 * state.
 * 
 * @author Leroy Velzel, Bernard Bot, Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public class MultiPlayState extends DefaultPlayState {

	private List<Player> players = new ArrayList<>();

	/**
	 * Constructor; sets background sprite.
	 * 
	 * @param background
	 */
	public MultiPlayState(Image background) {
		super(background);
	}

	/**
	 * Initializes the PlayState. The Player, Asteroids and sound are added to
	 * the game. Prints load time to console.
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame arg1) throws SlickException {
		super.init(gc, arg1);

		Player player = new Player(new Vector2f(gc.getWidth() / 2, gc.getHeight() / 2));
		player.init();
		players.add(player);

		player = new Player(new Vector2f(gc.getWidth() / 3, gc.getHeight() / 3));
		player.init();
		player.bindKeys(Input.KEY_W, Input.KEY_A, Input.KEY_D, Input.KEY_SPACE);
		players.add(player);

	}

	/**
	 * Renders the Player (Bullets are rendered in the Player Class), Asteroids
	 * and background.
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g) throws SlickException {
		super.render(gc, arg1, g);
		players.forEach(e -> {
			e.render(g);
		});
	}

	/**
	 * Updates the Player and Asteroids. Handles Bullet/Asteroid collision.
	 * Plays the explosion sound.
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		/* update player, exit game when player has exploded */
		Iterator<Player> playerIterator = players.iterator();
		while (playerIterator.hasNext()) {
			Player player = playerIterator.next();
			player.update(gc, delta);
			if (player.getExplosion().isStopped()) {
				LOGGER.log("Player collided with asteroid and died");
				playerIterator.remove();
				if (players.size() == 0) {
					LOGGER.log("Game over! The score was  " + player.getScore());
					gc.exit();
				}
			}

			/*
			 * update asteroids, play player explode animation, split asteroids,
			 */
			ListIterator<Asteroid> iterator = asteroids.listIterator();
			while (iterator.hasNext()) {
				Asteroid asteroid = iterator.next();
				/*
				 * if the player is colliding with the asteroid or the explosion
				 * was already playing, continue playing the explosion
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
					if (b.collide(asteroid) && asteroid.getExplosion().getFrame() == 0) {
						player.updateScore(asteroid.getPoints());
						asteroid.splitAsteroid(iterator);
						bullets.remove();
					}
				}
			}

		}

		super.update(gc, sbg, delta);
		LOGGER.update();
	}

	/**
	 * Override method.
	 */
	@Override
	public int getID() {
		return 0;
	}

	@Override
	public int getScore() {
		return players.stream().mapToInt(e -> e.getScore()).sum();
	}

}
