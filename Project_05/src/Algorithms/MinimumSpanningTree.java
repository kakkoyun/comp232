package Algorithms;

import java.util.AbstractSet;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.TreeSet;

import Utils.DisjointSet;

import Graph.Edge;
import Graph.Graph;
import Graph.Vertex;

/**
 * 
 * @author Kemal Akkoyun
 * Minimum Spanning tree algorithms implementations.
 * Prim's and Kruskal's algorithm has implemented in this class.
 * Source: Cormen
 */

public class MinimumSpanningTree {

	Vertex NIL = new Vertex("");

	/**
	 * Prim's algorithm.
	 * @param g Takes a proper graph.
	 * @param v Takes a source vertex for initialing algorithm, meanly a start vertex.
	 */
	public void Prim(Graph g, Vertex v){
		// First traverse all vertices and set their key to infinite.
		// In this case infinite refers to Integer.MAX_VALUE.
		// Set their predecessor's to null vertex.
		for(Vertex vx : g.getVertexList()){
			vx.setDistance(Integer.MAX_VALUE);
			vx.setPredessor(NIL);
		}
		// Started to traverse so first vertex is the root of spanning tree.
		v.setDistance(0);
		// I used priority queue from java API. Because it is an implementation of BinaryHeap.
		// But We have different opportunities such like fibonacci heap.
		// Cormen claims that fibonacci heap has better amortized time for sparse graphs.
		// You can check fundamentals of that claims from book. :)
		PriorityQueue<Vertex> Q = new PriorityQueue<Vertex>(g.getVertexList());
		//FibonacciHeap<Vertex> Q = new FibonacciHeap<Vertex>(g.getVertexList());
		while(!Q.isEmpty()){
			// Take minimum priority vertex from queue. 
			Vertex u = Q.poll();
			for(Edge e : u.getNeighbors()){
				// Get all neighbors f source vertex.
				if(Q.contains(e.getDestination()) && e.getWeight() < e.getDestination().getDistance()){
					// Set where you  came from, in the path.
					e.getDestination().setPredessor(u);
					// Update the Distance of vertex you gone.
					e.getDestination().setDistance(e.getWeight());
				}
			}
		}
	}
	/**
	 * Kruskal's algorithm.
	 * @param g Takes a proper graph to traverse.
	 * @return A set of edges that represents spanning tree.
	 */
	public TreeSet<Edge> Kruskal(Graph g){
		// Collects the edges of minimum spanning tree.
		TreeSet<Edge>  mst = new TreeSet<Edge>();
		// An auxilliary data structure to hold partial trees.
		DisjointSet set = new DisjointSet();
		// At first make sets for all vertices in graph.
		for(Vertex v : g.getVertexList()){
			set.makeSet(v);
		}
		// Traverse all edges in the graph.
		for(Edge e : g.getEdgeList()){
			// Get their source and destination vertices. 
			Vertex u = e.getSource();
			Vertex v = e.getDestination();
			// Check whether they are in the same set of not.
			if(!set.findSet(u).equals(set.findSet(v))){
				// If they are not add this edge to the minimum spanning tree path.
				mst.add(e);
				// Unite this vertices, because the are connected now in tree.
				set.union(set.findSet(u), set.findSet(v));
			}
		}
		return mst;
	}
	// Printing paths on my graph.
	public void printPath(Vertex destination){
		String path = "";
		if(destination.getPredessor() == NIL){
			// I do not need to print a node if has no path.
		} else {
			path += destination.getPredessor()+ " " + destination;
			System.out.println(path);
		}
	}
	
	// Print out all paths for all vertices.
	public void mapPrint(Graph g){
		for(Vertex v : g.getVertexList()){
			printPath(v);
		}
	}
}
