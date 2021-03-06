package agent;

import environnement.Box;

public class Effectors {// Class regrouping all the action the agent can do and the hardware that allows
						// it
	public Effectors() {
		super();
	}

	public void grab(Box box) {
		box.setJewel(0);
	}

	public void aspire(Box box) {
		box.setDirt(0);
		box.setJewel(0);
	}

	public boolean move(Agent agent, String string) {
		boolean didMove = false;
		if (string == "right") {// look whether you move vertically or horizontally
			if (agent.getPositionj() < 9) {
				didMove=true;
				agent.setPositionj(agent.getPositionj() + 1);
			}
		}
		if (string == "left") {
			if (agent.getPositionj() > 0) {
				didMove=true;
				agent.setPositionj(agent.getPositionj() - 1);
			}
		}
		if (string == "down") {
			if (agent.getPositioni() < 9) {
				didMove=true;
				agent.setPositioni(agent.getPositioni() + 1);
			}
		}
		if (string == "up") {
			if (agent.getPositioni() > 0) {
				didMove=true;
				agent.setPositioni(agent.getPositioni() - 1);
			}
		}
		return didMove;

	}
}
