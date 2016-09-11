package nl.tudelft.asteroids.model.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import nl.tudelft.asteroids.util.Util;

public class Player extends Entity {

	private static final float VELOCITY_MULTIPLIER = 1.05f;

	private Vector2f direction;
	private Vector2f movingDirection;
	private double velocity;

	public Player(Vector2f position, float rotation) throws SlickException {
		super(new Image("resources/Plane.png").getScaledCopy(0.1f), position, rotation);
	}

	public void update(GameContainer gc, int delta) {
		Input input = gc.getInput();
		boolean hasRotated = updateRotation(input);

		if (hasRotated) {
			direction = Util.decompose(Math.toRadians(getRotation() - 90));
			if (direction.length() > 0) {
				direction.normalise();
			}
		}

		if (input.isKeyDown(Input.KEY_UP)) {

			if (velocity == 0)
				velocity = 1;
			if (velocity <= 10)
				velocity *= VELOCITY_MULTIPLIER;
			if (hasRotated) {
				if (movingDirection == null) {
					movingDirection = new Vector2f(direction);
				} else {
					movingDirection = new Vector2f(direction).add(movingDirection).normalise();
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

	private void move(Vector2f direction, double velocity) {
		setPosition(getPosition().add(direction.scale((float) velocity)));
	}

	private boolean updateRotation(Input input) {
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			setRotation(getRotation() + 4);
			return true;
		} else if (input.isKeyDown(Input.KEY_LEFT)) {
			setRotation(getRotation() - 4);
			return true;
		}
		return false;
	}

}
