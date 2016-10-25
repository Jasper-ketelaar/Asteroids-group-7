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

		// BEGIN DEBUG BOUNDING BOX
		g.draw(player.getBoundingBox());
		asteroids.forEach(a -> g.draw(a.getBoundingBox()));
		player.getFiredBullets().forEach(b -> g.draw(b.getBoundingBox()));
		// END DEBUG BOUNDING BOX

		// Update PowerUps
		if (player.getPowerUp().isNullPowerUp()) {
			PowerUp pw = player.getPowerUp();
			g.setColor(pw.getType().getColor()); // set color of PowerUp
			g.drawString(pw.getType().toString(), gc.getWidth() / 2 - 50, 10); // draw
																				// PowerUp
																				// name
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

		/*
		 * Update asteroids, play player explode animation, split asteroids,
		 */
		updateAsteroids(asteroids, player);

		/* update power ups */
		updatePowerups(powerUps, player);

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

	public Player getPlayer() {
		return this.player;
	}

	/**
	 * @return The score of the Player
	 */
	@Override
	public int getScore() {
		return player.getScore();
	}

}