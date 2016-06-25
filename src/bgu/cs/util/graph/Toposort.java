package bgu.cs.util.graph;

import java.util.Collection;
import java.util.Collections;

/**
 * Computes a topological sort of a graph. Graphs with cycles are handled by
 * listing all nodes within the same strongly-connected component in an
 * arbitrary order.
 * 
 * @author romanm
 *
 */
public class Toposort {
	public <Node> Collection<Node> compute(Graph<Node, ?> g) {
		assert g != null;
		if (g.getNodes().isEmpty())
			return Collections.emptyList();
		throw new Error("Not implemented yet!");

		// LinkedHashSet<Node> order = new
		// LinkedHashSet<Node>(g.getNodes().size());
		//
		// HashSet<Node> processed = new HashSet<>(g.getNodes().size() / 4);
		// for (Node n : g.getNodes()) {
		// processed.add(n);
		//
		// }
		//
		// return null;
	}
}