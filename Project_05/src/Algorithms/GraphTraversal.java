package Algorithms;

import Graph.Edge;
import Utils.Queue;
import Graph.Graph;
import Graph.Vertex;
import Graph.VertexColor;

/**
 * 
 * @author Kemal Akkoyun
 * Implementations of Graph traversal algorithms.
 * Breath-first Search and Depth-first Search.
 *
 */
public class GraphTraversal {
	
	Vertex NIL = new Vertex("");
	Vertex source = NIL;;
	
	// Default constructor.
	public GraphTraversal(){
		
	}
	/**
	 * Constructor that stores start vertex.
	 * @param source
	 */
	public GraphTraversal(Vertex source) {
		this.source = source;
	}

	/**
	 * Breath-first Traversal Algorithm implementation from Cormen.
	 * @param g Graph that you want to traverse.
	 * @param s Source Vertex of graph, where you want to start.
	 */
	public void breathFirstSearch(Graph g, Vertex s){
		// Set colors of all vertices white.
		// Set all distances to infinity. *In this case only maxValue*
		// Set their predecessors to NIL.
		for(Vertex u : g.getVertexList()){
			u.setColor(VertexColor.WHITE);
			u.setDistance(Integer.MAX_VALUE);
			u.setPredessor(NIL);
		}
		// Set black where I have searched.
		s.setColor(VertexColor.BLACK);
		s.setDistance(0);
		// Add them to my queue.
		Queue<Vertex> Q = new Queue<Vertex>();
		Q.enqueue(s);
		// Traverse in all its neighbors.
		while(!Q.isEmpty()){
			Vertex u = Q.dequeue();
			for(Edge e : u.getNeighbors()){
				Vertex v = e.getDestination();
				if(v.getColor() == VertexColor.WHITE){
						v.setColor(VertexColor.BLACK);
						v.setDistance(u.getDistance() + 1);
						v.setPredessor(u);
						Q.enqueue(v);
				}
			}
		}
	}
	
	// Depth-first traversal implementation of algorithm in Cormen.
	public void depthFirstSearch(Graph g){
		// Set colors of all vertices white.
		// Set their predecessors to NIL.
		for(Vertex u : g.getVertexList()){
			// If this graph searched before I have to reset all properties.
			u.setColor(VertexColor.WHITE);
			u.setPredessor(NIL);
			u.setStart(0);
			u.setEnd(0);
		}
		g.setTime(0);
		// Set time to 0 always before start.
		for(Vertex u : g.getVertexList()){
			if(u.getColor() == VertexColor.WHITE){
				// Traverse through depth for every white vertices since white means not visited.
				dFSVisit(g,u);
			}
		}
	}
	
	// A helper function that search through deepness.
	public void dFSVisit(Graph g, Vertex u){
		g.setTime(g.getTime()+1);
		u.setStart(g.getTime()); 
		// In this algorithm we are storing time in distance field. 
		u.setColor(VertexColor.GRAY); 
		// Mark it gray when I am searching in it depth.
		for(Edge v : u.getNeighbors()){
			if(v.getDestination().getColor() == VertexColor.WHITE){
				v.getDestination().setPredessor(u);
				dFSVisit(g, v.getDestination());
			}
		}
		u.setColor(VertexColor.BLACK); // I have traverse in all its neighbors, so I am done with it.
		g.setTime(g.getTime()+1);
		u.setEnd(g.getTime()); 
		// Holding time properties inside vertices.
		g.getTopologicalOrder().add(u); 
		// insert it to topological order.
	}
	// Printing paths on my graph.
	public void printPath(Vertex destination){
		String path = "";
		Vertex lastPoint = destination;
		if(destination.getPredessor() == NIL){
		// I do not need to print a node if has no path.
		} else {
		while(lastPoint != NIL){
			path = path + " -> "  + lastPoint;
			lastPoint = lastPoint.getPredessor();
		}
		System.out.println(path);
		}
	}
	// Print out all paths for all vertices.
	public void mapPrint(Graph g){
		for(Vertex v : g.getVertexList()){
			printPath(v);
		}
	}
	
	// Print topological order by time properties with decreasing order.
	public void printTopologicalOrder(Graph g){
		String order="";
		for(Vertex v : g.getTopologicalOrder()){
			order= v + " " + v.getStart() + "/" + v.getEnd()+ "\n" + order;
			}
		System.out.println(order);
	}
}
