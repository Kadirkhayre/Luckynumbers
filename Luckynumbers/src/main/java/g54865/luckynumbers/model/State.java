package g54865.luckynumbers.model;

/**
 * A turn is composed in different states. The different states : NOT_STARTED,
 * PICK_TILE, PLACE_TILE, PLACE_OR_DROP_TILE, TURN_END, GAME_OVER
 *
 * @author Kadir Khayre (54865) <54865@etu.he2b.be>
 */
public enum State {

    /**
     * Game is not started
     */
    NOT_STARTED,
    
    /**
     * Player takes a tile
     */
    PICK_TILE,
    
    /**
     * Player puts a tile
     */
    PLACE_TILE,
    
    /**
     * Player choose if he puts or drop
     */
    PLACE_OR_DROP_TILE,
    
    /**
     * Round is completed, next player starts a round
     */
    TURN_END,
    
    /**
     * Game is over, there is a winner !
     */
    GAME_OVER;

}
