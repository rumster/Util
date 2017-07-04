package bgu.cs.util.rel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

import bgu.cs.util.Pair;

/**
 * An implementation of {@link Rel2} based on {@link HashMap> and
 * {@link HashSet}.
 * 
 * @author romanm
 *
 * @param <E1>The
 *            type of the first element in every pair of the relation.
 * @param <E2>
 *            The type of the second element in every pair of the relation.
 */
public class HashRel2Map<E1, E2> implements Rel2Map<E1, E2> {
	/**
	 * Maps an element of the first type to the second of element associated
	 * with it if there is one and null otherwise.
	 */
	private Map<E1, E2> e1ToE2 = new HashMap<>();

	/**
	 * Maps an element of the second type to the set of elements of the first
	 * with which it there is a pair in the relation. This map is maintained
	 * only if calls to selectSecond are made.
	 */
	private Map<E2, Set<E1>> e2ToE1 = null;

	@Override
	public int size() {
		return e1ToE2.size();
	}

	@Override
	public boolean isEmpty() {
		return e1ToE2.isEmpty();
	}

	@Override
	public boolean contains(E1 e1, E2 e2) {
		E2 v2 = e1ToE2.get(e1);
		return v2 != null && e2.equals(v2);
	}

	@Override
	public boolean add(E1 e1, E2 e2) {
		assert !e1ToE2.containsKey(e1) : "Attempt to violate functional dependency!";
		e1ToE2.put(e1, e2);

		if (e2ToE1 != null) {
			Set<E1> e1s = e2ToE1.get(e2);
			e1s.add(e1);
		}

		return true;
	}

	@Override
	public boolean remove(E1 e1, E2 e2) {
		if (contains(e1, e2)) {
			e1ToE2.remove(e1);
			if (e2ToE1 != null) {
				Set<E1> e1s = e2ToE1.get(e2);
				e1s.remove(e1);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void clear() {
		if (e1ToE2 != null)
			e1ToE2.clear();
		if (e2ToE1 != null)
			e2ToE1.clear();
	}

	@Override
	public Collection<E2> selectFirst(E1 e1) {
		E2 e2 = e1ToE2.get(e1);
		if (e2 == null) {
			return java.util.Collections.emptySet();
		} else {
			return java.util.Collections.singleton(e2);
		}
	}

	@Override
	public Collection<E1> select2(E2 e2) {
		if (e2ToE1 == null) {
			buildE2ToE1s();
		}

		Set<E1> e1s = e2ToE1.get(e2);
		if (e1s == null) {
			return java.util.Collections.emptySet();
		} else {
			return e1s;
		}
	}

	public void map(BiFunction<E1, E2, Void> fun) {
		if (e1ToE2 != null) {
			for (Map.Entry<E1, E2> entry : e1ToE2.entrySet()) {
				E1 e1 = entry.getKey();
				E2 e2 = entry.getValue();
				fun.apply(e1, e2);
			}
		} else {
			buildE2ToE1s();
			for (Map.Entry<E2, Set<E1>> entry : e2ToE1.entrySet()) {
				E2 e2 = entry.getKey();
				for (E1 e1 : entry.getValue()) {
					fun.apply(e1, e2);
				}
			}
		}
	}

	@Override
	public Collection<Pair<E1, E2>> all() {
		ArrayList<Pair<E1, E2>> result = new ArrayList<>();
		for (Map.Entry<E1, E2> entry : e1ToE2.entrySet()) {
			E1 e1 = entry.getKey();
			E2 e2 = entry.getValue();
			result.add(new Pair<>(e1, e2));
		}
		return result;
	}

	@Override
	public Collection<E2> all2() {
		if (e2ToE1 == null) {
			buildE2ToE1s();
		}
		return e2ToE1.keySet();
	}

	@Override
	public Collection<E1> all1() {
		return e1ToE2.keySet();
	}

	@Override
	public String toString() {
		int printed = 0;
		int size = size();
		StringBuilder result = new StringBuilder("[");
		for (Map.Entry<E1, E2> entry : e1ToE2.entrySet()) {
			E1 e1 = entry.getKey();
			E2 e2 = entry.getValue();
			result.append("(" + e1 + "," + e2 + ")");
			++printed;
			if (printed < size)
				result.append(", ");
		}
		result.append("]");
		return result.toString();
	}

	@Override
	public void replace1(E1 first, E1 second) {
		throw new Error("unimeplemented!");
	}

	@Override
	public void replace2(E2 first, E2 second) {
		throw new Error("unimeplemented!");
	}

	/**
	 * Populates {link e2ToE1} based on {@link e1ToE2}.
	 */
	protected void buildE2ToE1s() {
		e2ToE1 = new HashMap<>();
		for (Map.Entry<E1, E2> entry : e1ToE2.entrySet()) {
			E1 e1 = entry.getKey();
			E2 a2 = entry.getValue();
			Set<E1> e1s = e2ToE1.get(a2);
			if (e1s == null) {
				e1s = new HashSet<E1>();
				e2ToE1.put(a2, e1s);
			}
			e1s.add(e1);
		}
	}

	@Override
	public E2 get(E1 e1) {
		return e1ToE2.get(e1);
	}

	@Override
	public E2 put(E1 e1, E2 e2) {
		E2 result = e1ToE2.put(e1, e2);
		if (e2ToE1 != null) {
			e2ToE1.remove(e2);
		}
		return result;
	}
}