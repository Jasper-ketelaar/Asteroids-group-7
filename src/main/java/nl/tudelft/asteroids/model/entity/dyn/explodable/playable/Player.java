package nl.tudelft.asteroids.model.entity.dyn.explodable.playable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.openal.Audio;

import nl.tudelft.asteroids.model.entity.Entity;
import nl.tudelft.asteroids.model.entity.dyn.Bullet;
import nl.tudelft.asteroids.model.entity.dyn.explodable.ExplodableEntity;
import nl.tudelft.asteroids.model.entity.stat.PowerUp;
import nl.tudelft.asteroids.util.Logger;
import nl.tudelft.asteroids.util.Util;
import nl.tudelft.asteroids.util.Logger.Level;

/**
 * Player controlling the spaceship and shooting bullets.
 * 
 * @author Leroy Velzel, Bernard Bot, Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public class Player extends ExplodableEntity {

	private final static Logger LOGGER = Logger.getInstance(Player.class.getName());

	private static final float VELOCITY_MULTIPLIER = 1.03f;
	private static final float ROTATION_SPEED = 2.5f;
	private static final float MAXIMUM_VELOCITY = 7;

	private static final int BULLET_ADJUSTMENT = 35;

	private List<Bullet> bullets = new ArrayList<>();

	private PowerUp powerUp = null;

	private Vector2f direction;
	private Vector2f movingDirection;

	private Animation still, moving;

	private Audio fire, thrust;

	private int score;
	private int multiplier; // used for power up

	private boolean invincible;

	private double velocity;

	/**
	 * Constructor.
	 * 
	 * @param position
	 * @param rotation
	 * @throws SlickException
	 */
	public Player(Vector2f position) throws SlickException {
		super(position);
		this.direction = new Vector2f(0, -1);
		this.fire = Util.load("WAV", "fire.wav");
		this.thrust = Util.load("WAV", "thrust.wav");
		this.score = 0;
		this.multiplier = 1;
		this.invincible = false;
		LOGGER.log("Player initialized", Level.INFO, true);
	}

	/**
	 * Creates moving and still animations for the plane controlled by the
	 * Player. The animation is initially set to still.
	 */
	public void init() {
		try {
			Image image = new Image("resources/Plane.png");

			Image canvasStill = new Image(image.getWidth(), image.getHeight());
			Graphics gfx = canvasStill.getGraphics();
			gfx.drawImage(image, 0, 0);
			gfx.flush();
			still = new Animation(new Image[] { canvasStill }, 50);
			LOGGER.log("Still animation loaded");

			Image exhaust = new Image("resources/Exhaust.png");
			Image canvasMoving = new Image(image.getWidth(), image.getHeight());
			gfx = canvasMoving.getGraphics();
			gfx.drawImage(image, 0, 0);
			gfx.drawImage(exhaust, 44, 53);
			gfx.drawImage(exhaust, 22, 53);
			gfx.flush();
			moving = new Animation(new Image[] { canvasMoving }, 50);
			LOGGER.log("Moving animation loaded");

			setAnimation(new Animation(new Image[] { canvasStill }, 50));
			LOGGER.update();
			LOGGER.putData("delta", new Object[] { 0 });
			LOGGER.putData("position", new Object[] { getPosition() });
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Updates the movement and bullets of the Player, based on the user's
	 * input. Spawns the Player on the opposite side of the screen when the
	 * Player goes off-screen. Updates the animation of the Player.
	 * 
	 * @param gc
	 * @param delta
	 */
	public void update(GameContainer gc, int delta) {
		if (!getAnimation().getImage(0).equals(still.getImage(0))
				&& !getAnimation().getImage(0).equals(moving.getImage(0))) {
			System.out.println(getAnimation().getImage(0).equals(still.getImage(0)));
			getAnimation().update(delta);
		} else {

			if (getMaxX() < 0 && getMinX() < 0) {
				setPosition(new Vector2f(gc.getWidth(), getY()));
			} else if (getMaxX() > gc.getWidth() && getMinX() > gc.getWidth()) {
				setPosition(new Vector2f(0.0f - getSprite().getWidth(), getY()));
			}

			if (getMaxY() < 0 && getMinY() < 0) {
				setPosition(new Vector2f(getX(), gc.getHeight()));
			} else if (getMaxY() > gc.getHeight() && getMinY() > gc.getHeight()) {
				setPosition(new Vector2f(getX(), 0.0f - getSprite().getHeight()));
			}

			Input input = gc.getInput();
			handleMovement(input, delta);
			handleBullets(gc);
			handlePowerUps();
			LOGGER.update();
		}
	}

	/**
	 * @return list containing all the bullets on screen
	 */
	public List<Bullet> getFiredBullets() {
		return bullets;
	}

	/**
	 * @return The current power up
	 */
	public PowerUp getPowerUp() {
		return powerUp;
	}

	/**
	 * @param The
	 *            current power up
	 */
	public void setPowerUp(PowerUp powerUp) {
		this.powerUp = powerUp;
	}

	/**
	 * Handles the different power ups a player can pick up.
	 * 
	 * @param gc
	 */
	private void handlePowerUps() {
		if (powerUp == null) {
			this.multiplier = 1;
			this.invincible = false;
		} else if (powerUp.pickupTimeElapsed() > powerUp.getType().getDuration()) {
			powerUp = null;
		} else {
			switch (powerUp.getType()) {
			case BULLET:
				bullets.forEach(b -> b.setScale(24));
				break;

			case INVINCIBILITY:
				this.invincible = true;
				break;

			case POINTS:
				this.multiplier = 2;
				break;
			}
		}
	}

	/**
	 * Handles the input and movement of the Players bullets. Deletes bullets
	 * when they go out of the screen.
	 * 
	 * @param gc
	 */
	private void handleBullets(GameContainer gc) {
		if (gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
			fire.playAsSoundEffect(1, 1, false);
			try {
				double rotationRadians = Math.toRadians(getRotation() - DEGREE_ADJUSTMENT);
				float x = (float) Math.cos(rotationRadians) * BULLET_ADJUSTMENT + getX() + BULLET_ADJUSTMENT;
				float y = (float) Math.sin(rotationRadians) * BULLET_ADJUSTMENT + getY() + BULLET_ADJUSTMENT;
				bullets.add(new Bullet(new Vector2f(x, y), getRotation()));
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}

		bullets = bullets.stream().filter(e -> !(e.getX() < 0 || e.getX() > gc.getScreenWidth() || e.getY() < 0
				|| e.getY() > gc.getScreenHeight())).collect(Collectors.toList());
		bullets.stream().forEach(e -> e.move());
	}

	/**
	 * Handles the Players movement: converting input to speed, position, and
	 * rotation.
	 * 
	 * @param input
	 */
	private void handleMovement(Input input, int delta) {
		int deltaTotal = (int) LOGGER.getData("delta")[0] + delta;

		boolean upd = deltaTotal > 1000;

		LOGGER.putData("delta", new Object[] { deltaTotal });

		boolean hasRotated = updateRotation(input);
		if (hasRotated) {
			direction = Util.decompose(Math.toRadians(getRotation() - DEGREE_ADJUSTMENT));
			if (direction.length() > 0) {
				direction.normalise();
			}
		}

		if (input.isKeyDown(Input.KEY_UP)) {
			setAnimation(moving); // sprite with thrusters

			if (!thrust.isPlaying())
				thrust.playAsSoundEffect(1, 1, false);

			if (velocity == 0 || movingDirection == null)
				velocity = 1;

			if (velocity <= MAXIMUM_VELOCITY)
				velocity *= VELOCITY_MULTIPLIER;

			if (hasRotated) {
				if (movingDirection == null) {
					movingDirection = new Vector2f(direction);
				} else {
					movingDirection = new Vector2f(direction).add(movingDirection).scale(0.5f).normalise();
				}
				move(movingDirection, velocity);
				if (upd) {
					Vector2f old = (Vector2f) LOGGER.getData("position")[0];
					LOGGER.log("Position changed from (" + old.getX() + ", " + old.getY() + ") to ("
							+ getPosition().getX() + ", " + getPosition().getY() + ")");
					LOGGER.putData("position", new Object[] { getPosition().copy() });
					LOGGER.putData("delta", new Object[] { 0 });

				}
			} else {
				movingDirection = new Vector2f(direction);
				move(movingDirection, velocity);
				if (upd) {
					Vector2f old = (Vector2f) LOGGER.getData("position")[0];
					LOGGER.log("Position changed from (" + old.getX() + ", " + old.getY() + ") to ("
							+ getPosition().getX() + ", " + getPosition().getY() + ")");
					LOGGER.putData("position", new Object[] { getPosition().copy() });
					LOGGER.putData("delta", new Object[] { 0 });

				}

			}
		} else {
			setAnimation(still); // sprite without thrusters
			if (velocity > 0.1f) {
				if (movingDirection.length() > 0) {
					movingDirection.normalise();
				}
				velocity /= (VELOCITY_MULTIPLIER - 0.01f);
				move(movingDirection, velocity);
			} else {
				velocity = 0;
				movingDirection = null;
			}
		}
	}

	/**
	 * Updates the position of the Player.
	 * 
	 * @param direction
	 * @param velocity
	 */
	private void move(Vector2f direction, double velocity) {
		setPosition(getPosition().add(direction.scale((float) velocity)));
	}

	/**
	 * Updates the Players rotation.
	 * 
	 * @param input
	 * @return
	 */
	private boolean updateRotation(Input input) {
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			moving.getCurrentFrame().setRotation(getRotation() + ROTATION_SPEED);
			still.getCurrentFrame().setRotation(getRotation() + ROTATION_SPEED);
			return true;
		} else if (input.isKeyDown(Input.KEY_LEFT)) {
			moving.getCurrentFrame().setRotation(getRotation() - ROTATION_SPEED);
			still.getCurrentFrame().setRotation(getRotation() - ROTATION_SPEED);
			return true;
		}
		return false;
	}

	/**
	 * @return The score of the player
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param points
	 *            Amount of points with which the score is increased.
	 */
	public void updateScore(int points) {
		score += points * multiplier;
		LOGGER.log(String.format("Gained %d points with multiplier %d", points, multiplier));
	}

	/**
	 * Renders the Player and Bullet sprites.
	 */
	public void render(Graphics g) {

		if (powerUp != null) {
			Color clr = powerUp.getType().getColor();
			getSprite().setImageColor(clr.r, clr.g, clr.b);
		} else {
			getSprite().setImageColor(1, 1, 1);
		}
		

		getSprite().draw(getX(), getY());
		g.drawString("SCORE: " + score, 8, 22); // location (x,y) is magic
												// numbers for now
		bullets.stream().forEach(e -> e.render(g));
		// TODO: render picked up power ups in the right corner
	}

	/**
	 * Overrides collision of Entity to implement invincibility.
	 */
	@Override
	public boolean collide(Entity entity) {
		if (entity == null)
			return false;
		if (invincible && !entity.getClass().equals(PowerUp.class))
			return false;
		if (this.getBoundingBox() == null) {
			return false;
		}
		return this.getBoundingBox().intersects(entity.getBoundingBox());
	}

}
