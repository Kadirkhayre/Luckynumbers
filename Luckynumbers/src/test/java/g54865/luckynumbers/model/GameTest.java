package g54865.luckynumbers.model;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author MCD <mcodutti@he2b.be>
 */
public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    /* =====================
         Tests for start()
       ===================== */

 /* --- test related to the state --- */
    @Test
    public void start_when_game_not_started_ok() {
        game.start(4);
    }

    @Test
    public void start_when_game_over_ok() {
        fullPlay();
        game.start(2);
    }

    /* Play a game till the end */
    private void fullPlay() {
        game.start(2);
        int value = 1;
        int line = 0;
        int col = 0;

        for (int turn = 1; turn < game.getBoardSize() * game.getBoardSize(); turn++) {
            for (int player = 0; player < game.getPlayerCount(); player++) {
                game.pickTile(value);
                game.putTile(new Position(line, col));
                game.nextPlayer();
            }
            value++;
            col++;
            if (col == game.getBoardSize()) {
                col = 0;
                line++;
            }
        }
        game.pickTile(20);
        game.putTile(new Position(line, col));
    }

    @Test
    public void start_when_game_in_progress_ISE() {
        game.start(4);
        assertThrows(IllegalStateException.class,
                () -> game.start(1));
    }

    @Test
    public void start_state_changed_to_PICK_TILE() {
        game.start(3);
        assertEquals(State.PICK_TILE, game.getState());
    }

    /* --- tests related to the parameter --- */
    @Test
    public void start_playerCount_too_small_Exception() {
        assertThrows(IllegalArgumentException.class,
                () -> game.start(1));
    }

    @Test
    public void start_playerCount_minimum_accepted() {
        game.start(2);
    }

    @Test
    public void start_playerCount_maximum_accepted() {
        game.start(4);
    }

    @Test
    public void start_playerCount_too_big_Exception() {
        assertThrows(IllegalArgumentException.class,
                () -> game.start(5));
    }

    /* -- tests related to fields initialization --- */
    @Test
    public void start_playerCount_initialized() {
        game.start(4);
        assertEquals(4, game.getPlayerCount());
    }

    @Test
    public void start_current_player_is_player_0() {
        game.start(4);
        assertEquals(0, game.getCurrentPlayerNumber());
    }

    /* === À vous de compléter... === */
    /**
     * Test of getBoardSize method, of class Game.
     */
    @Test
    public void testGetBoardSize_check_different_sizes() {
        game.start(2);

        game.setSizeBoard(3);
        assertEquals(3, game.getBoardSize());

        game.setSizeBoard(10);
        assertEquals(10, game.getBoardSize());

    }

    /**
     * Test of pickTile method, of class Game.
     */
    @Test
    public void testPicktile_check_state_pick_tile() {
        game.start(2);
        game.setState(State.GAME_OVER);
        assertThrows(IllegalStateException.class,
                () -> game.pickFaceDownTile());

        game.setState(State.NOT_STARTED);
        assertThrows(IllegalStateException.class,
                () -> game.pickFaceDownTile());

        game.setState(State.PICK_TILE);
        assertDoesNotThrow(() -> game.pickFaceDownTile());

    }

    @Test
    public void testPicktile_value_of_tile_between_1_and_20() {
        boolean isBetween1and20 = true;
        game.start(2);
        game.pickFaceDownTile();
        game.setState(State.PICK_TILE);

        for (int i = 0; i < game.faceDownTileCount() && isBetween1and20; i++) {
            game.setState(State.PLACE_OR_DROP_TILE);
            if (game.getPickedTile().getValue() < 1 || game.getPickedTile().getValue() > 20) {
                isBetween1and20 = false;

            }
            game.setState(State.PICK_TILE);
            game.pickFaceDownTile();
            game.setState(State.PICK_TILE);

        }
        assertTrue(isBetween1and20);
    }

    /**
     * Test of putTile method, of class Game.
     */
    @Test
    public void testPutTile_state_is_place_tile() {
        game.start(2);
        game.pickFaceDownTile();
        assertEquals(State.PLACE_OR_DROP_TILE, game.getState());
    }

    @Test
    public void testPutTile_tile_can_be_put_at_the_given_position() {
        game.start(2);
        game.pickFaceDownTile();

        assertDoesNotThrow(() -> game.putTile(new Position(1, 1)));
        game.setState(State.PLACE_TILE);
        assertEquals(game.getTile(0, new Position(1, 1)), game.getPickedTile()
        );
    }

    /**
     * Test of nextPlayer method, of class Game.
     */
    @Test
    public void testNextPlayer_currentPlayerNumber_is_0_at_first_turn() {
        game.start(2);
        assertEquals(0, game.getCurrentPlayerNumber());

    }

    @Test
    public void testNextPlayer_currentPlayerNumber_is_1_after_end_turn() {
        game.start(2);
        game.pickFaceDownTile();
        game.putTile(new Position(0, 0));
        game.nextPlayer();
        assertEquals(1, game.getCurrentPlayerNumber());

    }

    /**
     * Test of getState method, of class Game.
     */
    @Test
    public void testGetState_state_is_not_started_before_start() {
        assertEquals(State.NOT_STARTED, game.getState());

    }

    @Test
    public void testGetState_state_becomes_pick_tile_after_start() {
        game.start(2);
        assertEquals(State.PICK_TILE, game.getState());

    }

    @Test
    public void testGState_state_becomes_place_or_drop_tile_after_pick_tile() {
        game.start(2);
        game.pickFaceDownTile();
        assertEquals(State.PLACE_OR_DROP_TILE, game.getState());

    }

    /**
     * Test of isInside method, of class Game.
     */
    @Test
    public void testIsInside_position_is_inside() {
        game.start(2);
        assertTrue(game.isInside(new Position(3, 3)));
    }

    @Test
    public void testIsInside_position_is_not_inside() {
        game.start(2);
        assertFalse(game.isInside(new Position(23, -3)));
        assertFalse(game.isInside(new Position(-10, -89)));
    }

    /**
     * Test of pickFaceDownTile method, of class Game.
     */
    @Test
    public void testPickFaceDownTile_pick_tile_and_check_size_of_deck() {
        game.start(3);
        game.pickAndPlaceRandomTilesDiagonal();
        Tile tile = game.pickFaceDownTile();
        assertTrue(tile.isFaceUp());
        game.setState(State.PICK_TILE);
        assertEquals(47, game.faceDownTileCount());
        game.setState(State.PLACE_TILE);
        assertEquals(tile, game.getPickedTile());
    }

    /**
     * Test of pickFaceUpTile method, of class Game.
     */
    @Test
    public void testPickFaceUpTile_pick_tile_and_check_size_of_deck() {
        game.start(3);
        Tile tile = game.pickFaceDownTile();
        game.dropTile();
        game.nextPlayer();
        assertEquals(tile, game.getAllFaceUpTiles().get(0));
        assertEquals(1, game.getAllFaceUpTiles().size());
        game.pickFaceUpTile(tile);
        assertEquals(tile, game.getPickedTile());
        game.setState(State.PICK_TILE);
        assertEquals(0, game.getAllFaceUpTiles().size());

    }

    /**
     * Test of dropTile method, of class Game.
     */
    @Test
    public void testDropTile_pick_2_tiles_and_put_both_back() {
        game.start(3);
        Tile tile1 = game.pickFaceDownTile();
        game.dropTile();
        game.nextPlayer();
        Tile tile2 = game.pickFaceDownTile();
        game.dropTile();
        game.setState(State.PICK_TILE);
        assertEquals(2, game.getAllFaceUpTiles().size());
        assertEquals(tile1, game.getAllFaceUpTiles().get(0));
        assertEquals(tile2, game.getAllFaceUpTiles().get(1));
    }

    /**
     * Test of faceDownTileCount method, of class Game.
     */
    @Test
    public void testFaceDownTileCount_pick_1_tile_face_down() {
        game.start(3);
        game.pickAndPlaceRandomTilesDiagonal();
        assertEquals(48, game.faceDownTileCount());
        game.pickFaceDownTile();
        game.setState(State.PICK_TILE);
        assertEquals(47, game.faceDownTileCount());
    }

    /**
     * Test of faceUpTileCount method, of class Game.
     */
    @Test
    public void testFaceUpTileCount_pick_1_tile_face_down_and_put_back() {
        game.start(4);
        assertEquals(0, game.faceUpTileCount());
        game.pickFaceDownTile();
        game.dropTile();
        game.setState(State.PICK_TILE);
        assertEquals(1, game.faceUpTileCount());
    }

    /**
     * Test of getAllFaceUpTiles method, of class Game.
     */
    @Test
    public void testGetAllFaceUpTiles_the_2_picked_tiles_are_face_up() {
        game.start(2);
        Tile tile1 = game.pickFaceDownTile();
        game.dropTile();
        game.setState(State.PICK_TILE);
        Tile tile2 = game.pickFaceDownTile();
        game.dropTile();
        game.setState(State.PICK_TILE);
        List<Tile> expResult = List.of(tile1, tile2);
        List<Tile> result = game.getAllFaceUpTiles();
        assertEquals(expResult, result);
    }
}
