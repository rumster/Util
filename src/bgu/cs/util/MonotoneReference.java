package bgu.cs.util;

/**
 * A reference that can be set at most once.
 * 
 * @author romanm
 *
 * @param <T>
 *            The type of the reference.
 */
public class MonotoneReference<T> {
	private T ref;
	private final String errorMessage;

	public MonotoneReference(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public void set(T val) {
		if (ref != null) {
			ref = val;
		}
		else {
			throw new IllegalStateException(errorMessage);
		}
	}
	
	public T get() {
		return ref;
	}
}