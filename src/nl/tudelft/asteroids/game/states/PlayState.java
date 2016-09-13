package nl.tudelft.asteroids.game.states;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import nl.tudelft.asteroids.model.entity.Asteroid;
import nl.tudelft.asteroids.model.entity.Bullet;
import nl.tudelft.asteroids.model.entity.Entity;
import nl.tudelft.asteroids.model.entity.Player;

/**
 * The play state of the Asteroids game.
 * The actual gameplay is executed in this state.
 * 
 * @author Bernard
 *
 */
public class PlayState extends BasicGameState {

	private Player player;

	private final ArrayList<Asteroid> asteroids = new ArrayList<>();
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
	 * Initializes the PlayState. The Player and Asteroids are added to the game.
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame arg1) throws SlickException {
		this.player = new Player(new Vector2f(gc.getWidth() / 2, gc.getHeight() / 2));
		this.player.init();
		asteroids.add(new Asteroid(new Vector2f(gc.getWidth() / 2, 0), 0, 1));
		Sound sound = new Sound("resources/sfx/music_loop.ogg");
		sound.loop(1f, 0.5f);
	}

	/**
	 * Renders the Player and Asteroid sprites on the screen.
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0);
		player.render(g);
		Iterator<Asteroid> iterator = asteroids.iterator();
		while (iterator.hasNext()) {
			Asteroid next = iterator.next();
			next.render(g);
		}
	}

	/**
	 * Updates the location (for now) of the Player and Asteroids.
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		player.update(gc, delta);

		ListIterator<Asteroid> iterator = asteroids.listIterator();
		while (iterator.hasNext()) {
			Asteroid next = iterator.next();
			if (next.getExplosion().isStopped()) {
				iterator.remove();
				continue;
			}
			next.update(gc);
			if (next.getExplosion().getFrame() > 0) {
				continue;
			}
			if(player.collide(next)){
				System.out.println("Player/Asteroid intersect");
			}
			Bullet[] activeBullets = new Bullet[player.getFiredBullets().size()];
			player.getFiredBullets().toArray(activeBullets);
			for(Bullet a : activeBullets) {
				if(a.collide(next)){
					System.out.println("Bullet/Asteroid intersect");
					next.destroyAsteroid(iterator);
					
					player.getFiredBullets().remove(a);
					break;
				}
			}
		}
		
		
	}
	

	/**
	 * Override method.
	 */
	@Override
	public int getID() {
		return 0;
	}

}
