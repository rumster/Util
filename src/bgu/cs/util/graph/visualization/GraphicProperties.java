package bgu.cs.util.graph.visualization;

import java.util.HashMap;
import java.util.Map;

/**
 * Associates graphic properties to a graph.
 * 
 * @author romanm
 *
 * @param <V>
 *            The type of graph nodes.
 * @param <ED>
 *            The type of data labeling graph edges.
 */
public class GraphicProperties<V, ED> {
	public String nodeShape = "ellipse";
	protected Map<V, NodeProperties> nodeProps = new HashMap<>();
	protected Map<ED, EdgeDataProperties> edgeProps = new HashMap<>();

	public NodeProperties nodeProps(V node) {
		NodeProperties result = nodeProps.get(node);
		if (result == null) {
			result = new NodeProperties(node.toString());
			nodeProps.put(node, result);
		}
		return result;
	}

	public EdgeDataProperties edgeDataProps(ED edgeData) {
		EdgeDataProperties result = edgeProps.get(edgeData);
		if (result == null) {
			result = new EdgeDataProperties(edgeData.toString());
			edgeProps.put(edgeData, result);
		}
		return result;
	}

	public void setProp(V node, NodeProperties props) {
		nodeProps.put(node, props);
	}

	public void setProp(ED ed, EdgeDataProperties props) {
		edgeProps.put(ed, props);
	}
}