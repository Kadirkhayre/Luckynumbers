/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g54865.evaluation.model;

/**
 *
 * @author Kadir
 */
public class Field {

    private final Plant[][] field;

    /**
     * Constructor of Field
     * 
     */
    public Field() {
        this.field = new Plant[10][10];
    }

    /**
     * Simple getter of Field
     * 
     * @return the field attribute
     */
    public int getSize() {
        return field.length;
    }
    
    /**
     * Gives the plant
     * 
     * @param position the given position
     * @return the plant at the given position
     */
    public Plant get (Position position){
        return field[position.getRow()][position.getColumn()];
    }
    
    /**
     * Checks if the position is inside the board
     * 
     * @param position the given position
     * @return true if the position is inside the game board, otherwise false
     */
    public boolean isInside (Position position){
        return position.getRow() >= 0 && position.getRow() < field.length 
                && position.getColumn() >= 0 && position.getColumn() < field.length;
    }
    
    /**
     * Checks if a plant can be placed 
     * 
     * @param position the given position
     * @return true if the square at the given position can be planted, otherwise false
     */
    public boolean isSowable (Position position){
        return isInside(position) && field[position.getRow()][position.getColumn()] == null;
    }
    /**
     * Plants a plant at the given position
     * 
     * @param position the given position
     * @param plant the given plant
     */
    public void sow (Position position, Plant plant){
        if (field[position.getRow()][position.getColumn()] != null){        
           throw new IllegalArgumentException("Plant cannot be put");
        }
        
        field[position.getRow()][position.getColumn()] = plant;
    }

}
