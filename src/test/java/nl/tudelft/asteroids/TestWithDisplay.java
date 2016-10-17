package nl.tudelft.asteroids;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class TestWithDisplay extends TestWithLWJGL {

	@BeforeClass
    public static void createDisplay() throws LWJGLException {
        Display.setDisplayMode(new DisplayMode(1, 1));
        Display.create();
        
    }

    @AfterClass
    public static void destroyDisplay() {
        Display.destroy();
    }
    
}
