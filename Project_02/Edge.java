
public class Edge {
	
	// Because my Vertex class storing the Edges as neighbors, Edge has only has destination vertex
	//	-- and weight. At least for now. 
	
	private Vertex destination; 
	private int weight;
	
	// Creating an edge.
	public Edge(Vertex destination, int weight) {
	
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
	public void setDestination(Vertex destination) {
		this.destination = destination;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}

	// toString method in order to print out edges.
	@Override
	public String toString() {
		return "(" + destination.getVertex() + ", " + weight + ")";
	}
	
	
	
}
