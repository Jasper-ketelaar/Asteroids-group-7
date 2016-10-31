package nl.tudelft.asteroids.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DifficultyTest {
	
	@Test
	public void testGetDifficulty(){
		assertEquals(1, Difficulty.EASY.getDifficulty());
	}
	
	@Test
	public void testToString(){
		assertEquals("Easy", Difficulty.EASY.toString());
	}
}
