
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

import Algorithms.GraphTraversal;
import Algorithms.ShortestPath;
import Graph.Graph;
import Graph.Vertex;


public class TestShortestPath{

	// Testing my graph and traverse.
	
	public static void main(String[] args){
		
		try{
		
			// Instances for reading data.
			InputStreamReader input = new InputStreamReader(System.in);
			Scanner scan = new Scanner(input);
		
			// Count of vertex in graph.
			int vertexCount = Integer.parseInt(scan.nextLine());
	
			// List of vertices.
			String[] vertexList = scan.nextLine().split(" ");
	
			// Source vertex to find. Destination.
			String source = scan.nextLine(); // Not needed for depth first search in this particular project.
	
			int[][] distanceMatrix = new int[vertexCount][vertexCount];
			// Taking distance matrix from console.
			for(int i = 0; i < vertexCount; i++){
				String[] line = scan.nextLine().split(" ");
				for(int j = 0; j < vertexCount; j++){
					distanceMatrix[i][j] = Integer.parseInt(line[j]);	
				}	
			}
			
			// Creating an instance of graph from given data.
			Graph instanceOfGraph = new Graph(vertexCount, vertexList, distanceMatrix);
			
			
			// Finding source vertex from given String vertex name.
			// I presume vertex list is ordered.
			Vertex sourceVertex = instanceOfGraph.getVertexList().get(Arrays.binarySearch(vertexList, source));
	
			//GraphTraversal gt = new GraphTraversal(sourceVertex);
			
			// Shortest path object.
			ShortestPath sp = new ShortestPath(instanceOfGraph, sourceVertex);
			
			// Apply bellman-ford.
			sp.bellmanFord();
			//gt.mapPrint(instanceOfGraph);
			
			// Apply dijkstraCormen.
			sp.dijkstraCormen();
			//gt.mapPrint(instanceOfGraph);
			
			// Apply dijskstra.
			sp.dijkstra();
			//gt.mapPrint(instanceOfGraph);
			
			
			
		}
		catch(ArrayIndexOutOfBoundsException e){ 
			System.out.println("You have given inconsistent data.");
		}
		catch(NumberFormatException e){
			System.out.println("Give a proper number for Count!");
		}
		/*catch(NullPointerException e){
			System.out.println("There is no data, you have given!");
		}
		catch(Exception e){
			System.out.println("Unqualified programmer !!!");
	}*/
	}
}
