package agent;

import environnement.Grid;

public class InternState {
	private boolean IamAlive;
	private Grid belief;
	private String[] intent;
	
	//Constructor
	
	public InternState(boolean iamAlive, Grid belief, String[] intent) {
		super();
		IamAlive = iamAlive;
		this.belief = belief;
		this.setIntent(intent);
	}
	
	//Getter and Setter
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
	public String[] getIntent() {
		return intent;
	}

	public void setIntent(String[] intent) {
		this.intent = intent;
	}

	// Other Methods
	public void depth_LimitedSearch(Grid environment,int l) {
		
	}
	
}
