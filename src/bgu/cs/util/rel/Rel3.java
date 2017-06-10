package bgu.cs.util.rel;

import java.util.Collection;

/**
 * A ternary relation is a set of ordered triples.
 * 
 * @author romanm
 *
 * @param <E1>
 *            The type of the first element in every tuple of the relation.
 * @param <E2>
 *            The type of the second element in every tuple of the relation.
 * @param <E3>
 *            The type of the third element in every tuple of the relation.
 */
public interface Rel3<E1, E2, E3> {
	/**
	 * The number of tuples in the relation.
	 */
	public int size();

	/**
	 * Checks whether the relation is empty.
	 */
	public boolean isEmpty();

	/**
	 * Checks whether a given tuple of values is contained in the relation.
	 * 
	 * @param e1
	 *            The first value of the tuple.
	 * @param e2
	 *            The second value of the tuple.
	 * @param e3
	 *            The third value of the tuple.
	 */
	public boolean contains(E1 e1, E2 e2, E3 e3);

	/**
	 * Adds a tuple to the relation.
	 * 
	 * @param e1
	 *            The first value of the tuple.
	 * @param e2
	 *            The second value of the tuple.
	 * @param e3
	 *            The third value of the tuple.
	 * @return True if the relation changed as the result of this operation.
	 */
	public boolean add(E1 e1, E2 e2, E3 e3);

	public void clear();

	public boolean remove(E1 e1, E2 e2, E3 e3);

	public boolean remove1(E1 e1);

	public boolean remove2(E2 e2);

	public boolean remove3(E3 e3);

	public boolean remove12(E1 e1, E2 e2);

	public boolean remove13(E1 e1, E3 e3);

	public boolean remove23(E2 e2, E3 e3);

	public Rel2<E2, E3> select1(E1 e1);

	public Rel2<E1, E3> select2(E2 e2);

	public Rel2<E1, E2> select3(E3 e3);

	public Collection<E3> select12(E1 e1, E2 e2);

	public Collection<E2> select13(E1 e1, E3 e3);

	public Collection<E1> select23(E2 e2, E3 e3);

	public Collection<E1> all1();

	public Collection<E2> all2();

	public Collection<E3> all3();
}