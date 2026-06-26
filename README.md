=== HANGMAN (JAVA) ===

Ein klassisches Hangman-Spiel, entwickelt in Java.
Das Spiel besitzt sowohl eine Konsolen-Version als auch eine grafische Oberfläche (Swing).

----------------------------------------
FEATURES
----------------------------------------

- Buchstabenweise Wort erraten
- Maximal 7 Fehlversuche
- Anzeige des aktuellen Wortstands (_ _ _ _)
- Anzeige bereits geratener Buchstaben
- Highscore-System (Speicherung in Datei)
- Zufällige Wortauswahl
- GUI mit Swing

----------------------------------------
PROJEKTSTRUKTUR
----------------------------------------

hangman/

└── src/
 
      ├── Main.java (Startpunkt)
      ├── Game.java              (Spiellogik)
      ├── WordProvider.java      (liefert Wörter)
      ├── HangmanPrinter.java    (Konsolenanzeige)
      ├── HighscoreManager.java  (Highscores speichern/laden)
      └── HangmanGUI.java        (Grafische Oberfläche)

----------------------------------------
PROGRAMM STARTEN
----------------------------------------

1. In den src-Ordner gehen:

cd src

2. Kompilieren:

javac *.java

3. Starten:

java Main

----------------------------------------
SPIELREGELN
----------------------------------------

- Ein zufälliges Wort wird ausgewählt
- Spieler gibt Buchstaben ein
- Richtige Buchstaben werden angezeigt
- Falsche erhöhen Fehlversuche
- Bei 7 Fehlversuchen → verloren
- Wenn Wort vollständig → gewonnen

----------------------------------------
HIGHSCORE-SYSTEM
----------------------------------------

Score-Berechnung:

Score = (Wortlänge * 10) - (Fehlversuche * 5)

Nach dem Spiel:
- Name eingeben
- Score wird gespeichert

Datei:
highscores.txt

Beispiel:
Pascal: 120
Anna: 80
Max: 150

----------------------------------------
TECHNOLOGIEN
----------------------------------------

- Java
- Swing (GUI)
- FileWriter / BufferedReader (Dateien)

----------------------------------------
HINWEISE
----------------------------------------

- Alle .java Dateien müssen im selben Ordner liegen
- Immer zuerst kompilieren:
  javac *.java

----------------------------------------
ERWEITERUNGSMÖGLICHKEITEN
----------------------------------------

- Top 5 Highscores sortieren
- GUI verbessern (Farben, Layout)
- Grafischer Galgen
- Multiplayer-Version
- JavaFX-Version

----------------------------------------
AUTOR
----------------------------------------

Cyrill-Pascal Zenkner
www.Zenkner-Technology.de

----------------------------------------
FAZIT
----------------------------------------

Dieses Projekt zeigt Grundlagen von:
- Objektorientierter Programmierung (OOP)
- Benutzerinteraktion
- Dateioperationen
- GUI-Entwicklung mit Swing
