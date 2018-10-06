package environnement;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import agent.Agent;

public class UI extends JFrame implements Runnable {

	// Fields
	private Border Line = BorderFactory.createLineBorder(Color.black, 3);
	private Agent Test_Agent;
	private JFrame Terrain;
	private Grid environment;
	private JPanel GrillePan = new JPanel(new GridLayout(10, 10));
	int h = 0;

	// Process
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			h++;
			this.updateUI();
		}
	}

	// Constructor
	public UI(Agent Test_Agent, Grid environment) {
		Terrain = new JFrame();
		GrillePan = new JPanel(new GridLayout(10, 10));
		for (int i = 0; i < 100; i++) {
			JPanel Content = new JPanel();
			Content.setBorder(Line);
			GrillePan.add(Content);
		}
		this.environment = environment;
		this.Test_Agent = Test_Agent;
		GrillePan.setBorder(Line);
		Terrain.add(GrillePan);
		Terrain.setVisible(true);
		Terrain.setExtendedState(Terrain.getExtendedState() | JFrame.MAXIMIZED_BOTH);
	}

	// Getter and setter
	public Grid getEnvironment() {
		return environment;
	}

	public void setEnvironment(Grid environment) {
		this.environment = environment;
	}

	public Agent getTest_Agent() {
		return Test_Agent;
	}

	public void setTest_Agent(Agent test_Agent) {
		Test_Agent = test_Agent;
	}

	// Other Methods
	public void updateUI() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				GrillePan.getComponent(i * 10 + j).setBackground(Color.white);
				if (Test_Agent.getPositioni() * 10 + (Test_Agent.getPositionj()) == i * 10 + j
						&& Test_Agent.getPositioni() < 10 && Test_Agent.getPositionj() < 10) {
					GrillePan.getComponent(i * 10 + j).setBackground(Color.black);
				}
				if ((this.getEnvironment().getBoxI(i, j).getDirt() == 1)
						&& (this.getEnvironment().getBoxI(i, j).getJewel() == 1)) {
					GrillePan.getComponent(i * 10 + j).setBackground(Color.yellow);
				} else if (this.getEnvironment().getBoxI(i, j).getDirt() == 1) {
					GrillePan.getComponent(i * 10 + j).setBackground(Color.green);// TO DO dessiner des objet
				} else if (this.getEnvironment().getBoxI(i, j).getJewel() == 1) {
					GrillePan.getComponent(i * 10 + j).setBackground(Color.red);// TO DO dessiner des objet
				}
			}
		}
		this.Terrain.setContentPane(GrillePan);
	}

}
