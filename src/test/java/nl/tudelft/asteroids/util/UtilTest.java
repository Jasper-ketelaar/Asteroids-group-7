package nl.tudelft.asteroids.util;

import org.junit.Assert;
import org.junit.Test;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.openal.Audio;

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

	/**
	 * Tests audio cache loading
	 */
	@Test
	public void testLoadAudio() {
		Audio audio = Util.loadAudio("explode.wav");
		Assert.assertEquals(audio, Util.loadAudio("explode.wav"));

	}
}
