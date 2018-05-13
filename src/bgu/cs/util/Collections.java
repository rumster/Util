package bgu.cs.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;

/**
 * Utilities for managing collections.
 * 
 * @author romanm
 * 
 */
public class Collections {
	/**
	 * Adds the given element to the given collection, unless it already appears
	 * there.
	 * 
	 * @return true if the collection has changed due to the operation, and false
	 *         otherwise.
	 */
	public static <E> boolean addNoCopies(Collection<E> c, E obj) {
		if (!c.contains(obj)) {
			c.add(obj);
			return true;
		}
		return false;
	}

	/**
	 * Adds the given element to the given collection, unless it contains an element
	 * that tests positively when compared to the given element.
	 * 
	 * @return true if the collection has changed due to the operation, and false
	 *         otherwise.
	 */
	public static <E> boolean addNoEquiv(Collection<E> c, E obj, BiPredicate<E, E> equivTest) {
		for (var member : c) {
			if (equivTest.test(member, obj)) {
				return false;
			}
		}
		c.add(obj);
		return true;
	}

	/**
	 * Fills a list with the given number of the given element.
	 */
	public static <E> void addCopies(List<? super E> list, E obj, int num) {
		for (int i = 0; i < num; ++i) {
			list.add(obj);
		}
	}

	/**
	 * Compares two lists of non-null elements, index-by-index.
	 */
	public static <T> boolean compareLists(List<T> l1, List<T> l2) {
		assert l1 != null && l2 != null;
		if (l1.size() != l2.size())
			return false;
		for (int i = 0; i < l1.size(); ++i) {
			if (!l1.get(i).equals(l2.get(i)))
				return false;
		}
		return true;
	}

	/**
	 * Adds to result the elements in op1 that are not in op2.
	 */
	public static <T> void subtract(Set<T> result, Set<T> op1, Set<T> op2) {
		for (T elem : op1) {
			if (!op2.contains(elem))
				result.add(elem);
		}
	}

	/**
	 * Compares two collections by checking whether they contain the same sets of
	 * elements.
	 */
	public static boolean equalSets(Collection<?> c1, Collection<?> c2) {
		return c1.containsAll(c2) && c2.containsAll(c1);
	}

	/**
	 * Converts an array to an equivalent list. If 'arr' is null or length 0, it
	 * returns an empty list.
	 */
	public static <T> List<T> arrayToList(T[] arr) {
		if (arr == null || (arr != null && arr.length == 0)) {
			return java.util.Collections.emptyList();
		} else {
			ArrayList<T> result = new ArrayList<>();
			for (T e : arr) {
				result.add(e);
			}
			return result;
		}
	}

	/**
	 * A generic method to compute hash codes for collections, assuming their
	 * built-in iteration is consistent.
	 * 
	 * @param c
	 *            A collection.
	 * @return The hash code for the given collection.
	 */
	public static int hashCode(Collection<?> c) {
		final int prime = 31;

		int result = 1;
		if (c == null) {
			return result;
		}
		for (Object o : c) {
			result = o != null ? prime * result + o.hashCode() : prime * result + 1;
		}
		return result;
	}

	/**
	 * Checks whether a collection contains a null element.
	 */
	public static <T> boolean containsNull(Collection<T> c) {
		for (T e : c) {
			if (e == null)
				return true;
		}
		return false;
	}

	/**
	 * @param list
	 *            of items
	 * @return a HashMap where the key is the index for each item on the given list
	 *         and the value is a set of the indexes of items which are equal to
	 *         that index's item.
	 */
	public static <T> Map<Integer, List<Integer>> createEqualityMap(final List<T> list) {
		Map<Integer, List<Integer>> equallityMap = new HashMap<>();

		// populate the equality mapper
		for (int i = 0; i < list.size(); i++) {
			equallityMap.put(i, new ArrayList<Integer>());
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).equals(list.get(j))) {
					equallityMap.get(i).add(j);
				}
			}
		}
		return equallityMap;
	}
}