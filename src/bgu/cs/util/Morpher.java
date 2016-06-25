package bgu.cs.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A framework for "morphing" objects of "source" types to objects of
 * "destination" types.
 * 
 * @author romanm
 */
public class Morpher {
	protected final Class<?> srcCls;
	protected final Class<?> dstCls;

	protected final Map<Class<?>, Class<?>> srcTypeToDstType = new HashMap<>();

	public Morpher(Class<?> srcCls, Class<?> dstCls) {
		this.srcCls = srcCls;
		this.dstCls = dstCls;
	}

	public void addSrcClass(Class<?> cls) {
	}

	public void addDstClass(Class<?> cls) {
	}

	public void addSourceClassesFromPkg(Package pkg, Class<?> superCls) {
		Collection<Class<?>> classesInPkg = ReflectionUtils.getClasses(pkg, superCls);
		for (Class<?> cls : classesInPkg) {
			addSrcClass(cls);
		}
	}

	public void addDstClassesFromPkg(Package pkg, Class<?> superCls) {
		Collection<Class<?>> classesInPkg = ReflectionUtils.getClasses(pkg, superCls);
		for (Class<?> cls : classesInPkg) {
			addDstClass(cls);
		}
	}

	public Object morph(Object o) {
		return null;
	}
}