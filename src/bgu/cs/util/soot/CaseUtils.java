package bgu.cs.util.soot;

import soot.Local;
import soot.Type;

/**
 * Utilities for conducting case analysis on Jimple elements.
 * 
 * @author romanm
 *
 */
public class CaseUtils {
	public static boolean localMatchesType(Type t, Class<?> cls) {
		String typeName = t.toString();
		try {
			Class<?> typeClass = Class.forName(typeName);
			if (typeClass == cls)
				return true;
			for (Class<?> implementedInterface : typeClass.getInterfaces()) {
				if (implementedInterface == cls) {
					return true;
				}
			}
			return false;
		} catch (ClassNotFoundException e) {
			throw new Error("Unable to find class " + typeName + "!");
		}
	}

	public static Class<?> getClass(Local x) {
		String typeName = x.getType().toString();
		try {
			Class<?> cls = Class.forName(typeName);
			return cls;
		} catch (ClassNotFoundException e) {
			throw new Error("Unable to find class " + typeName + "!");
		}
	}
}