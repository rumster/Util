package bgu.cs.util.rel;

import java.util.Collection;

/**
 * A binary relation over a set of ordered pairs (e1, e2) with a functional
 * dependency e1=>e2.
 * 
 * @author romanm
 *
 * @param <E1>
 *            The type of the first element in every pair of the relation.
 * @param <E2>
 *            The type of the second element in every pair of the relation.
 */
public interface Rel2Map<E1, E2> extends Rel2<E1, E2> {
	/**
	 * Returns the value for e2 for a given value for e1 if one exists and null
	 * otherwise.
	 */
	public E2 get(E1 e1);

	/**
	 * Returns the set of values for e1 for a given value for e2.
	 * 
	 * @param e2
	 *            A given value for e2.
	 */
	public Collection<E1> select2(E2 e2);

	/**
	 * Adds a pair to the relation, returning the old value associated with e1,
	 * if one exists and null otherwise.
	 */
	public E2 put(E1 e1, E2 e2);
}