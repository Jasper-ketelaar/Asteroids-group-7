package nl.tudelft.asteroids;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;

import org.junit.Before;
import org.junit.BeforeClass;

public class TestWithLWJGL {

	@BeforeClass
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
