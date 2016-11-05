package bgu.cs.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Runtime-type related utility functions.
 * 
 * @author romanm
 */
public class ReflectionUtils {
	/**
	 * Tests whether {@link supercls} is the same as {@link cls} or one of its
	 * super classes.
	 */
	public static boolean isSubclass(Class<?> cls, final Class<?> supercls) {
		assert cls != null && supercls != null;
		while (cls != null) {
			if (cls.equals(supercls))
				return true;
			cls = cls.getSuperclass();
		}
		return false;
	}

	/**
	 * Returns the set of classes that exist in a given package and are
	 * optionally a sub-class of the given class.
	 * 
	 * @param pkg
	 *            The package to look in.
	 * @param superCls
	 *            The super class used to filter out the classes, or null if all
	 *            classes should be returned.
	 */
	public static Collection<Class<?>> getClasses(Package pkg, Class<?> superCls) {
		String path = pkg.getName();
		path = path.replace('.', '/');
		ArrayList<Class<?>> res = new ArrayList<>();

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL pathURL = classLoader.getResource(path);
		File pathFile = new File(pathURL.getFile());
		File[] contents = pathFile.listFiles();
		try {
			for (File f : contents) {
				if (f.getName().endsWith(".class")) {
					String clsName = pkg.getName() + "." + f.getName().substring(0, f.getName().length() - 6);
					Class<?> cls = Class.forName(clsName);
					if (superCls != null && isSubclass(cls, superCls)) {
						res.add(cls);
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return res;
	}

	/**
	 * Assigns the default value to the given field of the given objects.
	 * 
	 * @param o
	 *            An object
	 * @param f
	 *            A field of the given object
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void resetField(Object o, Field f) throws IllegalArgumentException, IllegalAccessException {
		Class<?> fieldType = f.getType();
		if (fieldType.isPrimitive()) {
			if (fieldType.equals(Boolean.class)) {
				f.setBoolean(o, false);
			} else if (fieldType.equals(Byte.class)) {
				f.setByte(o, (byte) 0);
			} else if (fieldType.equals(Character.class)) {
				f.setChar(o, (char) 0);
			} else if (fieldType.equals(Integer.class)) {
				f.setInt(o, 0);
			} else if (fieldType.equals(Long.class)) {
				f.setLong(o, 0);
			} else if (fieldType.equals(Float.class)) {
				f.setFloat(o, 0);
			} else if (fieldType.equals(Double.class)) {
				f.setDouble(o, 0);
			} else if (fieldType.equals(Short.class)) {
				f.setShort(o, (short) 0);
			} else {
				throw new Error("Encountered an unknown primitive type: " + fieldType.getName());
			}
		} else {
			f.set(o, null);
		}
	}

	/**
	 * Copies the value of a given field from one object to another.
	 * 
	 * @param from
	 *            The object from which the value of the field is taken.
	 * @param f
	 *            The field that should be copied.
	 * @param to
	 *            The object whose field is assigned.
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void copyField(Object from, Field f, Object to)
			throws IllegalArgumentException, IllegalAccessException {
		f.set(to, f.get(from));
	}

	/**
	 * Returns the value of the named field in the given object.
	 */
	public static Object getFieldValue(Object obj, String fieldName)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = obj.getClass().getField(fieldName);
		if (field != null)
			return field.get(obj);
		return null;
	}

	public static boolean fieldExists(Object obj, String fieldName) {
		for (Field field : obj.getClass().getDeclaredFields()) {
			if (field.getName().equals(fieldName))
				return true;
		}
		return false;
	}

	/**
	 * Sets the value of the named field of the given object to the to the given
	 * value.
	 */
	public static void setFieldValue(Object obj, String fieldName, Object value)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = obj.getClass().getField(fieldName);
		if (field != null)
			field.set(obj, value);
	}

	public static Method getMethodByName(Class<?> cls, String name) {
		for (Method method : cls.getMethods()) {
			if (method.getName().equals(name))
				return method;
		}
		return null;
	}

	public static boolean isObjectRefType(Class<?> type) {
		return !type.isPrimitive() && !type.isArray() && !type.isSynthetic();
	}

	public static boolean isIntType(Class<?> type) {
		return type.isPrimitive() && !type.isSynthetic() && type.equals(Integer.TYPE);
	}

}