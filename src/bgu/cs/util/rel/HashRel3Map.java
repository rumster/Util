package bgu.cs.util.rel;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import bgu.cs.util.Pair;

/**
 * An implementation of {@link Rel3Map} based on {@link HashMap> and
 * {@link HashSet}.
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
public class HashRel3Map<E1, E2, E3> implements Rel3Map<E1, E2, E3> {
	private Rel2Map<E1, Rel2Map<E2, E3>> e1ToE23 = new HashRel2Map<>();
	private Rel2Map<E2, Rel2Map<E1, E3>> e2ToE13 = null;
	private Rel2Map<E3, Rel2<E1, E2>> e3ToE12 = null;

	private int size = 0;

	public HashRel3Map() {
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public boolean contains(E1 e1, E2 e2, E3 e3) {
		return get(e1, e2).equals(e3);
	}

	@Override
	public boolean add(E1 e1, E2 e2, E3 e3) {
		boolean result = false;
		if (e1ToE23 != null) {
			Rel2Map<E2, E3> e23s = e1ToE23.get(e1);
			if (e23s == null) {
				e23s = new HashRel2Map<>();
				e1ToE23.put(e1, e23s);
			}
			result |= e23s.get(e2).equals(e3);
			e23s.put(e2, e3);
		}
		if (e2ToE13 != null) {
			Rel2Map<E1, E3> e13s = e2ToE13.get(e2);
			if (e13s == null) {
				e13s = new HashRel2Map<>();
				e2ToE13.put(e2, e13s);
			}
			result |= e13s.add(e1, e3);
		}
		if (e3ToE12 != null) {
			Rel2<E1, E2> e12s = e3ToE12.get(e3);
			if (e12s == null) {
				e12s = new HashRel2<>();
				e3ToE12.put(e3, e12s);
			}
			result |= e12s.add(e1, e2);
		}
		return result;
	}

	@Override
	public void clear() {
		e1ToE23.clear();
		e2ToE13.clear();
		e3ToE12.clear();
	}

	@Override
	public boolean remove(E1 e1, E2 e2, E3 e3) {
		boolean result = false;
		if (e1ToE23 != null) {
			Rel2Map<E2, E3> e23s = e1ToE23.get(e1);
			if (e23s != null) {
				result = e23s.remove(e2, e3);
			}
		}
		if (e2ToE13 != null) {
			Rel2<E1, E3> e13s = e2ToE13.get(e2);
			if (e13s != null) {
				result = e13s.remove(e1, e3);
			}
		}
		if (e3ToE12 != null) {
			Rel2<E1, E2> e12s = e3ToE12.get(e3);
			if (e12s != null) {
				result = e12s.remove(e1, e2);
			}
		}
		return result;
	}

	@Override
	public boolean remove1(E1 e1) {
		throw new Error("Unimplemented!");
	}

	@Override
	public boolean remove2(E2 e2) {
		throw new Error("Unimplemented!");
	}

	@Override
	public boolean remove3(E3 e3) {
		throw new Error("Unimplemented!");
	}

	@Override
	public boolean remove12(E1 e1, E2 e2) {
		throw new Error("Unimplemented!");
	}

	@Override
	public boolean remove13(E1 e1, E3 e3) {
		throw new Error("Unimplemented!");
	}

	@Override
	public boolean remove23(E2 e2, E3 e3) {
		throw new Error("Unimplemented!");
	}

	@Override
	public Rel2<E2, E3> select1(E1 e1) {
		throw new Error("Unimplemented!");
	}

	@Override
	public Rel2Map<E1, E3> select2(E2 e2) {
		buildE2ToE13();
		return e2ToE13.get(e2);
	}

	@Override
	public Rel2<E1, E2> select3(E3 e3) {
		throw new Error("Unimplemented!");
	}

	@Override
	public Collection<E3> select12(E1 e1, E2 e2) {
		throw new Error("Unimplemented!");
	}

	@Override
	public Collection<E2> select13(E1 e1, E3 e3) {
		throw new Error("Unimplemented!");
	}

	@Override
	public Collection<E1> select23(E2 e2, E3 e3) {
		throw new Error("Unimplemented!");
	}

	@Override
	public Collection<E1> all1() {
		throw new Error("Unimplemented!");
	}

	@Override
	public Collection<E2> all2() {
		throw new Error("Unimplemented!");
	}

	@Override
	public Collection<E3> all3() {
		throw new Error("Unimplemented!");
	}

	protected void buildE1ToE23() {
		if (e1ToE23 == null) {
			throw new Error("Unimplemented!");
		}
	}

	protected void buildE2ToE13() {
		if (e2ToE13 == null) {
			e2ToE13 = new HashRel2Map<>();
			for (Pair<E1, Rel2Map<E2, E3>> entry : e1ToE23.all()) {
				E1 e1 = entry.first;
				Rel2Map<E2, E3> e2ToE3 = entry.second;
			}
			throw new Error("Unimplemented!");
		}
	}

	protected void buildE3ToE11() {
		if (e3ToE12 == null) {
			throw new Error("Unimplemented!");
		}
	}

	@Override
	public E3 get(E1 e1, E2 e2) {
		buildE1ToE23();
		Rel2Map<E2, E3> valsForE1 = e1ToE23.get(e1);
		if (valsForE1 == null)
			return null;
		return valsForE1.get(e2);
	}

	@Override
	public E3 put(E1 e1, E2 e2, E3 e3) {
		buildE1ToE23();
		Rel2Map<E2, E3> valsForE1 = e1ToE23.get(e1);
		if (valsForE1 == null) {
			valsForE1 = new HashRel2Map<E2, E3>();
			e1ToE23.put(e1, valsForE1);
		}
		return valsForE1.put(e2, e3);
	}

	@Override
	public Map<E2, E3> get1(E1 e1) {
		throw new Error("Unimplemented!");
	}
}