package nl.tudelft.asteroids.model.entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;

/**
 * Game Entity that can play an explode animation, 
 * using the explosion sprites located in the "resources/asteroid" package.
 * Created on top of the Entity Class.
 * 
 * @author Leroy Velzel, Bernard Bot, 
 * Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public class ExplodableEntity extends Entity {

	private static final int EXPLOSION_SPEED = 35;

	private static Image[] sprites;

	static {
		try {
			sprites = new Image[] { new Image("resources/asteroid/Explosion-1.png"),
					new Image("resources/asteroid/Explosion-2.png"), new Image("resources/asteroid/Explosion-3.png"),
					new Image("resources/asteroid/Explosion-4.png"), new Image("resources/asteroid/Explosion-5.png"),
					new Image("resources/asteroid/Explosion-6.png"), new Image("resources/asteroid/Explosion-7.png"),
					new Image("resources/asteroid/Explosion-8.png") };
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	private final Animation explosion;
	private final Sound explSound;
	
	/**
	 * Constructor. Sets explosion speed.
	 * 
	 * @param 	sprite
	 * 			The sprite used for the explosion
	 * @param 	pos
	 * 			The position of the Entity
	 * @param 	rotation
	 * 			The rotation of the Entity
	 * @throws SlickException
	 */
	public ExplodableEntity(Image sprite, Vector2f pos, float rotation) throws SlickException {
		super(sprite, pos, rotation);
		explosion = new Animation(sprites, EXPLOSION_SPEED);
		explosion.setLooping(false);
		this.explSound = new Sound("resources/sfx/explode1.ogg");
	}
	
	/**
	 * Custom Constructor, only takes a vector.
	 * 
	 * @param 	pos
	 * 			The position of the Entity
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
		explosion.start();
		if (!explSound.playing())
			explSound.play();
		
	}
	
	/**
	 * @return The explosion animation
	 */
	public Animation getExplosion() {
		return explosion;
	}

}
