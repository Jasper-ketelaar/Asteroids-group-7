package nl.tudelft.asteroids.game.menu.components;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Jasper on 10/20/2016.
 */
public class MenuSelector<T> extends MenuComponent {

    private final TrueTypeFont font = new TrueTypeFont(new Font("Verdana", Font.BOLD, 20), true);

    private final Image left;
    private final Image right;

    private int current;

    private final ArrayList<T> items;

    public MenuSelector(MenuComponent parent, int x, int y, int width, int height) throws SlickException {
        this(parent, x, y, width, height, new ArrayList<>());
    }

    public MenuSelector(MenuComponent parent, int x, int y, int width, int height, ArrayList<T> items) throws SlickException {
        super(parent, x, y, width, height);
        this.items = items;
        this.left = new Image("menu/LeftRightIcon.png");
        this.right = left.copy();
        this.right.rotate(180);

    }

    @Override
    public void process(Graphics graphics) {
        T item = items.get(current);
        left.draw(0, 0);
        font.drawString(10, 0, item.toString());
    }

    public void addItem(T item) {
        items.add(item);
    }
}
