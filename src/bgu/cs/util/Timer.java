package bgu.cs.util;

/**
 * A time-measuring utility.
 * 
 * @author romanm
 */
public class Timer {
	private long total;
	private long lastLeg;

	/**
	 * Resumes time measurement.
	 */
	public void start() {
		lastLeg = System.currentTimeMillis();
	}

	/**
	 * Pauses time measurement.
	 */
	public void stop() {
		lastLeg = System.currentTimeMillis() - lastLeg;
		total += lastLeg;
	}

	/**
	 * Returns the elapsed time in milliseconds.
	 */
	public long getTotal() {
		return total;
	}
}