package bgu.cs.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of a binary relation based on {@link HashMap> and
 * {@link HashSet}.
 * 
 * @author romanm
 *
 * @param <E1>The
 *            type of the first element in every pair of the relation.
 * @param <E2>
 *            The type of the second element in every pair of the relation.
 */
public class HashRel2<E1, E2> implements Rel2<E1, E2> {
	/**
	 * Maps an element of the first type to the set of elements of the second
	 * with which it there is a pair in the relation.
	 */
	private Map<E1, Set<E2>> e1ToE2s = new HashMap<>();

	/**
	 * Maps an element of the second type to the set of elements of the first
	 * with which it there is a pair in the relation. This map is maintained
	 * only if calls to selectSecond are made.
	 */
	private Map<E2, Set<E1>> e2ToE1s = null;
	private int size = 0;

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public boolean contains(E1 e1, E2 e2) {
		Set<E2> e2s = e1ToE2s.get(e1);
		if (e2s == null)
			return false;
		return e2s.contains(e2);
	}

	@Override
	public boolean add(E1 e1, E2 e2) {
		Set<E2> e2s = e1ToE2s.get(e1);
		if (e2s == null) {
			e2s = new HashSet<>();
			e1ToE2s.put(e1, e2s);
		}
		boolean result = e2s.add(e2);
		if (result)
			++size;

		if (result && e2ToE1s != null) {
			Set<E1> e1s = e2ToE1s.get(e2);
			e1s.add(e1);
		}

		return result;
	}

	@Override
	public boolean remove(E1 e1, E2 e2) {
		Set<E2> e2s = e1ToE2s.get(e1);
		if (e2s == null) {
			return false;
		}
		boolean result = e2s.remove(e2);
		if (result)
			--size;

		if (result && e2ToE1s != null) {
			Set<E1> e1s = e2ToE1s.get(e2);
			e1s.remove(e1);
		}

		return result;
	}

	@Override
	public Collection<E2> selectFirst(E1 e1) {
		Set<E2> e2s = e1ToE2s.get(e1);
		if (e2s == null) {
			return java.util.Collections.emptySet();
		} else {
			return e2s;
		}
	}

	@Override
	public Collection<E1> selectSecond(E2 e2) {
		if (e2ToE1s == null) {
			buildE2ToE1s();
		}

		Set<E1> e1s = e2ToE1s.get(e2);
		if (e1s == null) {
			return java.util.Collections.emptySet();
		} else {
			return e1s;
		}
	}

	@Override
	public Collection<E2> selectFirst() {
		if (e2ToE1s == null) {
			buildE2ToE1s();
		}
		return e2ToE1s.keySet();
	}

	@Override
	public Collection<E1> selectSecond() {
		return e1ToE2s.keySet();
	}

	@Override
	public String toString() {
		int printed = 0;
		StringBuilder result = new StringBuilder("[");
		for (Map.Entry<E1, Set<E2>> entry : e1ToE2s.entrySet()) {
			E1 e1 = entry.getKey();
			Set<E2> e2s = entry.getValue();
			for (E2 e2 : e2s) {
				result.append("(" + e1 + "," + e2 + ")");
				++printed;
				if (printed < size)
					result.append(", ");
			}
		}
		result.append("]");
		return result.toString();
	}

	private void buildE2ToE1s() {
		e2ToE1s = new HashMap<>();
		for (Map.Entry<E1, Set<E2>> entry : e1ToE2s.entrySet()) {
			E1 e1 = entry.getKey();
			Set<E2> e2s = entry.getValue();
			for (E2 a2 : e2s) {
				Set<E1> e1s = e2ToE1s.get(a2);
				if (e1s == null) {
					e1s = new HashSet<E1>();
					e2ToE1s.put(a2, e1s);
				}
				e1s.add(e1);
			}
		}
	}
}