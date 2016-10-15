package nl.tudelft.asteroids.game.menu.components;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.util.InputAdapter;

/**
 * Menu component of the Asteroids game. Makes use of the composite pattern by
 * allowing children to be instance of it's own class. This way we could for
 * example have a MenuItem containing a Menu (which is impractical but as we add
 * more classes to this the use stays simpler).
 * 
 * @author Leroy Velzel, Bernard Bot, Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public abstract class MenuComponent extends InputAdapter {

	private final ArrayList<MenuComponent> children = new ArrayList<>();

	protected int x;
	protected int y;

	protected final int width;
	protected final int height;

	private final MenuComponent parent;

	protected final Image canvas;

	/**
	 * Constructor with parent
	 * 
	 * @throws SlickException
	 */
	public MenuComponent(MenuComponent parent, int x, int y, int width, int height) throws SlickException {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.canvas = new Image(width, height);
		this.parent = parent;
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
	 * Gets absolute x value
	 */
	public int getAbsoluteX() {
		if (parent == null) {
			return getX();
		} else {
			return getX() + parent.getAbsoluteX();
		}
	}

	/**
	 * Gets absolute y value
	 */
	public int getAbsoluteY() {
		if (parent == null) {
			return getY();
		} else {
			return getY() + parent.getAbsoluteY();
		}
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
		canvasGraphics.clear();

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
	 * Returns bounding box
	 */
	public Shape getBoundingBox() {
		return new Rectangle(getAbsoluteX(), getAbsoluteY(), width, height);
	}

	/**
	 * 
	 */
	public boolean contains(float x, float y) {
		return getBoundingBox().contains(x, y);
	}

	/**
	 * Performs component related actions to the graphics instance. Flusing is
	 * performed by render so flushing operations need not to be done within
	 * this method.
	 */
	public abstract void process(Graphics graphics);

}
