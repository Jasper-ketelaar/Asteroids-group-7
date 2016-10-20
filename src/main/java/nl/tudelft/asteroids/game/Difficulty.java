package nl.tudelft.asteroids.game;

/**
 * Created by Jasper on 10/20/2016.
 */
public enum Difficulty {

    EASY(1), MEDIUM(2), HARD(3);

    private int difficulty;

    Difficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return super.toString().charAt(0) + super.toString().substring(1).toLowerCase();
    }

    public int getDifficulty() {
        return this.difficulty;
    }
}
