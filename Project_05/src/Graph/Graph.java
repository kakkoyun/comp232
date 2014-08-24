package Graph;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * 
 * @author Kemal Akkoyun
 *	General graph representation in order to be usable for several elementary graph algorithms.
 *
 */

public class Graph {
	
	private int VertexCount;
	// Number of Vertex in graph.
	
	private ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
	// Adjacency List represent as an ArrayList.
	
	private int[][] adjacencyMatrix = new int[VertexCount][VertexCount];
	// Adjacency Matrix that show connections.
	
	private TreeSet<Edge> edgeList = new TreeSet<Edge>();
	// List of Edges represent as an  TreeSet.
	
	Vertex NIL = new Vertex("");
	// Simply a null vertex. Needed for traversals.
	
	private int time = 0;
	// A time property for depth-first search algorithm.
	
	private LinkedList<Vertex> topologicalOrder = new LinkedList<Vertex>();
	// A linked list to store topological order.
	
	private boolean undirected = false;
	// True if it is undirected.
	
	/**
	 * Creating a proper graph with an adjacency matrix.
	 * @param count Number of Vertices in the graph.
	 * @param vList List off vertices as strings, meanly their labels.
	 * @param distanceMatrix Double dimensional array as representation of adjacency matrix.
	 */
	public Graph(int count, String[] vList, int[][] distanceMatrix) {
	
			addVerticesFromArray(vList);
			this.VertexCount = count;
			this.adjacencyMatrix = distanceMatrix;
			createFromMatrix();
			createEdgeList();
		
	}
	/**
	 * Creating a proper graph with an adjacency matrix.
	 * @param count Number of Vertices in the graph.
	 * @param vList List off vertices as strings, meanly their labels.
	 * @param distanceMatrix Double dimensional array as representation of adJacency matrix.
	 * @param undirected Whether is undirected or not.
	 */
	public Graph(int count, String[] vList, int[][] distanceMatrix, boolean undirected) {
		
		addVerticesFromArray(vList);
		this.VertexCount = count;
		this.adjacencyMatrix = distanceMatrix;
		this.undirected = undirected;
		createFromMatrix();
		createEdgeList();
	
}
	/**
	 * Creating a graph with a proper adjacency list.
	 * @param vertices A list of all vertices to create a proper graph.
	 */
	public Graph(ArrayList<Vertex> vertices){
		this.vertexList = vertices;
		this.VertexCount = vertices.size();
		createFromList();
		createEdgeList();
	}
	
	//Creating a graph with a proper vertices list as String.
	public Graph(String[] vList){
		addVerticesFromArray(vList);
		this.VertexCount = vertexList.size();
		createFromList();
		createEdgeList();
	}

	/**
	 * Creating a proper vertex list for my graph with given String array.
	 * @param vList String array contains, labels of vertices.
	 */
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
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTopologicalOrder(LinkedList<Vertex> topologicalOrder) {
		this.topologicalOrder = topologicalOrder;
	}
	
	public LinkedList<Vertex> getTopologicalOrder() {
		return topologicalOrder;
	}
	public TreeSet<Edge> getEdgeList() {
		return edgeList;
	}
	public void setEdgeList(TreeSet<Edge> edgeList) {
		this.edgeList = edgeList;
	}

	// Creating a graph data structure with given distance matrix.
	public void createFromMatrix(){
		for(int i = 0; i < VertexCount; i++){
			for (int j = 0; j < VertexCount; j++){
				if(adjacencyMatrix[i][j] != 0){
					vertexList.get(i).neighbors.add(new Edge(vertexList.get(i),vertexList.get(j), adjacencyMatrix[i][j]));	
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
	// Creating a list of edges.
	public void createEdgeList(){
		if(undirected){
		for(int i = 0; i < VertexCount; i++){
			for (int j = 0; j < VertexCount; j++){
				if(adjacencyMatrix[i][j] != 0 && j>=i){
					edgeList.add(new Edge(vertexList.get(i),vertexList.get(j), adjacencyMatrix[i][j]));
				}
			}
		}
		} else {
			for(int i = 0; i < VertexCount; i++){
				for (int j = 0; j < VertexCount; j++){
					if(adjacencyMatrix[i][j] != 0){
						edgeList.add(new Edge(vertexList.get(i),vertexList.get(j), adjacencyMatrix[i][j]));
					}
				}
			}
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
			printOut += line + "\n";
		}
		System.out.println(printOut);
	}
	// Printing out adjacency list.
	public void printAdjancecyList(){
		for(Vertex v : vertexList){
			v.printNeighbor();
		}
	}
}
