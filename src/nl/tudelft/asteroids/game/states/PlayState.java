package nl.tudelft.asteroids.game.states;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import nl.tudelft.asteroids.model.entity.Asteroid;
import nl.tudelft.asteroids.model.entity.Player;

public class PlayState extends BasicGameState {

	private Player player;

	private final ArrayList<Asteroid> asteroids = new ArrayList<>();
	private final Image background;

	public PlayState(Image background) {
		this.background = background;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame arg1) throws SlickException {
		this.player = new Player(new Vector2f(gc.getWidth() / 2, gc.getHeight() / 2), 0);
		asteroids.add(new Asteroid(new Vector2f(gc.getWidth() / 2, 0), 0));
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0);
		player.render(g);
		for (Asteroid as : asteroids) {
			as.render(g);
		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		player.update(gc, delta);
		for (Asteroid as : asteroids) {
			as.update(gc);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
