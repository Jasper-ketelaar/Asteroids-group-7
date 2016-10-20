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

    public MenuSelector(MenuComponent parent, int x, int y, int width, int height) throws SlickException {
        this(parent, x, y, width, height, new ArrayList<>());
    }

    public MenuSelector(MenuComponent parent, int x, int y, int width, int height, List<T> items) throws SlickException {
        super(parent, x, y, width, height + 5);
        this.items = items;
        Image base = new Image("menu/LeftRightIcon.png");
        float scale = (float) height / base.getHeight();
        this.left = base.getScaledCopy(scale);
        this.right = left.copy();
        this.right.rotate(180);

    }

    @Override
    public void process(Graphics graphics) {
        T item = items.get(current);
        left.draw(0, 5);
        font.drawString((width / 2) - (font.getWidth(item.toString()) / 2), 0, item.toString());
        right.draw(width - right.getWidth(), 5);
    }

    public void addItem(T item) {
        items.add(item);
    }

    public T getItem() {
        return items.get(current);
    }

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

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        Rectangle left = new Rectangle(getAbsoluteX(), getAbsoluteY(), this.left.getWidth(), this.left.getHeight());
        Rectangle right = new Rectangle(getAbsoluteX() + (width - this.right.getWidth()),
                getAbsoluteY(), this.right.getWidth(), this.right.getHeight());
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
