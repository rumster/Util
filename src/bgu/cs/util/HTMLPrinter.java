package bgu.cs.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.stringtemplate.v4.ST;

import bgu.cs.util.graph.MultiGraph;
import bgu.cs.util.graph.visualization.GraphToHTMLRenderer;
import bgu.cs.util.graph.visualization.GraphizVisualizer;

/**
 * Outputs HTML-formatted information to aid visualizing and debugging
 * applications.
 * 
 * @author romanm
 */
public class HTMLPrinter {
	protected STGLoader templates = new STGLoader(HTMLPrinter.class);
	protected String title;
	protected String outputDirPath;
	protected GraphToHTMLRenderer graphVisualizer = new GraphizVisualizer();
	protected final Logger logger;
	protected boolean printedConstantFiles = false;

	public HTMLPrinter(Logger logger, String title, String outputDirPath) {
		this.logger = logger;
		this.title = title;
		this.outputDirPath = outputDirPath;
	}

	/**
	 * A list of descriptions and links.
	 */
	protected ArrayList<Pair<String, String>> descriptionFileList = new ArrayList<>();

	/**
	 * Re-prints the table of contents file.
	 * 
	 * @return true if no I/O errors occurred.
	 */
	public boolean refresh() {
		if (!printedConstantFiles) {
			boolean ok = printEmptyFrame() && printIndexFile();
			if (!ok) {
				return false;
			}
			printedConstantFiles = true;
		}
		return printTableofContentsFile();
	}

	public boolean printCodeFile(String fileName, String content, String description) {
		ST template = templates.load("code");
		template.add("description", description);
		content = content.replace("<", "&lt;");
		content = content.replace(">", "&gt;");
		template.add("content", content);
		return printTextFile(fileName, template.render(), description) && refresh();
	}

	public boolean printLink(String url, String description) {
		descriptionFileList.add(new Pair<String, String>(description, url));
		return refresh();
	}

	public boolean printTextFile(String fileName, String content, String description) {
		String finalHTMLFileName = outputDirPath + File.separator + fileName + ".html";
		File file = new File(finalHTMLFileName);
		try {
			String path = file.getCanonicalPath();
			FileUtils.stringToFile(content, path);
			descriptionFileList.add(new Pair<String, String>(description, path));
		} catch (IOException e) {
			logger.severe("Unable to write to file " + file.getName() + " (" + e.getMessage() + ")!");
			return false;
		}
		return refresh();

	}

	public boolean printGraph(MultiGraph<?, ?> graph, String description) {
		String fileName = "graph_" + descriptionFileList.size();
		File file = new File(outputDirPath + File.separator + fileName + ".html");
		try {
			graphVisualizer.renderToFile(graph, description, file);
			String path = file.getCanonicalPath();
			descriptionFileList.add(new Pair<String, String>(description, path));
		} catch (IOException e) {
			logger.severe("Unable to write to file " + file.getName() + " (" + e.getMessage() + ")!");
			return false;
		}
		return refresh();
	}

	protected boolean printIndexFile() {
		ST template = templates.load("index");
		File file = new File(outputDirPath + File.separator + "index.html");
		try {
			FileUtils.stringToFile(template.render(), file.getCanonicalPath());
		} catch (IOException e) {
			logger.severe("Unable to write to file " + file.getName() + " (" + e.getMessage() + ")!");
			return false;
		}
		return true;
	}

	protected boolean printTableofContentsFile() {
		ST template = templates.load("tableOfContents");
		template.add("title", title);

		for (Pair<String, String> entry : descriptionFileList) {
			template.add("descriptionFileList", entry);
		}

		File file = new File(outputDirPath + File.separator + "main.html");
		try {
			FileUtils.stringToFile(template.render(), file.getCanonicalPath());
		} catch (IOException e) {
			logger.severe("Unable to write to file " + file.getName() + " (" + e.getMessage() + ")!");
			return false;
		}

		return true;
	}

	protected boolean printEmptyFrame() {
		ST template = templates.load("empty");
		File file = new File(outputDirPath + File.separator + "empty.html");
		try {
			FileUtils.stringToFile(template.render(), file.getCanonicalPath());
		} catch (IOException e) {
			logger.severe("Unable to write to file " + file.getName() + " (" + e.getMessage() + ")!");
			return false;
		}
		return true;
	}
}