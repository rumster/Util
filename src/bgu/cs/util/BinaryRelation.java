package bgu.cs.util;

import java.util.Collection;

/**
 * A set of pairs.
 * 
 * @author romanm
 *
 * @param <E1>
 *            The type of the first element in every pair of the relation.
 * @param <E2>
 *            The type of the second element in every pair of the relation.
 */
public interface BinaryRelation<E1, E2> {
	public int size();
	
	public boolean isEmpty();

	public boolean contains(E1 e1, E2 e2);	

	public boolean add(E1 e1, E2 e2);
	
	//public boolean addAll(Collection<Pair<E1,E2>> pairs);

	public boolean remove(E1 e1, E2 e2);

	public Collection<E2> selectFirst(E1 e1);

	public Collection<E1> selectSecond(E2 e2);
	
	public Collection<E2> selectFirst();
	
	public Collection<E1> selectSecond();
}