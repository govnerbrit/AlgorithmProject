/* Sabrina Cown
 * CS4500
 * This is the class for a Vertex.
*/
public class Vertex implements Comparable<Vertex>{
	private String name;
	private Edge adjacencies[];
	private Integer minDistance;
	private Vertex previous;
	
	public Vertex(String vertexName){
		this.setName(vertexName);
		this.minDistance = (int) Double.POSITIVE_INFINITY;
	}

	public int compareTo(Vertex otherVertex) {
		return Integer.compare(minDistance, otherVertex.minDistance);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Edge[] getAdjacencies() {
		return adjacencies;
	}

	public void setAdjacencies(Edge adjacencies[]) {
		this.adjacencies = adjacencies;
	}

	public Vertex getPrevious() {
		return previous;
	}

	public void setPrevious(Vertex previous) {
		this.previous = previous;
	}
	
	public int getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(Integer newMinDistance) {
		this.minDistance = newMinDistance;
	}
	
	public String toString(){
		return this.name;
	}
}
