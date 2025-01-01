package g54865.luckynumbers.model;

/**
 * Board is the place on which we place the tiles
 *
 * @author Kadir Khayre (54865) <54865@etu.he2b.be>
 */
public class Board {

    private final Tile[][] tiles;
    
    private static final int DEFAULT_SIZE = 4;

    /**
     * Constructor of Board, without parameters
     */
    public Board() {
        this.tiles = new Tile[DEFAULT_SIZE][DEFAULT_SIZE];
    }

    /**
     * Constructor of board. Shoud be used only for the JUnit tests.
     *
     * @param size the given size
     */
    Board(int size) {
        this.tiles = new Tile[size][size];
    }

    /**
     * Gives the size of board
     *
     * @return the size of board
     */
    public int getSize() {
        return tiles.length;
    }

    /**
     * Checks if the position is inside the board
     *
     * @param position the given position
     * @return true if the position is inside the game board, otherwise false
     */
    public boolean isInside(Position position) {
        return (position.getRow() >= 0 && position.getRow() < getSize()
                && position.getColumn() >= 0 && position.getColumn() < getSize());
    }

    /**
     * Gives the tile at the given position
     *
     * @param position the given position
     * @return the tile at the given position
     */
    public Tile getTile(Position position) {
        return tiles[position.getRow()][position.getColumn()];
    }

    /**
     * Checks if the tile can be placed at the given position
     *
     * @param tile the given tile
     * @param position the given position
     * @return true, if the tile can be placed at the given position, otherwise
     * false
     */
    public boolean canBePut(Tile tile, Position position) {
        if (isInside(position)) {
            Position newPositionLeft = new Position(position.getRow(), position.getColumn() - 1);
            Position newPositionRight = new Position(position.getRow(), position.getColumn() + 1);
            Position newPositionUp = new Position(position.getRow() - 1, position.getColumn());
            Position newPositionDown = new Position(position.getRow() + 1, position.getColumn());

            if (isInside(newPositionLeft)) {
                while (newPositionLeft.getColumn() != 0 && getTile(newPositionLeft) == null) {
                    newPositionLeft = new Position(newPositionLeft.getRow(), newPositionLeft.getColumn() - 1);
                }
                if (getTile(newPositionLeft) != null && getTile(newPositionLeft).getValue() >= tile.getValue()) {
                    return false;
                }
            }

            if (isInside(newPositionRight)) {
                while (newPositionRight.getColumn() != getSize() - 1 && getTile(newPositionRight) == null) {
                    newPositionRight = new Position(newPositionRight.getRow(), newPositionRight.getColumn() + 1);
                }
                if (getTile(newPositionRight) != null && getTile(newPositionRight).getValue() <= tile.getValue()) {
                    return false;
                }
            }

            if (isInside(newPositionUp)) {
                while (newPositionUp.getRow() != 0 && getTile(newPositionUp) == null) {
                    newPositionUp = new Position(newPositionUp.getRow() - 1, newPositionUp.getColumn());
                }
                if (getTile(newPositionUp) != null && getTile(newPositionUp).getValue() >= tile.getValue()) {
                    return false;
                }
            }

            if (isInside(newPositionDown)) {
                while (newPositionDown.getRow() != getSize() - 1 && getTile(newPositionDown) == null) {
                    newPositionDown = new Position(newPositionDown.getRow() + 1, newPositionDown.getColumn());
                }
                if (getTile(newPositionDown) != null && getTile(newPositionDown).getValue() <= tile.getValue()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Puts the given tile at the given position
     *
     * @param tile the given tile
     * @param position the given position
     */
    public void put(Tile tile, Position position) {
        tiles[position.getRow()][position.getColumn()] = tile;
    }

    /**
     * Checks if the board is completely filled with tiles.
     *
     * @return true if the board is full, otherwise false
     */
    public boolean isFull() {
        for (Tile[] row : tiles) {
            for (Tile value : row) {
                if (value == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Defines the format to display the board
     *
     * @return a string representation of the board
     */
    @Override
    public String toString() {
        String tilesArray = null;
        for (Tile[] row : tiles) {
            for (Tile value : row) {
                tilesArray += value + " ";
            }
            tilesArray += "\n";
        }
        return tilesArray;
    }
}
