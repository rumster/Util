package bgu.cs.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.stringtemplate.v4.ST;

/**
 * Uses reflection to fill a template using the fields/accessors of an object.
 * Prefixing a template attribute with <code>raw_</code> loads the attribute
 * without the prefix as-is (i.e., without attempting to load a template for
 * it).
 * 
 * @author romanm
 */
public class STHierarchyRenderer {
	protected STGLoader templates;
	protected boolean debug = false;
	protected Logger logger;

	/**
	 * Constructs an {@link STHierarchyRenderer} which loads templates from the
	 * given {@link STGLoader}.
	 * 
	 * @param templates
	 *            The template loader that will be used to populate templates.
	 */
	public STHierarchyRenderer(STGLoader templates) {
		this.templates = templates;
		if (debug && logger != null)
			logger.finest("Loading templates from "
					+ templates.getGroupFileName());
	}

	public STHierarchyRenderer(Class<?> cls, String templatesFileName) {
		templates = new STGLoader(cls, templatesFileName);
		if (debug && logger != null)
			logger.finest("Loading templates from " + templatesFileName);
	}

	/**
	 * Fills a template of the same name as the object by taking the attribute
	 * values from the fields/accessors of the given object.
	 */
	public String render(Object o) {
		if (o == null) {
			return "";
		} else {
			// Anonymous classes have no simple name so get a name from their
			// super class.
			Class<?> cls = o.getClass();
			String className = cls.getSimpleName();
			while (className.equals("")) {
				cls = cls.getSuperclass();
				className = cls.getSimpleName();
			}
			return render(o, className);
		}
	}

	/**
	 * Fill a template by taking the attribute values from the fields/accessors
	 * of the given object.
	 */
	public String render(Object o, String templateName) {
		// Use reflection to get the name of the template - should be the same
		// as the name of the AST node class.
		ST template = templates.load(templateName);
		if (template == null)
			return o.toString();

		// Get the names of the template attributes and then use reflection
		// to set the values of the corresponding fields in the node object.
		Set<String> formalArgs = template.impl.formalArguments != null ? template.impl.formalArguments
				.keySet() : new HashSet<String>();
		for (String formalArg : formalArgs) {
			if (formalArg.equals("date")) {
				template.add("date", new Date().toString());
			} else if (formalArg.startsWith("raw_")) {
				String attributeName = formalArg.replaceFirst("raw_", "");
				Object attributeValue = accessAttribute(o, attributeName);
				template.add(formalArg, attributeValue);
			} else if (formalArg.startsWith("is_")) {
				String attributeName = StringUtils.camelNotation("is",
						formalArg.replaceFirst("is_", ""));
				try {
					Method testMethod = o.getClass().getMethod(attributeName);
					Object answer = testMethod.invoke(o);
					if (answer instanceof Boolean
							&& ((Boolean) answer).booleanValue())
						template.add(formalArg, "true");
				} catch (NoSuchMethodException | SecurityException
						| IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
			} else {
				Object attributeValue = accessAttribute(o, formalArg);
				if (attributeValue != null) {
					if (attributeValue instanceof Iterable<?>) {
						Iterable<?> attributeValueCollection = (Iterable<?>) attributeValue;
						for (Object e : attributeValueCollection) {
							template.add(formalArg, render(e));
						}
						// The following case is not necessarily needed, since a
						// missing template leads to calling toString on
						// attributeValue anyway.
					} else if (attributeValue instanceof String) {
						template.add(formalArg, attributeValue.toString());
					} else {
						template.add(formalArg, render(attributeValue));
					}
				}
			}

		}
		return template.render();
	}

	public void setDebug(boolean debug) {
		setDebug(debug, null);
	}

	public void setDebug(boolean debug, Logger log) {
		this.debug = debug;
		this.logger = log;
	}

	protected Object accessAttribute(Object o, String attributeName) {
		Object attributeValue = null;

		attributeValue = getFieldAttributeFromField(o, attributeName);
		if (attributeValue == null)
			attributeValue = getFieldAttributeFromMethod(o, attributeName);
		if (attributeValue == null) {
			attributeValue = getFieldAttributeFromMethod(o,
					StringUtils.camelNotation("get", attributeName));
		}
		return attributeValue;
	}

	/**
	 * Attempts to retrieve the value of a public field.
	 */
	protected Object getFieldAttributeFromField(Object o, String fieldName) {
		try {
			Field attributeField = o.getClass().getField(fieldName);
			return attributeField.get(o);
		} catch (NoSuchFieldException | IllegalArgumentException
				| IllegalAccessException | SecurityException e) {
		}
		return null;
	}

	/**
	 * Attempts to retrieve the value from a method.
	 */
	protected Object getFieldAttributeFromMethod(Object o, String methodName) {
		try {
			Method method = o.getClass().getMethod(methodName);
			return method.invoke(o);
		} catch (NoSuchMethodException | IllegalArgumentException
				| IllegalAccessException | SecurityException
				| InvocationTargetException e) {
		}
		return null;
	}

	protected boolean hasArgument(ST template, String arg) {
		return template.impl.formalArguments != null
				&& template.impl.formalArguments.containsKey(arg);
	}
}