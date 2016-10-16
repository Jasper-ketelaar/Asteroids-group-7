package nl.tudelft.asteroids.util;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Class used to log messages to be viewed after the program has been closed to review
 * states the program might have been in that could have caused failure. Also useful
 * to check what states the program goes through when executing certain actions.
 * 
 * @author Jasper
 *
 */
public class Logger {

	/**
	 * Logger class uses the Singleton design pattern. This is the only instance of this class.
	 */
	private static Logger instance;

	/**
	 * Map containing maps of data linked to ClassNames. 
	 * Only inner map of data is available to the program.
	 */
	private final Map<String, Map<String, Object[]>> data = new HashMap<>();
	
	/**
	 * List containing PrintStreams to which the outputs have to be written
	 */
	private final List<PrintStream> outputs = new ArrayList<>();
	
	/**
	 * Output queue, used to buffer outputs so only one update per loop is required
	 * instead of updating every time a log is requested.
	 */
	private final Queue<String> outputQueue = new LinkedList<>();

	/**
	 * Time on which this instance is initialized to track running time of program.
	 */
	private final long initialized;

	/**
	 * Logger constructor. Is private so no other part of the program is able to create
	 * instances of this class which is essential to the Singleton pattern. 
	 * Upon initializing this class the initialize time is stored (System.currentTimeMillis()).
	 */
	private Logger() {
		this.initialized = System.currentTimeMillis();
	}

	/**
	 * Returns an instance of this class. Only method allowed to create an instance
	 * of the Logger class. Will create an instance if one does not yet exist and if it does
	 * then it will return the existing instance.
	 * 
	 * @param className class name which is required for storing data in the data map.
	 * @return instance of the Logger class.
	 */
	public synchronized static Logger getInstance(String className) {
		if (instance == null) {
			instance = new Logger();
		}
		instance.register(className);
		return instance;
	}

	/**
	 * Private access method that creates a new HashMap in which a class can request
	 * to store data.
	 * 
	 * @param className name of the class that wants to be registered.
	 */
	private void register(String className) {
		this.data.put(className, new HashMap<String, Object[]>());
	}

	/**
	 * Gets data from the data HashMap which contains a HashMap linked to the 
	 * class name. Class name is retrieved using the private getCaller() method.
	 * 
	 * @param key the key of the data attribute that needs to be retrieved from the HashMap.
	 * @return the data element from the HashMap.
	 */
	public Object[] getData(String key) {
		StackTraceElement previous = getCaller(3);
		return data.get(previous.getClassName()).get(key);
	}
	
	/**
	 * Puts data inside the HashMap which contains a HashMap linked to the 
	 * class name. Class name is retrieved using the private getCaller() method.
	 * 
	 * @param key key to store the data with
	 * @param data data to store
	 */
	public void putData(String key, Object[] data) {
		StackTraceElement previous = getCaller(3);
		this.data.get(previous.getClassName()).put(key, data);
	}

	/**
	 * Request to add new OutputStream to the list of PrintStreams to which the Logger should write.
	 * @param stream the stream to be added
	 */
	public void registerOutput(OutputStream stream) {
		this.outputs.add(new PrintStream(stream));
	}

	/**
	 * This is called when the log output queue needs to be cleared forcefully or when the logger requests 
	 * instant updates. Clears the outputQueue by taking the top element every time and printing it to every PrintStream.
	 * Since this Logger class uses a Queue as log 'cache', which is a FIFO structure, the logs are printed in the correct 
	 * time order. At the end of the update method every PrintStream is flushed using the flush() method provided in this class.
	 */
	public void update() {
		while (outputQueue.size() > 0) {
			String next = outputQueue.poll();
			for (PrintStream stream : outputs) {
				stream.println(next);
			}
		}
		flush();
	}
	
	/**
	 * Logs a message, calls {@link #log(String, Level, boolean, int)} using the Info level
	 * and does not force an update. Uses depth 4 because this is the depth at which the 
	 * previous class (original caller) is from.
	 * 
	 * @param message message to be logged.
	 */
	public void log(String message) {
		log(message, Level.INFO, false, 4);
	}

	/**
	 * Logs a message, calls {@link #log(String, Level, boolean, int)} 
	 * using the info provided.Uses depth 4 because this is the depth at which the 
	 * previous class (original caller) is from.
	 * 
	 * @param message message to log
	 * @param level level of the message to be logged
	 * @param update whether or not to force an update
	 */
	public void log(String message, Level level, boolean update) {
		log(message, level, update, 4);
	}
	
	/**
	 * Logs a message in the following format: [LEVEL TIME] (CLASS#METHOD) - MESSAGE.
	 * 
	 * @param message the message to be logged.
	 * @param level the level of the log.
	 * @param update whether or not to force an update.
	 * @param callerDepth at which depth the original call originated from.
	 */
	public void log(String message, Level level, boolean update, int callerDepth) {
		StackTraceElement caller = getCaller(callerDepth);
		long millis = System.currentTimeMillis() - initialized;
		String time = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis),
				TimeUnit.MILLISECONDS.toSeconds(millis)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
		String className = caller.getClassName().substring(caller.getClassName().lastIndexOf('.') + 1,
				caller.getClassName().length());
		String result = String.format("[%s %s] (%s#%s) - %s", level.toString(), time, className, caller.getMethodName(),
				message);
		outputQueue.add(result);
		if (update) {
			update();
		}
	}

	/**
	 * Flushes all outputs.
	 */
	private void flush() {
		outputs.forEach(e -> {
			e.flush();
		});
	}

	/**
	 * Gets the StackTraceElement of a certain depth. This is used to determine which
	 * class and/or method a method within the Logger was called from. Depth is necessary since for
	 * some methods	the Stack has more (internal) calls than other methods.
	 * 
	 * @param depth the depth at which the StackTraceElement is searched for
	 * @return StackTraceElement at the depth if it exists, if not a RuntimeException is thrown.
	 */
	private StackTraceElement getCaller(int depth) {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		if (elements.length > depth) {
			StackTraceElement previous = elements[depth];
			return previous;
		} else {
			throw new RuntimeException("Stacktrace malfunctioning");
		}
	}

	/**
	 * Enum containing levels of logging.
	 * 
	 * @author Jasper
	 */
	public enum Level {
		INFO, WARNING, ERROR
	}

}
