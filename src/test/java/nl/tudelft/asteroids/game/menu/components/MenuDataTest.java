package nl.tudelft.asteroids.game.menu.components;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.geom.Vector2f;

public class MenuDataTest{
	
	MenuData menuData;
	
	@Before
	public void init() {
		this.menuData = new MenuData(new Vector2f(0, 0), 0, 0);
	}
	
	@Test
	public void testGetX() {
		Assert.assertEquals(0, menuData.x, 0);
	}
	
	@Test
	public void testGetY() {
		Assert.assertEquals(0, menuData.y, 0);
	}
	
	@Test
	public void testGetWidth() {
		Assert.assertEquals(0, menuData.width);
	}
	
	@Test
	public void testGetHeight() {
		Assert.assertEquals(0, menuData.height);
	}
}
