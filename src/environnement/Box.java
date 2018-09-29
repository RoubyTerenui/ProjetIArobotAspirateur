package environnement;

import java.util.Random;
import java.lang.Math;

public class Box {

    //Attributes
    private int jewel, dirt;

    //Construct
    public Box(int j, int d)
    {
        jewel = j;
        dirt = d;
    }

    //Getter and Setter
    public int getJewel()
    {
        return jewel;
    }

    public void setJewel(int j)
    {
        this.jewel = j;
    }

    public int getDirt()
    {
        return dirt;
    }

    public void setDirt(int d)
    {
        this.dirt = d;
    }

    //Method
    public void print()
    {
        System.out.println("jewel: " + jewel + " dirt: " + dirt);
    }

    public int generateJewel(){
        double proba = Math.random() * 1000;
        if(proba < 50) {
            return 1;
        }
        else return 0;
    }

    public int generateDirt(){
        double proba = Math.random() * 1000;
        if(proba < 200) {
            return 1;
        }
        else return 0;
    }
}
