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
 * <p>
 * This class is designed using the Composite pattern. Basically a MenuComponent
 * can have a parent and/or children which are of the same type. We then have
 * multiple subclasses of this MenuComponent to create different Menu items.
 * </p>
 * 
 * <p>
 * Using the composite pattern we can allow for a tree like structure in our
 * menu. An example of this being a Menu that has multiple children of which one
 * is a {@link MenuLabel} and of which multiple are {@link MenuButton}'s to
 * create a titled Menu.
 * </p>
 * 
 * <p>
 * This class extends InputAdapter
 * 
 * @author Jasper Ketelaar
 *
 */
public abstract class MenuComponent extends InputAdapter {

	/**
	 * An {@link ArrayList} containing the children components of this component
	 */
	protected final ArrayList<MenuComponent> children = new ArrayList<>();

	/**
	 * The relative location vector
	 */
	protected MenuData menudata;

	/**
	 * The parent of this component
	 */
	private final MenuComponent parent;

	/**
	 * An {@link Image} representing the canvas on which the component is drawn
	 */
	protected final Image canvas;

	/**
	 * Simple constructor which sets x and y to 0 initially. A constructor like
	 * this is necessary for positioning by layout.
	 * 
	 * @see #MenuComponent(MenuComponent, int, int, int, int) for further
	 *      information.
	 */
	public MenuComponent(MenuComponent parent, int width, int height) throws SlickException {

		this(parent, new MenuData(new Vector2i(0, 0), width, height));
	}

	/**
	 * Constructor with parent and width/height. Coordinates are relative to the
	 * parent component, so if for example the parent component is a Menu with a
	 * width of 100 and a height of 100 then the x and y value are inside this
	 * component. This means that the actual on screen x value is all the parent
	 * x values summed.
	 * 
	 * If the total bounding box of the component extends the area of the parent
	 * component the component will not be seen.
	 * 
	 * @throws SlickException
	 *             can be thrown when instantiating an {@link Image}
	 */
	public MenuComponent(MenuComponent parent, MenuData menudata) throws SlickException {
		this.menudata = menudata;
		this.canvas = new Image(menudata.width, menudata.height);
		this.parent = parent;
	}

	/**
	 * @return the {@link #height} attribute of this component
	 */
	public int getHeight() {
		return menudata.height;
	}

	/**
	 * @return the {@link #width} attribute of this component
	 */
	public int getWidth() {
		return menudata.width;
	}

	/**
	 * The absolute x value is the x value of this component and all it's
	 * parents component's x values </br>
	 * thus creating the x value where this element is painted on the display.
	 * 
	 * @return the absolute x value
	 */
	public int getAbsoluteX() {

		return getX() + parent.getAbsoluteX();

	}

	/**
	 * The absolute y value is the y value of this component and all it's
	 * parents component's y values </br>
	 * thus creating the y value where this element is painted on the display.
	 * 
	 * @return the absolute y value
	 */
	public int getAbsoluteY() {

		return getY() + parent.getAbsoluteY();

	}

	/**
	 * Overrides to check parent as well
	 */
	@Override
	public boolean isAcceptingInput() {
		return super.isAcceptingInput() && parent.isAcceptingInput();
	}

	/**
	 * @return the {@link #x} attribute of this component
	 */
	public int getX() {
		return menudata.x;
	}

	/**
	 * @return the {@link #y} attribute of this component
	 */
	public int getY() {
		return menudata.y;
	}

	/**
	 * <p>
	 * Requests the {@link Graphics} from the {@link #canvas} instance of this
	 * component and clears it before calling {@link #process(Graphics)}.
	 * </p>
	 * 
	 * <p>
	 * Then the child components of this component are rendered by calling this
	 * method ({@link #render(Graphics)}) so this causes a recursive loop of
	 * rendering on the canvas. At the end the {@link Graphics} operations are
	 * flushed to push the operations to the {@link #canvas}.
	 * </p>
	 * 
	 * <p>
	 * This {@link Image} is then drawn on the {@link Graphics} instance
	 * provided in the parameters. This means it is recursively drawn and in the
	 * end drawn by the caller
	 * </p>
	 * 
	 * @param g
	 *            the {@link Graphics} instance for this component to be
	 *            rendered on.
	 * @throws SlickException
	 *             for requesting {@link Image} graphics.
	 */
	public void render(Graphics g) throws SlickException {
		Graphics canvasGraphics = canvas.getGraphics();
		canvasGraphics.clear();

		process(canvasGraphics);

		for (MenuComponent child : children) {
			child.render(canvasGraphics);
		}

		canvasGraphics.flush();
		g.drawImage(canvas, menudata.x, menudata.y);
	}

	/**
	 * Changes the location of the component to the specified arguments
	 * 
	 * @param x
	 *            the {@link #x} attribute to be updated to
	 * @param y
	 *            the {@link #y} attribute to be updated to
	 */
	public void setLocation(int x, int y) {
		this.menudata = new MenuData(new Vector2i(x, y), getWidth(), getHeight());
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
		return new Rectangle(getAbsoluteX(), getAbsoluteY(), menudata.width, menudata.height);
	}

	/**
	 * Check if this component contains a certain point
	 */
	public boolean contains(float x, float y) {
		return getBoundingBox().contains(x, y);
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
