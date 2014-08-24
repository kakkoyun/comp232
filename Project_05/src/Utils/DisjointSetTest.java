package Utils;

import java.util.ArrayList;

import Graph.Vertex;

public class DisjointSetTest {
	
	// Testing disjoint sets.
	public static void main(String[] args) {
		DisjointSet set = new DisjointSet();
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		Vertex a = new Vertex("a");
		vertices.add(a);
		Vertex b = new Vertex("b");
		vertices.add(b);
		Vertex c = new Vertex("c");
		vertices.add(c);
		Vertex d = new Vertex("d");
		vertices.add(d);
		Vertex e = new Vertex("e");
		vertices.add(e);
		for(Vertex v : vertices){
			set.makeSet(v);
		}
		System.out.println(set.findSet(a));
		set.union(a, b);
		System.out.println(set.findSet(a).getPredessor());
		System.out.println(set.findSet(b).getPredessor());
		System.out.println(set.findSet(c).getPredessor());
		set.union(c, b);
		set.union(a, c);
		set.union(a, d);
		//set.union(b, e);
		for(Vertex v : vertices){
			System.out.println(set.findSet(v).getPredessor());
		}
		System.out.println(set.findSet(a).equals(set.findSet(b)));
		System.out.println(set.findSet(a).equals(set.findSet(e)));
	}
}
