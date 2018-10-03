package agent;

import environnement.Box;

public class Effectors {// Class regrouping all the action the agent can do and the hardware that allows
						// it

	void grab(Box box) {
			box.setJewel(0);
	}

	void aspire(Box box) {
			box.setDirt(0);
			box.setJewel(0);
	}

	void move(Agent agent, String string) throws IndexOutOfBoundsException {
		try {
			if (string == "right") {// look whether you move vertically or horizontally
				agent.setPositioni(agent.getPositionj() + 1);
			}
			if (string == "left") {
				agent.setPositioni(agent.getPositionj() - 1);
			}
			if (string == "down") {
				agent.setPositioni(agent.getPositioni() + 1);
			}
			if (string == "up") {
				agent.setPositioni(agent.getPositioni() - 1);
			}
		} catch (	IndexOutOfBoundsException e 	
				System.out.println("Mouvement Non autorisé !!")

			
	

}}
