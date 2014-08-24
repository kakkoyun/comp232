package Utils;

import java.util.ArrayList;

import Graph.Vertex;

/**
 * 
 * @author Kemal Akkoyun
 * This a disjoint set data structure implementation.
 * It is used in Kruskal's algorithm.
 * It is NOT taking generic parameters because in order to make efficient sets,
 * It is using graphs special fields.
 * It is a tree like list structure.
 * Source: Cormen
 */

public class DisjointSet{

	// Creating a set for every vertex in the graph.
	public void makeSet(Vertex v){
		// At start each of vertices has their own sets.
		// Meanly, their predecessor is is theirselves.
		v.setPredessor(v);
		// Their distance is 0.
		v.setDistance(0);
	}
	// Takes two vertices, find their sets and basically link them.
	public void union(Vertex v, Vertex u) {
		link(findSet(v), findSet(u));
	}
	// LinkS two vertices, like union operation on an abstract set.
	public void link(Vertex v, Vertex u){
		if(v.getDistance() > u.getDistance()){
			u.setPredessor(v);
		} else {
			v.setPredessor(u);
			if(v.getDistance() == u.getDistance()){
				u.setDistance(u.getDistance() + 1);
			}
		}
	}
	// Finding related set.
	public Vertex findSet(Vertex v){
		if(!v.equals(v.getPredessor())){
			v.setPredessor(findSet(v.getPredessor()));
		}
		return v.getPredessor();
	}
}
