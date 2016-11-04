package nl.tudelft.asteroids.game;

/**
 * Describes the difficulty as an Enum
 * 
 * Created by Jasper on 10/20/2016.
 */
public enum Difficulty {

	/**
	 * Difficulty types
	 */
    EASY(1), MEDIUM(2), HARD(3);

	/**
	 * Integer belonging to this difficulty
	 */
    private int difficulty;

    /**
     * Enum constructor
     * @param difficulty {@link #difficulty}
     */
    Difficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Creates a normal case word from the uppercase name
     */
    @Override
    public String toString() {
        return super.toString().charAt(0) + super.toString().substring(1).toLowerCase();
    }

    /**
     * Returns the integer difficulty value
     * @return {@link #difficulty}
     */
    public int getDifficulty() {
        return this.difficulty;
    }
}
