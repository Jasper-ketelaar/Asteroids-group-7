package nl.tudelft.asteroids.game.states;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import nl.tudelft.asteroids.model.entity.dyn.explodable.playable.Player;
import nl.tudelft.asteroids.model.entity.stat.PowerUp;

/**
 * The play state of the Asteroids game. The actual gameplay is executed in this
 * state.
 *
 * @author Leroy Velzel, Bernard Bot, Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public class NormalPlayState extends DefaultPlayState {

	protected List<Player> players = new ArrayList<>();
	private boolean multiplayer;

	/**
	 * Initializes the PlayState. The Player, Asteroids and sound are added to
	 * the game. Prints load time to console.
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame arg1) throws SlickException {
		super.init(gc, arg1);

		Player player1 = new Player(new Vector2f(gc.getWidth() / 2, gc.getHeight() / 2));
		player1.init();
		players.add(player1);

		if (multiplayer) {
			Player player2 = new Player(new Vector2f(gc.getWidth() / 3, gc.getHeight() / 3));
			player2.init();
			player2.bindKeys(Input.KEY_W, Input.KEY_A, Input.KEY_D, Input.KEY_SPACE);
			players.add(player2);
		}
	}

	/**
	 * Renders the Player (Bullets are rendered in the Player Class), Asteroids
	 * and background.
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g) throws SlickException {
		super.render(gc, arg1, g);

		players.forEach(p -> p.render(g));
		
		int activePowerUps = 0; // used to draw powerup text beneath each other
		for (int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			if (!p.getPowerUp().isNullPowerUp()) {
				drawPowerUps(g, gc, p.getPowerUp(), activePowerUps);
				activePowerUps++;
			}
		}
	}

	/**
	 * Draw powerUps on the top of the screen.
	 */
	private static void drawPowerUps(Graphics g, GameContainer gc, PowerUp pw, int activePowerUps) {
		g.setColor(pw.getType().getColor());
		g.drawString(pw.getType().toString(), gc.getWidth() / 2 - 50, 10 + (activePowerUps * 10));
	}

	/**
	 * Updates the Player and Asteroids. Handles Bullet/Asteroid collision.
	 * Plays the explosion sound.
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Iterator<Player> playerIterator = players.iterator();
		while (playerIterator.hasNext()) {
			Player player = playerIterator.next();

			// Update player
			player.update(gc, delta);

			// Death check
			if (player.getExplosion().isStopped()) {
				playerIterator.remove();
				LOGGER.log("Player collided with asteroid and died");

				if (players.size() == 0) {
					asteroids.clear();
					sbg.enterState(0);
					LOGGER.log("Game over! The score was  " + player.getScore());
				}
			}

			// Update Asteroids (super class) and PowerUps (this class)
			updateAsteroids(asteroids, player);
			updatePowerUps(player);
		}

		super.update(gc, sbg, delta);
		LOGGER.update();
	}

	protected void updatePowerUps(Player player) {
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
	}
	
	/**
	 * @param multiplayer Boolean indicating muti- or single player.
	 */
	public void setMultiplayer(boolean multiplayer) {
		this.multiplayer = multiplayer;
	}
	
	/**
	 * @return List containing the players in this playstate.
	 */
	public List<Player> getPlayers() {
		return this.players;
	}

	/**
	 * Override method.
	 */
	@Override
	public int getID() {
		return 1;
	}

	/**
	 * @return The sum of both Players scores.
	 */
	@Override
	public int getScore() {
		return players.stream().mapToInt(e -> e.getScore()).sum();
	}

}