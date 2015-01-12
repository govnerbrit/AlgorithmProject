/* Sabrina Cown
 * CS4500
 * This is the implementation for gathering graph information from the user console.
*/
import java.util.*;

public class MainProject {
	final static int MAX_VALUE = 999;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		//request the number of vertices from the user
		System.out.println("Please input the number of vertices in your graph.");
		int x = scan.nextInt();
		int i = 0;
		int j = 0;
		String belPath = "";
		//create multidimensional arrays for bellman ford
		int[][] adj = new int[x][x];
		int[][] dist = new int[x][x];
		int[][] newDist = new int[x][x];
		//create an array of vertices for dijkestra.
		Vertex[] vertices = new Vertex[x];
		//create an arraylist for the edges.
		ArrayList<Edge> edges = new ArrayList<Edge>();
		int w = 0;
		String name ="";
		
		//add the number of new vertices to the array.
		for(i = 1; i <= x; i++){
			name = "" + i;
			vertices[i-1] = new Vertex(name);
		}
		
		//loops to collect values from user.
		for(i = 0; i < x; i++){
			for(j = 0; j < x; j++){
				//if we are on the diagonal of the matrix, make entry 0.
				if(i == j)
					adj[i][j] = 0;
				else {
					//if there is a value in the entry adj[j][i]
					if(adj[j][i] != 0){
						//copy the value to adj[i][j]
						w = adj[j][i];
						adj[i][j] = w;
						//if the value is not the MAX_VALUE create an edge for it. 
						if(w != MAX_VALUE)
							edges.add(new Edge(vertices[j], w));
					}
					else{
						//ask the user for a new value
						System.out.println("Please input the weight of the edge between " + (i+1) + " and " + (j+1) + ". if there is no edge between them enter i.");
						String input = scan.next();
						//if the indicated value is infinity add MAX_VALUE to the matrix
						if(input.equals("i")||input.equals("I")){
							adj[i][j] = MAX_VALUE;
						}
						else{
							//collect the value of the integer
							w = Integer.parseInt(input);
							//set the matrix entry to the gathered value and create an edge for it.
							adj[i][j] = w;
							edges.add(new Edge(vertices[j], w));
						}
					}
				}
			}
			//create an array of edges
			Edge[] edgeArray = new Edge[edges.size()];
			//get all the values from the array list.
			edges.toArray(edgeArray);
			//set the edges
			vertices[i].setAdjacencies(edgeArray);
			//clear the array list.
			edges.clear();
		}
		//get the starting point from the user.
		System.out.println("Please provide the number of the vertex that you would like the paths for: ");
		x = scan.nextInt();
		//find the vertex in the array.
		Vertex source = vertices[x-1];
		//Compute path using Dijkstra
		Dijkstra.computePath(source);
		//Print out Dijkstra paths.  
		for(Vertex v: vertices){
			System.out.println("Distance to " + v.getName() + ": " + v.getMinDistance());
			List<Vertex> path = Dijkstra.getShortestPath(v);
			System.out.println("Path: " + path.toString().substring(1, path.toString().length() -1).replaceAll(",", " ->"));
			}
		System.out.println("");
		
		//Print cost matrix
		AlgorithmProject.printArray(adj);
		
		//Create first distance matrix
		dist = AlgorithmProject.copyArray(adj, dist);
		//run the bellman ford algorithm until dist and newDist are equal.
		for(int h = 0; h < MAX_VALUE; h++){
			newDist = BellmanFord.Bellman(adj, dist);
			if (AlgorithmProject.equal(newDist, dist)){
				AlgorithmProject.printArray(dist);
				break;
			}
			else{
				dist = AlgorithmProject.copyArray(newDist, dist);
			}
		}
		int belSource = x-1;
		System.out.println("Bellman Path:");
		for(i = 0; i < adj.length; i++){
			belPath = BellmanFord.getBelPath(adj, newDist, belSource, i);
			System.out.println("Distance to R" + (i + 1) + ": " + dist[belSource][i]);
			System.out.print("Path: " + belPath.charAt(0));
			for(j = 1; j < belPath.length(); j++){
				System.out.print(" -> " + belPath.charAt(j));
			}
			System.out.println("");
		}
		
	}
}
