package bgu.cs.util.rel;

/**
 * An implementation of {@link Rel3Map} based on {@link java.util.HashMap> and
 * {@link java.util.HashSet}.
 * 
 * @author romanm
 *
 * @param <E1>The
 *            type of the first element in every tuple of the relation.
 * @param <E2>
 *            The type of the second element in every tuple of the relation.
 * @param <E3>
 *            The type of the third element in every tuple of the relation.
 */
public class HashRel3MapD<E1, E2, E3> extends HashRel3Map<E1, E2, E3> {
	protected final E3 defaultVal;

	public HashRel3MapD(E3 defaultVal) {
		this.defaultVal = defaultVal;
	}

	@Override
	public E3 get(E1 e1, E2 e2) {
		E3 result = super.get(e1, e2);
		return result == null ? defaultVal : result;
	}
}