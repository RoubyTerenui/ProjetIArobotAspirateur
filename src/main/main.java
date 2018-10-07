package main;

import environnement.Box;
import environnement.Grid;
import environnement.UI;
import junit.framework.Test;
import agent.Agent;
import agent.Node;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class main {

	public static void main(String[] args) throws InterruptedException {
		// Bloc de test de l'ui
		Agent Test_Agent = new Agent(0, 0);
		Grid environment = new Grid(new Box[10][10]);
		environment.generateEnvironment();
		UI Test_UI = new UI(Test_Agent, environment);
		// environment Thread
		Thread thread = new Thread(environment);
		thread.start();
		// UI Thread
		Thread thread2 = new Thread(Test_UI);
		thread2.start();
		// main thread
		while (Test_Agent.getBdi().isIamAlive()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Test_Agent.observ(environment);
			Test_Agent.createIntent(environment, 20);
			Test_Agent.executeIntent(environment);
		}
	}
}
