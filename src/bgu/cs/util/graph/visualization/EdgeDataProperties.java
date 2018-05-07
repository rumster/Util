package bgu.cs.util.graph.visualization;

/**
 * Associates graphic properties with graph edges.
 * 
 * @author romanm
 */
public class EdgeDataProperties {
	public String label = "";
	public String style;

	public EdgeDataProperties() {
	}

	public EdgeDataProperties(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "label=\"" + label + "\" " + "style=" + style;
	}
}
