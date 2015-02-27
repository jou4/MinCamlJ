package mincamlj;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

public class Log {

	private static final Logger DEFAULT_LOGGER = Logger
			.getLogger(Logger.GLOBAL_LOGGER_NAME);

	static {
		DEFAULT_LOGGER.addHandler(new StreamHandler() {
			{
				// setOutputStream(System.out);
				setLevel(Level.ALL);
			}
		});
	}

	public static Logger getLogger() {
		return DEFAULT_LOGGER;
	}

}
