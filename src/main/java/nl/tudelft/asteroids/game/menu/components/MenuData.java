package nl.tudelft.asteroids.game.menu.components;

public class MenuData {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected Vector2i coordinates;

	public MenuData(Vector2i coordinates, int width, int height) {
		this.coordinates = coordinates;
		this.x = coordinates.x;
		this.y = coordinates.y;
		this.width = width;
		this.height = height;
	}

}
