package nl.tudelft.asteroids.game.menu.components;

import org.newdawn.slick.geom.Vector2f;

public class MenuData {
	protected float x;
	protected float y;
	protected int width;
	protected int height;
	protected Vector2f coordinates;

	public MenuData(Vector2f coordinates, int width, int height) {
		this.coordinates = coordinates;
		this.x = coordinates.x;
		this.y = coordinates.y;
		this.width = width;
		this.height = height;
	}

}
