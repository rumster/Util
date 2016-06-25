package bgu.cs.util.graph;

public interface Tree<Node, ED> extends Graph<Node, ED> {
	/**
	 * Should be 0 for the root and 1 for every non-root node.
	 */
	@Override
	public int inDegree(Node n);

	/**
	 * Tests whether the given node is the root of the tree.
	 */
	public boolean isRoot(Node n);

	/**
	 * Returns the number of children of the given node.
	 */
	@Override
	public int outDegree(Node n);
}