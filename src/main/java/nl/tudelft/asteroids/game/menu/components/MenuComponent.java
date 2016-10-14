package nl.tudelft.asteroids.game.menu.components;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

/**
 * Menu component of the Asteroids game. Makes use of the composite pattern by
 * allowing children to be instance of it's own class. This way we could for
 * example have a MenuItem containing a Menu (which is impractical but as we add
 * more classes to this the use stays simpler).
 * 
 * @author Leroy Velzel, Bernard Bot, Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public abstract class MenuComponent {

	private final ArrayList<MenuComponent> children = new ArrayList<>();

	protected int x;
	protected int y;

	protected final int width;
	protected final int height;

	protected final Image canvas;

	public MenuComponent(Image image, int x, int y) {
		this.x = x;
		this.y = y;
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.canvas = image;
	}

	/**
	 * Constructor with parent
	 * 
	 * @throws SlickException
	 */
	public MenuComponent(int x, int y, int width, int height) throws SlickException {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.canvas = new Image(width, height);
	}

	/**
	 * @return The height of the menu
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return The width of the menu
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return The 'starting' x-coordinate of the menu
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return The 'starting' y-coordinate of the menu
	 */
	public int getY() {
		return y;
	}

	/**
	 * Renders the different MenuItems.
	 */
	public void render(Graphics g) throws SlickException {
		Graphics canvasGraphics = canvas.getGraphics();
		process(canvasGraphics);

		for (MenuComponent child : children) {
			child.render(canvasGraphics);
		}

		canvasGraphics.flush();

		g.drawImage(canvas, x, y);

	}

	/**
	 * Change location of component
	 */
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Appends a child to list of children
	 * 
	 * @param comp
	 *            the child component to append
	 */
	public void append(MenuComponent comp) {
		this.children.add(comp);
	}

	/**
	 * Returns the list of children currently contained by the children
	 * ArrayList
	 * 
	 * @return a List containing all the component's children components.
	 */
	public List<MenuComponent> getChildren() {
		return this.children;
	}

	/**
	 * Returns the canvas image
	 * 
	 * @return canvas image
	 */
	public Image getCanvas() {
		return canvas;
	}

	/**
	 * Performs component related actions to the graphics instance. Flusing is
	 * performed by render so flushing operations need not to be done within
	 * this method.
	 */
	public abstract void process(Graphics graphics);

}
