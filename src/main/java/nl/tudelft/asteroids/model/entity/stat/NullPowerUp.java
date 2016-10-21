package nl.tudelft.asteroids.model.entity.stat;

public class NullPowerUp extends PowerUp {

		public NullPowerUp() {
			super(PowerupType.NULL);
		}
		
		@Override
		public void setPickupTime() {
			
		}
		@Override
		public long pickupTimeElapsed() {
			return 0;
		}

		@Override
		public long creationTimeElapsed() {
			return 0;
		}
	}