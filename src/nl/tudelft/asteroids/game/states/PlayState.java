package nl.tudelft.asteroids.game.states;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import nl.tudelft.asteroids.model.entity.Asteroid;
import nl.tudelft.asteroids.model.entity.Bullet;
import nl.tudelft.asteroids.model.entity.Player;

/**
 * The play state of the Asteroids game. The actual gameplay is executed in this
 * state.
 * 
 * @author Bernard
 *
 */
public class PlayState extends BasicGameState {

	private Player player;

	private List<Asteroid> asteroids = new ArrayList<>();
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
	 * Initializes the PlayState. The Player, Asteroids and sound are added to
	 * the game. Prints load time to console.
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame arg1) throws SlickException {
		long curr = System.currentTimeMillis(); // measure load time
		try {
			Audio audio = AudioLoader.getAudio("WAV",
					ResourceLoader.getResourceAsStream("resources/sfx/music_loop.wav"));
			audio.playAsMusic(1, 1, true);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.player = new Player(new Vector2f(gc.getWidth() / 2, gc.getHeight() / 2));
		this.player.init();
		asteroids.add(new Asteroid(new Vector2f(gc.getWidth() / 2, 0), 0, 3));
		asteroids.add(new Asteroid(new Vector2f(gc.getWidth() / 2, 0), 0, 1));

		System.out.println("Loaded in " + (System.currentTimeMillis() - curr) + " ms");
	}

	/**
	 * Renders the Player (Bullets are rendered in the Player Class), Asteroids
	 * and background.
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0);
		player.render(g);
		asteroids.stream().forEach(e -> e.render(g));
	}

	/**
	 * Updates the Player and Asteroids. Handles Bullet/Asteroid collision.
	 * Plays the explosion sound.
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		player.update(gc, delta);
		if (player.getExplosion().isStopped()) {gc.exit();}

		asteroids = asteroids.stream()
		.filter(e -> !e.getExplosion().isStopped())
		.collect(Collectors.toList());
		
		asteroids.stream().forEach(e -> {
			if(player.getExplosion().getFrame() < player.getExplosion().getFrameCount() && player.collide(e))
				player.playExplosion();
			if(e.getExplosion().getFrame() > 0) {
				for(Bullet b : player.getFiredBullets()) {
					if (b.collide(e)) {
						System.out.println("Bullet/Asteroid intersect");
						try {
							e.destroyAsteroid(asteroids);
						} catch (SlickException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						player.getFiredBullets().remove(b);
					}
				}
			}
		});
	}

	/**
	 * Override method.
	 */
	@Override
	public int getID() {
		return 0;
	}

}
