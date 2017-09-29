package bgu.cs.util.graph.visualization;

import java.util.HashMap;

import bgu.cs.util.graph.Graph;
import bgu.cs.util.graph.MultiGraph;

/**
 * Converts graph to a <A hre="http://www.graphviz.org/doc/info/lang.html">DOT
 * language</A> string.
 * 
 * @author romanm
 */
public class GraphToDOT {
	public static <V, ED> String render(Graph<V, ED> g, String name) {
		StringBuilder result = new StringBuilder();
		name = name.replace('-', '_');
		result.append(name + " {\n");

		HashMap<V, String> nodeToName = new HashMap<>();
		int nodeCounter = 0;
		for (V v : g.getNodes()) {
			String nodeName = "N" + nodeCounter++;
			nodeToName.put(v, nodeName);
			result.append(nodeName + " [label=\"" + v.toString() + "\"];\n");
		}
		for (V src : g.getNodes()) {
			final String srcName = nodeToName.get(src);
			g.mapSucc(src, new Graph.EdgeFun<V, ED>() {
				@Override
				public void apply(V src, V dst, ED edgeLabel) {
					final String dstName = nodeToName.get(dst);
					result.append(srcName + "->" + dstName + "[label=\"" + edgeLabel.toString() + "\"];\n");
				}
			});
		}
		result.append("}");

		return result.toString();
	}

	public static <V, ED> String render(MultiGraph<V, ED> g, String name) {
		StringBuilder result = new StringBuilder();
		name = name.replace('-', '_');
		result.append(name + " {\n");

		HashMap<V, String> nodeToName = new HashMap<>();
		int nodeCounter = 0;
		for (V v : g.getNodes()) {
			String nodeName = "N" + nodeCounter++;
			nodeToName.put(v, nodeName);
			result.append(nodeName + " [label=\"" + v.toString() + "\"];\n");
		}
		for (V src : g.getNodes()) {
			final String srcName = nodeToName.get(src);
			for (MultiGraph.Edge<V, ED> edge : g.succEdges(src)) {
				final String dstName = nodeToName.get(edge.getDst());
				result.append(srcName + "->" + dstName + "[label=\"" + edge.getLabel().toString() + "\"];\n");
			}
		}
		result.append("}");

		return result.toString();
	}
}