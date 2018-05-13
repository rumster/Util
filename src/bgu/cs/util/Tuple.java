package bgu.cs.util;

import java.util.Arrays;

/**
 * A tuple of objects.
 * 
 * @author romanm
 */
public class Tuple implements Cloneable {
	protected final Object[] components;

	/**
	 * Constructs a tuple from a variable length array of objects.
	 * 
	 * @param components
	 */
	public Tuple(Object... components) {
		assert components != null;
		this.components = new Object[components.length];
		for (int i = 0; i < components.length; ++i)
			this.components[i] = components[i];
	}

	/**
	 * Returns a shallow copy of this tuple. That is, the individual components
	 * are the same.
	 */
	@Override
	public Tuple clone() {
		Object[] newComponents = new Object[components.length];
		for (int i = 0; i < newComponents.length; ++i) {
			newComponents[i] = components[i];
		}
		Tuple result = new Tuple(newComponents);
		return result;
	}

	public Object get(int i) {
		return components[i];
	}

	public void set(int i, Object o) {
		components[i] = o;
	}

	@Override
	public String toString() {
		return "(" + StringUtils.toString(components) + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(components);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Tuple other = (Tuple) obj;
		if (!Arrays.equals(components, other.components))
			return false;
		return true;
	}
}