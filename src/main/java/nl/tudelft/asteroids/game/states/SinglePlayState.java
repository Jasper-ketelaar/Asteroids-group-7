package nl.tudelft.asteroids.game.states;

import java.util.Iterator;
import java.util.ListIterator;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import nl.tudelft.asteroids.model.entity.dyn.Bullet;
import nl.tudelft.asteroids.model.entity.dyn.explodable.Asteroid;
import nl.tudelft.asteroids.model.entity.dyn.explodable.playable.Player;
import nl.tudelft.asteroids.model.entity.stat.PowerUp;

/**
 * The play state of the Asteroids game. The actual gameplay is executed in this
 * state.
 * 
 * @author Leroy Velzel, Bernard Bot, Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public class SinglePlayState extends DefaultPlayState {

	private Player player;

	/**
	 * Constructor; sets background sprite.
	 * 
	 * @param background
	 */
	public SinglePlayState(Image background) {
		super(background);
	}

	/**
	 * Initializes the PlayState. The Player, Asteroids and sound are added to
	 * the game. Prints load time to console.
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame arg1) throws SlickException {
		super.init(gc, arg1);

		this.player = new Player(new Vector2f(gc.getWidth() / 2, gc.getHeight() / 2));
		this.player.init();
	}

	/**
	 * Renders the Player (Bullets are rendered in the Player Class), Asteroids
	 * and background.
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g) throws SlickException {
		super.render(gc, arg1, g);
		player.render(g);
		
		//Update PowerUps
		if (player.getPowerUp().isNullPowerUp()) {
			PowerUp pw = player.getPowerUp();
			g.setColor(pw.getType().getColor()); //set color of PowerUp
			g.drawString(pw.getType().toString(), gc.getWidth() / 2 - 50, 10); //draw PowerUp name 
		}
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
			asteroids.clear();
			sbg.enterState(0);
		}

		/* update asteroids, play player explode animation, split asteroids, */
		ListIterator<Asteroid> iterator = asteroids.listIterator();
		while (iterator.hasNext()) {
			Asteroid asteroid = iterator.next();
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
				if (b.collide(asteroid) && asteroid.getExplosion().getFrame() == 0) {
					player.updateScore(asteroid.getPoints());
					asteroid.splitAsteroid(iterator);
					bullets.remove();
				}
			}
		}
		
		/* update power ups */
		Iterator<PowerUp> power_up_it = powerUps.listIterator();
		while (power_up_it.hasNext()) {
			PowerUp powerUp = power_up_it.next();
			if (player.collide(powerUp)) {
				powerUp.setPickupTime();
				player.setPowerUp(powerUp);
				power_up_it.remove();
				
				LOGGER.log("Power up picked up and removed from screen");
			} else if (powerUp.creationTimeElapsed() > PowerUp.DISAPPEAR_AFTER) {
				power_up_it.remove();
				
				LOGGER.log("Power up despawned after being on screen to long");
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
		return 1;
	}

	/**
	 * @return The score of the Player
	 */
	@Override
	public int getScore() {
		return player.getScore();
	}

}
