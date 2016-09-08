package nl.tudelft.asteroids.model;

/**
 * Enum used to define a direction
 * @author Jasper
 *
 */
public enum Direction {
	
	NORTH(0), EAST(1), SOUTH(2), WEST(3);
	
	private int identifier;
	
	private Direction(int identifier) {
		this.identifier = identifier;
	}
	
	public int getIdentifier() {
		return identifier;
	}
	
	public Direction forIdentifier(int identifier) {
		for (Direction dir : Direction.values()) {
			if (dir.getIdentifier() == identifier) {
				return dir;
			}
		}
		return null;
	}
}
