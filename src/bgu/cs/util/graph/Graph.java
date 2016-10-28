package bgu.cs.util.graph;

import java.util.Collection;

/**
 * An interface for a mutable directed graph.
 * 
 * @author romanm
 *
 * @param <Node>
 *            The type of the graph nodes.
 * @param <ED>
 *            The type of objects associated with graph edges.
 */
public interface Graph<Node, ED> {
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
	 * Checks whether an edge exists between the two given nodes, assumed to be
	 * in the graph.
	 * 
	 * @param src
	 *            The edge non-null source node, which is assumed to be in the
	 *            graph.
	 * @param dst
	 *            The edge non-null destination node, which is assumed to be in
	 *            the graph.
	 */
	public boolean containsEdge(Node src, Node dst);

	/**
	 * Returns the number of successor to the given node.
	 * 
	 * @param n
	 *            A non-null node assumed to be in the graph.
	 */
	public int inDegree(Node n);

	/**
	 * Returns the number of predecessors to the given node.
	 * 
	 * @param n
	 *            A non-null node assumed to be in the graph.
	 */
	public int outDegree(Node n);

	/**
	 * Returns the number of node incident to the given node.
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
	 * Returns the label object associated with the edge between the given
	 * source and destination node, if such an edge exists and null otherwise.
	 * 
	 * @param src
	 *            The potential edge non-null source node, which is assumed to
	 *            be in the graph.
	 * @param dst
	 *            The potential edge non-null destination node, which is assumed
	 *            to be in the graph.
	 * @return A label object or null.
	 */
	public ED getEdgeLabel(Node src, Node dst);

	/**
	 * Returns the set of successor nodes of a non-null node.
	 * 
	 * @param n
	 *            A non-null node.
	 * @return A set of nodes.
	 */
	public Collection<Node> succ(Node n);

	/**
	 * Returns the set of predecessor nodes of a non-null node.
	 * 
	 * @param n
	 *            A non-null node.
	 * @return A set of nodes.
	 */
	public Collection<Node> pred(Node n);

	/**
	 * Applies a given function to the outgoing edges of a given node.
	 * 
	 * @param n
	 *            A non-null node, assumed to be in the graph.
	 * @param fun
	 *            A non-null function object operating on elements of an edge.
	 */
	public void mapSucc(Node n, EdgeFun<Node, ED> fun);

	/**
	 * Applies a given function to the incoming edges of a given node.
	 * 
	 * @param n
	 *            A non-null node, assumed to be in the graph.
	 * @param fun
	 *            A non-null function object operating on elements of an edge.
	 */
	public void mapPred(Node n, EdgeFun<Node, ED> fun);

	/**
	 * Attempts to add a node to the graph.
	 * 
	 * @param n
	 *            A non-null node.
	 * @return true if the given node was not in the graph before the call.
	 */
	public boolean addNode(Node n);

	/**
	 * Adds a labeled edge between two nodes assumed to be in the graph. If an
	 * edge between the two nodes exists, the label is updated.
	 * 
	 * @param src
	 *            A non-null node, assumed to be in the graph.
	 * @param dst
	 *            A non-null node, assumed to be in the graph.
	 * @return true if the graph has changed due to the method call.
	 */
	public boolean addEdge(Node src, Node dst, ED edgeLabel);

	/**
	 * Assigns the given label to the edge between two given nodes, which is
	 * assumed to exist.
	 * 
	 * @param src
	 *            A non-null node.
	 * @param dst
	 *            A non-null node.
	 * @param edgeLabel
	 *            An object.
	 */
	public void setEdgeLabel(Node src, Node dst, ED edgeLabel);

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
	 * @param src
	 *            A non-null node.
	 * @param dst
	 *            A non-null node.
	 * @return true if an edge between the two given nodes existed in the graph
	 *         prior to the call.
	 */
	public boolean removeEdge(Node src, Node dst);

	/**
	 * Removes all nodes and edges.
	 */
	public void clear();
}