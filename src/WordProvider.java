import java.util.Random;

public class WordProvider {

    private static final String[] WORDS = {
            "JAVA", "PROGRAMMIEREN", "COMPUTER", "SPIEL", "ENTWICKLUNG", "SOFTWARE", "HARDWARE", "ALGORITHMUS", "DATENBANK", "NETZWERK","KOMPILIEREN", "DEBUGGING", "FUNKTION", "KLASSE", "OBJEKT", "METHODEN", "VARIABLE", "SCHLEIFE", "BEDINGUNG"
    };

    public static String getRandomWord() {
        Random random = new Random();
        return WORDS[random.nextInt(WORDS.length)];
    }
}
