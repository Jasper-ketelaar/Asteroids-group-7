package nl.tudelft.asteroids;

import java.io.File;

import org.junit.Before;

public class TestWithLWJGL {

	@Before
	public void setLWJGLLibraryPath() {
		if (System.getProperty("os.name").contains("Windows")) {
			System.setProperty("org.lwjgl.librarypath",
					new File(".", "lwjgl" + File.separator + "native" + File.separator + "windows").getAbsolutePath());
		} else {
			System.setProperty("org.lwjgl.librarypath",
					new File(".", "lwjgl" + File.separator + "native" + File.separator + "linux").getAbsolutePath());
		}
	
	}
}
