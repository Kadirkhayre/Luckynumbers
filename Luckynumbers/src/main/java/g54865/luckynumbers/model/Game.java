package g54865.luckynumbers.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Game gathers the elements necessary for the game and implements the different
 * stages of the game
 *
 * @author Kadir Khayre (54865) <54865@etu.he2b.be>
 */
public class Game implements Model {

    private State state;
    private int playerCount;
    private int currentPlayerNumber;
    private Board[] boards;
    private Tile pickedTile;
    private Deck deck;

    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 4;
    private static final int MAX_VALUE = 20;

    /**
     * Constructor of Game, without parameters
     */
    public Game() {
        this.state = State.NOT_STARTED;
    }

    @Override
    public void start(int playerCount) {
        deck = new Deck(playerCount);

        if (state != State.NOT_STARTED && state != State.GAME_OVER) {
            throw new IllegalStateException("State must be NOT_STARTED or GAME_OVER"
                    + " State :  " + state);
        }

        if (playerCount < MIN_PLAYERS || playerCount > MAX_PLAYERS) {
            throw new IllegalArgumentException("the number of players is not "
                    + "between 2 and 4 (both included)" + " PlayerCount : " + playerCount);
        }
        this.playerCount = playerCount;
        boards = new Board[this.playerCount];

        for (int i = 0; i < this.playerCount; i++) {
            boards[i] = new Board();
        }
        currentPlayerNumber = 0;
        state = State.PICK_TILE;
    }

    @Override
    public void pickAndPlaceRandomTilesDiagonal() {
        for (int h = 0; h < playerCount; h++) { // nb de joueurs
            List<Tile> randomTiles = new ArrayList<>();
            for (int n = 0; n < getBoardSize(); n++) { // nb tuiles en fct du board
                randomTiles.add(deck.pickFaceDown());               
            }
            Collections.sort(randomTiles);

            for (int i = 0; i < randomTiles.size(); i++) {
                boards[h].put(randomTiles.get(i), new Position(i, i));
            }
        }
    }

    @Override
    public int getBoardSize() {
        return boards[0].getSize();
    }

    @Override
    public Tile pickFaceDownTile() {
        if (state != State.PICK_TILE) {
            throw new IllegalStateException("State must be PICK_TILE"
                    + " State : " + state);
        }
        pickedTile = deck.pickFaceDown();
        state = State.PLACE_OR_DROP_TILE;
        return pickedTile;
    }

    @Override
    public void pickFaceUpTile(Tile tile) {
        if (state != State.PICK_TILE) {
            throw new IllegalStateException("State must be PICK_TILE"
                    + " State : " + state);
        }

        if (deck.faceUpCount() == 0) {
            throw new IllegalArgumentException("There are no visible tiles ... ");
        }

        if (!deck.hasFaceUp(tile)) {
            throw new IllegalArgumentException("Tile is not face up");
        }
        deck.pickFaceUp(tile);
        pickedTile = tile;
        state = State.PLACE_TILE;
    }

    @Override
    public void putTile(Position pos) {
        if (!(canTileBePut(pos))) {
            throw new IllegalArgumentException("Cannot place the tile at "
                    + "the given position");
        }

        if (boards[currentPlayerNumber].getTile(pos) != null) {
            Tile replaced = boards[currentPlayerNumber].getTile(pos);
            deck.putBack(replaced);
        }
        boards[currentPlayerNumber].put(pickedTile, pos);

        if (boards[currentPlayerNumber].isFull() || faceDownTileCount() == 0) {
            state = State.GAME_OVER;

        } else {
            state = State.TURN_END;
        }
    }

    @Override
    public void dropTile() {
        if (state != State.PLACE_OR_DROP_TILE) {
            throw new IllegalStateException("State must be PLACE_OR_DROP_TILE"
                    + " State : " + state);
        }
        deck.putBack(pickedTile);

        if (faceDownTileCount() == 0) {
            state = State.GAME_OVER;
        } else {
            state = State.TURN_END;
        }
    }

    @Override
    public int faceDownTileCount() {
        if (state == State.NOT_STARTED) {
          throw new IllegalStateException("State cannot be NOT_STARTED"
                    + " State : " + state);
        }
        return deck.faceDownCount();
    }

    @Override
    public int faceUpTileCount() {
        if (state == State.NOT_STARTED) {
            throw new IllegalStateException("State cannot be NOT_STARTED"
                    + " State : " + state);
        }
        return deck.faceUpCount();
    }

