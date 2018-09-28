package agent;

import environnement.Grid;

public class Sensors {//Class that regroup all the sensors of the agent and the function that allows him to receive a feedback from the environment
	void analyzeEnvironment(Grid environment,Agent agent) {
		agent.setBelief(environment);
	}
}
