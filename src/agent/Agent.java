package agent;

import environnement.Box;
import environnement.Grid;

public class Agent {//Agent which will evolve in the environment he is based on a purpose model
	//Fields	
	private InternState bdi;
	private int positionj;
	private int positioni;
	private Sensors sensors;
	private Effectors effectors;
	private int electricityUsed;
	private int mesureDePerformance;

	//Constructor	
	public Agent(InternState bdi,int positionj, int positioni,int mesureDePerformance,int electricityUsed) {
		this.bdi = bdi;
		this.positionj = positionj;
		this.positioni = positioni;
		this.setElectricityUsed(electricityUsed);
		this.mesureDePerformance = mesureDePerformance;
	}

	//Getter and Setter
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
	//Other Methods
	public Box act(String intent,Grid environment) {
		
		if (intent=="grab") {
			if(environment.getBoxI(positioni, positionj).getJewel()==1) {
				mesureDePerformance+=1;
			}
			this.getEffectors().grab(environment.getBoxI(positioni, positionj));
			electricityUsed+=1;
		}
		else {
			if (intent=="aspire") {
				if(environment.getBoxI(positioni, positionj).getJewel()==1) {
					mesureDePerformance-=1;
				}
				if(environment.getBoxI(positioni, positionj).getDirt()==1) {
					mesureDePerformance+=1;
				}
				this.getEffectors().aspire(environment.getBoxI(positioni, positionj));
				electricityUsed+=1;
			}
			else {
				if(intent!="Ne rien faire") {
					this.getEffectors().move(this, intent);
					electricityUsed+=1;
				}
			}
		}
		return(environment.getBoxI(this.positioni, this.positionj));
	}
	public int cost(String intent) {
		if(intent!="Ne rien faire") {
			return(1);
		}
		else return(0);
	}
		
	}
}
