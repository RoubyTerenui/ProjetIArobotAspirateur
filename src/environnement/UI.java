package environnement;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import agent.Agent;

public class UI extends JFrame implements Runnable{

    Border Line = BorderFactory.createLineBorder(Color.black,3);
    public Agent Test_Agent;
    JFrame Terrain ;
    JPanel GrillePan = new JPanel (new GridLayout(10,10));
    public void run(){

        while(true) {
            Test_Agent.setPositioni(Test_Agent.getPositioni() + 1);
            this.updateUI(Test_Agent);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public UI(Agent Test_Agent) {
        Terrain=new JFrame();
        GrillePan = new JPanel (new GridLayout(10,10));
        for(int i = 0; i<100;i++){
            JPanel Content = new JPanel();
            Content.setBorder(Line);
            GrillePan.add(Content);
        }
        this.Test_Agent=Test_Agent;
        GrillePan.setBorder(Line);
        Terrain.add(GrillePan);
        Terrain.setVisible(true);
        Terrain.setExtendedState(Terrain.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    public void updateUI(Agent a) {
//        JPanel GrillePan = new JPanel (new GridLayout(10,10));
        System.out.println((a.getPositioni() + (a.getPositionj()*10)));
        for(int i = 0; i<100;i++){
//            JPanel Content = new JPanel();
            GrillePan.getComponent(i).setBackground(Color.white);
            if (a.getPositioni() + (a.getPositionj()*10)==i) {
                System.out.println((a.getPositioni() + (a.getPositionj()*10)));
                GrillePan.getComponent(i).setBackground(Color.blue);
            }
//            GrillePan.add(Content);
        }
        this.Terrain.setContentPane(GrillePan);
    }

}
