package agent;

import java.util.ArrayList;
import java.util.List;

import environnement.Box;
import environnement.Grid;

public class Node {
	//Fields
	private Node parent;
	private Box actualState;
	private String action;
	private int depth;
	private int cost;
	//Constructor
	public Node(Node parent, Box actualState, int depth, int cost) {
		super();
		this.parent = parent;
		this.actualState = actualState;
		this.depth = depth;
		this.cost = cost;
	}
	//Getter and Setter
	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Box getActualState() {
		return actualState;
	}

	public void setActualState(Box actualState) {
		this.actualState = actualState;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	//Expand Function used in the search
	public List<Node> expand( Grid belief,Agent agent) {
		List<Node> successors=new ArrayList<Node>();
		List<String> actions=this.successor_Node();
		for (String act : actions) {
			Node s=new Node(this,agent.act(act, belief), this.depth+1, agent.cost(action));
			successors.add(s);
		}
		return successors;
	}
	
	public List<String> successor_Node() {
		List<String> actions=new ArrayList<String>();
		actions.add("ne rien faire");
		actions.add("grab");
		actions.add("aspire");
		if(actualState.getPositionI()!=9) {
			actions.add("right");
		}
		if(actualState.getPositionI()!=0) {
			actions.add("left");
		}
		if(actualState.getPositionJ()!=9) {
			actions.add("down");
		}
		if(actualState.getPositionJ()!=0) {
			actions.add("up");
		}
		return actions;
		
	}

	
}
