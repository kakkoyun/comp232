package Graph;
/**
 * 
 * @author Kemal Akkoyun. 
 * An implementation for a graph.
 * Stores source, destination and weight of the edge.
 */

public class Edge implements Comparable<Edge> {

	private Vertex source;
	// Points the source vertex of edge.
	private Vertex destination; 
	// Points the destination vertex of edge.
	private Integer weight;
	// Stores the weight information of edge.
	
	/**
	 * Creating an edge.
	 * @param source Source Vertex object.
	 * @param destination Destination Vertex object.
	 * @param wight Weight of the edge.
	 */
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
	// Edges are comparable through their weights.
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
