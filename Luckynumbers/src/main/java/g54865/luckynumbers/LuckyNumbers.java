package g54865.luckynumbers;

import g54865.luckynumbers.controller.Controller;
import g54865.luckynumbers.model.Game;
import g54865.luckynumbers.model.Model;
import g54865.luckynumbers.view.MyView;

/**
 * The main class of Lucky Numbers. Creates the controller and starts the game
 * 
 * @author Kadir Khayre (54865) <54865@etu.he2b.be>
 */
public class LuckyNumbers {  
    public static void main(String[] args) {
        Model game = new Game();
        Controller controller = new Controller(game, new MyView(game));
        controller.play();
    }    
}
