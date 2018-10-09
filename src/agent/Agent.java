package agent;

import java.util.ArrayList;
import java.util.List;

import environnement.Box;
import environnement.Grid;

public class Agent {// Agent which will evolve in the environment he is based on a purpose model
	// Fields
	private InternState bdi;
	private int positionj;
	private int positioni;
	private Sensors sensors;
	private Effectors effectors;
	private int mesureDePerformance;

	// Constructor
	public Agent(InternState bdi, int positionj, int positioni, int mesureDePerformance, int electricityUsed) {
		this.sensors = new Sensors();
		this.effectors = new Effectors();
		this.bdi = bdi;
		this.positionj = positionj;
		this.positioni = positioni;
		this.mesureDePerformance = mesureDePerformance;
	}

	public Agent(int positioni, int positionj) {
		this.sensors = new Sensors();
		this.effectors = new Effectors();
		this.bdi = new InternState();
		this.positionj = positionj;
		this.positioni = positioni;
		this.mesureDePerformance = 0;
	}

	// Getter and Setter
	public int getPositionj() {
		return positionj;
	}

	public void setPositionj(int positionj) {
		this.positionj = positionj;
	}

	public int getPositioni() {
		return positioni;
	}

	public void setPositioni(int positioni) {
		this.positioni = positioni;
	}

	public Effectors getEffectors() {
		return effectors;
	}

	public Grid getBelief() {
		return bdi.getBelief();
	}

	public void setBelief(Grid belief) {
		bdi.setBelief(belief);
	}

	public Sensors getSensors() {
		return sensors;
	}

	public int getMesureDePerformance() {
		return mesureDePerformance;
	}

	public void setMesureDePerformance(int mesureDePerformance) {
		this.mesureDePerformance = mesureDePerformance;
	}

	public InternState getBdi() {
		return bdi;
	}

	public void setBdi(InternState bdi) {
		this.bdi = bdi;
	}

	// Other Methods
	public Box act(String intent, Grid environment) {
		if (intent != "Ne rien faire" && intent != "" && intent != null) {
			try {
				Thread.sleep(10);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (intent == "grab") {
			if (this.getBelief().getBoxI(positioni, positionj).getJewel() == 1) {
				mesureDePerformance += 5;
			}
			if (environment.getBoxI(positioni, positionj).getJewel() == 1) {
				environment.setMesureDePerformance(environment.getMesureDePerformance()+5);
			}
			this.getEffectors().grab(environment.getBoxI(positioni, positionj));
			this.getEffectors().grab(this.getBelief().getBoxI(positioni, positionj));
			mesureDePerformance -= 1;
			environment.setMesureDePerformance(environment.getMesureDePerformance()-1);
		} else {
			if (intent == "aspire") {
				if (environment.getBoxI(positioni, positionj).getJewel() == 1) {
					environment.setMesureDePerformance(environment.getMesureDePerformance()-50);
				}
				if (this.getBelief().getBoxI(positioni, positionj).getJewel() == 1) {
					mesureDePerformance -= 50;
				}
				if (environment.getBoxI(positioni, positionj).getDirt() == 1) {
					environment.setMesureDePerformance(environment.getMesureDePerformance()+50);
				}
				if (this.getBelief().getBoxI(positioni, positionj).getDirt() == 1) {
					mesureDePerformance += 50;
				}
				
				this.getEffectors().aspire(environment.getBoxI(positioni, positionj));
				this.getEffectors().aspire(this.getBelief().getBoxI(positioni, positionj));
				mesureDePerformance -= 1;
				environment.setMesureDePerformance(environment.getMesureDePerformance()-1);
			} else {
				if (intent != "Ne rien faire" && intent != "" && intent != null) {
					boolean res = this.getEffectors().move(this, intent);
					if (res) {
						mesureDePerformance -= 1;
						environment.setMesureDePerformance(environment.getMesureDePerformance()-1);
					}
				}
			}
		}
		return (this.getBelief().getBoxI(this.positioni, this.positionj));
	}

	public void executeIntent(Grid environment,int limitIntent) {
		for (int j = 0; j < Math.min(this.getBdi().getIntent().size(),limitIntent); j++) {
			System.out.println(this.getBdi().getIntent().get(this.getBdi().getIntent().size() - j - 1));
			this.act(this.getBdi().getIntent().get(this.getBdi().getIntent().size() - j - 1), environment);
		}
	}

	public void observ(Grid environment) {
		this.setBelief(this.sensors.analyzeEnvironment(environment));
	}

	public void createIntent( boolean informed,int l) {
		String action = "initial";
		List<String> res = new ArrayList<String>();
		Node nodes;
		if (informed) {
			nodes = aStar(this.getBelief());
		}
		else {
			nodes=depth_LimitedSearch(this.getBelief(), l);
		}
		while (action != "") {
			action = nodes.getAction();
			nodes = nodes.getParent();
			res.add(action);
		}
		this.getBdi().setIntent(res);
	}

	public Node depth_LimitedSearch(Grid environment, int l) {
		Node initialNode = new Node(environment.getBoxI(this.getPositioni(), this.getPositionj()));
		return (recursive_DLS(initialNode, environment, l));
	}

	public Node recursive_DLS(Node node, Grid environment, int l) {
		boolean cutOff = false;
		Node nodeCutOff = null;
		if (this.testGoal(node)) {
			return (node);
		} else {
			if (node.getDepth() == l) {
				node.setcutoff(true);
				return (node);
			} else {
				List<Node> newNodes = node.expand(environment, null);
				for (Node node2 : newNodes) {
					Node result = recursive_DLS(node2, environment, l);
					if (result.iscutoff()) {
						cutOff = true;
						nodeCutOff = result;
					} else {
						if (result != null) {
							return result;
						}
					}
				}
			}
		}
		if (cutOff) {
			return nodeCutOff;
		} else {
			return null;
		}
	}

	public Node aStar(Grid environment) {
		Node nodeStart = new Node(environment.getBoxI(this.getPositioni(), this.getPositionj()));
		nodeStart.affectHeuristique(this);
		if(nodeStart.getHeuristique()==-1) {//to handle the no_Dirt case
			return nodeStart;
		}
		List<Node> nodeList = new ArrayList<Node>();
		nodeList.add(nodeStart);
		while (!testGoal(nodeList.get(0))) {
			Node tmp = nodeList.get(0);
			nodeList.remove(0);
			List<Node> newNodes = tmp.expand(this.getBelief(), this);
			for (Node node : newNodes) {
				nodeList.add(node);
			}
			quickSort(nodeList, 0, nodeList.size());
		}
		return nodeList.get(0);
	}

	public Box findBoxGoal() {
		List<Box> dirtyBox = new ArrayList<Box>();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (this.getBelief().getBoxI(i, j).getDirt() == 1) {
					dirtyBox.add(this.getBelief().getBoxI(i, j).clone());
				}
			}
		}
		Box res = new Box(0, 0, this.getPositioni(), this.getPositionj());
		try {
			res = dirtyBox.get(0);
			for (Box box : dirtyBox) {
				if (norme(box, new Box(0, 0, this.getPositioni(), this.getPositionj())) < norme(res,
						new Box(0, 0, this.getPositioni(), this.getPositionj()))) {
					res = box;
				}
			}
		} catch(IndexOutOfBoundsException e) {
			return null;
		}
		return res;
	}

