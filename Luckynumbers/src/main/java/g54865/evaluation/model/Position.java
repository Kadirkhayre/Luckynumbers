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
public class Position {
    
    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    
    public Position getPositionInDirection(Direction direction){
        int newRow = this.getRow() + direction.getRow();
        int newColumn = this.getColumn() + direction.getColumn();
        
        return new Position(newRow, newColumn);
    }
}
