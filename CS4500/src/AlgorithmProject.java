/* Sabrina Cown
 * CS4500
 * This is the implementation of the specified graph in the project outline.
 * It also contains helper methods for copying arrays, printing out matrices, and comparing arrays.
*/
import java.util.*;
public class AlgorithmProject {
	final static int MAX_VALUE = 999;
	public static void main(String[] args) {
		int x = 6;
		int i = 0;
		int j = 0;
		int counterD = 0;
		int counterB = 0;
		int[][] adj = new int[x][x];
		int[][] dist = new int[x][x];
		int[][] newDist = new int[x][x];
		String belPath = "";
		
		
		// Initialize graph for Dijkstra.
		Vertex R1 = new Vertex("R1");
		Vertex R2 = new Vertex("R2");
		Vertex R3 = new Vertex("R3");
		Vertex R4 = new Vertex("R4");
		Vertex R5 = new Vertex("R5");
		Vertex R6 = new Vertex("R6");
		
		Edge R1Edges[] = {new Edge(R2, 2),
						  new Edge(R3, 1), 
						  new Edge(R4, 1)};
		R1.setAdjacencies(R1Edges);
		
		Edge R2Edges[] = {new Edge(R1, 2),
						  new Edge(R6, 4)};
		R2.setAdjacencies(R2Edges);
		
		Edge R3Edges[] = {new Edge(R1, 3),
				  		  new Edge(R4, 2), 
				  		  new Edge(R5, 4)};
		R3.setAdjacencies(R3Edges);
		
		Edge R4Edges[] = {new Edge(R1, 1),
						  new Edge(R3, 2), 
						  new Edge(R6, 2)};
		R4.setAdjacencies(R4Edges);
		
		Edge R5Edges[] = {new Edge(R3, 4),
				  		  new Edge(R6, 3)};
		R5.setAdjacencies(R5Edges);

		Edge R6Edges[] = {new Edge(R2, 4),
		  		  		  new Edge(R4, 2), 
		  		  		  new Edge(R5, 3)};
		R6.setAdjacencies(R6Edges);
		
		Vertex[] vertices = {R1, R2, R3, R4, R5, R6};
		
		//Compute path using Dijkstra
		Dijkstra.computePath(R5);
		counterD = Dijkstra.getCounter();
		System.out.println("Dijkstra path: ");
		//Print out Dijkstra paths.  
		for(Vertex v: vertices){
			System.out.println("Distance to " + v.getName() + ": " + v.getMinDistance());
			List<Vertex> path = Dijkstra.getShortestPath(v);
			System.out.println("Path: " + 
					path.toString().substring(1, path.toString().length() -1).replaceAll(",", " ->"));
		}
		System.out.println("Count for Dijkstra: " + counterD);
		System.out.println("");
		
		//Fill in Adjacency matrix for Bellman-Ford.
		for(i = 0; i <= 5; i++){
			for(j = 0; j <= 5; j++){
				if(i == j)
					adj[i][j] = 0;
				else if(i == 0){
					if(j == 1){
						adj[i][j] = 2;
						adj[j][i] = 2;
					}
					else if(j == 2){
						adj[i][j] = 4;
						adj[j][i] = 4;
					}
					else if(j == 3){
						adj[i][j] = 1;
						adj[j][i] = 1;
					}
					else {
						adj[i][j] = MAX_VALUE;
						adj[j][i] = MAX_VALUE;
					}
				}
				else if(i == 1 && j > 1){
					if(j == 5){
						adj[i][j] = 4;
						adj[j][i] = 4;
					}
					else {
						adj[i][j] = MAX_VALUE;
						adj[j][i] = MAX_VALUE;
					}
				}
				else if(i == 2 && j > 2){
					if(j == 3){
						adj[i][j] = 2;
						adj[j][i] = 2;
					}
					else if(j == 4){
						adj[i][j] = 4;
						adj[j][i] = 4;
					}
					else {
						adj[i][j] = MAX_VALUE;
						adj[j][i] = MAX_VALUE;
					}
				}
				else if(i == 3 && j > 3){
					if(j == 5){
						adj[i][j] = 2;
						adj[j][i] = 2;
					} 
					else {
						adj[i][j] = MAX_VALUE;
						adj[j][i] = MAX_VALUE;
					}
				}
				else if(i == 4 && j > 4){
					if(j == 5){
						adj[i][j] = 3;
						adj[j][i] = 3;
					} 
					else {
						adj[i][j] = MAX_VALUE;
						adj[j][i] = MAX_VALUE;
					}
				}
			}
		}
		
		//Create the first version of the distance matrix a copy of the cost matrix
		printArray(adj);
		dist = copyArray(adj, dist);
		BellmanFord.setCounter(0);
		//for loop to compute the bellman ford algorithm
		for(int h = 0; h < MAX_VALUE; h++){
			counterB++;
			//set the new matrix to the returned matrix from bellman ford
			newDist = BellmanFord.Bellman(adj, dist);
			//if newDist and dist are equal break the loop
			if (equal(newDist, dist)){
				printArray(dist);
				newDist = BellmanFord.Bellman(adj, dist);
				break;
			}
			//else copy the values from new dist in to dist. 
			else{
				dist = copyArray(newDist, dist);
			}
		}
		System.out.println("Bellman Path:");
		for(i = 0; i < adj.length; i++){
			belPath = BellmanFord.getBelPath(adj, newDist, 4, i);
			System.out.println("Distance to R" + (i + 1) + ": " + dist[4][i]);
			System.out.print("Path: ");
			System.out.print("R" + belPath.charAt(0));
			for(j = 1; j < belPath.length(); j++){
				System.out.print(" -> " + "R" + belPath.charAt(j));
			}
			
			System.out.println("");
		}
		System.out.println("Counter for Bellman main program: " + counterB + ".");
		System.out.println("Counter for Bellman algorithm: " + BellmanFord.getCounter() + ".");
	}
	
	//method to copy a two dimensional array.
	public static int[][] copyArray(int[][] old, int[][] newArray){
		for(int i = 0; i < old.length; i++){
			for(int j = 0; j < old.length; j++){
				newArray[i][j] = old[i][j];
			}
		}
		return newArray;
	}
	
	//prints out a multidimensional array
	public static void printArray(int[][] array){
		for(int i = 0; i < array.length; i++){
			System.out.print("|");
			for(int j = 0; j < array.length; j++){
				if(array[i][j] == MAX_VALUE)
					System.out.print(" I");
				else
					System.out.print(" " + array[i][j]);
			}
			System.out.print(" |");
			System.out.println("");
		}
		System.out.println("");
	}
	
	//compares two arrays to see if they are equal.
	public static boolean equal(int[][] a, int[][] b){
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < a.length; j++){
				if(a[i][j] != b[i][j])
					return false;
			}
		}
		return true;
	}
}
