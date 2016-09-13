package nl.tudelft.asteroids.game.states;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

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
		long curr = System.currentTimeMillis();
		
		
		this.player = new Player(new Vector2f(gc.getWidth() / 2, gc.getHeight() / 2));
		this.player.init();
		asteroids.add(new Asteroid(new Vector2f(gc.getWidth() / 2, 0), 0, 3));
		try {
			Audio audio = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("resources/sfx/music_loop.wav"));
			audio.playAsMusic(1, 1, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Loaded in " + (System.currentTimeMillis() - curr) + " ms");
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

		Iterator<Asteroid> iterator = asteroids.iterator();
		while (iterator.hasNext()) {
			Asteroid next = iterator.next();
			if (next.getExplosion().isStopped()) {
				iterator.remove();
				continue;
			}
			
			if (player.getExplosion().isStopped()) {
				gc.exit();
			}
			next.update(gc);
			if(player.getExplosion().getFrame() < player.getExplosion().getFrameCount() && player.collide(next)) {
				player.playExplosion();
				continue;
			}
			Bullet[] activeBullets = new Bullet[player.getFiredBullets().size()];
			player.getFiredBullets().toArray(activeBullets);
			for(Bullet a : activeBullets) {
				if(a.collide(next)){
					System.out.println("Bullet/Asteroid intersect");
					next.playExplosion();
					player.getFiredBullets().remove(a);
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
