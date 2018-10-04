package test;

import static org.junit.Assert.*;

import org.junit.Test;

import agent.Agent;
import environnement.Box;
import environnement.Grid;

public class Test_Sensors {

	@Test
	public void test() {
//		Grid environment1=new Grid(new Box[10][10]);
//		environment1.getBoxI(5, 2).setDirt(1);
//		Agent test_Agent=new Agent(0,0);
//		Grid res=test_Agent.getSensors().analyzeEnvironment(environment1);
//		test_Agent.observ(res);
//		assertEquals(test_Agent.getBelief().getBoxI(5, 2).getDirt(),environment1.getBoxI(5, 2).getDirt());
//		System.out.println(test_Agent.getBelief().getBoxI(1, 0).getJewel());
//		environment1.getBoxI(1, 0).setJewel(1);
//		System.out.println(test_Agent.getBelief().getBoxI(1, 0).getJewel());
		Grid environment1=new Grid(new Box[10][10]);
		assertNotEquals(test_Agent.getBelief().getBoxI(1, 0).getJewel(),environment1.getBoxI(1, 0).getJewel());		
	}
}
