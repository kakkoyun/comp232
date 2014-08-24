

public class Edge implements Comparable<Edge> {

	private Vertex source;
	private Vertex destination; 
	private Integer weight;
	
	// Creating an edge.
	public Edge(Vertex source, Vertex destination, int weight) {
		this.setSource(source);
		this.destination = destination;
		this.weight = weight;
	}
	//Setters and getters.
	public Vertex getDestination() {
		return destination;
	}
	public int getWeight() {
		return weight;
	}
	public Vertex getSource() {
		return source;
	}
	public void setDestination(Vertex destination) {
		this.destination = destination;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setSource(Vertex source) {
		this.source = source;
	}
	// Edges are comparable through their weight.
	@Override
	public int compareTo(Edge otherEdge) {
		return weight.compareTo(otherEdge.weight);
	}
	// toString method in order to print out edges.
	@Override
	public String toString() {
		return source +" -"+"("+ weight +")"+"-> "+ destination; 
	}
}
