package bgu.cs.util.graph.visualization;

import java.io.File;
import java.io.IOException;

import bgu.cs.util.graph.MultiGraph;

/**
 * Creates an HTML file visualizing a given graph.
 * 
 * @author romanm
 */
public abstract class GraphToHTMLRenderer {
	/**
	 * Generates a visualization file.
	 * 
	 * @param graph
	 *            A multigraph.
	 * @param description
	 *            A description of the graph.
	 */
	public abstract void renderToFile(MultiGraph<?, ?> graph, String description, File outputFile) throws IOException;
}