
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * 
 * @author Kemal Akkoyun
 *	Some properites of graph and vertex class has been reduced.
 *	This particular code only covers requirements of Project 4.
 *	Meanly, this representation only for Prim and Kruskal.
 *	I am going to form more modular and flexible class organization for all graphs and graph algorithms.
 *	Wait for updates. 
 *
 */

public class Graph {
	
	private int VertexCount;
	// Number of Vertex in graph.
	
	private ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
	// Adjacency List represent as an ArrayList.
	
	private TreeSet<Edge> edgeList = new TreeSet<Edge>();
	// List of Edges represent as an  TreeSet.
	
	private int[][] adjacencyMatrix = new int[VertexCount][VertexCount];
	// Adjacency Matrix that show connections.
	
	// Creating a proper graph with an adjacency matrix.
	public Graph(int count, String[] vList, int[][] distanceMatrix) {
		addVerticesFromArray(vList);
		this.VertexCount = count;
		this.adjacencyMatrix = distanceMatrix;
		createFromMatrix();
		createEdgeList();
	}
	
	//Creating a graph with a proper adjacency list.
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
		for(int i = 0; i < VertexCount; i++){
			for (int j = 0; j < VertexCount; j++){
				if(adjacencyMatrix[i][j] != 0 && j>=i){
					edgeList.add(new Edge(vertexList.get(i),vertexList.get(j), adjacencyMatrix[i][j]));
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
}
