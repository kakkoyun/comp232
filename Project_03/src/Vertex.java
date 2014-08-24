import java.util.ArrayList;

public class Vertex implements Comparable<Vertex>{
	
	private String vertex; // Vertex id, name etc.
	
	private VertexColor color; // Vertex color property for traversal algorithms.
	private Integer distance; // Distance from given source node.
	private Vertex predessor; // Predessor, parent or pi whatever you called.
	// Basically, it stores predecessor vertex of my path in graph.
	
	//Start and end time for depth-first search.
	private int start = 0; // Start time.
	private int end = 0; // End time.
	
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
	public void setVertex(String vertex) {
		this.vertex = vertex;
	}
	public void setNeighbours(ArrayList<Edge> neighbors) {
		this.neighbors = neighbors;
	}

	public void setColor(VertexColor color) {
		this.color = color;
	}

	public VertexColor getColor() {
		return color;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setPredessor(Vertex predessor) {
		this.predessor = predessor;
	}

	public Vertex getPredessor() {
		return predessor;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setEnd(int end) {
		this.end = end;
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
		return this.vertex.compareTo(o.vertex);
	}
	
	// String representation of my Vertex simply its label.
	@Override
	public String toString() {
		return vertex;
	}

	
}
