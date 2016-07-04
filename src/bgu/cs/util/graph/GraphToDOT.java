package bgu.cs.util.graph;

import java.util.HashMap;

/**
 * Creates a representation of the graph in DOT format.
 * 
 * @author romanm
 */
public class GraphToDOT {
	public static <V, ED> String render(String name, Graph<V, ED> g) {
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
}