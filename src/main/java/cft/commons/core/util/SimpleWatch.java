package cft.commons.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleWatch {

	private static Logger logger = LoggerFactory.getLogger(SimpleWatch.class);

	private long startTime;

	public SimpleWatch() {
		startTime = System.currentTimeMillis();
	}

	public long getMs() {
		return System.currentTimeMillis() - startTime;
	}

	public void printMs() {
		logger.info(">>>>>>>>Excution time = "
				+ String.valueOf(System.currentTimeMillis() - startTime));
	}

	public void reset() {
		startTime = System.currentTimeMillis();
	}
}
