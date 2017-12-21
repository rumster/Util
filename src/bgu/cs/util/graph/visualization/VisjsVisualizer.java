package bgu.cs.util.graph.visualization;

import java.io.IOException;

import org.stringtemplate.v4.ST;

import bgu.cs.util.FileUtils;
import bgu.cs.util.STGLoader;
import bgu.cs.util.graph.MultiGraph;

/**
 * Visualizes graph using the <A href="http://visjs.org/">visjs</A> library.
 * 
 * @author romanm
 */
public class VisjsVisualizer extends GraphToHTMLRenderer {
	protected STGLoader templates = new STGLoader(VisjsVisualizer.class);

	@Override
	public void renderToFile(MultiGraph<?, ?> graph, String description, String filename, String path)
			throws IOException {
		ST graphTemplate = templates.load("graph");
		graphTemplate.add("description", description);

		String graphTxt = graph.toString();
		graphTxt = graphTxt.replace("<", "&lt;");
		graphTxt = graphTxt.replace(">", "&gt;");
		graphTemplate.add("graphAsText", graphTxt);
		String dotStr = GraphToDOT.render(graph, filename);
		dotStr = dotStr.replace("\n", ""); // VIS doesn't parse newline characters well.
		graphTemplate.add("dotstr", dotStr);
		FileUtils.stringToFile(graphTemplate.render(), path);
	}
}