package bgu.cs.util;

/**
 * Utilities for managing arrays.
 * 
 * @author romanm
 */
public class Arrays {
	/**
	 * Checks whether all elements in the array are different from null.
	 * 
	 * @param arr
	 *            An array of references.
	 * @param shouldAssert
	 *            If true, raises an assertion error when one of the elemetns is
	 *            null.
	 */
	public static boolean checkNoNulls(Object[] arr, boolean shouldAssert) {
		assert arr != null;
		for (int i = 0; i < arr.length; ++i) {
			if (arr[i] == null) {
				if (shouldAssert)
					assert false;
				return false;
			}
		}
		return true;
	}
}