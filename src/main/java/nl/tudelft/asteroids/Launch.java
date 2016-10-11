package nl.tudelft.asteroids;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import nl.tudelft.asteroids.game.AsteroidsGame;
import nl.tudelft.asteroids.util.Logger;
import nl.tudelft.asteroids.util.Logger.Level;

/**
 * Launcher for the game Asteroids. Defines the screen height and width, and
 * sets the target frame rate.
 * 
 * @author Leroy Velzel, Bernard Bot, Jasper Ketelaar, Emre Ilgin, Bryan Doerga
 *
 */
public class Launch {

	private final static Logger LOGGER = Logger.getInstance(Launch.class.getName());

	/**
	 * Main method for launching the game. The multiplayer flag can be passed to
	 * this method to initialize a multiplayer game.

	 * @param args
	 */
	public static void main(String[] args) {
		LOGGER.registerOutput(System.out);
		
		try {
			addLibraryPath();
			File file = new File(String.format("game%d.log", (int) System.currentTimeMillis()));
			if (!file.exists()) {
				file.createNewFile();
			}
			LOGGER.registerOutput(new FileOutputStream("game.log"));
		} catch (FileNotFoundException e) {
			LOGGER.log("FileNotFoundException thrown", Level.ERROR, true);
		} catch (IOException e) {
			LOGGER.log("IOException thrown", Level.ERROR, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (args.length > 0) {
				System.out.println(args[0]);
			}
			boolean multiplayer = args.length > 0 && args[0].equals("-mp");
			AppGameContainer appgc = new AppGameContainer(new AsteroidsGame("Asteroids", multiplayer));
			appgc.setDisplayMode(1200, 700, false);
			appgc.setTargetFrameRate(100);
			LOGGER.log("Game container fired up", Level.INFO, true);
			appgc.start();

		} catch (SlickException ex) {
			LOGGER.log("SlickException thrown", Level.ERROR, true);
		}

	}
	
	public static void addLibraryPath() throws Exception {
		String pathToAdd = new File("lwjgl/native").getAbsolutePath();
	    final Field usrPathsField = ClassLoader.class.getDeclaredField("usr_paths");
	    usrPathsField.setAccessible(true);
	 
	    //get array of paths
	    final String[] paths = (String[])usrPathsField.get(null);
	 
	    //check if the path to add is already present
	    for(String path : paths) {
	        if(path.equals(pathToAdd)) {
	            return;
	        }
	    }
	 
	    //add the new path
	    final String[] newPaths = Arrays.copyOf(paths, paths.length + 1);
	    newPaths[newPaths.length-1] = pathToAdd;
	    usrPathsField.set(null, newPaths);
	}

}
