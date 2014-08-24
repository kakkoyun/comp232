package Graph;

import java.util.ArrayList;
/**
 * 
 * @author Kemal Akkoyun
 * General representation of a vertex.
 */
public class Vertex implements Comparable<Vertex>{
	
	/*
	 *  Some of these fields can be used instead of theirselves but 
	 *  I wanted to express each of them explicitly.
	 */
	
	private String vertex; 
	// Vertex id, name etc.
	
	private VertexColor color; 
	// Vertex color property for traversal algorithms.
	
	private Integer distance;
	// Distance property from given source node (Used in traversal algorithms).
	// This property is going to be used for several algorithms with different meaning.
	// If I had time I could be implement different fields but when I did that I also had to
	// implement different comparator classes but I am skipinng this for now.
	
	/*
	private Integer key; 
	// Current key for Vertex ( Used in Prim's algorithm.)
	*/
	//private Integer rank;
	// Rank of a vertex. (Used in disjoints set so for Kruskal's algorithm.)
	
	private Vertex predessor; 
	// Predecessor, parent or pi whatever you called.
	// Basically, it stores predecessor vertex of my path in graph.
	
	//Start and end time for depth-first search when the algorithm traversing.
	private int start = 0; // Start time.
	private int end = 0; // End time.
	
	ArrayList<Edge> neighbors = new ArrayList<Edge>(); 
	// List of edges that represent connections, neighbors stored as edges.
	// Helpful while printing out the adjency list.
	
	/**
	 * @param vertex Name of Vertex.
	 */
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

	// Vertices are comparable with the relation of their rank property.
	@Override
	public int compareTo(Vertex o) {
		return this.distance.compareTo(o.distance);
	}
	
	// String representation of my Vertex simply its label.
	@Override
	public String toString() {
		return vertex;
	}	
}
