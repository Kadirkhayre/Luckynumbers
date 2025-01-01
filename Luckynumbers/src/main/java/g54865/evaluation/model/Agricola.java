/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g54865.evaluation.model;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Kadir
 */
public class Agricola implements Iterable<Plant> {

    private Player currentPlayer;
    private Player opponentPlayer;
    private Field field;

    public Agricola() {
        this.currentPlayer = currentPlayer;
        this.opponentPlayer = opponentPlayer;
        this.field = field;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getOpponentPlayer() {
        return opponentPlayer;
    }

    public Field getField() {
        return field;
    }

    public void switchPlayer() {
        Player tempon;
        tempon = currentPlayer;
        currentPlayer = opponentPlayer;
        opponentPlayer = tempon;
    }

    public void spread() {

    }

    public void sow(Position position, Plant plant) {

    }
    
    

    public boolean isOver() {
//        for (Plant[] row : field) {
//            for (Plant value : row) {
//                if (value == null) {
//                    return false;
//                }
//            }
//        }
//        return true;
        return false;

    }

    public Player getWinner() {
        return null;
    }

    @Override
    public Iterator<Plant> iterator() {
        return null;
    }

    // A faire iterator
//    class PlantIterator implements Iterator<Integer> {
//
//        @Override
//        public boolean hasNext() {
//            return 
//        }
//
//        @Override
//        public Integer next() {
//            return 
//
//        }
//
//    }
}
