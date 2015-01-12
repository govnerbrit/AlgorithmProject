/* Sabrina Cown
 * CS4500
 * This is the implementation of the Bellman-Ford algorithm.
*/
public class BellmanFord {
	static int counter = 0;
	static String node = "";
	final static int MAX_VALUE = 999;
	static int nodeNum;
	static String[] path;
	public static int[][] Bellman(int[][] adj, int[][] dist){
		//Create multidimensional array with the same dimensions as the input.
		int[][] newDist = new int[adj.length][adj.length];
		path = new String[adj.length];
		int i;
		int j;
		int l;
		int newValue;
		//create array for added values
		int[] k = new int[adj.length];
		for(i = 0; i < adj.length; i++){
			for(j = 0; j < adj.length; j++){
				//if we are on the diagonal of the matrix, make entry 0.
				if(i == j){
					newDist[i][j] = 0;
				}
				//else do computations.
				else{
					for(l = 0; l < adj.length; l++){
						//If either entry in the matrices is MAX_VALUE make the added entry MAX_VALUE
						if(adj[i][l] == MAX_VALUE || dist[l][j] == MAX_VALUE)
							k[l] = MAX_VALUE;
						//else add the two values together and put them in the array
						else
							k[l] = adj[i][l] + dist[l][j];							
					}
					counter++;
					//find the minimum of the added values
					newValue = min(k);
					//make the entry in the new matrix the min value
					newDist[i][j] = newValue;
				}
			}
		}
		//return the new matrix
		return newDist;
	}
	
	//method to find the minimum value of an array.
	public static int min(int[] value){
		int min = 999;
		node = "";
		for(int h = 0; h < value.length; h++){
			int temp = value[h];
			if(temp < min){
				min = temp;
				nodeNum = (h+1);
			}
		}
		return min;
	}
	
	public static int getCounter(){
		return counter;
	}
	
	public static void setCounter(int newCount){
		counter = newCount;
	}
	
	//Method to get the path from source to destination.
	public static String getBelPath(int[][] cost, int[][] dist, int source, int dest){
		//create an array to hold values 
		int[] added = new int[dist.length];
		//two strings needed to record  the path
		String bellPath= "";
		String nodePath = "";
		//loop adds together the source row and dest column of the cost matrix.
		for(int i = 0; i < dist.length; i++){
			added[i] = dist[source][i] + dist[i][dest];
		}
		int min = 999;
		//for the values in the added array we count all the nodes that are listed as the min
		for(int h = 0; h < dist.length; h++){
			int temp = added[h];
			//number to record the nodes. Starts at 1. 
			int count = h + 1;
			//if the value in added is less than min value: set min to temp value and record the node in bellPath 
			if(temp < min){
				min = temp;
				bellPath = String.valueOf(count);
			}
			//if temp == min record the node.
			else if (temp == min){
				bellPath += String.valueOf(count);
			}
		}
		//start the path recording the source node. 
		nodePath = String.valueOf(source + 1);
		//while the source and dest nodes are not equal, run
		while(source != dest){
			//set newDest to max value
			int newDest = 999;
			//in to record the next node value
			int nextNodeValue = 0;
			//replace the source node value in the recorded string with ""
			bellPath.replace(String.valueOf((source + 1)), "");
			//for loop to record new values
			for(int k = 0; k < dist.length; k++){
				//if the value in the cost matrix is not 0, 
				//and the value in the cost matrix is less than the max value
				//and the value node is in the bellPath string
				if(cost[source][k] != 0 && 
						cost[source][k] < newDest && 
						bellPath.contains(String.valueOf(k+1))){
					//if the value is not the max value and we are at the destination node
					if(cost[source][k] != 999 && dest == k)
					{
						//set the newDest as the value
						newDest = cost[source][k];
						//set the next node to k
						nextNodeValue = k;
						//break out of the loop
						break;
					}
					else
					{
						//set the newDest to the new value
						newDest = cost[source][k];
						//set the next node value
						nextNodeValue = k;
					}
				}
			}
			//set the source as the next node value
			source = nextNodeValue;
			//add the node to the string
			nodePath += String.valueOf(source + 1);
		}
		//return the path
		return nodePath;
	}
}
