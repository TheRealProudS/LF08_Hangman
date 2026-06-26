import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;


public class HangmanGUI extends JFrame {

    private String secretWord;
    private char[] guessedWord;
    private ArrayList<Character> guessedLetters;
    private int wrongAttempts;
    private int animatedParts = 0;
    private JLabel wordLabel;
    private JLabel infoLabel;
    private JTextField inputField;
    private HangmanPanel drawPanel;
    private JTextArea highscoreArea;


    
    private final Color BG_COLOR = new Color(30, 30, 30);
    private final Color TEXT_COLOR = new Color(220, 220, 220);
    private final Color ACCENT = new Color(0, 200, 140);


    public HangmanGUI() {
        secretWord = WordProvider.getRandomWord();
        guessedLetters = new ArrayList<>();
        guessedWord = new char[secretWord.length()];
        wrongAttempts = 0;

        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }

        setTitle("Hangman 🪢");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        getContentPane().setBackground(BG_COLOR);

        wordLabel = new JLabel(getWordDisplay(), SwingConstants.CENTER);
        wordLabel.setFont(new Font("Consolas", Font.BOLD, 32));
        wordLabel.setForeground(TEXT_COLOR);
        
        infoLabel = new JLabel("Fehlversuche: 0/7", SwingConstants.CENTER);
        infoLabel.setForeground(ACCENT);

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.BOLD, 18));
        inputField.setBackground(new Color(50, 50, 50));
        inputField.setForeground(TEXT_COLOR);
        inputField.setCaretColor(TEXT_COLOR);
        inputField.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        inputField.addActionListener(e -> handleGuess());

        
        drawPanel = new HangmanPanel();

        add(wordLabel, BorderLayout.NORTH);

        add(drawPanel, BorderLayout.CENTER);

        // Highscore Panel rechts
        highscoreArea = new JTextArea();
        highscoreArea.setEditable(false);
        highscoreArea.setBackground(BG_COLOR);
        highscoreArea.setForeground(TEXT_COLOR);
        highscoreArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        highscoreArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel highscorePanel = new JPanel(new BorderLayout());
        highscorePanel.setBackground(BG_COLOR);
        highscorePanel.setPreferredSize(new Dimension(200, 0));

        highscorePanel.add(highscoreArea, BorderLayout.CENTER);

        add(highscorePanel, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(BG_COLOR);

        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel inputWrapper = new JPanel();
        inputWrapper.setBackground(BG_COLOR);

        inputField.setMaximumSize(new Dimension(150, 30));
        inputField.setPreferredSize(new Dimension(150, 40));

        inputWrapper.add(inputField);

        bottomPanel.add(infoLabel);
        bottomPanel.add(Box.createVerticalStrut(15));
        bottomPanel.add(inputWrapper);
        bottomPanel.add(Box.createVerticalStrut(15));

        add(bottomPanel, BorderLayout.SOUTH);

        updateHighscores();

        setVisible(true);
    }

    private void handleGuess() {
        String input = inputField.getText().toUpperCase();

        if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            showMessage("Ungültig!");
            return;
        }

        char letter = input.charAt(0);

        if (guessedLetters.contains(letter)) {
            showMessage("Schon geraten!");
            return;
        }

        guessedLetters.add(letter);

        if (secretWord.indexOf(letter) >= 0) {
            revealLetter(letter);
        } else {
            wrongAttempts++;
        }

        updateUI();

        if (isWon()) {
            endGame("🎉 Gewonnen!");
        }

        if (isLost()) {
            endGame("💀 Verloren! Wort: " + secretWord);
        }

        inputField.setText("");
    }

    private void revealLetter(char letter) {
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                guessedWord[i] = letter;
            }
        }
    }

    private void updateUI() {
        wordLabel.setText(getWordDisplay());
        infoLabel.setText("Fehlversuche: " + wrongAttempts + "/7");
        animateHangman();
    }

    private String getWordDisplay() {
        StringBuilder sb = new StringBuilder();
        for (char c : guessedWord) {
            sb.append(c).append(" ");
        }
        return sb.toString();
    }

    private boolean isWon() {
        for (char c : guessedWord) {
            if (c == '_') return false;
        }
        return true;
    }

    private boolean isLost() {
        return wrongAttempts >= 7;
    }

    private void endGame(String msg) {
        JOptionPane.showMessageDialog(this, msg);

        int score = (secretWord.length() * 10) - (wrongAttempts * 5);
        String name = JOptionPane.showInputDialog("Dein Name:");

        HighscoreManager.saveScore(name, score);

        dispose();
        new HangmanGUI();
    }

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    
    private void animateHangman() {
    Timer timer = new Timer(150, null);

    timer.addActionListener(e -> {
        if (animatedParts < wrongAttempts) {
            animatedParts++;
            drawPanel.repaint();
        } else {
            timer.stop();
        }
    });

    timer.start();
}

    private void updateHighscores() {
    StringBuilder sb = new StringBuilder();
    sb.append("=== HIGHSCORES ===\n\n");

    try (BufferedReader reader = new BufferedReader(new FileReader("highscores.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
    } catch (Exception e) {
        sb.append("Noch keine Scores");
    }

    highscoreArea.setText(sb.toString());
}







    // GALGEN ZEICHNEN
    class HangmanPanel extends JPanel {

    public HangmanPanel() {
        setBackground(BG_COLOR); // Dark Mode Hintergrund
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        int centerX = width / 2;
        int baseY = height / 2 + 100;

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(ACCENT); // Farbe

        // Galgen
        g2.drawLine(centerX - 100, baseY, centerX + 50, baseY);
        g2.drawLine(centerX - 25, baseY, centerX - 25, baseY - 250);
        g2.drawLine(centerX - 25, baseY - 250, centerX + 100, baseY - 250);
        g2.drawLine(centerX + 100, baseY - 250, centerX + 100, baseY - 220);    


        // Kopf
        if (animatedParts >= 1)
            g2.drawOval(centerX - 80, baseY - 220, 40, 40);

        // Körper
        if (animatedParts >= 2)
            g2.drawLine(250, 120, 250, 200);

        // Arm links
        if (animatedParts >= 3)
            g2.drawLine(250, 140, 220, 180);

        // Arm rechts
        if (animatedParts >= 4)
            g2.drawLine(250, 140, 280, 180);

        // Bein links
        if (animatedParts >= 5)
            g2.drawLine(250, 200, 220, 250);

        // Bein rechts
        if (animatedParts >= 6)
            g2.drawLine(250, 200, 280, 250);
    }
}
}