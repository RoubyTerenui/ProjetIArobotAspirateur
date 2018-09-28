package agent;

import environnement.Box;

public class Effectors {//Class regrouping all the action the agent can do and the hardware that allows it
	void saisir(Box box){
		box.setJewel(0);
	}
	void Aspirer(Box box) {
		box.setDirt(0);
		box.setJewel(0);
	}
	void move(Agent agent,boolean horizontal,boolean front) {//créer une exeption pour les cas inferieur a 0 ou supérieur a 10
		if( horizontal){//look whether you move vertically or horizontally
			if(front) {//look whether you advance or you go backward in the grid 
				agent.setPositionj(agent.getPositionj()+1);
			}
			else {//look whether you advance or you go backward in the grid 
				agent.setPositionj(agent.getPositionj()-1);
			}
		}
		else {//look whether you move vertically or horizontally
			if(front) {//look whether you advance or you go backward in the grid 
				agent.setPositioni(agent.getPositioni()+1);
			}
			else {//look whether you advance or you go backward in the grid 
				agent.setPositioni(agent.getPositioni()-1);
			}
		}
	}

}
