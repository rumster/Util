package bgu.cs.util;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * A priority heap where each key is associated with a LIFO-sorted sequence of
 * values. We refer to each such sequence as a bucket.
 * 
 * @author romanm
 *
 * @param <K>
 *            The type of bucket keys.
 * @param <V>
 *            The type of values.
 */
public class BucketHeap<K, V> {
	/**
	 * Maintains the number of heap entries.
	 */
	protected int size = 0;

	/**
	 * Maps each key to a bucket of values.
	 */
	protected final TreeMap<K, LIFOHashSet<V>> keyToVals = new TreeMap<>();

	/**
	 * Inserts a given value in the bucket matching the given key.
	 * 
	 * @param k
	 *            A bucket key.
	 * @param v
	 *            A value.
	 */
	public void put(K k, V v) {
		LIFOHashSet<V> vals = keyToVals.get(k);
		if (vals == null) {
			vals = new LIFOHashSet<>();
			keyToVals.put(k, vals);
		}
		boolean change = vals.add(v);
		size = change ? size + 1 : size;
	}

	/**
	 * Removes the last-inserted value from the minimal-key bucket.
	 */
	public V pop() {
		Map.Entry<K, LIFOHashSet<V>> entry = keyToVals.firstEntry();
		if (entry != null) {
			K k = entry.getKey();
			LIFOHashSet<V> vals = entry.getValue();
			V v = vals.pop();
			if (vals.isEmpty())
				keyToVals.remove(k);
			--size;
			return v;
		} else {
			return null;
		}
	}

	/**
	 * Returns the number of heap entires.
	 */
	public int size() {
		return size;
	}

	/**
	 * Tests whether there are any entries in the heap.
	 */
	public boolean isEmpty() {
		return keyToVals.isEmpty();
	}

	@Override
	public String toString() {
		int itemCounter = 0;
		StringBuilder result = new StringBuilder();
		for (Map.Entry<K, LIFOHashSet<V>> entry : keyToVals.entrySet()) {
			K key = entry.getKey();
			Set<V> entries = entry.getValue();
			for (V item : entries) {
				result.append("(" + key + "," + item + ")");
				++itemCounter;
				if (itemCounter < size)
					result.append(" ");
			}
		}
		return result.toString();
	}
}