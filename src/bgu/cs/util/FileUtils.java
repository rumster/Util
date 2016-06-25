package bgu.cs.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import org.stringtemplate.v4.ST;

public class FileUtils {
	public static final String newline = System.getProperty("line.separator", "\n");

	/**
	 * Creates all the necessary directories to store the given file.
	 * 
	 * @param filePath
	 *            The full path to the file, including its name.
	 * @return true if the operation succeeded.
	 */
	public static boolean makeSubDirectories(String filePath) {
		char sep = File.separatorChar;
		int lastSep = filePath.lastIndexOf(sep);
		if (lastSep <= 1)
			return true;
		// remove file name from path
		filePath = filePath.substring(0, lastSep);
		File dir = new File(filePath);
		if (dir.exists())
			return true;
		return dir.mkdirs();
	}

	/**
	 * Returns a string representing the current date and time format.
	 */
	public static String getCurrentDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		String dateTime = dateFormat.format(date);
		return dateTime;
	}

	/**
	 * Lists all sub-directories under <code>dirName</code>.
	 * 
	 * @param dirName
	 *            A directory name.
	 * @return A colon separated list of sub-directories under
	 *         <code>dirName</code>.
	 */
	public static String getSubDirs(String dirName) {
		Collection<String> dirs = new ArrayList<>();
		subdirs(dirName, dirs);
		ST dirList = new ST("$dirs; separator=\":\"$", '$', '$');
		dirList.add("dirs", dirs.toArray());
		return dirList.toString();
	}

	/**
	 * A helper method for getSubDirs.
	 */
	protected static void subdirs(String dirName, Collection<String> result) {
		File dir = new File(dirName);
		if (dir.isDirectory()) {
			result.add(dir.getAbsolutePath());
			String[] children = dir.list();
			for (String child : children) {
				subdirs(dirName + File.separatorChar + child, result);
			}
		}
	}

	/**
	 * Reads a file and stores its content in a string.
	 */
	public static String fileToString(File f) {
		String result;
		try {
			int fileLength = (int) f.length();
			byte[] buffer = new byte[fileLength];
			BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(f));
			inputStream.read(buffer);
			inputStream.close();
			result = new String(buffer);
		} catch (IOException e) {
			throw new Error(e.getMessage());
		}
		return result;
	}

	/**
	 * Reads a file and stores its content in a string.
	 */
	public static String fileToString(String filename) {
		return fileToString(new File(filename));
	}

	public static void stringToFile(String content, String filename) {
		assert content != null && filename != null;
		try {
			FileWriter writer = new FileWriter(filename);
			writer.write(content.toString());
			writer.close();
		} catch (IOException e) {
			throw new Error(e.getMessage());
		}
	}

	/**
	 * Converts a file system path to one using operating system specific path
	 * separators.
	 */
	public static String normalizePath(String path) {
		path = path.replace('\\', File.separatorChar);
		path = path.replace('/', File.separatorChar);
		path = path.replaceAll("%20", " "); // For windows
		return path;
	}

	/**
	 * Converts a package name to a relative path. Simple substitution of . with
	 * /
	 * 
	 * @param packageName
	 *            the package name of the file.
	 * @return relative path
	 */
	public static String packageToPath(String packageName) {
		if (packageName == null || packageName.length() == 0)
			return "";
		char[] res = packageName.toCharArray();
		for (char c : res)
			if (c == '.')
				c = '/';
		return new String(res) + "/";
	}

}