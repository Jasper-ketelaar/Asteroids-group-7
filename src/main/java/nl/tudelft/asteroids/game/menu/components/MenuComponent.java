package nl.tudelft.asteroids.game.menu.components;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

/**
 * Menu component of the Asteroids game.
 * Makes use of the composite pattern by allowing children to be instance of it's own class.
 * This way we could for example have a MenuItem containing a Menu (which is impractical but as
 * we add more classes to this the use stays simpler).
 * 
 * @author Leroy Velzel, Bernard Bot, Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public abstract class MenuComponent extends AbstractComponent {

	private final ArrayList<MenuComponent> children = new ArrayList<>();
	private final MenuComponent parent;
	
	private int x;
	private int y;
	
	private final int width;
	private final int height;

	/**
	 * Constructor without parent.
	 */
	public MenuComponent(GUIContext container, int x, int y, int width, int height) {
		this(container, null, x, y, width, height);
	}
	
	/**
	 * Constructor with parent
	 */
	public MenuComponent(GUIContext container, MenuComponent parent, int x, int y, int width, int height) {
		super(container);
		this.parent = parent;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * @return The height of the menu
	 */
	@Override
	public int getHeight() {
		return height;
	}

	/**
	 * @return The width of the menu
	 */
	@Override
	public int getWidth() {
		return width;
	}

	/**
	 * @return The 'starting' x-coordinate of the menu
	 */
	@Override
	public int getX() {
		return x;
	}

	/**
	 * @return The 'starting' y-coordinate of the menu
	 */
	@Override
	public int getY() {
		return y;
	}
	
	/**
	 * Returns the absolute x value (so the value on the game container and 
	 * not the value that this component has within a certain other component)
	 * @return absolute value of x
	 */
	public int getAbsoluteX() {
		if (parent == null) {
			return x;
		} else {
			return x + parent.getAbsoluteX();
		}
	}
	
	/**
	 * Returns the absolute y value (so the value on the game container and 
	 * not the value that this component has within a certain other component)
	 * @return absolute value of y
	 */
	public int getAbsoluteY() {
		if (parent == null) {
			return y;
		} else {
			return y + parent.getAbsoluteY();
		}
	}

	/**
	 * Renders the different MenuItems.
	 */
	@Override
	public void render(GUIContext c, Graphics g) throws SlickException {
		for (MenuComponent comp : children) {
			comp.render(c, g);
		}
	}

	/**
	 * Empty override method.
	 */
	@Override
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Appends a child to list of children 
	 * @param comp the child component to append
	 */
	public void append(MenuComponent comp) {
		this.children.add(comp);
	}
	
	/**
	 * Returns the list of children currently contained by the children ArrayList
	 * @return a List containing all the component's children components.
	 */
	public List<MenuComponent> getChildren() {
		return this.children;
	}

}
