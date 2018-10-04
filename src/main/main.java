package main;

import environnement.Box;
import environnement.Grid;
import environnement.UI;
import agent.Agent;
import agent.Node;

import java.util.List;
import java.util.concurrent.TimeUnit;
public class main {

	public static void main(String[] args) {
		//Bloc de test de l'ui
		Agent Test_Agent = new Agent(0,0);
		Grid environment=new Grid(new Box[10][10]);
		environment.getBoxI(9,5).setDirt(1);
		environment.getBoxI(2,2).setDirt(1);
		environment.getBoxI(3,3).setJewel(1);
		environment.getBoxI(9,5).setJewel(1);
		UI Test_UI = new UI(Test_Agent,environment);
		int h=0;
		while(true)
		{	
			Test_UI.run();
			h++;
//			Test_Agent.setPositionj(Test_Agent.getPositionj()+1);
			Test_Agent.setPositioni(Test_Agent.getPositioni()+1);
			Test_UI.setTest_Agent(Test_Agent);	
		}		
	}
}




