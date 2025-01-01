package g54865.evaluation.controller;

import g54865.evaluation.model.Agricola;
import g54865.evaluation.model.Plant;
import g54865.evaluation.model.Position;
import g54865.evaluation.view.View;

public class Controller {

    public static void main(String[] args) {
        Controller ctr = new Controller();
        ctr.run(new Agricola(), new View());
    }

    public void run(Agricola game, View view) {
        int i = 0;
        while (! game.isOver()) {
            view.displayField(game.getField());
            boolean hasPlayed = false;

            while (! hasPlayed) {
                try {
                    Position position = view.askSowPosition(game.getCurrentPlayer());
                    Plant plant = view.askPlantToSow(game.getCurrentPlayer());

                    game.sow(position, plant);
                    hasPlayed = true;
                } catch (Exception e) {
                    view.displayError(e.getMessage());
                }
            }

            game.switchPlayer();
            i++;
            if (i % 2 == 0) {
                game.spread();
            }
        }

        view.displayWinner(game.getWinner());
    }
}
