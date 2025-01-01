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
public class Player {
    
   private final PlantColor color;

    public Player(PlantColor color) {
        this.color = color;
    }

    public PlantColor getColor() {
        return color;
    } 
}
