package bgu.cs.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Iterates over all tuples in the Cartesian product of a tuple of collections.
 * 
 * @author romanm
 *
 * @param <T>
 *            The type of elements in the collections.
 */
public class CartesianIterator<T> implements Iterator<List<T>> {
	private final List<Collection<T>> domains;
	private final List<Iterator<T>> digits;
	private final List<T> tuple;
	private boolean done = false;

	public CartesianIterator(List<Collection<T>> domains, List<T> tuple) {
		assert domains != null && tuple != null && domains.size() == tuple.size();
		this.domains = domains;
		this.digits = new ArrayList<Iterator<T>>(domains.size());
		int i = 0;
		for (Collection<T> domain : domains) {
			Iterator<T> domainIter = domain.iterator();
			if (!domainIter.hasNext()) {
				throw new IllegalArgumentException(
						"The " + i + "th domain passed to " + CartesianIterator.class.getSimpleName() + " is empty!");
			}
			digits.set(i, domainIter);
			tuple.set(i, domainIter.next());
			++i;
		}
		this.tuple = tuple;
	}

	@Override
	public boolean hasNext() {
		return done;
	}

	@Override
	public List<T> next() {
		if (done) {
			throw new NoSuchElementException();
		}
		for (int i = 0; i < domains.size(); ++i) {
			Iterator<T> digitIter = digits.get(i);
			if (digitIter.hasNext()) {
				tuple.set(i, digitIter.next());
				break;
			} else {
				if (i == domains.size()) {
					done = true;
				} else {
					digits.set(i, domains.get(i).iterator());
				}
			}
		}
		return tuple;
	}
}