package nl.tudelft.asteroids.game.states;

import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import nl.tudelft.asteroids.TestWithDisplay;
import nl.tudelft.asteroids.model.entity.dyn.explodable.playable.Player;
import nl.tudelft.asteroids.model.entity.stat.PowerUp;

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class NormalPlayStateTest extends TestWithDisplay {

	
	private NormalPlayState playstateTest;
	private NormalPlayState playstateTest2;
	private Player testPlayer;
	private Player nullPlayer;
	private GameContainer gc;
	private StateBasedGame arg1;
	private Graphics g;
	private Image background;
	
	
	@Before
	public void setup() throws SlickException{
		background = new Image("BG4.jpg");
		testPlayer = Mockito.mock(Player.class);
		playstateTest = new NormalPlayState(background);

		playstateTest2 = new NormalPlayState(background);
		
		gc = Mockito.mock(GameContainer.class);
		arg1 = Mockito.mock(StateBasedGame.class);
		g = Mockito.mock(Graphics.class);
	}
	
	
	@Test
	public void testInit() throws SlickException {
		playstateTest2.init(gc, arg1);		
		assertNotNull(playstateTest2.getPlayers());
	}
	
	@Test
	public void testRender() throws SlickException {
		playstateTest.init(gc, arg1);
		Mockito.when(testPlayer.getPowerUp()).thenReturn(Mockito.mock(PowerUp.class));
		//playstateTest.render(gc, arg1, g);
		//Mockito.verify(g, Mockito.times(1)).setColor(Mockito.anyObject());
		//Mockito.verify(g, Mockito.times(1)).drawString("SCORE: 0", 8.0f, 22.0f);
	}
	
	@Test
	public void testUpdate() throws SlickException {
		playstateTest.init(gc,  arg1);
		//Mockito.when(testPlayer.getExplosion().isStopped()).thenReturn(true);
		//assertTrue(testPlayer.getExplosion().isStopped());
		//playstateTest.update(gc, arg1, 1);
		//Mockito.verify(gc, Mockito.times(1)).exit();
	}
	

}
