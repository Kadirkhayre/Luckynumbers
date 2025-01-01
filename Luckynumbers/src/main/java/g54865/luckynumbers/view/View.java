package g54865.luckynumbers.view;

import g54865.luckynumbers.model.Position;
import g54865.luckynumbers.model.Tile;

/**
 * Interface for MyView
 * 
 * @author Kadir Khayre (54865) <54865@etu.he2b.be>
 */
public interface View {
    /**
     * Displays the game name, author and version of the game
     */
    void displayWelcome();
    
    /**
     * Displays the current player and his board as well as the tile he must place
     */
    void displayGame();
    
    /**
     * Displays the picked tile
     */
    void displayPickedTile();
    
    /**
     * Displays the number of the winner
     */
    void displayWinner();
    
    /**
     * Asks for the number of participants (from 2 to 4)
     * 
     * @return the number of participants
     */
    int askPlayerCount();
    
    /**
     * Asks for a row number and a column number to form a position
     * 
     * @return the position formed from the given row and the given column
     */
    Position askPosition();
    
    /**
     * Asks if the player wants to take a face down tile or a face up tile
     * 
     * @return The user choice
     */
    String askFaceUpOrFaceDown();
    
    /**
     * Asks the index of the tile and returns this tile
     * 
     * @return the tile from the given index
     */
    Tile askTile();
    
    /**
     * Asks the user if he wants to put the tile or place
     * 
     * @return The user choice
     */
    String askDropOrPut();
    
    /**
     * Displays an error message
     * 
     * @param message the given error message
     */
    void displayError(String message);
    
}
