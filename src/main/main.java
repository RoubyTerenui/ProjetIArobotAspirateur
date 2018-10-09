package main;

import environnement.Box;
import environnement.Grid;
import environnement.UI;
import junit.framework.Test;
import agent.Agent;
import agent.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class main {

	public static void main(String[] args) {
		// Bloc de test de l'ui
		Agent test_Agent = new Agent(0, 0);
		Grid environment = new Grid(new Box[10][10]);
		UI Test_UI = new UI(test_Agent, environment);
		// environment Thread
		Thread thread = new Thread(environment);
		thread.start();
		// UI Thread
		Thread thread2 = new Thread(Test_UI);
		thread2.start();
		// main thread
		int count=0;
		double perf=0;
		double perf2=0;
		List<Double> performances=new ArrayList<Double>();
		List<Double> performancesReel=new ArrayList<Double>();
		while (test_Agent.getBdi().isIamAlive() && count<100) {
			perf=0;
			perf2=0;
			for(int j=0;j<500;j++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				test_Agent.observ(environment);
				test_Agent.createIntent(true, 5);//boolean at true if informed at false otherwise the int is only used for the other case
				test_Agent.executeIntent(environment,4);
				perf+=test_Agent.getMesureDePerformance()-environment.getMesureDePerformance();
				perf2+=environment.getMesureDePerformance();
			}
			performances.add(perf/500);
			performancesReel.add(perf2/500);
			count++;
		}
		count=performancesReel.indexOf((Collections.max(performancesReel)));
		while (test_Agent.getBdi().isIamAlive()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			test_Agent.observ(environment);
			test_Agent.createIntent(true, count);//boolean at true if informed at false otherwise the int is only used for the other case
			test_Agent.executeIntent(environment,4);
		}
	}
}
