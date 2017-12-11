package bgu.cs.util;

/**
 * A generic ordered-triple-of-elements type.
 * 
 * @author romanm
 * 
 * @param <Type1>
 *            The type of the first element.
 * @param <Type2>
 *            The type of the second element.
 * @param <Type3>
 *            The type of the third element.
 */
public class Tuple3<Type1, Type2, Type3> {
	public final Type1 e1;
	public final Type2 e2;
	public final Type3 e3;

	public Tuple3(Type1 first, Type2 second, Type3 third) {
		this.e1 = first;
		this.e2 = second;
		this.e3 = third;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((e1 == null) ? 0 : e1.hashCode());
		result = prime * result + ((e2 == null) ? 0 : e2.hashCode());
		result = prime * result + ((e3 == null) ? 0 : e3.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		Tuple3<Type1, Type2, Type3> other = (Tuple3<Type1, Type2, Type3>) obj;
		if (e1 == null) {
			if (other.e1 != null)
				return false;
		} else if (!e1.equals(other.e1))
			return false;
		if (e2 == null) {
			if (other.e2 != null)
				return false;
		} else if (!e2.equals(other.e2)) {
			return false;
		}
		if (e3 == null) {
			if (other.e3 != null)
				return false;
		} else if (!e3.equals(other.e3)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "(" + e1 + ", " + e2 + ", " + e3 + ")";
	}
}