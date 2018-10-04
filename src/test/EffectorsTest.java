package test;

import static org.junit.Assert.*;

import org.junit.Test;

import agent.Agent;
import agent.Effectors;
import environnement.Box;

public class EffectorsTest {

	@Test
	public void test() {
		Effectors effectors = new Effectors();
		Box testGrab1 = new Box(1, 0, 0, 0);
		Box testGrab2 = new Box(1, 1, 0, 0);
		Box testGrab3 = new Box(0, 0, 0, 0);
		Box testGrab5 = new Box(0, 1, 0, 0);
		// Test Jewels
		effectors.grab(testGrab1);
		assertEquals(testGrab1.getDirt(), testGrab3.getDirt());
		assertEquals(testGrab1.getJewel(), testGrab3.getJewel());
		effectors.grab(testGrab2);
		assertEquals(testGrab2.getJewel(), testGrab3.getJewel());
		assertNotEquals(testGrab2.getDirt(), testGrab3.getDirt());
		effectors.grab(testGrab3);
		assertEquals(testGrab2.getJewel(), testGrab5.getJewel());
		assertNotEquals(testGrab2.getDirt(), testGrab3.getDirt());
		// Test Dirt
		effectors.aspire(testGrab1);
		assertEquals(testGrab1.getDirt(), testGrab3.getDirt());
		assertEquals(testGrab1.getJewel(), testGrab3.getJewel());
		effectors.aspire(testGrab2);
		testGrab5.setJewel(1);
		assertNotEquals(testGrab2.getJewel(), testGrab5.getJewel());
		assertNotEquals(testGrab2.getDirt(), testGrab5.getDirt());
		// Test Both
		effectors.aspire(testGrab5);
		effectors.grab(testGrab5);
		assertEquals(testGrab5.getDirt(), testGrab3.getJewel());
		assertEquals(testGrab5.getJewel(), testGrab3.getDirt());
		// Test move
		Agent topLeftS = new Agent(0, 0);
		Agent downRightS = new Agent(99, 99);
		String r = "right";
		String l = "left";
		String d = "down";
		String u = "up";
		effectors.move(topLeftS, l);
		assertEquals(topLeftS.getPositionj(), 0);
		effectors.move(downRightS, r);
		assertEquals(downRightS.getPositionj(), 99);
		effectors.move(topLeftS, u);
		assertEquals(topLeftS.getPositioni(), 0);
		effectors.move(downRightS, d);
		assertEquals(downRightS.getPositioni(), 99);
		effectors.move(downRightS, l);
		assertEquals(downRightS.getPositionj(), 98);
		effectors.move(topLeftS, d);
		assertEquals(topLeftS.getPositioni(), 1);
		effectors.move(downRightS, u);
		assertEquals(downRightS.getPositioni(), 98);
		effectors.move(topLeftS, r);
		assertEquals(topLeftS.getPositionj(), 1);


	}
}
