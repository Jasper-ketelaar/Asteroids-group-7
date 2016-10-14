package nl.tudelft.asteroids.game.menu.input.interfaces;

import nl.tudelft.asteroids.game.menu.input.events.MouseEvent;

/**
 * Interface that allows elements to be clickable
 * @author Jasper
 *
 */
public interface MouseListener extends Listener {

    public void update(MouseEvent event);
    
   
}
