package bgu.cs.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Text-related utilities.
 * 
 * @author romanm
 */
public class StringUtils {
	/**
	 * An operating system-independent new line string.
	 */
	public static final String newLine = System.getProperty("line.separator");

	/**
	 * The character used to create a line (for #addUnderline).
	 */
	public static final char underlineCharacter = '-';

	/**
	 * Converts a collection to a comma-separated string.
	 * 
	 * @param c
	 *            A collection.
	 * @return A list of the string-representation of the elements in the
	 *         collection separated by a comma.
	 */
	public static <T> String toString(Collection<T> c) {
		StringBuilder result = new StringBuilder();
		int i = c.size();
		for (T elem : c) {
			result.append(elem.toString());
			--i;
			if (i > 0)
				result.append(", ");
		}
		return result.toString();
	}

	/**
	 * Converts an array to a comma-separated string.
	 * 
	 * @param c
	 *            An array.
	 * @return A list of the string-representation of the elements in the array
	 *         separated by a comma.
	 */
	public static <T> String toString(T[] c) {
		StringBuilder result = new StringBuilder();
		int i = c.length;
		for (T elem : c) {
			result.append(elem.toString());
			--i;
			if (i > 0)
				result.append(", ");
		}
		return result.toString();
	}

	/**
	 * Can be used to add an underline to another string.
	 * 
	 * @param source
	 *            The original string.
	 * @return The source string with an underline added to it.
	 */
	public static String addUnderline(String source) {
		StringBuffer result = new StringBuffer();
		result.append(source + newLine);

		for (int index = 0; index < source.length(); ++index) {
			result.append(underlineCharacter);
		}

		return result.toString();
	}

	public static String constantNotation(String... strs) {
		if (strs == null || strs.length == 0)
			return "";

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < strs.length; ++i) {
			builder.append(strs[i].toUpperCase());
			if (i + 1 < strs.length)
				builder.append("_");
		}
		return builder.toString();
	}

	public static String camelNotation(String... strs) {
		return camelNotation(false, strs);
	}

	public static String camelNotation(boolean capitalizeFirst, String... strs) {
		if (strs == null || strs.length == 0)
			return "";

		StringBuilder builder;
		if (capitalizeFirst)
			builder = new StringBuilder(capitalizeFirst(strs[0]));
		else
			builder = new StringBuilder(strs[0]);
		for (int i = 1; i < strs.length; ++i) {
			builder.append(capitalizeFirst(strs[i]));
		}
		return builder.toString();
	}

	public static String capitalizeFirst(String str) {
		if ('A' <= str.charAt(0) && str.charAt(0) <= 'Z')
			return str;
		String firstChar = str.substring(0, 1).toUpperCase();
		String tail = str.substring(1, str.length());
		return firstChar + tail;
	}

	public static String lowerFirst(String str) {
		if ('a' <= str.charAt(0) && str.charAt(0) <= 'z')
			return str;
		String firstChar = str.substring(0, 1).toLowerCase();
		String tail = str.substring(1, str.length());
		return firstChar + tail;
	}

	/**
	 * Splits a string by considering " " as a separator and storing the
	 * resulting sub-string in a list.
	 * 
	 * @param s
	 *            A string.
	 * @return A list of strings.
	 */
	public static List<String> breakString(String s) {
		return breakString(s, " ");
	}

	/**
	 * Splits a string according to the specified separator string and stores
	 * the result in a list.
	 * 
	 * @param s
	 *            A string.
	 * @param sep
	 *            A separator string.
	 * @return A list of strings.
	 */
	public static List<String> breakString(String s, String sep) {
		List<String> result = new ArrayList<>();
		StringTokenizer tokenizer = new StringTokenizer(s, sep);

		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			result.add(token);
		}

		return result;
	}

	/**
	 * Compares two strings after removing all white space characters.
	 */
	public static boolean equalModuloWhitespace(String s1, String s2) {
		s1 = s1.replaceAll(" ", "");
		s1 = s1.replaceAll("\t", "");
		s1 = s1.replaceAll("\n", "");
		s1 = s1.replaceAll("\r", "");
		s2 = s2.replaceAll(" ", "");
		s2 = s2.replaceAll("\t", "");
		s2 = s2.replaceAll("\n", "");
		s2 = s2.replaceAll("\r", "");
		return s1.equals(s2);
	}

	/**
	 * Converts a collection to a list of sub-strings separated by the given
	 * separator string.
	 * 
	 * @param c
	 *            A collection.
	 * @return A list of sub-strings separated by the given separator string.
	 */
	public static <T> String toString(Collection<T> c, String separator) {
		StringBuilder result = new StringBuilder();
		int i = c.size();
		for (T elem : c) {
			result.append(elem.toString());
			--i;
			if (i > 0)
				result.append(separator);
		}
		return result.toString();
	}
}