/* Sabrina Cown
 * CS4500
 * This is the class for an Edge.
*/
public class Edge {
	private Vertex target;
	private int weight;
	
	public Edge(Vertex target, int weight){
		this.setTarget(target);
		this.setWeight(weight);
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Vertex getTarget() {
		return target;
	}

	public void setTarget(Vertex target) {
		this.target = target;
	}
	
}
