/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g54865.evaluation.model;

/**
 *
 * @author kadir
 */
public class Wheat extends Plant {

    public Wheat(PlantColor color) {
        super(color);
    }

    public Direction[] getSpreadDirection() {
        Direction[] directions = {Direction.UP_RIGHT, Direction.DOWN_RIGHT,
            Direction.DOWN_LEFT, Direction.UP_LEFT};
        return directions;
    }

    @Override
    public Plant copy() {
        return new Wheat(PlantColor.BLUE);
    }

}
