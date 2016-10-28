package bgu.cs.util.graph;

import java.util.*;

/**
 * A hash table-based implementation of a {@link Graph}.
 * 
 * @author romanm
 *
 * @param <Node>
 *            The type of the graph nodes.
 * @param <ED>
 *            The type of objects associated with graph edges.
 */
public class HashGraph<Node, ED> implements Graph<Node, ED> {
	protected Map<Node, Map<Node, ED>> succs = new LinkedHashMap<>();
	protected Map<Node, Map<Node, ED>> preds = new LinkedHashMap<>();

	@Override
	public boolean containsNode(Node n) {
		assert n != null : "Detected null node!";
		return succs.keySet().contains(n);
	}

	@Override
	public boolean containsEdge(Node src, Node dst) {
		assert containsNode(src) && containsNode(dst);
		Map<Node, ED> outgoing = succs.get(src);
		return outgoing != null && outgoing.containsKey(dst);
	}

	public ED getEdgeLabel(Node src, Node dst) {
		assert containsEdge(src, dst);
		Map<Node, ED> outgoing = succs.get(src);
		return outgoing.get(dst);
	}

	@Override
	public Set<Node> getNodes() {
		return Collections.unmodifiableSet(succs.keySet());
	}

	@Override
	public Collection<Node> succ(Node n) {
		assert containsNode(n);
		Map<Node, ED> outgoing = succs.get(n);
		return outgoing.keySet();
	}

	@Override
	public Collection<Node> pred(Node n) {
		assert containsNode(n);
		Map<Node, ED> incoming = preds.get(n);
		return incoming.keySet();
	}

	@Override
	public void mapSucc(Node n, Graph.EdgeFun<Node, ED> fun) {
		assert containsNode(n) && fun != null;
		for (Map.Entry<Node, ED> halfEdge : succs.get(n).entrySet()) {
			fun.apply(n, halfEdge.getKey(), halfEdge.getValue());
		}
	}

	@Override
	public void mapPred(Node n, Graph.EdgeFun<Node, ED> fun) {
		assert containsNode(n) && fun != null;
		for (Map.Entry<Node, ED> halfEdge : preds.get(n).entrySet()) {
			fun.apply(halfEdge.getKey(), n, halfEdge.getValue());
		}
	}

	@Override
	public boolean addNode(Node n) {
		if (succs.containsKey(n))
			return false;
		Map<Node, ED> outgoing = new LinkedHashMap<>();
		Map<Node, ED> incoming = new LinkedHashMap<>();
		succs.put(n, outgoing);
		preds.put(n, incoming);
		return true;
	}

	@Override
	public boolean addEdge(Node src, Node dst, ED edgeLabel) {
		assert containsNode(src) && containsNode(dst);
		Map<Node, ED> outgoing = succs.get(src);
		Map<Node, ED> incoming = preds.get(dst);
		boolean change = outgoing.put(dst, edgeLabel) != null;
		incoming.put(src, edgeLabel);
		return change;
	}

	@Override
	public void setEdgeLabel(Node src, Node dst, ED edgeLabel) {
		assert containsEdge(src, dst);
		Map<Node, ED> outgoing = succs.get(src);
		Map<Node, ED> incoming = preds.get(dst);
		outgoing.put(src, edgeLabel);
		incoming.put(dst, edgeLabel);
	}

	@Override
	public boolean removeNode(Node n) {
		if (!containsNode(n))
			return false;
		Map<Node, ED> outgoing = succs.get(n);
		for (Node succ : outgoing.keySet()) {
			Map<Node, ED> succPreds = preds.get(succ);
			succPreds.remove(n);
		}
		Map<Node, ED> incoming = preds.get(n);
		for (Node pred : incoming.keySet()) {
			Map<Node, ED> predSuccs = succs.get(pred);
			predSuccs.remove(n);
		}
		succs.remove(n);
		preds.remove(n);
		return true;
	}

	@Override
	public boolean removeEdge(Node src, Node dst) {
		if (!containsEdge(src, dst))
			return false;
		Map<Node, ED> outgoing = succs.get(src);
		outgoing.remove(dst);
		Map<Node, ED> incoming = preds.get(dst);
		incoming.remove(src);
		return true;
	}

	@Override
	public void clear() {
		succs.clear();
		preds.clear();
	}

	@Override
	public int inDegree(Node n) {
		assert containsNode(n);
		return preds.get(n).size();
	}

	@Override
	public int outDegree(Node n) {
		assert containsNode(n);
		return succs.get(n).size();
	}

	@Override
	public int degree(Node n) {
		assert containsNode(n);
		return succs.get(n).size() + preds.get(n).size();
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("{\n");
		for (Map.Entry<Node, Map<Node, ED>> nodeEntry : succs.entrySet()) {
			Node src = nodeEntry.getKey();
			Map<Node, ED> outEdges = nodeEntry.getValue();
			if (outEdges.isEmpty()) {
				result.append(" (" + src.toString() + ")\n");
			} else {
				for (Map.Entry<Node, ED> edgeEntry : outEdges.entrySet()) {
					result.append(
							" (" + src.toString() + ", " + edgeEntry.getValue() + ", " + edgeEntry.getKey() + ")\n");
				}
			}
		}
		result.append("}");
		return result.toString();
	}
}