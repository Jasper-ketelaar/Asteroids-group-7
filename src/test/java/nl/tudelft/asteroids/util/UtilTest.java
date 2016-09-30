package nl.tudelft.asteroids.util;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class UtilTest {

	/**
	 * Tests Util.decompose method
	 */
	@Test
	public void testDecompose() {
		double radian = Math.toRadians(90);
		Vector2f decomposed = Util.decompose(radian);
		Assert.assertTrue((float) Math.cos(radian) == decomposed.x);
		Assert.assertTrue((float) Math.sin(radian) == decomposed.y);
	}
	
	@Test
	public void testLoadAudio() {
		try {
			Audio audio = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("sfx/explode.wav"));
			System.out.println(audio.toString() + ", " + Util.loadAudio("explode.wav").toString());
			Assert.assertEquals(audio, Util.loadAudio("explode.wav"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
