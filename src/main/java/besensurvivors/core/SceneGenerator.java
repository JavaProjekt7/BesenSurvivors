package besensurvivors.core;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;

import java.io.File;

public class SceneGenerator {
    // Schwerkraft
    private static final double GRAVITY = 0.5;  // Schwerkraft, die den Charakter wieder nach unten zieht
    private static final double JUMP_STRENGTH = -12;  // Stärke des Sprungs
    private double velocityY = 0;  // wie schnell der Charakter fällt
    private boolean isJumping = false;  // überprüft, ob der Charakter in der Luft ist
    private double gameWidth = 1584;  // Breite des Spielfeldes
    private double gameHeight = 840; // Höhe des Spielfeldes

    public Scene generateStartWindow(Main main) {
        Scene scene = new Scene(root, 400, 300);

        Label label = new Label("BesenSurvivors");
        label.setStyle("-fx-font-size: 37px;");
        root.getChildren().add(label);

        Button startGameButton = new Button("Start Game");
        startGameButton.setText("Start Game");
        startGameButton.setPrefHeight(50);
        startGameButton.setPrefWidth(200);
        root.getChildren().add(startGameButton);

        // Einstellungs button
        Button settingsButton = new Button("Einstellungen");
        settingsButton.setPrefHeight(50);
        settingsButton.setPrefWidth(200);
        root.getChildren().add(settingsButton);

        // Button zum beenden des Spiels
        Button endGameButton = new Button();
        endGameButton.setText("Endgame");
        endGameButton.setPrefHeight(50);
        endGameButton.setPrefWidth(200);
        root.getChildren().add(endGameButton);

        endGameButton.setOnAction(event -> ((Stage) endGameButton.getScene().getWindow()).close());
        settingsButton.setOnAction(event -> openSettingsWindow());

        // Startgame-Button Aktion
        startGameButton.setOnAction(event -> {
            String currentDir = System.getProperty("user.dir");
            main.getMusik().stopMusic();
            main.getMusik().playMusic("file:///" + currentDir + "\\musik\\JavaxDaniel.mp3");

            Stage stage = (Stage) startGameButton.getScene().getWindow();
            stage.setScene(generateGameWindow()); // Wechsel zur Spielszene
        });

        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #bb33ff");

        return scene;
    }

    private void openSettingsWindow() {
        Stage settingsStage = new Stage();
        settingsStage.setTitle("Einstellungen");

        VBox settingsRoot = new VBox(20);
        settingsRoot.setAlignment(Pos.CENTER);

        // Lautstärke-Label
        Label volumeLabel = new Label("Lautstärke:");
        volumeLabel.setStyle("-fx-font-size: 18px;");

        // Lautstärke-Slider zum Lautstärke ändern
        javafx.scene.control.Slider volumeSlider = new javafx.scene.control.Slider(0, 100, 50);
        volumeSlider.setShowTickLabels(true);
        volumeSlider.setShowTickMarks(true);
        volumeSlider.setMajorTickUnit(25);

        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double volume = newValue.doubleValue() / 100;
            System.out.println("Lautstärke: " + volume);
        });

        // Zurück-Button
        Button backButton = new Button("Zurück");
        backButton.setOnAction(event -> settingsStage.close());

        settingsRoot.getChildren().addAll(volumeLabel, volumeSlider, backButton);
        Scene settingsScene = new Scene(settingsRoot, 300, 200);
        settingsStage.setScene(settingsScene);
        settingsStage.show();
    }

    // Methode für die Spielszene (Spielfeld)
    public Scene generateGameWindow() {
        // Pane für die Spielfläche
        Pane gameRoot = new Pane();
        Scene gameScene = new Scene(gameRoot, gameWidth, gameHeight); // Größe des Spiels

        // Map als Hintergrund hinzufügen
        Gamemap map = new Gamemap(); // Map-Objekt erstellen
        gameRoot.getChildren().add(map); // Map als Hintergrund in die Szene einfügen

        // Erstelle einen Charakter (blaues Rechteck)
        Rectangle character = new Rectangle(50, 50, Color.BLUE);
        character.setX(375); // Startposition X
        character.setY(gameHeight - character.getHeight()); // Startposition Y auf dem Boden

        // Erstelle einen Gegner (rotes Rechteck)
        Rectangle enemy = new Rectangle(50, 50, Color.RED);
        enemy.setX(100); // Startposition X des Gegners
        enemy.setY(gameHeight - enemy.getHeight()); // Startposition Y des Gegners

        gameRoot.getChildren().add(character);
        gameRoot.getChildren().add(enemy);

        // Steuerung des Charakters
        gameScene.setOnKeyPressed(event -> {
            double newX = character.getX();  // Neue X-Position für den Charakter
            double newY = character.getY();  // Neue Y-Position für den Charakter

            // Bewegung nach oben-Befehl (Leertaste und 'W' für Sprung)
            if ((event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.W) && !isJumping) {  // Leertaste oder W für den Sprung
                isJumping = true;
                velocityY = JUMP_STRENGTH;  // Die Sprunggeschwindigkeit einstellen
            }

            // Bewegung nach links-Befehl 'A'
            if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
                newX = Math.max(0, character.getX() - 5); // Nach links bewegen, nicht über den Rand hinaus
            }

            // Bewegung nach rechts-Befehl 'D'
            else if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
                newX = Math.min(gameWidth - character.getWidth(), character.getX() + 5); // Nach rechts bewegen, nicht über den Rand hinaus
            }

            // Schwerkraft anwendend, wenn der Charakter in der Luft ist
            if (isJumping) {
                velocityY += GRAVITY;  // Geschwindigkeit auf der Y-Achse anpassen (Schwerkraft)
                newY += velocityY;  // Die Y-Position mit der Geschwindigkeit anpassen

                // Wenn der Charakter den Boden erreicht hat
                if (newY >= gameHeight - character.getHeight()) {
                    newY = gameHeight - character.getHeight(); // Auf den Boden setzen
                    isJumping = false;  // Der Charakter ist wieder auf dem Boden
                    velocityY = 0;  // Die Fallgeschwindigkeit zurücksetzen
                }
            }

            // Die neue Position des Charakters setzen
            character.setX(newX);
            character.setY(newY);
        });

        // Gegner verfolgt den Charakter
        // Ein einfacher Algorithmus, um den Spieler zu verfolgen (X und Y-Position)
        Thread enemyAI = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(16);  // Ein bisschen Zeitverzögerung (60 FPS)

                    // Berechne die Richtung, in die der Gegner sich bewegen muss
                    double deltaX = character.getX() - enemy.getX();
                    double deltaY = character.getY() - enemy.getY();

                    // Bewege den Gegner in Richtung des Spielers
                    double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
                    if (distance != 0) {
                        double moveX = deltaX / distance * 2; // Geschwindigkeit des Gegners (2 ist die Geschwindigkeit)
                        double moveY = deltaY / distance * 2; // Geschwindigkeit des Gegners

                        // Gegner bewegen
                        enemy.setX(enemy.getX() + moveX);
                        enemy.setY(enemy.getY() + moveY);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        enemyAI.setDaemon(true);  // Setzen wir den Thread als Daemon-Thread, damit er automatisch gestoppt wird, wenn die Anwendung endet.
        enemyAI.start();

        return gameScene;
    }
}