

import java.io.InputStreamReader;
//import java.util.Arrays;
import java.util.Arrays;
import java.util.Scanner;


public class Test {

	// Testing minimum spanning trees with my graph.
	static Long startKruskal;
	static Long endKruskal;
	static Long startPrim;
	static Long endPrim;
	private static long resultKruskal;
	private static long resultPrim;
	
	
	
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
			String source = scan.nextLine();
	
			int[][] distanceMatrix = new int[vertexCount][vertexCount];
			// Taking distance matrix from console.
			for(int i = 0; i < vertexCount; i++){
				String[] line = scan.nextLine().trim().split("\\s+");
				for(int j = 0; j < vertexCount; j++){
					distanceMatrix[i][j] = Integer.parseInt(line[j]);	
				}	
			}
			// Creating an instance of graph from given data.
			Graph instanceOfGraph = new Graph(vertexCount, vertexList, distanceMatrix);
			
			// Finding source vertex from given String vertex name.
			// I presume vertex list is ordered.
			
			Vertex sourceVertex = instanceOfGraph.getVertexList().get(Arrays.binarySearch(vertexList, source));
	
			MinimumSpanningTree mst = new MinimumSpanningTree();
			
			startKruskal = System.currentTimeMillis();
			mst.Kruskal(instanceOfGraph);
			endKruskal = System.currentTimeMillis();
			
			startPrim = System.currentTimeMillis();
			mst.Prim(instanceOfGraph, sourceVertex);
			endPrim = System.currentTimeMillis();
			
			resultPrim = endPrim-startPrim;
			resultKruskal =  endKruskal-startKruskal;
			
			System.out.println("Kruskal= " + resultKruskal+ "  Prim= " + resultPrim);
			
			
		}
		catch(ArrayIndexOutOfBoundsException e){ 
			System.out.println("You have given inconsistent data.");
		}
		catch(NumberFormatException e){
			System.out.println("Give a proper number for Count!");
		}
		catch(NullPointerException e){
			System.out.println("There is no data, you have given!");
		}
		catch(Exception e){
			System.out.println("Unqualified programmer !!!");
	}
	}
}
