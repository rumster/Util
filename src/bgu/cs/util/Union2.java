package bgu.cs.util;

import java.util.function.Consumer;

/**
 * Stores a value from one of two other types.
 * 
 * @author romanm
 *
 * @param <T1>
 *            The first possible type of the stored value.
 * @param <T2>
 *            The second possible type of the stored value.
 */
public class Union2<T1, T2> {
	private final T1 t1;
	private final T2 t2;

	public static <T1, T2> Union2<T1, T2> ofT1(T1 t1) {
		if (t1 == null) {
			throw new IllegalArgumentException("Null value are not allowed in UnionType!");
		}
		return new Union2<T1, T2>(t1, null);
	}

	public static <T1, T2> Union2<T1, T2> ofT2(T2 t2) {
		if (t2 == null) {
			throw new IllegalArgumentException("Null value are not allowed in UnionType!");
		}
		return new Union2<T1, T2>(null, t2);
	}

	public boolean isT1() {
		return t1 != null;
	}

	public boolean isT2() {
		return t1 != null;
	}

	public T1 getT1() {
		if (t1 == null) {
			throw new IllegalArgumentException("Attempt to get a value from UnionType for the wrong type!");
		}
		return t1;
	}

	public T2 getT2() {
		if (t2 == null) {
			throw new IllegalArgumentException("Attempt to get a value from UnionType for the wrong type!");
		}
		return t2;
	}

	public void match(Consumer<T1> case1, Consumer<T2> case2) {
		if (t1 != null) {
			case1.accept(t1);
		} else {
			assert t2 != null;
			case2.accept(t2);
		}
	}

	private Union2(T1 t1, T2 t2) {
		this.t1 = t1;
		this.t2 = t2;
	}
}