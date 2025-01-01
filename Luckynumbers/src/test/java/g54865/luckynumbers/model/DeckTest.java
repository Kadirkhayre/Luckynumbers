package g54865.luckynumbers.model;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Kadir Khayre (54865) <54865@etu.he2b.be>
 */
public class DeckTest {

    /**
     * Test of faceDownCount method, of class Deck.
     */
    @Test
    public void testFaceDownCount_number_of_tiles_according_to_the_number_player() {
        Deck instance = new Deck(2);
        int expResult = 40;
        int result = instance.faceDownCount();
        assertEquals(expResult, result);
    }
    
    
    /**
     * Test of pickFaceDown method, of class Deck.
     */
    @Test
    public void testPickFaceDown_pick_and_drop_tile() {
        Deck instance = new Deck(2);
        Tile expResult = instance.getAllFaceDown().get(0);
        Tile result = instance.pickFaceDown();
        assertEquals(expResult, result);
    }

    /**
     * Test of faceUpCount method, of class Deck.
     */
    @Test
    public void testFaceUpCount_number_of_tiles_face_up() {
        Deck instance = new Deck(2); 
        instance.putBack(instance.pickFaceDown());
        instance.putBack(instance.pickFaceDown());
        int expResult = 2;
        int result = instance.faceUpCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllFaceUp method, of class Deck.
     */
    @Test
    public void testGetAllFaceUp_compare_2_lists() {
        Deck instance = new Deck(2); 
        Tile tile1 = instance.pickFaceDown();
        Tile tile2 = instance.pickFaceDown();
        
        instance.putBack(tile1);
        instance.putBack(tile2);
        List<Tile> expResult = List.of(tile1,tile2);
        List<Tile> result = instance.getAllFaceUp();
        assertEquals(expResult, result);
    }

    /**
     * Test of hasFaceUp method, of class Deck.
     */
    @Test
    public void testHasFaceUp_tile_face_and_exist_in_deck() {
        Deck instance = new Deck(2);
        Tile tile = instance.pickFaceDown();
        assertFalse(instance.hasFaceUp(tile));
        instance.putBack(tile);
        assertTrue(instance.hasFaceUp(tile));      
        assertTrue(instance.getAllFaceUp().contains(tile));
    }
//
    /**
     * Test of pickFaceUp method, of class Deck.
     */
    @Test
    public void testPickFaceUp_tile_face_and_size_of_deck() {
        Deck instance = new Deck(2);
        Tile tile1 = instance.pickFaceDown();
        instance.putBack(tile1);
        Tile tile2 = instance.pickFaceDown();
        instance.putBack(tile2);
        instance.pickFaceUp(tile2);
        assertEquals(tile1, instance.getAllFaceUp().get(0));
        assertEquals(1, instance.getAllFaceUp().size());
    }

    /**
     * Test of putBack method, of class Deck.
     */
    @Test
    public void testPutBack_put_back_the_3_tiles() {
        Deck instance = new Deck(2);
        Tile tile1 = instance.pickFaceDown();
        instance.putBack(tile1);
        Tile tile2 = instance.pickFaceDown();
        instance.putBack(tile2);
        Tile tile3 = instance.pickFaceDown();
        instance.putBack(tile3);
        assertTrue(instance.getAllFaceUp().contains(tile1));
        assertTrue(instance.getAllFaceUp().contains(tile2));
        assertTrue(instance.getAllFaceUp().contains(tile3));
        assertEquals(3 ,instance.getAllFaceUp().size());
    }
 
}
