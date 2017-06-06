package bgu.cs.util;

import java.util.Collection;

/**
 * A binary relation is a set of ordered pairs.
 * 
 * @author romanm
 *
 * @param <E1>
 *            The type of the first element in every pair of the relation.
 * @param <E2>
 *            The type of the second element in every pair of the relation.
 */
public interface BinaryRelation<E1, E2> {
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
	 *            The first value of the pair.
	 * @param e2
	 *            The second value of the pair.
	 */
	public boolean contains(E1 e1, E2 e2);

	/**
	 * Adds a pair to the relation.
	 * 
	 * @param e1
	 *            The first value of the pair.
	 * @param e2
	 *            The second value of the pair.
	 * @return True if the relation changed as the result of this operation.
	 */
	public boolean add(E1 e1, E2 e2);

	public boolean remove(E1 e1, E2 e2);

	public Collection<E2> selectFirst(E1 e1);

	public Collection<E1> selectSecond(E2 e2);

	public Collection<E2> selectFirst();

	public Collection<E1> selectSecond();
}