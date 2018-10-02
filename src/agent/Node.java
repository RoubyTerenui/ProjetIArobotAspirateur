package agent;

import environnement.Box;
import environnement.Grid;

public class Node {
	//Fields
	Box parent;
	Box actualState;
	int depth;
	int cost;
	//Constructor
	public Node(Box parent, Box actualState, int depth, int cost) {
		super();
		this.parent = parent;
		this.actualState = actualState;
		this.depth = depth;
		this.cost = cost;
	}

	//Expand Function used in the search
	public Node[] expand(Grid environment) {
		
		return null;
	}

	
}