    @Override
    public void nextPlayer() {
        if (state != State.TURN_END) {
            throw new IllegalStateException("State must be TURN_END"
                    + " State : " + state);
        }
        currentPlayerNumber = (currentPlayerNumber + 1) % playerCount;
        state = State.PICK_TILE;
    }

    @Override
    public List<Tile> getAllFaceUpTiles() {
        if (state != State.PICK_TILE) {
            throw new IllegalStateException("State must be PICK_TILE"
                    + " State : " + state);
        }
        return Collections.unmodifiableList(deck.getAllFaceUp());
    }

    @Override
    public int getPlayerCount() {
        if (state == State.NOT_STARTED) {
            throw new IllegalStateException("State cannot be NOT_STARTED"
                    + " State : " + state);
        }
        return playerCount;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public int getCurrentPlayerNumber() {
        if (state == State.NOT_STARTED || state == State.GAME_OVER) {
            throw new IllegalStateException("State cannot be NOT_STARTED or GAME_OVER"
                    + " State : " + state);
        }
        return currentPlayerNumber;
    }

    @Override
    public Tile getPickedTile() {
        if (state != State.PLACE_TILE && state != State.PLACE_OR_DROP_TILE) {
            throw new IllegalStateException("State must be PLACE_TILE"
                    + " State: " + state);
        }
        return pickedTile;
    }

    @Override
    public boolean isInside(Position pos) {
        return boards[currentPlayerNumber].isInside(pos);
    }

    @Override
    public boolean canTileBePut(Position pos) {
        if (state != State.PLACE_TILE && state != State.PLACE_OR_DROP_TILE) {
            throw new IllegalStateException("State must be PLACE_TILE OR PLACE_OR_DROP_TILE"
                    + " State : " + state);
        }

        if (!(boards[currentPlayerNumber].isInside(pos))) {
            throw new IllegalArgumentException("Position is outside the board ");
        }

        return boards[currentPlayerNumber].canBePut(pickedTile, pos);
    }

    @Override
    public Tile getTile(int playerNumber, Position pos) {
        if (state == State.NOT_STARTED) {
            throw new IllegalStateException("State cannot be NOT_STARTED"
                    + " State : " + state);
        }

        if (!(boards[playerNumber].isInside(pos))) {
            throw new IllegalArgumentException("Position is outside the board");
        }

        if (playerNumber < 0 || playerNumber >= playerCount) {
            throw new IllegalArgumentException("Player number is outside of range" + playerNumber);
        }
        return boards[playerNumber].getTile(pos);
    }

    @Override
    public List<Integer> getWinners() {
        if (state != State.GAME_OVER) {
            throw new IllegalStateException("State must be GAME_OVER"
                    + " State : " + state);
        }
        List<Integer> players = new ArrayList<>();
        int sum = 0;

        for (int h = 0; h < playerCount; h++) {
            for (int i = 0; i < boards[h].getSize(); i++) {
                for (int j = 0; j < boards[h].getSize(); j++) {
                    if (boards[h].getTile(new Position(i, j)) != null) {
                        sum++;
                    }
                }
            }
            players.add(sum);
            sum = 0;
        }
        int maxTiles = Collections.max(players);

        List<Integer> winners = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) == maxTiles) {
                winners.add(i);
            }
        }
        return winners;
    }

    /**
     * Pick a tile with the given value. Shoud be used only for the JUnit tests.
     *
     * @param value the given integer
     * @return the picked tile
     */
    Tile pickTile(int value) {
        if (state != State.PICK_TILE) {
            throw new IllegalStateException("State must be PICK_TILE"
                    + " State : " + state);
        }
        state = State.PLACE_TILE;
        pickedTile = new Tile(value);
        return pickedTile;
    }

    /**
     * Changes the size of the table. Shoud be used only for the JUnit tests.
     *
     * @param value the given value
     */
    void setSizeBoard(int value) {
        Board board = new Board(value);
        boards[0] = board;
    }

    /**
     * Changes the state of the game. Shoud be used only for the JUnit tests.
     *
     * @param state the given state
     */
    void setState(State state) {
        this.state = state;
    }

    /**
     * Gives the list of tiles face down. Shoud be used only for the JUnit
     * tests.
     *
     * @return the list of tiles face down
     */
    List<Tile> getAllFaceDownTiles() {
        return deck.getAllFaceDown();
    }
}
