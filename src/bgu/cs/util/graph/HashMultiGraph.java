package bgu.cs.util.graph;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * A hash table-based implementation of a {@link MultiGraph}.
 * 
 * @author romanm
 *
 * @param <Node>
 *            The type of the graph nodes.
 * @param <ED>
 *            The type of objects associated with graph edges.
 */
public class HashMultiGraph<Node, ED> implements MultiGraph<Node, ED> {
	protected Map<Node, Set<HashEdge>> succs = new LinkedHashMap<>();
	protected Map<Node, Set<HashEdge>> preds = new LinkedHashMap<>();

	@Override
	public boolean containsNode(Node n) {
		assert n != null : "Detected null node!";
		return succs.keySet().contains(n);
	}

	@Override
	public boolean containsEdge(Node src, Node dst) {
		assert containsNode(src) && containsNode(dst);
		Set<HashEdge> outgoing = succs.get(src);
		if (outgoing == null || outgoing.isEmpty())
			return false;
		for (HashEdge edge : outgoing) {
			if (edge.dst.equals(dst))
				return true;
		}
		return false;
	}

	@Override
	public Set<Node> getNodes() {
		return Collections.unmodifiableSet(succs.keySet());
	}

	@Override
	public boolean addNode(Node n) {
		if (succs.containsKey(n))
			return false;
		Set<HashEdge> outgoing = new HashSet<>();
		Set<HashEdge> incoming = new HashSet<>();
		succs.put(n, outgoing);
		preds.put(n, incoming);
		return true;
	}

	@Override
	public boolean addEdge(Node src, Node dst, ED label) {
		assert containsNode(src) && containsNode(dst);
		Set<HashEdge> outgoing = succs.get(src);
		Set<HashEdge> incoming = preds.get(dst);
		HashEdge edge = new HashEdge(src, dst, label);
		boolean change = outgoing.add(edge);
		incoming.add(edge);
		return change;
	}

	@Override
	public boolean removeNode(Node n) {
		if (!containsNode(n))
			return false;
		Set<HashEdge> outgoing = succs.get(n);
		for (HashEdge edge : outgoing) {
			Set<HashEdge> succPreds = preds.get(edge.dst);
			Iterator<HashEdge> edgeIter = succPreds.iterator();
			while (edgeIter.hasNext()) {
				HashEdge predEdge = edgeIter.next();
				if (predEdge.src.equals(n))
					edgeIter.remove();
			}
		}

		Set<HashEdge> incoming = preds.get(n);
		for (HashEdge edge : incoming) {
			Set<HashEdge> predSuccs = succs.get(edge.src);
			Iterator<HashEdge> edgeIter = predSuccs.iterator();
			while (edgeIter.hasNext()) {
				HashEdge predEdge = edgeIter.next();
				if (predEdge.dst.equals(n))
					edgeIter.remove();
			}
		}

		succs.remove(n);
		preds.remove(n);
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

		for (Node n : getNodes()) {
			Set<HashEdge> outEdges = succs.get(n);
			if (outEdges.isEmpty()) {
				result.append(" (" + n.toString() + ")\n");
			} else {
				for (HashEdge edge : outEdges) {
					result.append(" (" + n.toString() + ", " + edge.label + ", " + edge.dst + ")\n");
				}
			}

		}

		result.append("}");
		return result.toString();
	}

	@Override
	public Set<HashEdge> succEdges(Node n) {
		assert containsNode(n);
		return succs.get(n);
	}

	@Override
	public Set<HashEdge> predEdges(Node n) {
		assert containsNode(n);
		return preds.get(n);
	}

	@Override
	public boolean removeEdge(Edge<Node, ED> edge) {
		// TODO Auto-generated method stub
		return false;
	}

	public class HashEdge implements MultiGraph.Edge<Node, ED> {
		public final Node src;
		public final Node dst;
		public final ED label;

		public HashEdge(Node src, Node dst, ED label) {
			this.src = src;
			this.dst = dst;
			this.label = label;
		}

		@Override
		public Node getSrc() {
			return src;
		}

		@Override
		public Node getDst() {
			return dst;
		}

		@Override
		public ED getLabel() {
			return label;
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object o) {
			HashEdge other = (HashEdge) o;
			return this.src.equals(other.src) && this.dst.equals(other.dst) && this.label.equals(other.label);
		}

		@Override
		public int hashCode() {
			int result = src.hashCode();
			result = result * 31 + dst.hashCode();
			result = result * 31 + label.hashCode();
			return result;
		}

		@Override
		public String toString() {
			return "(" + src + "," + label + "," + dst + ")";
		}
	}
}