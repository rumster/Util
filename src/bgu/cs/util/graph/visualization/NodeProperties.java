package bgu.cs.util.graph.visualization;

/**
 * Associates graphic properties with graph nodes.
 * 
 * @author romanm
 */
public class NodeProperties {
	public String label = "";
	public String style = "";

	public NodeProperties() {
	}

	public NodeProperties(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "label=\"" + label + "\" " + " style=" + style;
	}
}
