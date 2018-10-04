package agent;

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
				mesureDePerformance += 1;
			}
			this.getEffectors().grab(environment.getBoxI(positioni, positionj));
			electricityUsed += 1;
		} else {
			if (intent == "aspire") {
				if (environment.getBoxI(positioni, positionj).getJewel() == 1) {
					mesureDePerformance -= 1;
				}
				if (environment.getBoxI(positioni, positionj).getDirt() == 1) {
					mesureDePerformance += 1;
				}
				this.getEffectors().aspire(environment.getBoxI(positioni, positionj));
				electricityUsed += 1;
			} else {
				if (intent != "Ne rien faire") {
					this.getEffectors().move(this, intent);
					electricityUsed += 1;
				}
			}
		}
		return (environment.getBoxI(this.positioni, this.positionj));
	}
	
	// Other Methods
	
	public void observ(Grid environment) {
		this.setBelief(this.sensors.analyzeEnvironment(environment));
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

}



