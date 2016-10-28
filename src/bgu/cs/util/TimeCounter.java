package bgu.cs.util;

/**
 * A time-measuring utility.
 * 
 * @author romanm
 */
public class TimeCounter {
	private long total;
	private long lastLeg;

	public void start() {
		lastLeg = System.currentTimeMillis();
	}

	public void stop() {
		lastLeg = System.currentTimeMillis() - lastLeg;
		total += lastLeg;
	}

	public long getTotal() {
		return total;
	}
}