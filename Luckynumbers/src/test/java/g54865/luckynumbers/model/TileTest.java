package g54865.luckynumbers.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Kadir Khayre (54865) <54865@etu.he2b.be>
 */
public class TileTest {

    /**
     * Test of flipFaceUp method, of class Tile.
     */
    @Test
    public void testFlipFaceUp_tile_not_visible() {
        Tile instance = new Tile(5);
        instance.flipFaceUp();
        assertTrue(instance.isFaceUp());

    }

    @Test
    public void testFlipFaceUp_tile_visible() {
        Tile instance = new Tile(10);;
        instance.flipFaceUp();
        instance.flipFaceUp(); // Tile is already visible
        assertTrue(instance.isFaceUp());
    }
   
}
