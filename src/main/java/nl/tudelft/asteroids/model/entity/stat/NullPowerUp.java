package nl.tudelft.asteroids.model.entity.stat;

/**
 * Class describing a null powerup to remove ugly null checks
 * 
 * @author Jasper
 *
 */
public class NullPowerUp extends PowerUp {

	/**
	 * Simple constructor
	 */
	public NullPowerUp() {
		super(PowerupType.NULL);
	}

	/**
	 * Force override
	 */
	@Override
	public void setPickupTime() {

	}
	/**
	 * Force override
	 */
	@Override
	public long pickupTimeElapsed() {
		return 0;
	}

	/**
	 * Force override
	 */
	@Override
	public long creationTimeElapsed() {
		return 0;
	}
}