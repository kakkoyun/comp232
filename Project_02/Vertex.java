import java.util.ArrayList;

public class Vertex {
	
	private String vertex; // Vertex id, name etc.
	ArrayList<Edge> neighbors = new ArrayList<Edge>(); // List of edges that represent connections.
	
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
	public void setVertex(String vertex) {
		this.vertex = vertex;
	}
	public void setNeighbours(ArrayList<Edge> neighbors) {
		this.neighbors = neighbors;
	}

	// Printing out neighbors of my Vertex.
	public void printNeighbor() {
		
		String printOut = vertex + ":";
		for(Edge e : neighbors){
			printOut += " " + e.toString();
		}
		System.out.println(printOut);
		
	}

	
}
