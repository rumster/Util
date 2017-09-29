package bgu.cs.util.graph.visualization;

import java.io.File;
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
public class JSGraphItVisualizer extends HTMLVisualizer {
	protected STGLoader templates = new STGLoader(JSGraphItVisualizer.class);

	@Override
	public void visualizeToFile(MultiGraph<?, ?> graph, String description, File file) throws IOException {
		ST graphTemplate = templates.load("graph");
		graphTemplate.add("description", description);
		graphTemplate.add("graphAsText", graph.toString());
		
		
		//cfgST.add("dotstr", dotStr);
		String path = file.getCanonicalPath();
		FileUtils.stringToFile(graphTemplate.render(), path);
	}
}