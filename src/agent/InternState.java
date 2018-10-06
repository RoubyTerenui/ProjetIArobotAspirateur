package agent;

import java.util.ArrayList;
import java.util.List;

import environnement.Box;
import environnement.Grid;

public class InternState {
	private boolean IamAlive;
	private Grid belief;
	private List<String> intent;

	// Constructor

	public InternState(boolean iamAlive, Grid belief, List<String> intent) {
		super();
		IamAlive = iamAlive;
		this.belief = belief;
		this.setIntent(intent);
	}

	public InternState() {
		super();
		IamAlive = true;
		belief = new Grid(new Box[10][10]);
		this.intent = new ArrayList<String>();
	}

	// Getter and Setter
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

	public List<String> getIntent() {
		return intent;
	}

	public void setIntent(List<String> intent) {
		this.intent = intent;
	}
}

