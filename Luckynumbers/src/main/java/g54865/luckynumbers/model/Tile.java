package g54865.luckynumbers.model;

/**
 * A tile has a value between 1 and 20. A tile can be placed on the board by
 * respecting the placement rules
 *
 * @author Kadir Khayre (54865) <54865@etu.he2b.be>
 */
public class Tile implements Comparable<Tile> {

    private final int value;
    private boolean faceUp;

    /**
     * Constructor of Tile
     *
     * @param value the given integer
     */
    public Tile(int value) {
        this.value = value;
    }

    
    /**
     * Simple getter of value
     *
     * @return the value attribute
     */
    public int getValue() {
        return value;
    }

    /**
     * Simple getter of faceUp
     * 
     * @return the faceUp attribute
     */
    public boolean isFaceUp() {
        return faceUp;
    }
    
    /**
     * Makes a tile visible
     */
    void flipFaceUp() {
        faceUp = true;
    }
    
    /**
     * Compares two tiles
     * 
     * @param o an onther tile
     * @return if the 2 objects are equal, a positive number the current
     * object is larger than the other moment, a negative number otherwise
     */
    @Override
    public int compareTo(Tile o) {
        return this.getValue() - o.getValue();
    }

    /**
     * Defines the format to display the tile
     *
     * @return a string representation of the tile
     */
    @Override
    public String toString() {
        return "Tile{" + "value=" + value + '}';
    }

    /**
     * Returns a hash code
     * `
     * @return hash code value for this object
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.value;
        hash = 89 * hash + (this.faceUp ? 1 : 0);
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
        final Tile other = (Tile) obj;
        if (this.value != other.value) {
            return false;
        }
        if (this.faceUp != other.faceUp) {
            return false;
        }
        return true;
    }
}

