import java.util.ArrayList;

public class HangmanPrinter {

    public static void printState(char[] word, ArrayList<Character> letters, int wrongAttempts) {
        System.out.println("\n=== HANGMAN ===");
        printHangman(wrongAttempts);

        System.out.print("Wort: ");
        for (char c : word) {
            System.out.print(c + " ");
        }

        System.out.println("\nBereits geraten: " + letters);
        System.out.println("Fehlversuche: " + wrongAttempts + "/7");
    }

    public static void printHangman(int wrongAttempts) {
        switch (wrongAttempts) {
            case 0 ->
                    System.out.println(" +---+\n |   |\n     |\n     |\n     |\n     |\n=====");
            case 1 ->
                    System.out.println(" +---+\n |   |\n O   |\n     |\n     |\n     |\n=====");
            case 2 ->
                    System.out.println(" +---+\n |   |\n O   |\n |   |\n     |\n     |\n=====");
            case 3 ->
                    System.out.println(" +---+\n |   |\n O   |\n/|   |\n     |\n     |\n=====");
            case 4 ->
                    System.out.println(" +---+\n |   |\n O   |\n/|\\  |\n     |\n     |\n=====");
            case 5 ->
                    System.out.println(" +---+\n |   |\n O   |\n/|\\  |\n/    |\n     |\n=====");
            case 6 ->
                    System.out.println(" +---+\n |   |\n O   |\n/|\\  |\n/ \\  |\n     |\n=====");
            case 7 ->
                    System.out.println(" +---+\n |   |\n O   |\n/|\\  |\n/ \\  |\n     |\n=====");
        }
    }
}