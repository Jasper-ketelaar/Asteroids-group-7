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

import nl.tudelft.asteroids.game.AsteroidsGame;
import nl.tudelft.asteroids.model.entity.Entity;
import nl.tudelft.asteroids.model.entity.dyn.Bullet;
import nl.tudelft.asteroids.model.entity.dyn.explodable.ExplodableEntity;
import nl.tudelft.asteroids.model.entity.stat.NullPowerUp;
import nl.tudelft.asteroids.model.entity.stat.PowerUp;
import nl.tudelft.asteroids.util.Logger;
import nl.tudelft.asteroids.util.Logger.Level;

/**
 * Player controlling the spaceship and shooting bullets.
 * 
 * @author Leroy Velzel, Bernard Bot, Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public class Player extends ExplodableEntity {

	private final static Logger LOGGER = Logger.getInstance(Player.class.getName());

	private final static String FIRE_WAV = "fire.wav";
	private final static String THRUST_WAV = "thrust.wav";

	private final static String PLANE = "Plane.png";
	private final static String EXHAUST = "Exhaust.png";

	private static final float VELOCITY_MULTIPLIER = 1.03f;
	private static final float ROTATION_SPEED = 2.5f;
	private static final float MAXIMUM_VELOCITY = 7;

	private static final int BULLET_ADJUSTMENT = 35;

	private List<Bullet> bullets = new ArrayList<>();

	private PowerUp powerUp = new NullPowerUp();
	
	private boolean canFire = true;

	protected int up = Input.KEY_UP, right = Input.KEY_RIGHT, left = Input.KEY_LEFT, shoot = Input.KEY_RCONTROL;

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
		this.fire = AsteroidsGame.loadAudio(FIRE_WAV);
		this.thrust = AsteroidsGame.loadAudio(THRUST_WAV);
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
			Image image = new Image(PLANE);

			Image canvasStill = new Image(image.getWidth(), image.getHeight());
			Graphics gfx = canvasStill.getGraphics();
			gfx.drawImage(image, 0, 0);
			gfx.flush();
			still = new Animation(new Image[] { canvasStill }, 50);
			LOGGER.log("Still animation loaded");

			Image exhaust = new Image(EXHAUST);
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
		Image sprite = getAnimation().getImage(0);
		if(sprite.equals(still.getImage(0)) || sprite.equals(moving.getImage(0))) {
			Input input = gc.getInput();
			handleMovement(input, delta);
			if (canFire) {
				handleBullets(gc);
			}
			handlePowerUps();
			positionUpdate(gc);
			LOGGER.update();
		} else {
			getAnimation().update(delta);
		}
	}
	
	/**
	 * Disables firing for the player
	 */
	public void disableFire() {
		this.canFire = false;
	}
	
	
	/**
	 * canFire getter
	 */
	public boolean canFire() {
		return canFire;
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
	public void handlePowerUps() {
		if (powerUp.pickupTimeElapsed() > powerUp.getType().getDuration()) {
			powerUp = new NullPowerUp();
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

			case NULL:
				this.multiplier = 1;
				this.invincible = false;
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
		if (gc.getInput().isKeyPressed(shoot)) {
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
		bullets.forEach(e -> e.move());
	}

	/**
	 * Handles the Players movement: converting input to speed, position, and
	 * rotation.
	 * 
	 * @param input
	 */
	protected void handleMovement(Input input, int delta) {
		int deltaTotal = (int) LOGGER.getData("delta")[0] + delta;

		boolean upd = deltaTotal > 1000;

		LOGGER.putData("delta", new Object[] { deltaTotal });

		boolean hasRotated = updateRotation(input);
		if (hasRotated) {
			double radian = Math.toRadians(getRotation() - DEGREE_ADJUSTMENT);
			direction = new Vector2f((float) Math.cos(radian), (float) Math.sin(radian));
			if (direction.length() > 0) {
				direction.normalise();
			}
		}

		if (input.isKeyDown(up)) {
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
	protected void move(Vector2f direction, double velocity) {
		setPosition(getPosition().add(direction.scale((float) velocity)));
	}

	/**
	 * Updates the Players rotation.
	 * 
	 * @param input
	 * @return
	 */
	protected boolean updateRotation(Input input) {
		if (input.isKeyDown(right)) {
			moving.getCurrentFrame().setRotation(getRotation() + ROTATION_SPEED);
			still.getCurrentFrame().setRotation(getRotation() + ROTATION_SPEED);
			return true;
		} else if (input.isKeyDown(left)) {
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
	 * Binds keys to the 4 actions of the Player.
	 * 
	 * @param up
	 * @param left
	 * @param right
	 * @param shoot
	 */
	public void bindKeys(int up, int left, int right, int shoot) {
		this.up = up;
		this.left = left;
		this.right = right;
		this.shoot = shoot;
	}
	
	/**
	 * Returns the up-keybinding for the player.
	 * @return
	 */
	public int getUpKey(){
		return this.up;
	}
	
	/**
	 * Returns the left-keybinding for the player.
	 * @return
	 */
	public int getLeftKey(){
		return this.left;
	}
	
	/**
	 * Returns the right-keybinding for the player.
	 * @return
	 */
	public int getRightKey(){
		return this.right;
	}
	
	/**
	 * Returns the shoot-keybinding for the player.
	 * @return
	 */
	public int getShootKey(){
		return this.shoot;
	}

	/**
	 * Renders the Player and Bullet sprites.
	 */
	public void render(Graphics g) {
		Color clr = powerUp.getType().getColor();
		getSprite().setImageColor(clr.r, clr.g, clr.b);
		getSprite().draw(getX(), getY());

		bullets.forEach(e -> e.render(g));
	}

	/**
	 * Overrides collision of Entity to implement invincibility.
	 */
	@Override
	public boolean collide(Entity entity) {
		if (entity == null)
			return false;
		else if (invincible && !entity.getClass().equals(PowerUp.class))
			return false;
		else if (this.getBoundingBox() == null) {
			return false;
		}
		return this.getBoundingBox().intersects(entity.getBoundingBox());
	}

}
