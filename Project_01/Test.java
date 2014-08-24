
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;


public class Test {

	// Testing my graph.
	
	public static void main(String[] args){
		
		try{
		
			// Instances for reading data.
			InputStreamReader input = new InputStreamReader(System.in);
			Scanner scan = new Scanner(input);
			
			System.out.print("Number of Vertices: ");
			// Count of vertex in graph.
			int vertexCount = Integer.parseInt(scan.nextLine());
	
			System.out.print("Names of Vertices: ");
			// List of vertices.
			String[] vertexList = scan.nextLine().split(" ");
		
			System.out.print("Initial Vertex: ");
			// Source vertex to find. Destination.
			String source = scan.nextLine();
			
			System.out.print("Distance Matrix: ");
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
			Vertex sourceVertex = instanceOfGraph.getVertexList().get(Arrays.binarySearch(vertexList, source));
			
			// Returning the neighbor list as distance list.
			sourceVertex.printNeighbor();
			
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
