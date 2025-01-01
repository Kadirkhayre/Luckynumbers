package g54865.evaluation.view;

public class Color {
    private static final String RED = "\u001B[31m";
    private static final String BLUE = "\u001B[34m";
    private static final String RESET = "\u001B[0m";

    public static String fontRed(String message) {
        return fontColorize(RED, message);
    }

    public static String fontBlue(String message) {
        return fontColorize(BLUE, message);
    }

    public static String fontColorize(String color, String message) {
        return color + message + RESET;
    }
}
