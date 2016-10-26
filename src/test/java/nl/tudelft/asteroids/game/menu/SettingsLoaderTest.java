package nl.tudelft.asteroids.game.menu;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

public class SettingsLoaderTest {
	
	private SettingsLoader loader;
	private Properties props;
	
	@Before
	public void setUp(){
		loader = new SettingsLoader();
		props = new Properties();
	}
	
	@Test
	public void testConstructor(){
		assertEquals(props, loader.getProps());
	}
	
	@Test
	public void testLoadProps() throws IOException{
		assertEquals(props, loader.loadProps());
	}
	
	@Test
	public void testSetProps(){
		loader.setProps(null);
		assertEquals(null, loader.getProps());
	}
}
