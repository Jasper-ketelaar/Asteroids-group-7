package nl.tudelft.asteroids.game.states;

import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import nl.tudelft.asteroids.model.entity.dyn.explodable.Asteroid;
import nl.tudelft.asteroids.model.entity.dyn.explodable.playable.Player;

/**
 * Play state describing a Rampage play state in which the goal is to fly into
 * as many asteroids as possible within a certain time frame.
 * 
 * @author Jasper
 *
 */
public class RampagePlayState extends NormalPlayState {

	/**
	 * Time limit (currently 100)
	 */
	private static final int TIME_LIMIT = 100 * 1000;

	/**
	 * Starting time
	 */
	private long start;

	/**
	 * Score multiplier for combo
	 */
	private double multiplier = 1.0;

	/**
	 * Time at which last asteroid was hit
	 */
	private long lastHit = 0;

	/**
	 * Initialize the play state
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame arg1) throws SlickException {
		super.init(gc, arg1);
		this.start = System.currentTimeMillis();
		this.addPlayer(gc);
		this.players.get(0).disableFire();
	}

	/**
	 * Render the play state
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g) throws SlickException {
		super.render(gc, arg1, g);
		if (players.size() > 0) {
			g.drawString(
					"Time left: "
							+ String.format("%d", (TIME_LIMIT - (System.currentTimeMillis() - this.start)) / 1000),
					8, 35);
		}
		if (multiplier > 1.0 && players.size() > 0) {
			g.drawString(String.format("%.1fX", multiplier), players.get(0).getX(), players.get(0).getY());
		}
	}

	/**
	 * Update the play state, if the passed time is bigger than the time limit
	 * you are returned to the main menu.
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		super.update(gc, sbg, delta);
		if (System.currentTimeMillis() - start > TIME_LIMIT) {
			players.clear();
			sbg.enterState(0);
		}
	}

	@Override
	public void spawnAsteroids(GameContainer gc) throws SlickException {
		int max = 1 + (int) Math.ceil(((System.currentTimeMillis() - start) / (10 * 1000)));
		if (asteroids.size() < max) {
			asteroids.add(new Asteroid(randomLocation(gc), 0, 1, difficulty.getDifficulty()));
		}
	}

	/**
	 * Overidden update asteroids to explode on collision with player instead of
	 * default behaviour
	 */
	@Override
	public void updateAsteroids(List<Asteroid> asteroids, Player player, GameContainer gc) {
		Iterator<Asteroid> iterator = asteroids.iterator();
		while (iterator.hasNext()) {
			Asteroid current = iterator.next();
			current.update(gc);
			
			if (current.getExplosion().isStopped()) {
				iterator.remove();
				continue;
			}
			if (player.collide(current) && current.getExplosion().getFrame() == 0) {
				current.playExplosion();
				player.updateScore((int) (multiplier * current.getPoints()));
				if (System.currentTimeMillis() - lastHit < 1000) {
					multiplier *= 1.1;
				} else {
					multiplier = 1;
				}
				lastHit = System.currentTimeMillis();
			}

		}
	}

	/**
	 * Play state ID
	 */
	@Override
	public int getID() {
		return 2;
	}
}
