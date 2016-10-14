package nl.tudelft.asteroids.game.menu.components;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Class used for loading and saving settings.
 * 
 * @author Bernard
 *
 */
public class SettingsLoader {
	
	/**
	 * Name of the Property file
	 */
	private static final String PROP_FILE_NAME = "game.ini";

	/**
	 * @return Properties from the property file
	 */
	public Properties loadProps() {
		Properties props = new Properties();
		InputStream is = null;

		try {
			is = getClass().getResourceAsStream(PROP_FILE_NAME);
			props.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return props;
	}
	
	/**
	 * @param properties Map of properties that need to be saved
	 */
	public void saveProps(Map<String, String> properties) {
	    try {
	        Properties props = new Properties();
	        for(Map.Entry<String, String> p : properties.entrySet()) {
	        	props.setProperty(p.getKey(), p.getValue());
	        }
	        OutputStream out = new FileOutputStream(PROP_FILE_NAME);
	        props.store(out, "Asteroids settings");
	    }
	    catch (Exception e ) {
	        e.printStackTrace();
	    }
	}

}