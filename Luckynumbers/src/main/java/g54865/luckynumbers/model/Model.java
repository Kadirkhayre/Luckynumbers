package g54865.luckynumbers.model;

import java.util.List;

/**
 * Interface for the Game model.
 *
 * @author MCD {@literal <mcodutti@he2b.be>}
 */
public interface Model {

    /**
     * Initialize a game.
     * <ul>
     * <li>An empty board is created for each player</li>
     * <li>Player number 0 starts the game</li>
     * <li>State becomes PICK_TILE</li>
     * </ul>
     *
     * @param playerCount the number of players
     * @throws IllegalArgumentException if the number of players is not between
     * 2 and 4 (both included).
     * @throws IllegalStateException if called when state is not NOT_STARTED nor
     * GAME_OVER.
     */
    void start(int playerCount);
    
    /**
     * Picks and puts random tiles on the diagonal of the board of each player.
     */
    void pickAndPlaceRandomTilesDiagonal();

    /**
     * Give the size of the boards. We suppose that all boards are squares and
     * of the same size. So this is both number of lines and number of columns.
     * With the official rules, this should be 4 but this must not be assumed
     * and this methode must be used instead of hardcoding that value elsewhere
     * in the code.
     *
     * @return the size of the board.
     */
    int getBoardSize();

    /**
     * Give a random face down tile. State becomes PLACE_OR_DROP_TILE.
     * 
     * @return a face down tile.
     * @throws IllegalStateException if called when state is not PICK_TILE.
     */
    Tile pickFaceDownTile();

    /**
     * Give the given face up tile. State becomes PLACE_TILE.
     * 
     * @param tile the tile to place.
     * @throws IllegalArgumentException if tile is not face up.
     * @throws IllegalStateException if called when state is not PICK_TILE.
     */
    void pickFaceUpTile(Tile tile);

    /**
     * Put a tile at the given position. Put the previously picked tile of the
     * current player at the given position on its board. State becomes
     * TURN_END.
     *
     * @param pos where to put the tile.
     * @throws IllegalArgumentException if the tile can't be put on that
     * position (position outside of the board or position not allowed by the
     * rules)
     * @throws IllegalStateException if called when state is not PUT_TILE
     */
    void putTile(Position pos);

    /**
     * Replace the previously chosen tile in the deck, face up.
     * State becomes TURN_END.
     * 
     * @throws IllegalStateException if called when state is not PLACE_OR_DROP_TILE.
     */
    void dropTile();

    /**
     * Give the number of tiles face down.
     * 
     * @return the number of tiles face down.
     * @throws IllegalArgumentException if state is NOT_STARTED.
     */
    int faceDownTileCount();

    /**
     * Give the number of face up tiles
     * 
     * @return the number of face up tiles
     * @throws IllegalArgumentException if state is NOT_STARTED
     */
    int faceUpTileCount();

    /**
     * Change current player. The next player becomes the current one. The order
     * is : 0, 1, 2, 3 and again 0, 1, ... State becomes PICK_TILE
     *
     * @throws IllegalStateException if called when state is not TURN_END
     */
    void nextPlayer();

    /**
     * Give the list of tiles face up 
     * 
     * @return  the list of tiles face up 
     * @throws IllegalStateException if called when state is not PICK_TILE.
     */
    List<Tile> getAllFaceUpTiles();

    /**
     * Give the total number of players in this game.
     *
     * @return the total number of players in this game.
     * @throws IllegalArgumentException if state is NOT_STARTED
     */
    int getPlayerCount();

    /**
     * Get the current state of the game.
     *
     * @return the current state of the game.
     */
    State getState();

    /**
     * Give the number of the current player. Players are numeroted from 0 to
     * (count-1).
     *
     * @return the number of the current player.
     * @throws IllegalStateException if state is NOT_STARTED or GAME_OVER
     */
    int getCurrentPlayerNumber();

    /**
     * Get the current picked tile. Get the tile picked by the current player.
     *
     * @return the current picked tile.
     * @throws IllegalStateException if state is not PLACE_TILE
     */
    Tile getPickedTile();

    /**
     * Check if a position is inside the board of the current player or not.
     *
     * @param pos a position
     * @return true if the given position is inside the board.
     */
    boolean isInside(Position pos);

    /**
     * Check if a tile can be put at the given position. Check if the current
     * player is allowed to put its previously picked tile at the given position
     * on the board of the current player.
     *
     * @param pos the position to check
     * @return true if the picked tile can be put at that position.
     * @throws IllegalArgumentException if the position is outside the board.
     * @throws IllegalStateException if state is not PLACE_TILE.
     */
    boolean canTileBePut(Position pos);

    /**
     * Give a tile at a given position of the board of a given player.
     *
     * @param playerNumber the player number
     * @param pos a position on the board
     * @return the tile at that position for that player or <code>null</code> if
     * there is no tile there.
     * @throws IllegalArgumentException if the position is outside the board or
     * if the playerNUmber is ouside of range.
     * @throws IllegalStateException if game state is NOT_STARTED
     */
    Tile getTile(int playerNumber, Position pos);

    /**
     * Give the winners.
     *
     * @return the number of the winners.
     * @throws IllegalStateException if game state is not GAME_OVER
     */
    List <Integer> getWinners();

}
