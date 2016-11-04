package nl.tudelft.asteroids.game.menu.components;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jasper on 10/20/2016.
 */
public class MenuSelector<T> extends MenuComponent {

	private final TrueTypeFont font = new TrueTypeFont(new Font("Verdana", Font.BOLD, 20), true);

	private final Image left;
	private final Image right;
	private final List<T> items;
	private boolean leftHovered;
	private boolean rightHovered;
	private int current;

	/**
	 * Default super class constuctor.
	 */
	public MenuSelector(MenuComponent parent, MenuData menudata) throws SlickException {
		this(parent, menudata, new ArrayList<>());
	}

	/**
	 * Constructor which also initializes the right/left arrow icon and the list
	 * of selectable items.
	 * 
	 * @param parent
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param items
	 * @throws SlickException
	 */
	public MenuSelector(MenuComponent parent, MenuData menudata, List<T> items) throws SlickException {
		super(parent, new MenuData(menudata.coordinates, menudata.width, menudata.height + 5));
		this.items = items;
		Image base = new Image("menu/LeftRightIcon.png");
		float scale = (float) menudata.height / base.getHeight();
		this.left = base.getScaledCopy(scale);
		this.right = left.copy();
		this.right.rotate(180);
	}

	/**
	 * Draw currently selected item and left/right arrows.
	 */
	@Override
	public void process(Graphics graphics) {
		T item = items.get(current);
		left.draw(0, 5);
		font.drawString((menudata.width / 2) - (font.getWidth(item.toString()) / 2), 0, item.toString());
		right.draw(menudata.width - right.getWidth(), 5);
	}

	/**
	 * @param item
	 *            Item added to the selector.
	 */
	public void addItem(T item) {
		items.add(item);
	}

	/**
	 * @return Currently displayed item.
	 */
	public T getItem() {
		return items.get(current);
	}

	/**
	 * @param i
	 *            The index for the current selected item.
	 */
	public void setCurrent(int i) {
		if (i < items.size())
			current = i;
	}

	/**
	 * Changes current item when left/right arrow is pressed. Makes the items
	 * 'loop around' by checking the size of the list.
	 */
	@Override
	public void mousePressed(int button, int x, int y) {
		if (leftHovered) {
			current -= 1;
			if (current < 0) {
				current = items.size() - 1;
			}
		}

		if (rightHovered) {
			current += 1;
			if (current >= items.size()) {
				current = 0;
			}
		}
	}

	/**
	 * Checks if the mouse hovers over the left/right arrow after it is moved.
	 */
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		Rectangle left = new Rectangle(getAbsoluteX(), getAbsoluteY(), this.left.getWidth(), this.left.getHeight());
		Rectangle right = new Rectangle(getAbsoluteX() + (menudata.width - this.right.getWidth()), getAbsoluteY(),
				this.right.getWidth(), this.right.getHeight());
		if (left.contains(newx, newy)) {
			this.left.setAlpha(0.8f);
			leftHovered = true;
		} else {
			this.left.setAlpha(1f);
			leftHovered = false;
		}

		if (right.contains(newx, newy)) {
			this.right.setAlpha(0.8f);
			rightHovered = true;
		} else {
			this.right.setAlpha(1f);
			rightHovered = false;
		}
	}

}
