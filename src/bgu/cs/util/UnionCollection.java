package bgu.cs.util;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides a way to iterate over the elements of two collections as if they
 * were put together.
 * 
 * @author romanm
 *
 * @param <T>
 *            The type of elements in both collections.
 */
public class UnionCollection<T> extends AbstractCollection<T> {
	private final Collection<T> c1;
	private final Collection<T> c2;

	public UnionCollection(Collection<T> c1, Collection<T> c2) {
		this.c1 = c1;
		this.c2 = c2;
	}

	@Override
	public Iterator<T> iterator() {
		return new UnionIterator();
	}

	@Override
	public int size() {
		return c1.size() + c2.size();
	}

	private class UnionIterator implements Iterator<T> {
		private final Iterator<T> it1;
		private final Iterator<T> it2;
		private int iterationsLeftToTake;

		public UnionIterator() {
			it1 = c1.iterator();
			it2 = c2.iterator();
			iterationsLeftToTake = c1.size() + c1.size();
		}

		@Override
		public boolean hasNext() {
			return iterationsLeftToTake > 0;
		}

		@Override
		public T next() {
			--iterationsLeftToTake;
			if (iterationsLeftToTake < 0)
				throw new NoSuchElementException();
			return iterationsLeftToTake < c2.size() ? it2.next() : it1.next();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}