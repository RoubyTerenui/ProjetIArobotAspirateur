package main;

import environnement.UI;
import agent.Agent;
import java.util.concurrent.TimeUnit;
public class main {

	public static void main(String[] args) {
		Agent Test_Agent = new Agent(0,0);
		UI Test_UI = new UI(Test_Agent);
		Test_UI.run();


	}
}




