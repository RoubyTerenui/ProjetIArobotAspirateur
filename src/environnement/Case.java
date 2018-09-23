package environnement;

public class Case {

    private int jewel, dirt;

    Case(int j, int d){
        jewel = j;
        dirt = d;
    }

    public int getJewel() {
        return jewel;
    }

    public void setJewel(int j){
        this.jewel = j;
    }

    public int getDirt() {
        return dirt;
    }

    public void setDirt(int d){
        this.dirt = d;
    }

    public void print()
    {
        System.out.println("jewel: " + jewel + " dirt: " + dirt);
    }
}
