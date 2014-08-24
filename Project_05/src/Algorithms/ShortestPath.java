package Algorithms;

import java.util.AbstractSet;
import java.util.PriorityQueue;
import java.util.TreeSet;

import Graph.Edge;
import Graph.Graph;
import Graph.Vertex;
import Graph.VertexColor;

/**
 * 
 * @author Kemal Akkoyun
 * Single-Source Shortest Path algorithms.
 * In  this class you will see implementation of Bellman-Ford and Dijkstra's shortest path algorithm.
 */
public class ShortestPath {
	
	// Null vertex.
	Vertex NIL = new Vertex("");
	// Graph that algorithms will work on.
	private Graph g;
	// Start vertex of graph for algorithms.
	private Vertex s;

	/**
	 * Creating a shortest path algorithm object.
	 * Initializing proper properties for graph.
	 * In order to make algorithms work properly.
	 * @param g Graph that is going to be traversed.
	 * @param s Source(Start) Vertex for algorithm.
	 */
	public ShortestPath(Graph g, Vertex s){
		// While creating the object of shortestPath initialize it proper conditions.
		// Maybe it is unneccesary because in order to be sure algorithms will set initial conditions
		// --- before they start to work.
		initializeSingleSource(g, s);
		this.g = g;
		this.s = s;
	}
	/**
	 * Initializing proper properties for graph.
	 * In order to make algorithms work properly.
	 * @param g Graph that is going to be traversed.
	 * @param s Source(Start) Vertex for algorithm.
	 */
	public void initializeSingleSource(Graph g, Vertex s){
		// Do this for all vertices in graph.
		for(Vertex v : g.getVertexList()){
			// Set distance to infinity.
			v.setDistance(Integer.MAX_VALUE);
			// Set predecessor to null vertex.
			v.setPredessor(NIL);
		}
		s.setDistance(0);
	}
	
	/**
	 * Relax method relaxing the given edges.
	 * Meanly, updates the distance property of destination vertices,
	 * To make it shortest distance.
	 * @param e Edge that is going to be relaxed.
	 */
	public void relax(Edge e){
		// Getting the vertices of edge.
		Vertex v = e.getSource();
		Vertex u = e.getDestination();
		// Checking is there a shortest distance.
		if(v.getDistance() > (u.getDistance() + e.getWeight())){
			// if we have shorter path, update distance.
			v.setDistance(u.getDistance() + e.getWeight());
			// set predecessor of node according to where you came from.
			v.setPredessor(u);
		}
	}
	/**
	 * The Bellman-Ford algorithm that solves single-source shortest path algorithm in a general case.
	 * Meanly, it works with negative weighted edges.
	 * It is also detects if there exist a negative cycle.
	 * @return Whether there exist a negative cycle or not.
	 */
	public boolean bellmanFord(){
		// Set initial conditions of graph.
		initializeSingleSource(g, s);
		// Traverse graph (possible maximum number of edge) times.
		for(int i = 0; i < g.getVertexList().size() - 1; i++){
			// And relax all the edges in the graph.
			for(Edge e : g.getEdgeList()){
				relax(e);
			}
		}
		// Traverse one more time.
		for(Edge e : g.getEdgeList()){
			// Get vertices of edge.
			Vertex v = e.getSource();
			Vertex u = e.getDestination();
			// Check whether still I have a shorter path.
			if(v.getDistance() > (u.getDistance() + e.getWeight())){
				// if I have then graph has a negative cycle.
				return false;
			}
		}
		return true;
	}
	/**
	 * Dijkstra's shortest path algorithm.
	 * but this algorithm form cormen.
	 * This algorithm  has a different approach from general implementations of
	 * Dijkstra's algorithm.
	 */
	public void dijkstraCormen(){
		// Set initial conditions of graph.
		initializeSingleSource(g, s);
		// Sp will contain shortest path.
		AbstractSet<Vertex> sp = new TreeSet<Vertex>();
		// Priority queue stores vertices with lightest weights.
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
		// while q is not empty.
		while(!q.isEmpty()){
			// extract minimum priority queue.
			// vertices are comparable according to distance.
			Vertex u = q.poll();
			// Shortest path.
			sp.add(u);
			// Then relax all outgoing edges.
			for(Edge e : u.getNeighbors()){
				relax(e);
			}
		}
	}
	/**
	 * General approach Dijkstra algorithm, it is  really similar to BFS.
	 */
	public void dijkstra(){
		// Set colors of all vertices white.
		// Set all distances to infinity. *In this case only maxValue*
		// Set their predecessors to NIL.
		for(Vertex u : g.getVertexList()){
			u.setColor(VertexColor.WHITE);
			u.setDistance(Integer.MAX_VALUE);
			u.setPredessor(NIL);
		}
		// Set gray while I am traversing.
		s.setColor(VertexColor.GRAY);
		s.setDistance(0);
		s.setPredessor(NIL);
		// Add them to my min-priority queue.
		PriorityQueue<Vertex> Q = new PriorityQueue<Vertex>();
		Q.add(s);
		// Traverse in all its neighbors.
		while(!Q.isEmpty()){
			// Get minimum priority vertex.
			Vertex u = Q.poll();
			// for each outdegree edge,
			for(Edge e : u.getNeighbors()){
				// get destination vertex.
				Vertex v = e.getDestination();
				// if it is white.
				if(v.getColor() == VertexColor.WHITE){
					// Set it gray because I am traversing it.
						v.setColor(VertexColor.GRAY);
						// Relax it.
						relax(e);
						// Add it to queue.
						Q.add(v);
				}
			// Add it to path because I am done with it.
			u.setColor(VertexColor.BLACK);
			}
		}
	}
}