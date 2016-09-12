package nl.tudelft.asteroids.model.entity;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import nl.tudelft.asteroids.util.Util;

/**
 * Player controlling the spaceship and shooting bullets.
 * 
 * @author Jasper, Bernard
 *
 */
public class Player extends Entity {

	private static final float VELOCITY_MULTIPLIER = 1.03f;
	private static final float ROTATION_SPEED = 2.5f;
	private static final float MAXIMUM_VELOCITY = 7;

	private static final int BULLET_ADJUSTMENT = 35;

	private ArrayList<Bullet> bulletList = new ArrayList<>();

	private Vector2f direction;
	private Vector2f movingDirection;
	private double velocity;
	private Animation still, moving;

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
	}
	
	public void init() {
		try {
			Image image = new Image("resources/Plane.png");
			
			Image canvasStill = new Image(image.getWidth(), image.getHeight());
			Graphics gfx = canvasStill.getGraphics();
			gfx.drawImage(image, 0, 0);
			gfx.flush();
			still = new Animation(new Image[] {canvasStill}, 50);
			
			Image exhaust = new Image("resources/Exhaust.png");
			Image canvasMoving = new Image(image.getWidth(), image.getHeight());
			gfx = canvasMoving.getGraphics();
			gfx.drawImage(image, 0, 0);
			gfx.drawImage(exhaust, 44, 53);
			gfx.drawImage(exhaust, 22, 53);
			gfx.flush();
			moving = new Animation(new Image[] {canvasMoving}, 50);
			
			setAnimation(new Animation(new Image[]{canvasStill}, 50));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Updates the movement and bullets of the Player, based on the user's
	 * input.
	 * 
	 * @param gc
	 * @param delta
	 */
	public void update(GameContainer gc, int delta) {
		
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
		handleMovement(input);
		handleBullets(gc);
	}

	/**
	 * 
	 * @return list containing all the bullets on screen
	 */
	public ArrayList<Bullet> getFiredBullets() {
		return bulletList;
	}

	/**
	 * Handles the input and movement of the Players bullets. Deletes bullets
	 * when they go out of the screen.
	 * 
	 * @param gc
	 */
	private void handleBullets(GameContainer gc) {
		if (gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
			try {
				double rotationRadians = Math.toRadians(getRotation() - DEGREE_ADJUSTMENT);
				float x = (float) Math.cos(rotationRadians) * BULLET_ADJUSTMENT + getX() + BULLET_ADJUSTMENT;
				float y = (float) Math.sin(rotationRadians) * BULLET_ADJUSTMENT + getY() + BULLET_ADJUSTMENT;
				Bullet bullet = new Bullet(new Vector2f(x, y), getRotation());
				bulletList.add(bullet);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}

		Iterator<Bullet> it = bulletList.iterator();
		while (it.hasNext()) {
			Bullet b = it.next();
			if (b.getX() < 0 || b.getX() > gc.getScreenWidth() || b.getY() < 0 || b.getY() > gc.getScreenHeight())
				it.remove();
			else
				b.move();
		}
	}

	/**
	 * Handles the Players movement: converting input to speed, position, and
	 * rotation.
	 * 
	 * @param input
	 */
	private void handleMovement(Input input) {
		boolean hasRotated = updateRotation(input);
		if (hasRotated) {
			direction = Util.decompose(Math.toRadians(getRotation() - DEGREE_ADJUSTMENT));
			if (direction.length() > 0) {
				direction.normalise();
			}
		}

		if (input.isKeyDown(Input.KEY_UP)) {
			setAnimation(moving);
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
			} else {
				movingDirection = new Vector2f(direction);
				move(movingDirection, velocity);
			}
		} else {
			setAnimation(still);
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
	 * Renders the Player and Bullet sprites.
	 */
	public void render(Graphics g) {
		
		
		getSprite().draw(getX(), getY());
		for (Bullet b : bulletList) {
			b.render(g);
		}

	}

}