	public int norme(Box nodeStart, Box nodeGoal) {
		return Math.abs(nodeGoal.getPositionI() - nodeStart.getPositionI())
				+ Math.abs(nodeGoal.getPositionJ() - nodeStart.getPositionJ());
	}

	// Ref http://www.algolist.net/Algorithms/Sorting/Quicksort
	public int partition(List<Node> nodeList, int left, int right) {
		int i = left, j = right;
		Node tmp;
		Node pivot = nodeList.get((left + right) / 2);
		while (i <= j) {
			while (nodeList.get(i).sumCost() < pivot.sumCost())
				i++;
			while (nodeList.get(j).sumCost() > pivot.sumCost())
				j--;
			if (i <= j) {
				tmp = nodeList.get(i);
				nodeList.set(i, nodeList.get(j));
				nodeList.set(j, tmp);
				i++;
				j--;
			}
		}

		return i;
	}

	// Ref http://www.algolist.net/Algorithms/Sorting/Quicksort
	public void quickSort(List<Node> nodeL, int left, int right) {
		int index = partition(nodeL, left, right-1);
		if (left < index - 1)
			quickSort(nodeL, left, index - 1);
		if (index < right)
			quickSort(nodeL, index, right);
	}

	public int simulPerf(String intent, Node n) {
		int mesure = 0;
		if (intent == "grab") {
			mesure += 4;
		} else {
			if (intent == "aspire") {
				if (this.getBelief().getBoxI(n.getActualState().getPositionI(), n.getActualState().getPositionJ())
						.getDirt() == 1) {
					mesure += 50;
				}
				if (this.getBelief().getBoxI(n.getActualState().getPositionI(), n.getActualState().getPositionJ())
						.getJewel() == 1) {
					mesure -= 50;
				}
				mesure -= 1;
			} else {
				if (intent == "right") {// look whether you move vertically or horizontally
					if (positionj < 9) {
						mesure -= 1;
					}
				}
				if (intent == "left") {
					if (positionj > 0) {
						mesure -= 1;
					}
				}
				if (intent == "down") {
					if (positioni < 9) {
						mesure -= 1;
					}
				}
				if (intent == "up") {
					if (positioni > 0) {
						mesure -= 1;
					}
				}
			}
		}
		return mesure;

	}

	public int simulIntent(Node n) {
		String action = "initial";
		int cost = 0;
		while (action != "") {
			action = n.getAction();
			cost += simulPerf(action, n);
			n = n.getParent();
		}
		return cost;
	}

	public boolean testGoal(Node n) {
		int actDecision = mesureDePerformance + simulIntent(n);
		if (actDecision > mesureDePerformance) {
			return true;
		} else {
			return false;
		}
	}

}
