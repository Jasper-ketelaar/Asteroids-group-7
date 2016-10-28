package nl.tudelft.asteroids.game.states;

import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import nl.tudelft.asteroids.model.entity.dyn.explodable.Asteroid;
import nl.tudelft.asteroids.model.entity.dyn.explodable.playable.Player;

public class RampagePlayState extends NormalPlayState {

	//100 seconds (in miliseconds)
	private static final int TIME_LIMIT = 10 * 1000;
	
	private long start;

	@Override
	public void init(GameContainer gc, StateBasedGame arg1) throws SlickException {
		super.init(gc, arg1);
		this.start = System.currentTimeMillis();
		System.out.println("Test13123");
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		super.update(gc, sbg, delta);
		if (System.currentTimeMillis() - start > TIME_LIMIT) {
			players.clear();
			sbg.enterState(0);
		}
	}

	@Override
	public void updateAsteroids(List<Asteroid> asteroids, Player player) {
		Iterator<Asteroid> iterator = asteroids.iterator();
		while (iterator.hasNext()) {
			Asteroid current = iterator.next();
			if (current.getExplosion().isStopped()) {
				iterator.remove();
			}

			if (player.collide(current) && current.getExplosion().getFrame() == 0) {
				current.playExplosion();
				player.updateScore(current.getPoints());

			}

		}
	}

	@Override
	public int getID() {
		return 2;
	}
}
