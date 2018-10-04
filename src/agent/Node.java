package agent;

import java.util.ArrayList;
import java.util.List;

import environnement.Box;
import environnement.Grid;

public class Node {
	// Fields
	private Node parent;
	private Box actualState;
	private String action;
	private int depth;
	private int cost;
	private boolean cutoff;

	// Constructor
	public Node(Node parent, Box actualState, String action, int depth, int cost) {
		super();
		this.parent = parent;
		this.actualState = actualState;
		this.action = action;
		this.depth = depth;
		this.cost = cost;
		this.setcutoff(false);
	}
	public Node(Box actualState) {
		super();
		this.parent=new Node(null,null,null,-1,0);
		this.actualState=actualState;
		this.action="ne rien faire";
		this.depth=0;
		this.cost=0;
		this.setcutoff(false);
	}
	// Getter and Setter
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

	public boolean iscutoff() {
		return cutoff;
	}

	public void setcutoff(boolean result) {
		this.cutoff = result;
	}

	// Expand Function used in the search
	public List<Node> expand(Grid belief) {
		List<Node> successors = new ArrayList<Node>();
		List<String> actions = this.successor_Node();
		for (String act : actions) {
			Agent agent = new Agent(actualState.getPositionI(), actualState.getPositionJ());
			Node s = new Node(this, agent.act(act, belief), act, this.depth + 1,costAction(action)+parent.getCost());
			successors.add(s);
		}
		return successors;
	}
	

	public List<String> successor_Node() {
		List<String> actions = new ArrayList<String>();
		actions.add("ne rien faire");
		actions.add("grab");
		actions.add("aspire");
		if (actualState.getPositionI() != 9) {
			actions.add("right");
		}
		if (actualState.getPositionI() != 0) {
			actions.add("left");
		}
		if (actualState.getPositionJ() != 9) {
			actions.add("down");
		}
		if (actualState.getPositionJ() != 0) {
			actions.add("up");
		}
		return actions;

	}

	public boolean testGoal() {
		if (actualState.getDirt() != 0 && action != "grab") {
			return true;
		} else {
			return false;
		}
	}
	
	public int costAction(String intent) {
		if (intent != "Ne rien faire" && !(intent.isEmpty())) {
			return (1);
		} else
			return (0);
	}

}
