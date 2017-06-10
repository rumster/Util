package bgu.cs.util.rel;

import java.util.Collection;

/**
 * A binary relation is a set of ordered pairs (e1, e2).
 * 
 * @author romanm
 *
 * @param <E1>
 *            The type of the first element in every pair of the relation.
 * @param <E2>
 *            The type of the second element in every pair of the relation.
 */
public interface Rel2<E1, E2> {
	/**
	 * The number of pairs in the relation.
	 */
	public int size();

	/**
	 * Checks whether there are pairs in the relation.
	 */
	public boolean isEmpty();

	/**
	 * Checks whether a given pair of values is a pair in the relation.
	 * 
	 * @param e1
	 *            A value for e1.
	 * @param e2
	 *            A value for e2.
	 */
	public boolean contains(E1 e1, E2 e2);

	/**
	 * Returns the set of values for e2 for a given value for e1.
	 * 
	 * @param e1
	 *            A given value for e1.
	 */
	public Collection<E2> selectFirst(E1 e1);

	/**
	 * Returns the set of values for e1 for a given value for e2.
	 * 
	 * @param e2
	 *            A given value for e2.
	 */
	public Collection<E1> selectSecond(E2 e2);

	/**
	 * Returns the multiset of values for e2 that exist in any pair in the
	 * relation. A given value for e2 appears as many times as there are pairs
	 * containing it.
	 */
	public Collection<E2> selectFirst();

	/**
	 * Returns the multiset of values for e1 that exist in any pair in the
	 * relation. A given value for e1 appears as many times as there are pairs
	 * containing it.
	 */
	public Collection<E1> selectSecond();

	/**
	 * Adds a pair to the relation.
	 * 
	 * @param e1
	 *            A value for e1.
	 * @param e2
	 *            A value for e2.
	 * @return True if the relation changed as the result of this operation.
	 */
	public boolean add(E1 e1, E2 e2);

	/**
	 * Removes the tuple with the given values.
	 * 
	 * @param e1
	 *            Value for the first attribute.
	 * @param e2
	 *            Value for the second attribute.
	 * @return true if such a pair exists.
	 */
	public boolean remove(E1 e1, E2 e2);

	/**
	 * Replace instance of 'first' with 'second' in all pairs.
	 * 
	 * @param first
	 *            The 'e1' element to be replaced.
	 * @param second
	 *            The 'e1' element used to be replace.
	 */
	public void replace1(E1 first, E1 second);

	/**
	 * Replace instance of 'first' with 'second' in all pairs.
	 * 
	 * @param first
	 *            The 'e2' element to be replaced.
	 * @param second
	 *            The 'e2' element used to be replace.
	 */
	public void replace2(E2 first, E2 second);
}