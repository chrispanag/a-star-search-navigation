package arti;

import java.util.*;

public class Graph {
	public HashMap<Coordinate, ArrayList<Edge>> graph;
	
	/*
	 * This finds the nodes. After that we need to find the Edges of the Graph!
	 */
	private HashMap<Coordinate, ArrayList<String>> findIntersections (ArrayList<String[]> mapPoints) {
		HashMap<Coordinate, ArrayList<String>> intersections = new HashMap<Coordinate, ArrayList<String>>();
		
		mapPoints.forEach((p) -> {
			Coordinate coord = new Coordinate(p[0], p[1]);
			ArrayList<String> roads;
			if (intersections.containsKey(coord)) {
				roads = intersections.get(coord);
			} else {
				roads = new ArrayList<String>();
			}
			roads.add(p[2]);
			intersections.put(coord, roads);
		});
		
		Set<Coordinate> keySet = intersections.keySet();
		Coordinate[] keys = new Coordinate[keySet.size()];
		keys = keySet.toArray(new Coordinate[0]);
		
		for (int i = 0; i < keys.length; i++) {
			if (intersections.get(keys[i]).size() == 1) {
				intersections.remove(keys[i]);
			}
		}
		return intersections; 
	}
	
	public Coordinate nearestNode(Coordinate coord) {
		double minDistance = Double.MAX_VALUE;
		Coordinate nearest = null;
		Set<Coordinate> coordinates = graph.keySet();
		
		Iterator<Coordinate> it = coordinates.iterator();
		while (it.hasNext()) {
			Coordinate c = it.next();
			double distance = coord.distanceFrom(c);
			if (distance < minDistance) {
				minDistance = distance;
				nearest = c;
			}
		}
		return nearest;
	}
	
	public void addEdgeToPoint(Coordinate from, Coordinate to, String road) {
		double distance = from.distanceFrom(to);
		ArrayList<Edge> edges;
		if (graph.containsKey(from)) {
			edges = graph.get(from);
		} else {
			edges = new ArrayList<Edge>();
		}
		edges.add(new Edge(to, road, distance));
		graph.put(from, edges);
		if (graph.containsKey(to)) {
			edges = graph.get(to);
		} else {
			edges = new ArrayList<Edge>();
		}
		edges.add(new Edge(from, road, distance));
		graph.put(to, edges);
	}
	
	public Graph (ArrayList<String[]> mapPoints) {
		String previousRoad = "";
		Coordinate prevPoint = new Coordinate(0, 0);
		
		graph = new HashMap<Coordinate, ArrayList<Edge>>();
		Coordinate coord;
		
		Iterator<String[]> it = mapPoints.iterator();
		while (it.hasNext()) {
			String[] point = it.next();
			coord = new Coordinate(point[0], point[1]);
			if (!(point[2].equals(previousRoad))) {
				previousRoad = point[2];
			} else {
				// TODO: Create Edge to previous point and from previous point
				addEdgeToPoint(coord, prevPoint, point[2]);
				previousRoad = point[2];
			}
			prevPoint = coord;
		}
	}
	
	public void print() {
		System.out.println("Print Size:" + graph.size());
		graph.forEach((c, E) -> {
			System.out.println(c.toString() + ":");
			E.forEach((e) -> {
				System.out.println("\t" + e.to.toString() + ": " + e.road + " L: " + e.length);
			});
		});
	}
}
