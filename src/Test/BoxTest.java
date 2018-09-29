package Test;

import environnement.Box;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoxTest {

    @Test
    public void TestGenerateJewel(){
        Box box = new Box(0,0);
        if (box.generateJewel() != 0){
            assertEquals(1,box.generateJewel());
        }
        else assertEquals(0,box.generateJewel());
    }

    @Test
    public void TestGenerateDirt(){
        Box box = new Box(0,0);
        if (box.generateDirt() != 0){
            assertEquals(1,box.generateDirt());
        }
        else assertEquals(0,box.generateDirt());
    }
}