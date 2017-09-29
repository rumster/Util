package bgu.cs.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A {@link HashSet} augmented with a LIFO linked list, which determines the
 * order of iteration to be the reverse of the insertion order.
 * 
 * @author romanm
 *
 * @param <T>
 *            The type of set members.
 */
@SuppressWarnings("serial")
public class LIFOHashSet<T> extends HashSet<T> {
	/**
	 * The set values in reversed insertion order.
	 */
	protected final LinkedList<T> lifo = new LinkedList<>();

	@Override
	public boolean add(T e) {
		boolean change = super.add(e);
		if (change) {
			lifo.addFirst(e);
		}
		return change;
	}

	/**
	 * Returns an iterator, which returns the set members in reverse insertion
	 * order.
	 */
	@Override
	public Iterator<T> iterator() {
		return lifo.iterator();
	}

	/**
	 * Removes the last-inserted member.
	 */
	public T pop() {
		T result = lifo.removeFirst();
		super.remove(result);
		return result;
	}
}