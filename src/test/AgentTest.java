package test;

import org.junit.Test;

import agent.Agent;
import environnement.Box;
import environnement.Grid;

import static org.junit.Assert.*;

public class AgentTest {

	@Test
	public void test_Agent_act(){
	Grid envi= new Grid(new Box[10][10]);
	Agent ag=new Agent(0,0);
	envi.getBoxI(5, 5).setDirt(1);
	envi.getBoxI(5, 5).setJewel(1);
	envi.getBoxI(0, 0).setDirt(1);
	envi.getBoxI(9, 9).setJewel(1);
	envi.getBoxI(9, 9).setDirt(1);
	ag.setBelief(envi);
	
	Box res=new Box(0, 0, 0, 0);
	res=ag.act("aspire", envi);
	assertEquals(envi.getBoxI(0, 0), ag.getBelief().getBoxI(0, 0));
	assertEquals(ag.getMesureDePerformance(),49);
	assertEquals(ag.getElectricityUsed(), 1);
	assertEquals(res.getPositionI(),0);
	assertEquals(res.getPositionJ(),0);
	assertEquals(envi.getBoxI(0, 0).getDirt(),0);
	assertEquals(envi.getBoxI(0, 0).getJewel(),0);
	
	Box res2=new Box(0, 0, 0, 0);
	res2=ag.act("right", envi);
	assertEquals(ag.getMesureDePerformance(),48);
	assertEquals(ag.getElectricityUsed(), 2);
	assertEquals(res2.getPositionI(),0);
	assertEquals(res2.getPositionJ(),1);
	assertEquals(ag.getPositioni(),0);
	assertEquals(ag.getPositionj(),1);
	assertEquals(envi.getBoxI(0, 1).getDirt(),0);
	assertEquals(envi.getBoxI(0, 1).getJewel(),0);
	
	ag.setPositioni(5);
	ag.setPositionj(5);
	Box res3=new Box(0, 0, 0, 0);
	res3=ag.act("aspire", envi);
	System.out.println(ag.getMesureDePerformance());
	assertEquals(envi.getBoxI(5,5), ag.getBelief().getBoxI(5, 5));
	assertEquals(ag.getMesureDePerformance(),77);
	assertEquals(ag.getElectricityUsed(), 3);
	assertEquals(res3.getPositionI(),5);
	assertEquals(res3.getPositionJ(),5);
	assertEquals(ag.getPositioni(),5);
	assertEquals(ag.getPositionj(),5);
	assertEquals(envi.getBoxI(5, 5).getDirt(),0);
	assertEquals(envi.getBoxI(5, 5).getJewel(),0);
	
	ag.setPositioni(9);
	ag.setPositionj(9);
	Box res4=new Box(0, 0, 0, 0);
	res4=ag.act("grab", envi);
	System.out.println(ag.getMesureDePerformance());
	assertEquals(envi.getBoxI(9,9), ag.getBelief().getBoxI(9, 9));
	assertEquals(ag.getMesureDePerformance(),86);
	assertEquals(ag.getElectricityUsed(), 4);
	assertEquals(envi.getBoxI(9, 9).getDirt(),1);
	assertEquals(envi.getBoxI(9, 9).getJewel(),0);

	}
	
	@Test
	public void Test_observ(){
		Grid envi= new Grid(new Box[10][10]);
		Agent ag=new Agent(0,0);
		envi.getBoxI(5, 5).setDirt(1);
		envi.getBoxI(5, 5).setJewel(1);
		ag.observ(envi);
		envi.getBoxI(0, 0).setDirt(1);
		assertNotEquals(envi.getBoxI(0, 0).getDirt(), ag.getBelief().getBoxI(0, 0).getDirt());
		ag.observ(envi);
		assertEquals(envi.getBoxI(0, 0).getDirt(), ag.getBelief().getBoxI(0, 0).getDirt());
		assertEquals(envi.getBoxI(0, 0).getJewel(), ag.getBelief().getBoxI(0, 0).getJewel());
	}
}