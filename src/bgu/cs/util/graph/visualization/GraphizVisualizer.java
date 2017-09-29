package bgu.cs.util.graph.visualization;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.stringtemplate.v4.ST;

import bgu.cs.util.FileUtils;
import bgu.cs.util.STGLoader;
import bgu.cs.util.graph.MultiGraph;

/**
 * Utilities to invoke Graphiz tools for rendering data as graphs.
 * 
 * @author romanm
 */
public class GraphizVisualizer extends HTMLVisualizer {
	protected STGLoader templates = new STGLoader(GraphizVisualizer.class);

	public static final long GRAPHVIZ_TIMEOUT = 1;

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
	 */
	public static boolean renderToFile(final String dotStr, final String fileBaseName, final String outputFormat) {
		File dotFile = new File(fileBaseName + ".dt");
		FileUtils.stringToFile(dotStr, dotFile.getName());
		boolean succeeded = false;
		if (outputFormat != null) {
			File outputFile = new File(fileBaseName + "." + outputFormat);
			succeeded = renderViaGraphviz("dot", dotFile.getName(), outputFile.getName(), outputFormat);
			if (!succeeded) {
				System.out.println("Retrying with neato");
				succeeded = renderViaGraphviz("neato", dotFile.getName(), outputFile.getName(), outputFormat);
			}
		}
		return succeeded;
	}

	public static boolean renderViaGraphviz(String graphvizUtility, String dotFileName, String outputFileName,
			String outputFormat) {
		ProcessBuilder pb = new ProcessBuilder(graphvizUtility, dotFileName, "-o" + outputFileName,
				"-T" + outputFormat);
		try {
			System.out.print("Invoking " + graphvizUtility + " on file: " + dotFileName + " ... ");
			Process p = pb.start();
			boolean exited = p.waitFor(GRAPHVIZ_TIMEOUT, TimeUnit.SECONDS);
			if (exited) {
				System.out.println("done");
			} else {
				System.out.println("timed out after " + GRAPHVIZ_TIMEOUT + " sec.");
			}
			p.destroyForcibly();
			return exited;
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void visualizeToFile(MultiGraph<?, ?> graph, String description, File outputFile) throws IOException {
		String dotStr = GraphToDOT.render(graph, "anonymous_graph");
		String dotFilename = outputFile.getName() + ".dt";
		renderToFile(dotStr, dotFilename, "svg");

		ST graphTemplate = templates.load("graph");
		graphTemplate.add("description", description);
		String graphTxt = graph.toString();
		graphTemplate.add("graphAsText", graphTxt);
		graphTemplate.add("dotstr", dotStr);
		String path = outputFile.getCanonicalPath();
		FileUtils.stringToFile(graphTemplate.render(), path);

	}
}