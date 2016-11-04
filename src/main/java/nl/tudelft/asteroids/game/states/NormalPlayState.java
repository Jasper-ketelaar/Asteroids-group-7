package nl.tudelft.asteroids.game.states;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

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
public class NormalPlayState extends DefaultPlayState {

	protected List<Player> players = new ArrayList<>();

	/**
	 * Initializes the PlayState. The Player, Asteroids and sound are added to
	 * the game. Prints load time to console.
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame arg1) throws SlickException {
		super.init(gc, arg1);
	}

	/**
	 * Creates a new player
	 * 
	 * @throws SlickException
	 */
	public Player addPlayer(GameContainer gc) throws SlickException {
		System.out.println(gc.getWidth() + " size");
		Player player = new Player(
				new Vector2f(gc.getWidth() / (players.size() + 2), gc.getHeight() / (players.size() + 2)));
		player.init();
		players.add(player);
		return player;
	}
	
	/**
	 * Creates a new player with special keybinds
	 * 
	 * @throws SlickException
	 */
	public Player addPlayer(GameContainer gc, int... binds) throws SlickException {
		Player player = addPlayer(gc);
		player.bindKeys(binds[0], binds[1], binds[2], binds[3]);
		return player;
	}

	/**
	 * Render player and respective powerups on top of render of superclass.
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g) throws SlickException {
		super.render(gc, arg1, g);

		for (int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			PowerUp pw = p.getPowerUp();

			// render player and powerUp
			p.render(g);
			if (!pw.isNullPowerUp()) {
				g.setColor(pw.getType().getColor());
				g.drawString(pw.getType().toString(), gc.getWidth() / 2 - 50, 10 + (i * 10));
			}
		}
	}

	/**
	 * Updates the Player and Asteroids. Handles Bullet/Asteroid collision.
	 * Plays the explosion sound.
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// Spawn asteroids and powerups
		super.update(gc, sbg, delta);

		Iterator<Player> playerIterator = players.iterator();
		while (playerIterator.hasNext()) {
			Player player = playerIterator.next();
			// Update player (bullets included), death check
			player.update(gc, delta);
			deathCheck(sbg, player, playerIterator);
			// Update asteroids, powerups
			updateAsteroids(asteroids, player, gc);
			updatePowerUps(powerUps, player);
		}
		LOGGER.update();
	}

	/**
	 * Overridden to prevent asteroids from spawning on the location of the
	 * player.
	 */
	@Override
	public Vector2f randomLocation(GameContainer gc) {
		Vector2f location = super.randomLocation(gc);
		Asteroid asteroid = null;
		try {
			asteroid = new Asteroid(location, 0, 1, difficulty.getDifficulty());
		} catch (SlickException e1) {
			e1.printStackTrace();
		}

		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			if (player.collide(asteroid)) {
				location = super.randomLocation(gc);
				asteroid.setPosition(location);
				i = 0;
			}
		}
		return location;
	}

	/**
	 * Checks if the player has died.
	 * 
	 * @param sbg
	 *            The game the Player is currently playing
	 * @param player
	 *            The Player that is checked
	 * @param playerIterator
	 *            The Iterator is passed to be able to remove the Player.
	 */
	public void deathCheck(StateBasedGame sbg, Player player, Iterator<Player> playerIterator) {
		if (player.getExplosion().isStopped()) {
			playerIterator.remove();
			LOGGER.log("Player collided with asteroid and died");
			if (players.size() == 0) {
				asteroids.clear();
				players.clear();
				sbg.enterState(0);
				LOGGER.log("Game over! The score was  " + player.getScore());
			}
		}
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