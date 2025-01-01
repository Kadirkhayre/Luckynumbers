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
public abstract class Plant {
    
    protected PlantColor color;
    protected boolean freshlySpread;

    public Plant(PlantColor color) {
        this.color = color;
    }

    public PlantColor getColor() {
        return color;
    }

    public boolean isFreshlySpread() {
        return freshlySpread;
    }

    public void setFreshlySpread(boolean freshlySpread) {
        this.freshlySpread = freshlySpread;
    }
  
    public abstract Plant copy();
}
