package arti;

public class Edge {
	public Coordinate to;
	public String road;
	public double length;
	
	public Edge(Coordinate to, String road, double length) {
		this.to = to;
		this.road = road;
		this.length = length;
	}
}
