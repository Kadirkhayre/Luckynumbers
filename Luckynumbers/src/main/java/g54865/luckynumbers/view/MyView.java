package g54865.luckynumbers.view;

import g54865.luckynumbers.model.Model;
import g54865.luckynumbers.model.Position;
import g54865.luckynumbers.model.Tile;
import java.util.Scanner;

/**
 * MyView displays the game and prepares the entries for the user
 *
 * @author Kadir Khayre (54865) <54865@etu.he2b.be>
 */
public class MyView implements View {

    private final Model model;

    /**
     * Constructor of MyView
     *
     * @param model the given model
     */
    public MyView(Model model) {
        this.model = model;
    }

    @Override
    public void displayWelcome() {
        System.out.println("============================================");
        System.out.println("     " + "Welcome to the Lucky Numbers game ! ");
        System.out.println("============================================");
        System.out.println("      " + " * By Kadir Khayre");
        System.out.println("      " + " * V2.0");
    }

    @Override
    public void displayGame() {
        displayPlayer();
        displayBoard();
        displayFaceDownTileCount();
        displayAllFaceUpTiles();
    }

    /**
     * Displays the player
     */
    private void displayPlayer() {
        System.out.print("Player " + (model.getCurrentPlayerNumber() + 1) + "\n");
    }

    /**
     * Displays the board
     */
    private void displayBoard() {
        System.out.print("     ");
        for (int i = 1; i <= model.getBoardSize(); i++) {
            System.out.print(i + "  ");
        }
        System.out.println("");
        makeLine(model.getBoardSize());

        for (int i = 0; i < model.getBoardSize(); i++) {
            System.out.print(i + 1 + " | ");
            for (int j = 0; j < model.getBoardSize(); j++) {
                Tile currentTile = model.getTile(model.getCurrentPlayerNumber()
                        , new Position(i, j));
                if (currentTile != null) {
                    System.out.printf("%2d", currentTile.getValue());
                    System.out.print(" ");

                } else {
                    System.out.print(" . ");
                }

            }
            System.out.println(" ");
        }
        makeLine(model.getBoardSize());

    }
    
    /**
     * Displays the number of tiles remaining face down
     */
    private void displayFaceDownTileCount() {
        System.out.println("Deck : " + model.faceDownTileCount() + " tiles left");
    }

    /**
     * Displays the list of face up tiles
     */
    private void displayAllFaceUpTiles() {
        System.out.print("Face up tiles : ");
        for (Tile tile : model.getAllFaceUpTiles()) {
            System.out.print(tile.getValue() + " | ");
        }
        System.out.println(" ");
    }
    
    /**
     * Displays a dotted line
     *
     * @param sizeBoard the size of the board
     */
    private static void makeLine(int sizeBoard) {
        StringBuilder sb = new StringBuilder();
        sb.append("-".repeat((sizeBoard * 3) + 3));
        System.out.println(sb);
    }

    @Override
    public void displayPickedTile() {
        System.out.print("Picked tile : " + model.getPickedTile().getValue() + "\n");
    }

    @Override
    public void displayWinner() {
        System.out.print(TerminalColor.GREEN + "Player ");
        for (int winner : model.getWinners()){
            System.out.print(TerminalColor.GREEN + "" + winner +", ");
        }
        System.out.println(TerminalColor.GREEN + " won the game !");
    }

    @Override
    public int askPlayerCount() {
        System.out.println("How many people participate in this game ? (from 2 to 4)");
        int players = robustReadingOfInteger();

        while (players < 2 || players > 4) {
            System.out.println("Please enter an integer between 2 and 4"
                    + " You entered : " + players);
            players = robustReadingOfInteger();
        }
        return players;
    }

    @Override
    public Position askPosition() {
        System.out.println("Enter a value for row : ");
        int row = (robustReadingOfInteger() - 1);
        System.out.println("Enter a value for column : ");
        int column = (robustReadingOfInteger() - 1);

        return new Position(row, column);
    }
    
    @Override
    public void displayError(String message) {
        System.err.println(TerminalColor.RED + message);
    }

    @Override
    public String askFaceUpOrFaceDown() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Press on : ");
        System.out.println(" - [D] -> to take a face down tile");
        System.out.println(" - [U] -> to take a face up tile");
        String letter = keyboard.next().toUpperCase();

        while ((!"D".equals(letter)) && (!"U".equals(letter))) {
            System.out.println("Please choose between D (face down) or U(face up)");
            System.out.println("You entered : " + letter);
            letter = keyboard.next().toUpperCase();

        }
        return letter;
    }

    @Override
    public Tile askTile() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the index of tile");
        int index = robustReadingOfInteger();
        
        if (index > model.faceUpTileCount()){
            throw new IllegalArgumentException("Tile does not exist");
        }     
        return model.getAllFaceUpTiles().get(index-1);
    }

    @Override
    public String askDropOrPut() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Press on : ");
        System.out.println(" - [D] -> to drop the tile");
        System.out.println(" - [P] -> to put the tile");
        String letter = keyboard.next().toUpperCase();

        while ((!"D".equals(letter)) && (!"P".equals(letter))) {
            System.out.println("Please choose between D (drop tile) or P(put tile)");
            System.out.println("You entered : " + letter);
            letter = keyboard.next().toUpperCase();
        }
        return letter;
    }


    /**
     * Robust reading of an integer As long as the user's input is not an
     * integer the method asks a new entry
     *
     * @return the integer entered by the user
     */
    public static int robustReadingOfInteger() {
        Scanner keyboard = new Scanner(System.in);
        while (!keyboard.hasNextInt()) {
            keyboard.next();
            System.out.println("Please enter an integer !");
        }
        return keyboard.nextInt();
    }
}
