package arti;

import java.util.*;

public class main {

	public static void main(String[] args) {
		CSVReader csv = new CSVReader("data/nodes.csv");
		ArrayList<String[]> mapPoints = csv.readCSV();
		
		Graph graph = new Graph(mapPoints);
		//graph.print();
		
		csv = new CSVReader("data/client.csv");
		ArrayList<String[]> clientData = csv.readCSV();
		Coordinate client = new Coordinate(clientData.get(0)[0], clientData.get(0)[1]);
		System.out.println(client.toString());
		Coordinate start = graph.nearestNode(client);
		System.out.println(start.toString());
		
		csv = new CSVReader("data/taxis.csv");
		ArrayList<String[]> taxisData = csv.readCSV();
		
		ArrayList<Taxi> taxis = new ArrayList<Taxi>();
		
		taxisData.forEach((t) -> {
			taxis.add(new Taxi(new Coordinate(t[0], t[1]), t[2]));
		});
		
		taxis.forEach((t) -> {
			t.node = graph.nearestNode(t.position);
		});
		
		taxis.forEach((t) -> {
			System.out.println(t.toString());
		});
	}

}
