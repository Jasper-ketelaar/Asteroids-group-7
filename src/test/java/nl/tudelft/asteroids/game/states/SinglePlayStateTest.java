package nl.tudelft.asteroids.game.states;

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

@Category(nl.tudelft.asteroids.TestWithDisplay.class)
public class SinglePlayStateTest extends TestWithDisplay {

	
	private SinglePlayState playstateTest;
	private Player testPlayer;
	private GameContainer gc;
	private StateBasedGame arg1;
	private Graphics g;
	
	
	@Before
	public void setup() throws SlickException{
		Image background = new Image("BG4.jpg");
		testPlayer = Mockito.mock(Player.class);
		playstateTest = new SinglePlayState(background, testPlayer);
		
		
		gc = Mockito.mock(GameContainer.class);
		arg1 = Mockito.mock(StateBasedGame.class);
		g = Mockito.mock(Graphics.class);
	}
	
	
	@Test
	public void testInit() throws SlickException {
		playstateTest.init(gc, arg1);		
		
		//Mockito.verify(testPlayer, Mockito.times(1)).init();
		

	}
	
	@Test
	public void testRender() throws SlickException {
		playstateTest.init(gc, arg1);
		playstateTest.render(gc, arg1, g);
		
		Mockito.verify(testPlayer, Mockito.times(1)).render(g);
	}
	
	@Test
	public void testUpdate() throws SlickException {
		playstateTest.init(gc,  arg1);
		playstateTest.update(gc, arg1, 1);
		
		Mockito.verify(testPlayer, Mockito.times(1)).update(gc, 1);
		
	}
	

}
