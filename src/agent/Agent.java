package agent;

import environnement.Grid;

public class Agent {//Agent which will evolve in the environment he is based on a purpose model
	//Fields
	
	private InternState bdi;
	private int positionj;
	private int positioni;
	private Sensors sensors;
	private Effectors effectors;
	private int electricityUnitUsed;

	public Agent(int x, int y){
		positioni = x;
		positionj = y;
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
	public int getElectricityUnitUsed() {
		return electricityUnitUsed;
	}
	public void setElectricityUnitUsed(int electricityUnitUsed) {
		this.electricityUnitUsed = electricityUnitUsed;
	}
	// Other Methods
	public void depth_LimitedSearch(Grid environment,int l) {
		
	}
}
