

import java.util.ArrayList;
import java.util.PriorityQueue;



public class MinimumSpanningTree {
	
	Vertex NIL = new Vertex("");
	
	// Collects the sets of spanning tree meanly group of vertices that connected.
	ArrayList<Set<Vertex>> sets = new ArrayList<Set<Vertex>>();
	
	public void Prim(Graph g, Vertex v){
		for(Vertex vx : g.getVertexList()){
			vx.setKey(Integer.MAX_VALUE);
			vx.setPredessor(NIL);
		}
		v.setKey(0);
		PriorityQueue<Vertex> Q = new PriorityQueue<Vertex>(g.getVertexList());
		while(!Q.isEmpty()){
			Vertex u = Q.poll();
			for(Edge e : u.neighbors){
				if(Q.contains(e.getDestination()) && e.getWeight() < e.getDestination().getKey()){
					e.getDestination().setPredessor(u);
					e.getDestination().setKey(e.getWeight());
				}
			}
		}
		//mapPrint(g);
	}
	
	public void Kruskal(Graph g){
		
		// Collects the edges of minimum spanning tree.
		ArrayList<Edge> mst = new ArrayList<Edge>();
		
		for(Vertex v : g.getVertexList()){
			sets.add(new Set<Vertex>(v));
		}
		
		for(Edge e : g.getEdgeList()){
			if(!findSet(e.getSource()).equals(findSet(e.getDestination()))){
				mst.add(e);
				Set<Vertex> unionSet = union(findSet(e.getSource()), findSet(e.getDestination()));
				sets.remove(findSet(e.getSource()));
				sets.remove(findSet(e.getDestination()));
				sets.add(unionSet);
			}
		}
		//System.out.println(mst);
	}
	
	private Set<Vertex> findSet(Vertex source){
		Set<Vertex> set = new Set<Vertex>();
		for(int i=0;i<sets.size();i++){
			if(sets.get(i).set.contains(source)){
				set = sets.get(i);
			}
		}
		return set;
	}
	
	public Set<Vertex> union(Set<Vertex> u, Set<Vertex> v){
		Set<Vertex> union = u; 
		for(Vertex e : v.set){
			if(!union.set.contains(e)){
				union.insert(e);
			}
		}
		return union;
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
}
