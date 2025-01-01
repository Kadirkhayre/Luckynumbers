package g54865.evaluation.view;

import g54865.evaluation.model.*;

import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

public class View {
    private Scanner in;

    public View() {
        this.in = new Scanner(System.in);
    }

    public Position askSowPosition(Player player) {
        System.out.println("À quelle position souhaitez vous semer joueur " + player.getColor() + " ?");
        int row;
        int column;

        try {
            System.out.print("Ligne > ");
            row = in.nextInt();
            System.out.print("Colonne > ");
            column = in.nextInt();
        } catch (InputMismatchException e) {
            in.nextLine();
            throw new RuntimeException("Veuillez entrer un entier");
        }

        return new Position(row, column);
    }

    public Plant askPlantToSow(Player currentPlayer) {
        System.out.println("Quelle plante souhaitez vous planter ?");
        System.out.println("\t 1. Herbe (+)");
        System.out.println("\t 2. Blé (x)");
        System.out.print("> ");
        int choice = in.nextInt();

        switch (choice) {
            case 1: return new Grass(currentPlayer.getColor());
            case 2: return new Wheat(currentPlayer.getColor());
            default:
                throw new RuntimeException("Cette plante n'existe pas");
        }
    }


    public void displayField(Field field) {
        System.out.print("   ");
        for (int i = 0; i < field.getSize(); i++) {
            System.out.print(String.format("|%d|", i));
        }
        System.out.println();

        for (int i = 0; i < field.getSize(); i++) {
            System.out.print(String.format("|%d|", i));
            for (int j = 0; j < field.getSize(); j++) {
                displayPlant(field.get(new Position(i, j)));
            }
            System.out.println();
        }
    }

    public void displayWinner(Player player) {
        System.out.println("Le vainqueur est le joueur " + player.getColor());
    }

    private void displayPlant(Plant plant) {
        System.out.print("|");
        if (plant == null) {
            System.out.print(" ");
        } else {
            String str;

            if (plant.getColor() == PlantColor.RED) {
                str = Color.fontRed(getPlantSymbol(plant));
            } else {
                str = Color.fontBlue(getPlantSymbol(plant));
            }

            System.out.print(str);
        }
        System.out.print("|");
    }

    private String getPlantSymbol(Plant plant) {
        String str;
        if (plant instanceof Grass) {
            str = "+";
        } else {
            str = "x";
        }
        return str;
    }

    public void displayError(String message) {
        System.out.println(Color.fontRed("/!\\ " + message));
    }

}
