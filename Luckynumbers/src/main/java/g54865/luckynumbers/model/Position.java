package g54865.luckynumbers.model;

/**
 * A position is characterized by a row and a column
 *
 * @author Kadir Khayre (54865) <54865@etu.he2b.be>
 */
public class Position {

    private final int row;
    private final int column;

    /**
     * Constructor of Position
     *
     * @param row the given integer
     * @param column the given integer
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Simple getter of row
     *
     * @return the row attribute
     */
    public int getRow() {
        return row;
    }

    /**
     * Simple getter of column
     *
     * @return the column attribute
     */
    public int getColumn() {
        return column;
    }

    /**
     * Returns a hash code 
     * 
     * @return hash code value for this object
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.row;
        hash = 53 * hash + this.column;
        return hash;
    }

    /**
     * Checks equality between 2 objects
     *
     * @param obj an other object
     * @return true if are identical, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;

        return (this.row == other.row && this.column == other.column);
    }

    /**
     * Defines the format to display the position
     *
     * @return a string representation of the position
     */
    @Override
    public String toString() {
        return "Position{" + "row=" + row + ", column=" + column + '}';
    }
}
