package g54865.luckynumbers.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * All tiles available in the center of the table
 * 
 * @author Kadir Khayre (54865) <54865@etu.he2b.be>
 */
public class Deck {

    private final List<Tile> faceUpTiles;
    private final LinkedList<Tile> faceDownTiles;
    
    private static final int VALUES_TILES = 20;

    /**
     * Constructor of Deck
     *
     * @param playerCount the given playerCount
     */
    public Deck(int playerCount) {
        faceUpTiles = new LinkedList<>();
        faceDownTiles = new LinkedList<>();

        for (int i = 1; i <= VALUES_TILES; i++) {
            for (int j = 0; j < playerCount; j++) {
                faceDownTiles.add(new Tile(i));
            }
        }
        Collections.shuffle(faceDownTiles);
    }

    /**
     * Gives the number of cards face down in the deck
     *
     * @return the number of cards face down
     */
    public int faceDownCount() {
        return faceDownTiles.size();
    }

    /**
     * Removes a tile face down and returns it
     *
     * @return a tile face down
     */
    public Tile pickFaceDown() {
        Tile tile = faceDownTiles.pop();
        tile.flipFaceUp();
        return tile;
    }

    /**
     * Gives the number of cards face up in the deck
     *
     * @return the number of cards face up
     */
    public int faceUpCount() {
        return faceUpTiles.size();
    }

    /**
     * Gives the list of visible tiles
     *
     * @return the list of visible tiles
     */
    public List<Tile> getAllFaceUp() {
        return faceUpTiles;
    }

    /**
     * Checks if the parameter tile exists and is visible in the deck
     *
     * @param tile the given tile
     * @return true if the tile exists and is visible in the deck, otherwise
     * false
     */
    public boolean hasFaceUp(Tile tile) {
        return faceUpTiles.contains(tile);
    }

    /**
     * Removes the picked tile from the deck
     *
     * @param tile the given tile
     */
    public void pickFaceUp(Tile tile) {
        faceUpTiles.remove(tile);
    }

    /**
     * Replaces the given tile in the deck, visible side
     *
     * @param tile the given tile
     */
    public void putBack(Tile tile) {
        faceUpTiles.add(tile);
    }

    /**
     * Gives the list of invisible tiles
     * 
     * @return the list of invisible tiles
     */
    List<Tile> getAllFaceDown() {
        return faceDownTiles;
    }

}
