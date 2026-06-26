import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private final int MAX_ATTEMPTS = 7;

    private String secretWord;
    private char[] guessedWord;
    private ArrayList<Character> guessedLetters;
    private int wrongAttempts;

    
    private int calculateScore() {
        return (secretWord.length() * 10) - (wrongAttempts * 5);
    }

    private void savePlayerScore(int score) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Dein Name: ");
        String name = scanner.nextLine();

        HighscoreManager.saveScore(name, score);
        System.out.println("Score gespeichert: " + score);
    }


    public void start() {
        secretWord = WordProvider.getRandomWord();
        HighscoreManager.showHighscores();
        guessedLetters = new ArrayList<>();
        guessedWord = new char[secretWord.length()];

        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }

        wrongAttempts = 0;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            HangmanPrinter.printState(guessedWord, guessedLetters, wrongAttempts);

            System.out.print("Buchstabe: ");
            String input = scanner.nextLine().toUpperCase();

            if (!isValidInput(input)) {
                System.out.println("Ungültige Eingabe!");
                continue;
            }

            char letter = input.charAt(0);

            if (guessedLetters.contains(letter)) {
                System.out.println("Schon geraten!");
                continue;
            }

            guessedLetters.add(letter);

            if (secretWord.indexOf(letter) >= 0) {
                revealLetter(letter);
            } else {
                System.out.println("Falsch!");
                wrongAttempts++;
            }

            if (isWon()) {
                HangmanPrinter.printState(guessedWord, guessedLetters, wrongAttempts);
                System.out.println("🎉 Gewonnen!");
                int score = calculateScore();
                savePlayerScore(score);
                break;
            }

            if (isLost()) {
                HangmanPrinter.printState(guessedWord, guessedLetters, wrongAttempts);
                System.out.println("💀 Verloren! Wort war: " + secretWord);
                break;
            }
        }
    }

    private boolean isValidInput(String input) {
        return input.length() == 1 && Character.isLetter(input.charAt(0));
    }

    private void revealLetter(char letter) {
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                guessedWord[i] = letter;
            }
        }
    }

    private boolean isWon() {
        for (char c : guessedWord) {
            if (c == '_') return false;
        }
        return true;
    }

    private boolean isLost() {
        return wrongAttempts >= MAX_ATTEMPTS;
    }
}
