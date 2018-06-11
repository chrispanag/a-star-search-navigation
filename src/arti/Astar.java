package arti;

import java.util.*;

public class Astar {
	Coordinate target;
	Coordinate start;
	Graph graph;
	
	int maxMetopo;
	
	class searchNode {
		public double f;
		public Coordinate coord;
		public ArrayList<Coordinate> path;
		
		public int compare(searchNode s1, searchNode s2) {
			return Double.compare(s1.f, s2.f);
		}
		
		public searchNode(double g, Coordinate coord, ArrayList<Coordinate> pastPath) {
			this.f = g + coord.distanceFrom(target);
			this.coord = coord;
			pastPath.add(coord);
			this.path = pastPath;
		}
	}
	
	private ArrayList<searchNode> open;
	private ArrayList<searchNode> closed;
	
	
	public Astar (Coordinate target, Coordinate start, int maxMetopo, Graph graph) {
		this.graph = graph;
		this.target = target;
		this.start = start;
		this.maxMetopo = maxMetopo;
		ArrayList<Coordinate> path = new ArrayList<Coordinate>();
		path.add(start);
		open = new ArrayList<searchNode>();
		open.add(new searchNode(0, start, path));
		closed = new ArrayList<searchNode>();
	}
	
	public ArrayList<Coordinate> start() {
		while (!open.isEmpty()) {
			open.sort(null);
			searchNode selected = open.get(0);
			closed.add(selected);
			ArrayList<Edge> edges = graph.graph.get(selected.coord);
			ArrayList<searchNode> children = new ArrayList<searchNode>();
			
			edges.forEach((e) -> {
				children.add(new searchNode(selected.f, e.to, new ArrayList<Coordinate>()))
			});
			
		}
	}
}
