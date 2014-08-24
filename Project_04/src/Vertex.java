
import java.util.ArrayList;

public class Vertex implements Comparable<Vertex>{
	
	private String vertex; // Vertex id, name etc.
	private Integer key; // Current key for Vertex, according MST algorithms.
	private Vertex predessor;// Basically, it stores predecessor vertex of my path in graph.

	ArrayList<Edge> neighbors = new ArrayList<Edge>(); 
	// List of edges that represent connections.
	
	public Vertex(String vertex) {
		this.vertex = vertex;
	}
	// Setter and Getter methods.
	public String getVertex() {
		return vertex;
	}
	
	public ArrayList<Edge> getNeighbors() {
		return neighbors;
	}

	public Vertex getPredessor() {
		return predessor;
	}

	public Integer getKey() {
		return key;
	}
	public void setVertex(String vertex) {
		this.vertex = vertex;
	}
	public void setNeighbours(ArrayList<Edge> neighbors) {
		this.neighbors = neighbors;
	}
	
	public void setPredessor(Vertex predessor) {
		this.predessor = predessor;
	}
	public void setKey(Integer key) {
		this.key = key;
	}
	// Printing out neighbors of my Vertex.
	public void printNeighbor() {
		
		String printOut = vertex + ":";
		for(Edge e : neighbors){
			printOut += " " + e.toString();
		}
		System.out.println(printOut);
		
	}
	// If I need to compare my vertices by their label.
	@Override
	public int compareTo(Vertex o) {
		return this.key.compareTo(o.key);
	}
	// String representation of my Vertex simply its label.
	@Override
	public String toString() {
		return vertex;
	}	
}
