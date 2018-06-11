package arti;

public class Taxi {
	public Coordinate position, node;
	public String id;
	
	public Taxi(Coordinate position, String id) {
		this.position = position;
		this.id = id;
	}

	@Override
	public String toString() {
		return "Taxi [position=" + position + ", node=" + node + ", id=" + id + "]";
	}
}
