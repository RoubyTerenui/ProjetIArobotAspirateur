package agent;

import environnement.Box;

public class Effectors {//Class regrouping all the action the agent can do and the hardware that allows it
	void saisir(Box box,String string){
		if(string=="saisir") {
			box.setJewel(0);
		}
	}
	void aspirer(Box box,String string) {
		if(string=="aspirer")
		{
			box.setDirt(0);
			box.setJewel(0);
		}
	}
	void move(Agent agent,boolean horizontal,boolean front,String string) throws IndexOutOfBoundsException{
		if(string=="move") 
		{
	
			try{
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
			catch(IndexOutOfBoundsException e){
				e.printStackTrace();
				
			}
		}
	}
}
