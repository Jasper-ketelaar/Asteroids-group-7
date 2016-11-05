package nl.tudelft.asteroids.model.entity.dyn.explodable.playable;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.newdawn.slick.Animation;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Vector2f;
import nl.tudelft.asteroids.TestWithDisplay;
import nl.tudelft.asteroids.model.entity.dyn.Bullet;
import nl.tudelft.asteroids.model.entity.dyn.explodable.playable.Player;

import nl.tudelft.asteroids.model.entity.stat.NullPowerUp;
import nl.tudelft.asteroids.model.entity.stat.PowerUp;
import nl.tudelft.asteroids.model.entity.stat.PowerUp.PowerupType;


/**
 * TODO: COMMENTS
 * 
 * @author Leroy
 *
 */
@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class PlayerTest extends TestWithDisplay {

	private Player testPlayer;
	private Player testPlayer2;
	private Vector2f testVector = new Vector2f(2, 7);
	private Vector2f testVector2 = new Vector2f(100, 100);

	private PowerUp puOne;
	private PowerUp puTwo;
	private PowerUp puThree;
	private PowerUp puNull;

	/**
	 * @throws SlickException
	 * 
	 *             TODO: COMMENTS
	 */
	@Before
	public void setUp() throws SlickException {
		testPlayer = new Player(testVector);
		testPlayer2 = new Player(testVector2);

		puOne = Mockito.mock(PowerUp.class);
		puTwo = Mockito.mock(PowerUp.class);
		puThree = Mockito.mock(PowerUp.class);
		puNull = Mockito.mock(PowerUp.class);
		Mockito.when(puOne.getType()).thenReturn(PowerupType.BULLET);
		Mockito.when(puTwo.getType()).thenReturn(PowerupType.INVINCIBILITY);
		Mockito.when(puThree.getType()).thenReturn(PowerupType.POINTS);
		Mockito.when(puNull.getType()).thenReturn(PowerupType.NULL);

	}

	/**
	 * TODO: COMMENTS
	 */
	@Test
	public void testUpdateScore() {
		final int score = 700;
		testPlayer.updateScore(score);

		assertEquals(score, testPlayer.getScore());

	}

	@Test
	public void testGetAnimation() throws SlickException {
		Animation animation = new Animation();
		testPlayer.setAnimation(animation);
		assertEquals(testPlayer.getAnimation(), animation);
	}

	@Test
	public void testGetBoundingBox() {
		testPlayer.init();
		float cX = testPlayer.getX() + testPlayer.getSprite().getWidth() / 2;
		float cY = testPlayer.getY() + testPlayer.getSprite().getHeight() / 2;
		float xRad = testPlayer.getSprite().getWidth() / 2;
		float yRad = testPlayer.getSprite().getHeight() / 2;
		Ellipse ellip = new Ellipse(cX, cY, xRad, yRad);

		assertEquals(ellip.getLocation(), testPlayer.getBoundingBox().getLocation());
	}

	@Test
	public void testCollide1() throws SlickException {
		testPlayer.init();
		assertTrue(testPlayer.collide(testPlayer));
	}

	@Test
	public void testCollide2() throws SlickException {
		testPlayer.init();
		testPlayer2.init();
		assertFalse(testPlayer.collide(testPlayer2));
	}

	@Test
	public void testbindKeys() {
		testPlayer.bindKeys(7, 7, 7, 7);

		assertTrue(testPlayer.left == 7);
		assertTrue(testPlayer.right == 7);
		assertTrue(testPlayer.shoot == 7);
		assertTrue(testPlayer.up == 7);
	}

	@Test
	public void testUpdateRotation() {
		assertFalse(testPlayer.updateRotation(new Input(0)));
	}

	@Test
	public void testMove() {
		Vector2f startPos = testPlayer.getPosition();

		testPlayer.move(testVector, 30.f);

		Vector2f endPos = testPlayer.getPosition();

		// assertNotEquals(startPos, endPos);
	}

	@Test
	public void testCanfire() {
		boolean fire = testPlayer.canFire();

		assertTrue(fire);
		testPlayer.disableFire();
		fire = testPlayer.canFire();

		assertFalse(fire);

	}

	@Test
	public void testPowerups() {
		PowerUp pu = testPlayer.getPowerUp();

		assertTrue(pu.isNullPowerUp());

		testPlayer.setPowerUp(new PowerUp(new Vector2f(0, 3), PowerupType.BULLET));
		pu = testPlayer.getPowerUp();
		assertFalse(pu.isNullPowerUp());
	}

	@Test
	public void testPowerupsHandle() {
		testPlayer.setPowerUp(puOne);
		testPlayer.handlePowerUps();
		testPlayer.setPowerUp(puTwo);
		testPlayer.handlePowerUps();
		testPlayer.setPowerUp(puThree);
		testPlayer.handlePowerUps();
		testPlayer.setPowerUp(puNull);
		testPlayer.handlePowerUps();

		Mockito.verify(puOne, Mockito.times(2)).getType();
		Mockito.verify(puTwo, Mockito.times(2)).getType();
		Mockito.verify(puThree, Mockito.times(2)).getType();

		Mockito.verify(puNull, Mockito.times(2)).getType();

	}

	@Test
	public void testBullets() {

		List<Bullet> bullets = testPlayer.getFiredBullets();
		
		assertTrue(bullets.isEmpty());
	}

	
	@Test
	public void testCanFire(){
		assertTrue(testPlayer.canFire());
	}
	
	@Test
	public void testDisableFire(){
		testPlayer.disableFire();
		assertFalse(testPlayer.canFire());
	}
	
	@Test
	public void testGetFiredBullets(){
		assertEquals(testPlayer.getFiredBullets(), testPlayer.getFiredBullets());
	}
	
	@Test
	public void testGetPowerUp(){
		PowerUp expected = new NullPowerUp();
		testPlayer.setPowerUp(expected);
		assertEquals(expected, testPlayer.getPowerUp());
	}
	
	@Test
	public void testGetUpKey(){
		assertEquals(Input.KEY_UP, testPlayer.getUpKey());
	}
	
	@Test
	public void testGetRightKey(){
		assertEquals(Input.KEY_RIGHT, testPlayer.getRightKey());
	}
	
	@Test
	public void testGetLeftKey(){
		assertEquals(Input.KEY_LEFT, testPlayer.getLeftKey());
	}
	
	@Test
	public void testGetShootKey(){
		assertEquals(Input.KEY_RCONTROL, testPlayer.getShootKey());
	}
	
	@Test
	public void testBindKeys(){
		testPlayer.bindKeys(0, 1, 2, 3);
		assertEquals(0, testPlayer.getUpKey());
	}
}
