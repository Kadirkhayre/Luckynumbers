/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g54865.evaluation.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Kadir
 */
public class FieldTest {
    
    /**
     * Test of isInside method, of class Field.
     */
    @Test
    public void testIsInside() {       
        Field instance = new Field();
        assertTrue(instance.isInside(new Position (3,3)));
        assertTrue(instance.isInside(new Position (3,1)));
        assertFalse(instance.isInside(new Position (100,10)));
        assertFalse(instance.isInside(new Position (-21,-9)));
    }

    /**
     * Test of isSowable method, of class Field.
     */
    @Test
    public void testIsSowable() {
        // A terminer
        Field instance = new Field();
        Position position = new Position (1,1);
        assertTrue(instance.isSowable(position));
        instance.sow(position, new Grass(PlantColor.BLUE));
        assertFalse(instance.isSowable(position));
    }
    
    // A terminer

//    /**
//     * Test of sow method, of class Field.
//     */
//    @Test
//    public void testSow() {
//        System.out.println("sow");
//        Position position = null;
//        Plant plant = null;
//        Field instance = null;
//        instance.sow(position, plant);
//    }
    
}
