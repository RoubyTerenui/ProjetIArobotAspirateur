package environnement;

import java.util.Random;
import java.lang.Math;

public class Box {

    //Attributes
    private int jewel, dirt;
    private int positionI,positionJ;
    //Construct
    public Box(int j, int d,int positionI,int positionJ)
    {
        jewel = j;
        dirt = d;
        this.setPositionJ(positionJ);
        this.setPositionI(positionI);
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

    public int getPositionJ() {
		return positionJ;
	}

	public void setPositionJ(int positionJ) {
		this.positionJ = positionJ;
	}

	public int getPositionI() {
		return positionI;
	}

	public void setPositionI(int positionI) {
		this.positionI = positionI;
	}

	//Method
    public void print()
    {
        System.out.println("jewel: " + jewel + " dirt: " + dirt);
    }

    public int generateJewel(){
        double proba = Math.random() * 1000;
        if(proba < 50) {
            jewel = 1;
            return jewel;
        }
        return jewel;
    }

    public int generateDirt(){
        double proba = Math.random() * 1000;
        if(proba < 200) {
            dirt = 1;
            return dirt;
        }
        return dirt;
    }
}
