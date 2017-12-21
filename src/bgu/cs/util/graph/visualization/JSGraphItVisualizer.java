package bgu.cs.util.graph.visualization;

import java.io.IOException;

import org.stringtemplate.v4.ST;

import bgu.cs.util.FileUtils;
import bgu.cs.util.STGLoader;
import bgu.cs.util.graph.MultiGraph;

/**
 * Visualizes graph using the <A href="http://js-graph-it.sourceforge.net/">js-graph.it</A> library.
 * 
 * @author romanm
 */
public class JSGraphItVisualizer extends GraphToHTMLRenderer {
	protected STGLoader templates = new STGLoader(JSGraphItVisualizer.class);

	@Override
	public void renderToFile(MultiGraph<?, ?> graph, String description, String filename, String path) throws IOException {
		ST graphTemplate = templates.load("graph");
		graphTemplate.add("description", description);
		graphTemplate.add("graphAsText", graph.toString());
		
		
		//cfgST.add("dotstr", dotStr);
		FileUtils.stringToFile(graphTemplate.render(), path);
	}
}