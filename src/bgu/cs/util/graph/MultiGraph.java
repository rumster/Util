package bgu.cs.util.graph;

import java.util.ArrayList;
import java.util.Collection;

/**
 * An interface for a mutable directed multigraph. That is, a graph that allows
 * multiple edges between any two pair of nodes.
 * 
 * @author romanm
 *
 * @param <Node>
 *            The type of the graph nodes.
 * @param <ED>
 *            The type of objects associated with graph edges.
 */
public interface MultiGraph<Node, ED> {
	/**
	 * A function object for processing a single edge.
	 * 
	 * @author romanm
	 *
	 * @param <Node>
	 *            The type of the graph nodes.
	 * @param <ED>
	 *            The type of objects associated with graph edges.
	 */
	public interface EdgeFun<Node, ED> {
		public void apply(Node src, Node dst, ED edgeLabel);
	}

	/**
	 * Checks whether a given node is a member of the graph.
	 * 
	 * @param n
	 *            A non-null node.
	 */
	public boolean containsNode(Node n);

	/**
	 * Checks whether an edge exists between the two given nodes, assumed to be in
	 * the graph.
	 * 
	 * @param src
	 *            The edge non-null source node, which is assumed to be in the
	 *            graph.
	 * @param dst
	 *            The edge non-null destination node, which is assumed to be in the
	 *            graph.
	 */
	public boolean containsEdge(Node src, Node dst);

	/**
	 * Returns the number of outgoing edges incident on the given node.
	 * 
	 * @param n
	 *            A non-null node assumed to be in the graph.
	 */
	public int inDegree(Node n);

	/**
	 * Returns the number of incoming edges incident on the given node.
	 * 
	 * @param n
	 *            A non-null node assumed to be in the graph.
	 */
	public int outDegree(Node n);

	/**
	 * Returns the number of edges incident on the given node.
	 * 
	 * @param n
	 *            A non-null node assumed to be in the graph.
	 */
	public int degree(Node n);

	/**
	 * Returns the set of graph nodes in a collection.
	 */
	public Collection<Node> getNodes();

	/**
	 * Returns the collection of edges outgoing from the given node.
	 */
	public Collection<? extends Edge<Node, ED>> succEdges(Node n);

	/**
	 * Returns the collection of edges incoming to the given node.
	 */
	public Collection<? extends Edge<Node, ED>> predEdges(Node n);

	/**
	 * Attempts to add a node to the graph.
	 * 
	 * @param n
	 *            A non-null node.
	 * @return true if the given node was not in the graph before the call.
	 */
	public boolean addNode(Node n);

	/**
	 * Adds a labeled edge between two nodes assumed to be in the graph.
	 * 
	 * @param src
	 *            A non-null node, assumed to be in the graph.
	 * @param dst
	 *            A non-null node, assumed to be in the graph.
	 */
	public boolean addEdge(Node src, Node dst, ED edgeLabel);

	/**
	 * Attempts to remove a node along with its incident edges.
	 * 
	 * @param n
	 *            A non-null node.
	 * @return true if the node was in the graph prior to the call.
	 */
	public boolean removeNode(Node n);

	/**
	 * Attempts to remove an edge between two given nodes.
	 * 
	 * @param edge
	 *            The edge to be removed.
	 * @return true if an edge between the two given nodes existed in the graph
	 *         prior to the call.
	 */
	public boolean removeEdge(Edge<Node, ED> edge);

	/**
	 * Removes all nodes and edges.
	 */
	public void clear();

	/**
	 * Merges the first node into the second (and removes the second node).
	 */
	public default void mergeInto(Node from, Node to) {
		if (from == to) {
			return;
		}
		Collection<Edge<Node, ED>> outgoingEdgesToAdd = new ArrayList<>(succEdges(from));
		for (Edge<Node, ED> edge : outgoingEdgesToAdd) {
			addEdge(to, edge.getDst(), edge.getLabel());
		}
		Collection<Edge<Node, ED>> incomingEdgesToAdd = new ArrayList<>(predEdges(from));
		for (Edge<Node, ED> edge : incomingEdgesToAdd) {
			addEdge(edge.getSrc(), to, edge.getLabel());
		}
		removeNode(from);
	}

	public static interface Edge<Node, ED> {
		public Node getSrc();

		public Node getDst();

		public ED getLabel();
	}
}