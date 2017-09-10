package bgu.cs.util;

/**
 * A time-measuring utility.
 * 
 * @author romanm
 */
public class Timer {
	private long total = 0;
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
		total += System.currentTimeMillis() - lastLeg;
		lastLeg = 0;
	}

	/**
	 * Returns the elapsed time in milliseconds.
	 */
	public long getTotal() {
		return total;
	}
}