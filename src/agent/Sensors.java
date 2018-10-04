package agent;

import environnement.Grid;

public class Sensors {//Class that regroup all the sensors of the agent and the function that allows him to receive a feedback from the environment
	public Sensors() {
		super();
	}
	public void analyzeEnvironment(Grid environment,Agent agent) {
		Grid res=environment.clone();
		agent.setBelief(res);
	}
}
