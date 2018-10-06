package test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import agent.Agent;
import agent.Node;
import environnement.Box;
import environnement.Grid;

public class NodeTest {
	@Test
	public void test() {
	Node nod1=new Node(new Node(new Box(0,0,1,0)),new Box(0,0,0,0),"left",1,1);
	Node nod2=new Node(new Node(new Box(0,0,0,0)),new Box(0,0,1,0),"right",1,0);
	Node nod3=new Node(new Node(new Box(0,0,0,1)),new Box(0,0,0,0),"up",1,1);
	Node nod4=new Node(new Node(new Box(0,0,0,0)),new Box(0,0,0,1),"down",1,1);
	
	assertFalse(nod1.testGoal());
	assertFalse(nod2.testGoal());
	assertFalse(nod3.testGoal());
	assertFalse(nod4.testGoal());
	
	Node nod5=new Node(new Node(new Box(1,0,0,0)),new Box(0,0,0,0),"grab",1,1);
	Node nod6=new Node(new Node(new Box(1,1,0,0)),new Box(0,1,0,0),"grab",1,1);
	Node nod7=new Node(new Node(new Box(1,0,0,0)),new Box(0,0,0,0),"aspire",1,1);
	Node nod8=new Node(new Node(new Box(1,1,0,0)),new Box(0,0,0,0),"aspire",1,1);
	Node nod9=new Node(new Node(new Box(1,1,0,0)),new Box(1,1,0,0),"ne rien faire",1,1);
	
	assertFalse(nod5.testGoal());
	assertFalse(nod6.testGoal());
	assertTrue(nod7.testGoal());
	assertTrue(nod8.testGoal());
	assertFalse(nod9.testGoal());
	
	Node nod10=new Node(new Node(new Box(1,1,0,0)),new Box(1,1,0,0),"",1,1);
	Node nod11=new Node(new Node(new Box(1,1,0,0)),new Box(1,1,0,0),"ne rien faire",1,1);
	assertEquals(nod10.costAction(""),0);
	assertEquals(nod11.costAction("ne rien faire"),0);
	
	nod11.setActualState(new Box(0,0,0,0));
	List<String> actions = new ArrayList<String>();
	actions.add("ne rien faire");
	actions.add("right");
	actions.add("down");
	assertEquals(actions.size(),nod11.successor_Node().size());
	for (int j=0; j<actions.size(); j++) {
		assertEquals(actions.get(j),nod11.successor_Node().get(j));
	}
	
	nod11.setActualState(new Box(0,0,5,5));
	List<String> actions2 = new ArrayList<String>();
	actions2.add("ne rien faire");
	actions2.add("right");
	actions2.add("left");
	actions2.add("down");
	actions2.add("up");
	assertEquals(actions2.size(),nod11.successor_Node().size());
	for (int j=0; j<actions2.size(); j++) {
		assertEquals(actions2.get(j),nod11.successor_Node().get(j));
	}
	
	nod11.setActualState(new Box(0,0,1,0));
	List<String> actions3 = new ArrayList<String>();
	actions3.add("ne rien faire");
	actions3.add("right");
	actions3.add("down");
	actions3.add("up");
	assertEquals(actions3.size(),nod11.successor_Node().size());
	for (int j=0; j<actions3.size(); j++) {
		assertEquals(actions3.get(j),nod11.successor_Node().get(j));
	}

	nod11.setActualState(new Box(0,0,0,1));
	List<String> actions4 = new ArrayList<String>();
	actions4.add("ne rien faire");
	actions4.add("right");
	actions4.add("left");
	actions4.add("down");
	assertEquals(actions4.size(),nod11.successor_Node().size());
	for (int j=0; j<actions4.size(); j++) {
		assertEquals(actions4.get(j),nod11.successor_Node().get(j));
	}
	
	nod11.setActualState(new Box(0,0,9,9));
	List<String> actions5 = new ArrayList<String>();
	actions5.add("ne rien faire");
	actions5.add("left");
	actions5.add("up");
	assertEquals(actions5.size(),nod11.successor_Node().size());
	for (int j=0; j<actions5.size(); j++) {
		assertEquals(actions5.get(j),nod11.successor_Node().get(j));
	}
	
	nod11.setActualState(new Box(0,0,9,0));
	List<String> actions6 = new ArrayList<String>();
	actions6.add("ne rien faire");
	actions6.add("right");
	actions6.add("up");
	for (int j=0; j<actions6.size(); j++) {
		assertEquals(actions6.get(j),nod11.successor_Node().get(j));
	}
	
	nod11.setActualState(new Box(0,0,9,9));
	List<String> actions7 = new ArrayList<String>();
	actions7.add("ne rien faire");
	actions7.add("left");
	actions7.add("up");
	assertEquals(actions7.size(),nod11.successor_Node().size());
	for (int j=0; j<actions7.size(); j++) {
		assertEquals(actions7.get(j),nod11.successor_Node().get(j));
	}
	
	Grid environment=new Grid(new Box[10][10]);
	environment.getBoxI(0, 1).setDirt(1);
	environment.getBoxI(1, 0).setJewel(1);
	Agent agent=new Agent(1,0);
	List<Node> succ1=nod1.expand(environment);
	System.out.println(nod1);
	for (Node node : succ1) {
		System.out.println(node.getAction());
		System.out.println(node.getCost());
		System.out.println(node.getDepth());
		System.out.println(node.getActualState());
		System.out.println(node.getClass());
		System.out.println(node.getParent());
	}
	
	}
	
	

	
	
	
}
