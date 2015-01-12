/* Sabrina Cown
 * CS4500
 * This is the implementation of the Dijkstra algorithm.
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
	static int counter = 0;
	//Method to compute the path for the vertices.
	public static void computePath(Vertex source){
		counter = 0;
		//set the source vertex min distance to 0.
		source.setMinDistance(0);
		//create a queue that can be arranged by priority.
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		//add the source vertex to the queue.
		vertexQueue.add(source);
		//while there are vertices in the queue
		while(!vertexQueue.isEmpty()){
			counter++;
			//retreives the first vertex in the queue
			Vertex a = vertexQueue.poll();
			//gets the edges for vertex a
			Edge aAdjacencies[] = a.getAdjacencies();
			//for each edge in the array
			for(Edge e : aAdjacencies){
				//get the target vertex from the edge
				Vertex v = e.getTarget();
				//get the weight from the edge
				int weight = e.getWeight();
				//add the minimum distance of the vertex at the top of the queue and the weight of the edge
				int distanceViaA = a.getMinDistance() + weight;
				//if the added distance is less than the minimum distance of edge
				if(distanceViaA < v.getMinDistance()){
					//remove the vertex from the queue
					vertexQueue.remove(v);
					//set the min distance to the added value
					v.setMinDistance(distanceViaA);
					//set the previous vertex as the vertex at the top of the queue
					v.setPrevious(a);
					//add the vertex back to the queue
					vertexQueue.add(v);
				}
			}
		}
	}
	
	//get the shortest path for a specific vertex
	public static List<Vertex> getShortestPath(Vertex target){
		//create a list to store the vertices
		List<Vertex> path = new ArrayList<Vertex>();
		//while there are previous vertexes add them to the path
		for(Vertex vertex = target; vertex != null; vertex = vertex.getPrevious()){
			path.add(vertex);
		}
		//reverse the list
		Collections.reverse(path);
		//return the path 
		return path;
	}
	
	public static int getCounter(){
		return counter;
	}

}
