package bgu.cs.util;

/**
 * A time-measuring utility.
 * 
 * @author romanm
 */
public class Timer {
	private long total = 0;
	private long lastLeg = 0;
	private boolean running = true;

	public void reset() {
		running = false;
		total = 0;
		lastLeg = System.currentTimeMillis();
	}

	/**
	 * Resumes time measurement.
	 */
	public void start() {
		lastLeg = System.currentTimeMillis();
		running = true;
	}

	/**
	 * Pauses time measurement.
	 */
	public void stop() {
		total += System.currentTimeMillis() - lastLeg;
		lastLeg = 0;
		running = false;
	}

	/**
	 * Returns the elapsed time in milliseconds.
	 */
	public long getTotal() {
		if (running) {
			throw new IllegalStateException("Can't invoke getTotal on a running timer!");
		}
		return total;
	}

	public String toSeconds() {
		if (running) {
			throw new IllegalStateException("Can't invoke getTotal on a running timer!");
		}
		return (total / 1000) + " sec.";
	}
}