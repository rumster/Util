package bgu.cs.util.rel;

import java.util.Map;

/**
 * A ternary relation with a functional dependency between two attributes and
 * the third.
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
public interface Rel3Map<E1, E2, E3> extends Rel3<E1, E2, E3> {
	/**
	 * Returns the value of 'e3' associated with the given values of 'e1' and
	 * 'e2', if there is such a tuple, and null otherwise.
	 */
	public E3 get(E1 e1, E2 e2);

	/**
	 * Returns the functional relation for a given value of 'e1'.
	 */
	public Map<E2, E3> get1(E1 e1);

	/**
	 * Removes any tuple (e1, e2, _) and adds (e1, e2, e3).
	 * 
	 * @return The previous value of e3, if such a tuple existed, and null
	 *         otherwise.
	 */
	public E3 put(E1 e1, E2 e2, E3 e3);
	
	@Override
	public Rel2Map<E1, E3> select2(E2 e2);
}