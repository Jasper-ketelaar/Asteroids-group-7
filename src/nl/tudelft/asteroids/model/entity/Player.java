package nl.tudelft.asteroids.model.entity;

import java.util.ArrayList;
import java.util.Iterator;

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
 * @author Bernard
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

	/**
	 * Constructor.
	 * 
	 * @param position
	 * @param rotation
	 * @throws SlickException
	 */
	public Player(Vector2f position, float rotation) throws SlickException {
		super(new Image("resources/Plane.png"), position, rotation);
		direction = Util.decompose(Math.toRadians(getRotation() - DEGREE_ADJUSTMENT));
	}

	/**
	 * Updates the movement and bullets of the Player,
	 * based on the user's input.
	 * 
	 * @param gc
	 * @param delta
	 */
	public void update(GameContainer gc, int delta) {
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
	 * Handles the input and movement of the Players bullets.
	 * Deletes bullets when they go out of the screen.
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
	 * Handles the Players movement: converting input to speed, position, and rotation.
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
			if (velocity == 0)
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
			if (velocity > 0.1f) {
				if (movingDirection.length() > 0) {
					movingDirection.normalise();
				}
				velocity /= VELOCITY_MULTIPLIER;
				move(movingDirection, velocity);
			} else {
				velocity = 0;
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
			setRotation(getRotation() + ROTATION_SPEED);
			return true;
		} else if (input.isKeyDown(Input.KEY_LEFT)) {
			setRotation(getRotation() - ROTATION_SPEED);
			return true;
		}
		return false;
	}

	/**
	 * Renders the Player and Bullet sprites.
	 */
	public void render(Graphics g) {
		g.drawImage(getSprite(), getX(), getY());
		for (Bullet b : bulletList) {
			b.render(g);
		}
	}

}
