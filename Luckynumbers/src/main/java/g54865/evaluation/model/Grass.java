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
public class Grass extends Plant {

    public Grass(PlantColor color) {
        super(color);
    }

    public Direction[] getSpreadDirection() {
        Direction[] directions = {Direction.UP, Direction.DOWN,Direction.LEFT, 
            Direction.RIGHT};
        return directions;
    }

    @Override
    public Plant copy() {
        return new Grass(PlantColor.BLUE);
    }

}
