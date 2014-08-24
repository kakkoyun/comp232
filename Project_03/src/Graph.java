import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
	
	private int VertexCount;
	// Number of Vertex in graph.
	
	private ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
	// Adjacency List represent as an ArrayList.
	
	private int[][] adjacencyMatrix = new int[VertexCount][VertexCount];
	// Adjacency Matrix that show connections.
	
	Vertex NIL = new Vertex("");
	// Simply a null vertex. Needed for traversals.
	
	private int time = 0;
	// A time property for depth-first search algorithm.
	
	private LinkedList<Vertex> topologicalOrder = new LinkedList<Vertex>();
	// A linked list to store topological order.
	
	// Creating a proper graph with an adjacency matrix.
	public Graph(int count, String[] vList, int[][] distanceMatrix) {
	
			addVerticesFromArray(vList);
			this.VertexCount = count;
			this.adjacencyMatrix = distanceMatrix;
			createFromMatrix();
		
	}
	
	//Creating a graph with a proper adjacency list.
	public Graph(ArrayList<Vertex> vertices){
		this.vertexList = vertices;
		this.VertexCount = vertices.size();
	}

	// Creating a proper vertex list for my graph with given String array.
	public void addVerticesFromArray(String[] vList) {
		for(String s : vList){
			vertexList.add(new Vertex(s));
		}
	}

	// Setters and getters.
	public void setVertexList(ArrayList<Vertex> vertexList) {
		this.vertexList = vertexList;
	}

	public ArrayList<Vertex> getVertexList() {
		return vertexList;
	}

	public void setAdjacencyMatrix(int[][] adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix;
	}

	public int[][] getAdjacencyMatrix() {
		return adjacencyMatrix;
	}

	// Creating a graph data structure with given distance matrix.
	public void createFromMatrix(){

		for(int i = 0; i < VertexCount; i++){
			for (int j = 0; j < VertexCount; j++){
				if(adjacencyMatrix[i][j] != 0){
					vertexList.get(i).neighbors.add(new Edge(vertexList.get(j), adjacencyMatrix[i][j]));	
				} else {
					continue;
				}
			}
		}
	}
	
	// Creating a proper graph representation from adjacency list.
	public void createFromList(){
		
		int[][] adjMatrix = new int[VertexCount][VertexCount];
		
		for(int i = 0; i < VertexCount; i++ ){
			for(Edge e : vertexList.get(i).neighbors){
				adjMatrix[i][vertexList.indexOf(e.getDestination())] = e.getWeight();
			}
		}
	}
	
	// Breath-first Traversal Algorithm implementation from Cormen.
	public void breathFirstSearch(Vertex s){
		// Set colors of all vertices white.
		// Set all distances to infinity. *In this case only maxValue*
		// Set their predecessors to NIL.
		for(Vertex u : this.getVertexList()){
			u.setColor(VertexColor.WHITE);
			u.setDistance(Integer.MAX_VALUE);
			u.setPredessor(NIL);
		}
		// Set black where I have searched.
		s.setColor(VertexColor.BLACK);
		s.setDistance(0);
		
		Queue<Vertex> Q = new Queue<Vertex>();
		// Add them to my queue.
		Q.enqueue(s);
		// Traverse in all its neighbors.
		while(!Q.isEmpty()){
			Vertex u = Q.dequeue();
			for(Edge e : u.neighbors){
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
	public void depthFirstSearch(){
		// Set colors of all vertices white.
		// Set their predecessors to NIL.
		for(Vertex u : this.getVertexList()){
			// If this graph searched before I have to reset all properties.
			u.setColor(VertexColor.WHITE);
			u.setPredessor(NIL);
			u.setStart(0);
			u.setEnd(0);
		}
		time = 0; // Set time to 0 always before start.
		for(Vertex u : this.getVertexList()){
			if(u.getColor() == VertexColor.WHITE){
				// Traverse through depth for every white vertices since white means not visited.
				dFSVisit(u);
			}
		}
	}
	
	// A helper function that search through deepness.
	public void dFSVisit(Vertex u){
		time += 1;
		u.setStart(time); // In this algorithm we are storing time in distance field. 
		u.setColor(VertexColor.GRAY); // Mark it gray when I am searching in it depth.
		for(Edge v : u.getNeighbors()){
			if(v.getDestination().getColor() == VertexColor.WHITE){
				v.getDestination().setPredessor(u);
				dFSVisit(v.getDestination());
			}
		}
		u.setColor(VertexColor.BLACK); // I have traverse in all its neighbors, so I am done with it.
		time += 1;
		u.setEnd(time); // Holding time properties inside vertices.
		topologicalOrder.add(u); // insert it to topological order.
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
	public void mapPrint(){
		for(Vertex v : this.getVertexList()){
			printPath(v);
		}
	}

	// Printing out adjacency matrix.
	public void printAdjancecyMatrix(){
		String printOut = "";
		for(int i = 0; i < VertexCount; i++){
			String line = "";
			for(int j = 0; j < VertexCount; j++){
				line += adjacencyMatrix[i][j] + " ";
			}
			printOut += line;
		}
		System.out.println(printOut);
	}
	
	// Printing out adjacency list.
	public void printAdjancecyList(){
		for(Vertex v : vertexList){
			v.printNeighbor();
		}
	}
	
	// Print topological order by time properties with decreasing order.
	public void printTopologicalOrder(){
		String order="";
		for(Vertex v : topologicalOrder){
			order= v + " " + v.getStart() + "/" + v.getEnd()+ "\n" + order;
			}
		System.out.println(order);
	}
}
