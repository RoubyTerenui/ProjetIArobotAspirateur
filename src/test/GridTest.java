package test;

import environnement.Box;
import environnement.Grid;
import org.junit.Test;

import static org.junit.Assert.*;

public class GridTest {

    private Grid testGrid = new Grid(new Box[10][10]);
    private int jewel = 0;
    private int dirt = 0;

    @Test
    public void TestGenerateEnvironment() {
        for(int i=0; i<1000; i++){
            testGrid.generateEnvironment();
        }
        for(int i=0; i<10; i++){
            for(int j =0; j<10; j++){
                if(testGrid.getBoxI(i,j).getDirt() == 1){
                    dirt += 1;
                }
                if(testGrid.getBoxI(i,j).getJewel() == 1){
                    jewel += 1;
                }
            }
        }
        assertTrue(dirt != 0);
        assertTrue(jewel != 0);
    }
}