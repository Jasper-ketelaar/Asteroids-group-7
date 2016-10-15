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
 * <p>This class is designed using the Composite pattern. Basically a MenuComponent can
 * have a parent and/or children which are of the same type. We then have multiple subclasses
 * of this MenuComponent to create different Menu items. </p>
 * 
 * <p>Using the composite pattern we can allow for a tree like structure in our menu.
 * An example of this being a Menu that has multiple children of which one is a {@link MenuLabel}
 * and of which multiple are {@link MenuButton}'s to create a titled Menu.</p>
 * 
 * @author Jasper Ketelaar
 *
 */
public abstract class MenuComponent extends InputAdapter {

	protected final ArrayList<MenuComponent> children = new ArrayList<>();

	protected int x;
	protected int y;

	protected final int width;
	protected final int height;

	private final MenuComponent parent;

	protected final Image canvas;

	/**
	 * Simple constructor which sets x and y to 0 initially. 
	 * A constructor like this is necessary for positioning by layout.
	 * 
	 * @see #MenuComponent(MenuComponent, int, int, int, int) for further information.
	 */
	public MenuComponent(MenuComponent parent, int width, int height) throws SlickException {
		this(parent, 0, 0, width, height);
	}
	
	/**
	 * Constructor with parent and width/height. 
	 * Coordinates are relative to the parent component, so if for example
	 * the parent component is a Menu with a width of 100 and a height of 100
	 * then the x and y value are inside this component. This means that the actual on screen
	 * x value is all the parent x values summed.
	 * 
	 * If the total bounding box of the component extends the area of the parent component
	 * the component will not be seen.
	 * 
	 * @throws SlickException can be thrown when instantiating an {@link Image}
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
	 * Check if this component contains a certain point
	 */
	public boolean contains(float x, float y) {
		return getBoundingBox().contains(x, y);
	}
	
	/**
	 * Overrides to check parent as well
	 */
	@Override
	public boolean isAcceptingInput() {
		return super.isAcceptingInput() && (parent == null || parent.isAcceptingInput());
	}
	
	/**
	 * If no mouse pressed method is overridden delegate to children.
	 */
	@Override
	public void mousePressed(int button, int x, int y) {
		for (MenuComponent child : children) {
			child.mousePressed(button, x, y);
		}
	}
	
	/**
	 * If no mouse moved method is overridden delegate to children.
	 */
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		for (MenuComponent child : children) {
			child.mouseMoved(oldx, oldy, newx, newy);
		}
	}

	/**
	 * Performs component related actions to the graphics instance. Flusing is
	 * performed by render so flushing operations need not to be done within
	 * this method.
	 */
	public abstract void process(Graphics graphics);

}
