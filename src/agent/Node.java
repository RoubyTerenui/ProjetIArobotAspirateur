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
	private int heuristique;

	// Constructor
	public Node(Node parent, Box actualState, String action, int depth, int cost, int heuristique) {
		super();
		this.parent = parent;
		this.actualState = actualState;
		this.action = action;
		this.depth = depth;
		this.cost = cost;
		this.setcutoff(false);
		this.heuristique = heuristique;
	}
	public Node(Box actualState) {
		super();
		this.parent=new Node(null,null,null,-1,0, 0);
		this.actualState=actualState;
		this.action="";
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

	public int getHeuristique() {
		return heuristique;
	}

	public void setHeuristique(int heuristique) {
		this.heuristique = heuristique;
	}

	// Expand Function used in the search
	public List<Node> expand(Grid belief,Agent agent) {
		List<Node> successors = new ArrayList<Node>();
		List<String> actions = this.successor_Node();
		for (String act : actions) {
			Node s = new Node(this, simulAct(act, belief), act, this.depth + 1,costAction(action)+parent.getCost(),0);
			if (agent!=null) {
				s.affectHeuristique(agent);
			}
			successors.add(s);
		}
		return successors;
	}
	
	public Box simulAct(String intent, Grid environment) {
		Box res=environment.getBoxI(actualState.getPositionI(),actualState.getPositionJ()).clone();
		if (intent == "grab") {
			res.setJewel(0);			
		} else {
			if (intent == "aspire") {
				res.setDirt(0);
				res.setJewel(0);
			} else {
				if (intent == "right") {// look whether you move vertically or horizontally
					if(res.getPositionJ()<9)
						{res=environment.getBoxI(actualState.getPositionI(),actualState.getPositionJ()+1).clone();}
				}
				if (intent == "left") {
					if(res.getPositionJ()>0)
						{res=environment.getBoxI(actualState.getPositionI(),actualState.getPositionJ()-1).clone();}
				}
				if (intent == "down") {
					if(res.getPositionI()<9)
					{res=environment.getBoxI(actualState.getPositionI()+1,actualState.getPositionJ()).clone();}
				}
				if (intent == "up") {
					if(res.getPositionI()>0)
					{res=environment.getBoxI(actualState.getPositionI()-1,actualState.getPositionJ()).clone();}
				}
			}
		}
		return res;
	}
	
	
	public List<String> successor_Node() {
		List<String> actions = new ArrayList<String>();
		//actions.add("ne rien faire");
		
		if(actualState.getJewel()==1) {
			actions.add("grab");
		}
		if (actualState.getDirt()==1) {
			actions.add("aspire");
		}
		if (actualState.getPositionJ() != 9) {
			actions.add("right");
		}
		if (actualState.getPositionJ() != 0) {
			actions.add("left");
		}
		if (actualState.getPositionI() != 9) {
			actions.add("down");
		}
		if (actualState.getPositionI() != 0) {
			actions.add("up");
		}
		return actions;

	}
	
	public int costAction(String intent) {
		if (intent != "ne rien faire" && !(intent.isEmpty())) {
			return (1);
		} else
			return (0);
	}

	public int sumCost(){
		return this.cost + this.heuristique;
	}
	
	public void affectHeuristique(Agent agent) {
		Box goal=agent.findBoxGoal();
		this.setHeuristique(agent.norme(this.getActualState(), goal));
	}
}
