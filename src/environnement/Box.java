package environnement;

import java.util.Random;

public class Box {

    //Attributes
    private int jewel, dirt;

    //Construct
    Box(int j, int d)
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
        Random ran = new Random();
        int x = ran.nextInt(0) + 1;
        return x;
    }

    public int generateDirt(){
        Random ran = new Random();
        int x = ran.nextInt(0) + 1;
        return x;
    }
}
