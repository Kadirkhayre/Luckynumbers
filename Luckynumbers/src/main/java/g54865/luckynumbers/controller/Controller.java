package g54865.luckynumbers.controller;

import g54865.luckynumbers.model.Model;
import g54865.luckynumbers.view.View;

/**
 * Controller is responsible for the dynamics of the game and for updating the
 * view as the game progresses
 *
 * @author Kadir Khayre (54865) <54865@etu.he2b.be>
 */
public class Controller {

    private final View view;
    private final Model game;

    /**
     * Constructor of Controller
     *
     * @param model the given model
     * @param view the given view
     */
    public Controller(Model model, View view) {
        this.view = view;
        this.game = model;
    }

    /**
     * Manages a game from beginning to end and is controlled by the state
     */
    public void play() {
        view.displayWelcome();
        while (true) {
            int playerCount = 0;
            switch (game.getState()) {
                case NOT_STARTED:
                    playerCount = view.askPlayerCount();
                    game.start(playerCount);                    
                    game.pickAndPlaceRandomTilesDiagonal();
                    break;

                case PICK_TILE:
                    view.displayGame();
                    if (game.faceUpTileCount() != 0) {
                        String choicePick = view.askFaceUpOrFaceDown();
                        if ("U".equals(choicePick)) {
                            try {
                                game.pickFaceUpTile(view.askTile());
                            } catch (Exception e) {
                                view.displayError(e.getMessage());
                            }
                            break;
                        }
                    }
                    game.pickFaceDownTile();
                    break;

                case PLACE_TILE:
                    view.displayPickedTile();
                    try {
                        game.putTile(view.askPosition());
                    } catch (Exception e) {
                        view.displayError(e.getMessage());
                    }
                    break;

                case PLACE_OR_DROP_TILE:
                    view.displayPickedTile();
                    String choiceDropOrPut = view.askDropOrPut();

                    if ("D".equals(choiceDropOrPut)) {
                        game.dropTile();
                    } else {
                        try {
                            game.putTile(view.askPosition());
                        } catch (Exception e) {
                            view.displayError(e.getMessage());
                        }
                    }
                    break;

                case TURN_END:
                    game.nextPlayer();
                    break;

                case GAME_OVER:
                    view.displayWinner();
                    playerCount = view.askPlayerCount();
                    game.start(playerCount);
                    game.pickAndPlaceRandomTilesDiagonal();
                    break;
            }
        }
    }
}
