package bgu.cs.util.graph.visualization;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.stringtemplate.v4.ST;

import bgu.cs.util.FileUtils;
import bgu.cs.util.STGLoader;
import bgu.cs.util.graph.MultiGraph;

/**
 * Utilities to invoke Graphiz tools for rendering data as graphs.
 * 
 * @author romanm
 */
public class GraphizVisualizer extends GraphToHTMLRenderer {
	public static final long GRAPHVIZ_TIMEOUT = 1;
	protected STGLoader templates;
	protected Logger logger;

	/**
	 * Uses DOT to render the given structure to a file with the given format
	 * option.
	 * 
	 * @param dotStr
	 *            A DOT-language input.
	 * @param fileBaseName
	 *            The prefix of the file to which the structure is rendered to.
	 * @param outputFormat
	 *            A DOT output format option - causes the an operating system
	 *            process for DOT to be invoked with a timeout value of
	 *            GRAPHVIZ_TIMEOUT seconds.
	 * @param logger
	 *            An optional logger to send messages to. Otherwise, messages are
	 *            printed to the standard output.
	 */
	public static boolean renderToFile(final String dotStr, final String fileBaseName, final String outputFormat,
			Logger logger) {
		File dotFile = new File(fileBaseName + ".dt");
		FileUtils.stringToFile(dotStr, dotFile.getAbsolutePath());
		boolean succeeded = false;
		if (outputFormat != null) {
			File outputFile = new File(fileBaseName + "." + outputFormat);
			succeeded = renderViaGraphviz("dot", dotFile.getAbsolutePath(), outputFile.getAbsolutePath(), outputFormat,
					logger);
			if (!succeeded) {
				if (logger != null) {
					logger.info("Retrying with neato");
				}
				succeeded = renderViaGraphviz("neato", dotFile.getName(), outputFile.getName(), outputFormat, logger);
			}
		}
		return succeeded;
	}

	public static boolean renderViaGraphviz(String graphvizUtility, String dotFileName, String outputFileName,
			String outputFormat, Logger logger) {
		ProcessBuilder pb = new ProcessBuilder(graphvizUtility, dotFileName, "-o" + outputFileName,
				"-T" + outputFormat);
		pb.redirectErrorStream(true);
		try {
			log("Invoking " + graphvizUtility + " on file: " + dotFileName + " ... ", logger);
			Process p = pb.start();
			boolean exited = p.waitFor(GRAPHVIZ_TIMEOUT, TimeUnit.SECONDS);
			if (exited) {
				log("done", logger);
			} else {
				log("timed out after " + GRAPHVIZ_TIMEOUT + " sec.", logger);
			}
			p.destroyForcibly();
			return exited;
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void renderToFile(MultiGraph<?, ?> graph, String description, File outputFile) throws IOException {
		initTemplates();
		
		String dotStr = GraphToDOT.render(graph, "anonymous_graph");
		String dotFilename = outputFile.getName() + ".dt";
		renderToFile(dotStr, dotFilename, "svg", logger);

		ST graphTemplate = templates.load("graphPage");
		graphTemplate.add("description", description);
		String graphTxt = graph.toString();
		graphTemplate.add("graphAsText", graphTxt);
		graphTemplate.add("dotstr", dotStr);
		String path = outputFile.getCanonicalPath();
		FileUtils.stringToFile(graphTemplate.render(), path);
	}

	protected static void log(String message, Logger logger) {
		if (logger != null) {
			logger.info(message);
		}
	}

	protected void initTemplates() {
		if (templates == null) {
			templates = new STGLoader(GraphizVisualizer.class, "Graphviz");
		}
	}
}