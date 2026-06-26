
import java.io.*;
import java.util.ArrayList;

public class HighscoreManager {

    private static final String FILE_NAME = "highscores.txt";

    public static void saveScore(String name, int score) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(name + ": " + score + "\n");
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern!");
        }
    }

    public static void showHighscores() {
        System.out.println("\n=== HIGHSCORES ===");

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("Noch keine Highscores vorhanden.");
        }
    }
}
