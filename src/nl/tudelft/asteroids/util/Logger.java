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

public class Logger {

	private static Logger instance;

	private final Map<String, Map<String, Object[]>> data = new HashMap<>();
	private final List<PrintStream> outputs = new ArrayList<>();
	private final Queue<String> outputQueue = new LinkedList<>();

	private final long initialized;

	private Logger() {
		this.initialized = System.currentTimeMillis();
	}

	public static Logger getInstance(String className) {
		if (instance == null) {
			instance = new Logger();
		}
		instance.register(className);
		return instance;
	}

	private void register(String className) {
		this.data.put(className, new HashMap<String, Object[]>());
	}

	public Object[] getData(String key) {
		StackTraceElement previous = getCaller();
		return data.get(previous.getClassName()).get(key);
	}

	public void putData(String key, Object[] data) {
		StackTraceElement previous = getCaller();
		this.data.get(previous.getClassName()).put(key, data);
	}

	public void registerOutput(OutputStream stream) {
		this.outputs.add(new PrintStream(stream));
	}

	public void update() {
		if (outputQueue.size() > 0) {
			String next = outputQueue.poll();
			for (PrintStream stream : outputs) {
				stream.println(next);
			}
		}
		flush();
	}

	public void log(String message, Level level, boolean update) {
		long millis = System.currentTimeMillis() - initialized;
		String time = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis),
				TimeUnit.MILLISECONDS.toSeconds(millis)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
		String result = String.format("[%s %s] %s", level.toString(), time, message);
		outputQueue.add(result);
		if (update) {
			update();
		}
	}

	private void flush() {
		outputs.forEach(e -> {
			e.flush();
		});
	}

	private StackTraceElement getCaller() {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		if (elements.length > 2) {
			StackTraceElement previous = elements[2];
			return previous;
		} else {
			throw new RuntimeException("Stacktrace malfunctioning");
		}
	}

	public enum Level {
		INFO, WARNING
	}

}
