package agent;

import java.util.ArrayList;
import java.util.List;

import environnement.Box;
import environnement.Grid;
import sun.management.resources.agent;

public class Agent {// Agent which will evolve in the environment he is based on a purpose model
	// Fields
	private InternState bdi;
	private int positionj;
	private int positioni;
	private Sensors sensors;
	private Effectors effectors;
	private int electricityUsed;
	private int mesureDePerformance;

	// Constructor
	public Agent(InternState bdi, int positionj, int positioni, int mesureDePerformance, int electricityUsed) {
		this.sensors=new Sensors();
		this.effectors=new Effectors();
		this.bdi = bdi;
		this.positionj = positionj;
		this.positioni = positioni;
		this.electricityUsed=electricityUsed;
		this.mesureDePerformance = mesureDePerformance;
	}
	public Agent(int positioni,int positionj) {
		this.sensors=new Sensors();
		this.effectors=new Effectors();
		this.bdi=new InternState();
		this.positionj=positionj;
		this.positioni=positioni;
		this.electricityUsed=0;
		this.mesureDePerformance=0;
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

	public int getElectricityUsed() {
		return electricityUsed;
	}

	public void setElectricityUsed(int electricityUsed) {
		this.electricityUsed = electricityUsed;
	}

	public InternState getBdi() {
		return bdi;
	}

	public void setBdi(InternState bdi) {
		this.bdi = bdi;
	}

	// Other Methods
	public Box act(String intent, Grid environment) {

		if (intent == "grab") {
			if (environment.getBoxI(positioni, positionj).getJewel() == 1) {
				mesureDePerformance += 10;
			}
			this.getEffectors().grab(environment.getBoxI(positioni, positionj));
			this.getEffectors().grab(this.getBelief().getBoxI(positioni, positionj));
			electricityUsed += 1;
			mesureDePerformance-=1;
		} else {
			if (intent == "aspire") {
				if (environment.getBoxI(positioni, positionj).getJewel() == 1) {
					mesureDePerformance -=20;
				}
				if (environment.getBoxI(positioni, positionj).getDirt() == 1) {
					mesureDePerformance += 50;
				}
				this.getEffectors().aspire(environment.getBoxI(positioni, positionj));
				this.getEffectors().aspire(this.getBelief().getBoxI(positioni, positionj));
				electricityUsed += 1;
				mesureDePerformance-=1;
			} else {
				if (intent != "Ne rien faire" && intent!="" && intent!=null) {
					this.getEffectors().move(this, intent);
					electricityUsed += 1;
					mesureDePerformance-=1;
				}
			}
		}
		return (this.getBelief().getBoxI(this.positioni, this.positionj));
	}
	
	public void executeIntent(Grid environment) {
		for(int j=0;j<this.getBdi().getIntent().size();j++) {
			System.out.println(this.getBdi().getIntent().get(this.getBdi().getIntent().size()-j-1));
			this.act(this.getBdi().getIntent().get(this.getBdi().getIntent().size()-j-1),environment);
		}
	}
  
	public void observ(Grid environment) {
		this.setBelief(this.sensors.analyzeEnvironment(environment));
	}
	
	public void createIntent(Grid environment,int l) {
		String action="initial";
		List<String> res=new ArrayList<String>();
		Node nodes=depth_LimitedSearch(environment, l);
		while (action!="") {
			action=nodes.getAction();
			nodes=nodes.getParent();
			res.add(action);
		}
		this.getBdi().setIntent(res);
	}
	
	public Node depth_LimitedSearch(Grid environment, int l) {
		Node initialNode=new Node(environment.getBoxI(this.getPositioni(),this.getPositionj()));
		return(recursive_DLS(initialNode, environment, l));
	}

	public Node recursive_DLS(Node node, Grid environment, int l) {
		boolean cutOff = false;
		Node nodeCutOff = null;
		if (node.testGoal()) {
			return (node);
		} else {
			if (node.getDepth() == l) {
				node.setcutoff(true);
				return (node);
			} else {
				List<Node> newNodes = node.expand(environment);
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

	public Node aStar(Node nodeStart, Grid environment){
		List<Node> nodeList = new ArrayList<Node>();
		nodeList.add(nodeStart);
		return null;
	}

	public int norme(Node nodeStart, Node nodeGoal){
		return Math.abs(nodeGoal.getActualState().getPositionI() - nodeStart.getActualState().getPositionI()) + Math.abs(nodeGoal.getActualState().getPositionJ() - nodeStart.getActualState().getPositionJ());
	}

	//Ref http://www.algolist.net/Algorithms/Sorting/Quicksort
	public int partition(List<Node> nodeList, int left, int right)
	{
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
				Node n = nodeList.get(j);
				nodeList.get(i) = n;
				nodeList.get(j) = tmp;
				i++;
				j--;
			}
		}

		return i;
	}

	public void quickSort(int arr[], int left, int right) {
		int index = partition(arr, left, right);
		if (left < index - 1)
			quickSort(arr, left, index - 1);
		if (index < right)
			quickSort(arr, index, right);
	}

}



