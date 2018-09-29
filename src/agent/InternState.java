package agent;

import environnement.Box;
import environnement.Grid;

public class InternState {
	private Grid belief;
	private int[] desire;//TO DO revoir les desires ce n'est pas clair ce que l'on doit y mettre
	private Effectors[] intent;

	public Grid getBelief() {
		return belief;
	}

	public void setBelief(Grid belief) {
		this.belief = belief;
	}
	
}
