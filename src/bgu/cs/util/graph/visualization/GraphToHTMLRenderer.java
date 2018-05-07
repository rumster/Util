package bgu.cs.util.graph.visualization;

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
	public abstract <V, ED> void renderToFile(MultiGraph<V, ED> graph, GraphicProperties<V, ED> gprops,
			String description, String filename, String path) throws IOException;
}