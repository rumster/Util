package bgu.cs.util.graph;

import java.io.OutputStream;

public interface GraphPrinter {
	public void print(Graph<?,?> g, OutputStream s);
}