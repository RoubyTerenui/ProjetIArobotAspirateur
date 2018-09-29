package agent;

import environnement.Grid;

public class InternState {
	private boolean IamAlive;
	private Grid belief;
	private int[] desire;//TO DO revoir les desires ce n'est pas clair ce que l'on doit y mettre
	private String[] intent;

	public Grid getBelief() {
		return belief;
	}

	public void setBelief(Grid belief) {
		this.belief = belief;
	}

	public boolean isIamAlive() {
		return IamAlive;
	}

	public void setIamAlive(boolean iamAlive) {
		IamAlive = iamAlive;
	}
	
}
