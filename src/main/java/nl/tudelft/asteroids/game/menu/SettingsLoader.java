package nl.tudelft.asteroids.game.menu;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

import nl.tudelft.asteroids.util.Logger;

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
	private Properties props;

	private final static Logger LOGGER = Logger.getInstance(SettingsLoader.class.getName());

	/**
	 * Constructor
	 * 
	 * @param props
	 *            The properties of this SettingsLoader
	 */
	public SettingsLoader() {
		this.props = new Properties();
	}

	/**
	 * Load properties from file.
	 * @return Properties from the property file
	 * @throws IOException
	 */
	public Properties loadProps() throws IOException {
		InputStream is = null;
		try {
			is = getClass().getResourceAsStream(PROP_FILE_NAME);
			props.load(is);

			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return props;
	}

	/**
	 * Save properties to file.
	 * @param properties
	 *            Map of properties that need to be saved
	 */
	public void saveProps(Map<String, String> properties) {
		try {
			for (Map.Entry<String, String> p : properties.entrySet()) {
				props.setProperty(p.getKey(), p.getValue());
			}
			OutputStream out = new FileOutputStream(PROP_FILE_NAME);
			props.store(out, "Asteroids settings");

			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}