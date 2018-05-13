package bgu.cs.util.graph;

/**
 * Graph-related utilities.
 * 
 * @author romanm
 */
public class GraphUtils {
	/**
	 * Merges all of the nodes and edges of the right-hand side graph into left-hand
	 * side graph. If a common edge exists then its data is over-written with the
	 * one in the right-hand side graph.
	 * 
	 * @param lhs
	 *            A graph.
	 * @param rhs
	 *            A graph.
	 * @param <V>
	 *            The type graph vertices.
	 * @param <ED>
	 *            the type of edge labels.
	 */
	public static <V, ED> void merge(Graph<V, ED> lhs, Graph<V, ED> rhs) {
		for (V n : rhs.getNodes()) {
			lhs.addNode(n);
		}
		for (V n : rhs.getNodes()) {
			rhs.mapSucc(n, new Graph.EdgeFun<V, ED>() {
				@Override
				public void apply(V src, V dst, ED edgeLabel) {
					lhs.addEdge(src, dst, edgeLabel);
				}
			});
		}
	}
}