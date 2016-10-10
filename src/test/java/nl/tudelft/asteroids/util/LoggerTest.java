package nl.tudelft.asteroids.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nl.tudelft.asteroids.util.Logger.Level;

/**
 * Test class for Logger
 * @author Jasper
 *
 */
public class LoggerTest {

	/** 
	 * Logger instance
	 */
	Logger logger;
	
	/**
	 * Gets instance (singleton)
	 */
	@Before
	public void init() {
		this.logger = Logger.getInstance(LoggerTest.class.getName());
	}
	
	/**
	 * Test if Singleton pattern holds by checking object hashcodes
	 */
	@Test
	public void testSingleton() {
		Assert.assertTrue(logger == Logger.getInstance(LoggerTest.class.getName()));
	}
	
	/**
	 * Tests if putting data and retrieving data works
	 */
	@Test
	public void testData() {
		Object[] data = new Object[] {"Test"};
		logger.putData("Test", data);
		Assert.assertArrayEquals(data, logger.getData("Test"));
	}
	
	/**
	 * Tests output without updating (no update means empty output stream)
	 */
	@Test
	public void testOutputStandardNoUpdate() {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		logger.registerOutput(stream);
		logger.log("Test");
		try {
			String message = stream.toString(StandardCharsets.UTF_8.name());
			Assert.assertEquals("", message);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		logger.update();
	}
	
	/**
	 * Tests output with updating
	 */
	@Test
	public void testOutputStandardUpdate() {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		logger.registerOutput(stream);
		logger.log("Test");
		logger.update();
		try {
			String message = stream.toString(StandardCharsets.UTF_8.name()).trim();
			Assert.assertEquals("[INFO 00:00] (LoggerTest#testOutputStandardUpdate) - Test", message);;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Tests output with updating and warning label
	 */
	@Test
	public void testOutputWarning() {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		logger.registerOutput(stream);
		logger.log("Test", Level.WARNING, true);
		try {
			String message = stream.toString(StandardCharsets.UTF_8.name()).trim();
			Assert.assertEquals("[WARNING 00:00] (LoggerTest#testOutputWarning) - Test", message);;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests output with updating and error label
	 */
	@Test
	public void testOutputError() {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		logger.registerOutput(stream);
		logger.log("Test", Level.ERROR, true);
		try {
			String message = stream.toString(StandardCharsets.UTF_8.name()).trim();
			Assert.assertEquals("[ERROR 00:00] (LoggerTest#testOutputError) - Test", message);;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
