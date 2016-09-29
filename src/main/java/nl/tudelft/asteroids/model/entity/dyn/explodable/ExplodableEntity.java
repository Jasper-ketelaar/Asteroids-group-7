package nl.tudelft.asteroids.model.entity.dyn.explodable;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.openal.Audio;

import nl.tudelft.asteroids.model.entity.Entity;
import nl.tudelft.asteroids.util.Util;

/**
 * Game Entity that can play an explode animation, using the explosion sprites
 * located in the "resources/asteroid" package. Created on top of the Entity
 * Class.
 * 
 * @author Leroy Velzel, Bernard Bot, Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public class ExplodableEntity extends Entity {

	private static final int EXPLOSION_SPEED = 35;

	private static final String EXPLOSION_AUDIO_FILE = "explode.wav";

	private static Image[] big = new Image[8];
	private static Image[] medium = new Image[8];
	private static Image[] small = new Image[8];

	static {
		try {
			for (int i = 0; i < big.length; i++) {
				String name = String.format("resources/asteroid/Explosion-%d.png", i + 1);
				big[i] = new Image(name);
				medium[i] = new Image(name).getScaledCopy(0.7f);
				small[i] = new Image(name).getScaledCopy(0.4f);
			}
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	private Animation explosion;
	private final Audio explosionAudio;

	/**
	 * Constructor. Sets explosion speed.
	 * 
	 * @param sprite
	 *            The sprite used for the explosion
	 * @param pos
	 *            The position of the Entity
	 * @param rotation
	 *            The rotation of the Entity
	 * @throws SlickException
	 */
	public ExplodableEntity(Image sprite, Vector2f pos, float rotation) throws SlickException {
		super(sprite, pos, rotation);
		explosion = new Animation(big, EXPLOSION_SPEED);
		explosion.setLooping(false);
		this.explosionAudio = Util.load("WAV", EXPLOSION_AUDIO_FILE);
	}

	public ExplodableEntity(Image sprite, Vector2f pos, float rotation, int size) throws SlickException {
		super(sprite, pos, rotation);
		explosion = new Animation();
		switch (size) {
		case 3:
			explosion = new Animation(small, EXPLOSION_SPEED);
			break;
		case 2:
			explosion = new Animation(medium, EXPLOSION_SPEED);
			break;
		default:
			explosion = new Animation(big, EXPLOSION_SPEED);
			break;
		}
		explosion.setLooping(false);
		this.explosionAudio = Util.load("WAV", EXPLOSION_AUDIO_FILE);
	}

	/**
	 * Custom Constructor, only takes a vector.
	 * 
	 * @param pos
	 *            The position of the Entity
	 * @throws SlickException
	 */
	public ExplodableEntity(Vector2f pos) throws SlickException {
		this(null, pos, 0);
	}

	/**
	 * Plays the explosion animation and sound.
	 */
	public void playExplosion() {
		setAnimation(explosion);
		explosion.update(0);
		explosionAudio.playAsSoundEffect(1, 1, false);

	}

	/**
	 * @return The explosion animation
	 */
	public Animation getExplosion() {
		return explosion;
	}

}
