package cft.commons.core.util;

public class SimpleWatch {

	private long startTime;

	public SimpleWatch() {
		startTime = System.currentTimeMillis();
	}

	public long getMs() {
		return System.currentTimeMillis() - startTime;
	}

	public void reset() {
		startTime = System.currentTimeMillis();
	}
}
