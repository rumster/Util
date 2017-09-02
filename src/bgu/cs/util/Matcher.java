package bgu.cs.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A framework for defining predicates (dubbed "cases") on objects and applying
 * them to objects to find a match. The predicates are arranged in a forest such
 * that each predicate refines (i.e., more specific) its parent predicate. The
 * hierarchy is determined by the inheritance relation between the predicate
 * classes (this is automatically inferred by using reflection).<br>
 * The matcher finds the most specific predicate that matches an object.<br>
 * In addition to matching, the predicates can reference internal elements of a
 * matched object to aid its clients to process the object.
 * 
 * @author romanm
 *
 * @param <E>
 *            The type of elements being matched.
 */
public class Matcher<E> {
	/**
	 * The roots of the forest of predicates.
	 */
	protected final List<Case<E>> rootCases = new ArrayList<>(10);

	/**
	 * Creates a matcher with an empty set of case classes.
	 */
	public Matcher() {
	}

	/**
	 * 
	 * 
	 * @param caseClasses
	 */
	public void addAllCases(Collection<Class<?>> caseClasses) {
		buildCasesTree(caseClasses);
	}

	/**
	 * Adds all sub-classes of {@link Case} found in the given package to this
	 * matcher.
	 * 
	 * @param pkg
	 *            The package to search in.
	 */
	public void addAllCasesFromPkg(Package pkg) {
		Collection<Class<?>> caseClassesInPkg = ReflectionUtils.getClasses(pkg, Matcher.Case.class);
		buildCasesTree(caseClassesInPkg);
	}

	/**
	 * Finds the most specific case that matches the given object.
	 * 
	 * @param o
	 *            The object to match.
	 * @return The most specific case that matches the given object or null if
	 *         non does.
	 */
	public Case<E> match(E o) {
		List<Case<E>> currentCases = new ArrayList<>(rootCases);
		Case<E> currentMatchingCase = null;
		while (!currentCases.isEmpty()) {
			Case<E> currCase = currentCases.remove(0);
			currCase.clearFields();
			if (currentMatchingCase != null) {
				currCase.initFromSuperCase(currentMatchingCase);
			}

			if (currCase.match(o)) {
				currentMatchingCase = currCase;
				currentCases.clear();
				currentCases.addAll(currCase.subCases);
			}
		}
		return currentMatchingCase;
	}

	/**
	 * Builds a tree of case objects corresponding to the given case classes,
	 * based on their inheritance relation.
	 * 
	 * @param classes
	 *            A collection of classes extending {@link Case}.
	 */
	protected void buildCasesTree(Collection<Class<?>> classes) {
		Map<Class<?>, Case<E>> clsToInstance = new HashMap<>();

		// First create an object for each Case class.
		for (Class<?> cls : classes) {
			try {
				@SuppressWarnings("unchecked")
				Case<E> caseObj = (Case<E>) cls.newInstance();
				clsToInstance.put(cls, caseObj);
			} catch (InstantiationException | IllegalAccessException e) {
				throw new Error("Failed to create a new " + cls.getName());
			}
		}

		// Second, connect sub-cases to their parents.
		for (Class<?> cls : classes) {
			Case<E> caseObj = clsToInstance.get(cls);
			Class<?> superCls = cls.getSuperclass();
			Case<E> superCaseObj = clsToInstance.get(superCls);
			if (superCaseObj != null) {
				superCaseObj.addSubCase(caseObj);
				caseObj.rootCase = false;
			}
		}

		// Third, find the root cases.
		for (Case<E> caseObj : clsToInstance.values()) {
			if (caseObj.rootCase)
				rootCases.add(caseObj);
		}
	}

	/**
	 * Defines a predicate over objects.<br>
	 * This is the base class of all case classes that can be used as predicates
	 * by a {@link Matcher}.
	 * 
	 * @author romanm
	 *
	 * @param <E>
	 *            The type of objects being matched.
	 */
	public abstract static class Case<E> {
		/**
		 * Indicates whether this case is not a sub-case of any other case.
		 */
		protected boolean rootCase = true;

		/**
		 * Cases refining (i.e., more specific than) this one.
		 */
		protected List<Case<E>> subCases = new ArrayList<>();

		/**
		 * Tests whether this predicate holds for the given object.
		 * 
		 * @param element
		 *            The object being tested.
		 * @return true if the given element satisfies the predicate of this
		 *         class.
		 */
		public abstract boolean match(E element);

		/**
		 * Adds a predicate that refines this predicate as a child in the
		 * hierarchy of predicates.
		 * 
		 * @param sub
		 */
		protected void addSubCase(Case<E> sub) {
			subCases.add(sub);
		}

		protected void initFromSuperCase(Case<E> supercase) {
			for (Field f : supercase.getClass().getFields()) {
				try {
					ReflectionUtils.copyField(supercase, f, this);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new Error("Failed to copy field " + f.getName() + "between Case objects "
							+ this.getClass().getSimpleName() + " and " + supercase.getClass().getSimpleName());
				}
			}
		}

		protected void clearFields() {
			for (Field f : this.getClass().getFields()) {
				try {
					ReflectionUtils.resetField(this, f);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new Error("Failed to reset Case object " + this.getClass().getSimpleName()
							+ " by assigning null to field " + f.getName());
				}
			}
		}
	}
}