package besensurvivors.core;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class SceneGenerator {
    // Schwerkraft-Parameter
    private static final double GRAVITY = 0.5;
    private static final double JUMP_STRENGTH = -12;
    private double velocityY = 0;
    private boolean isJumping = false;
    private double gameWidth = 3000; // Angepasste Breite für mehr Bewegungsfreiheit
    private double gameHeight = 950;

    public Scene generateStartWindow(Main main) {
        VBox root = new VBox(20);
        Scene scene = new Scene(root, 400, 300);
        // Charakter erstellen (Startposition jetzt am Boden)
        Rectangle character = new Rectangle(50, 50, Color.BLUE);
        character.setX(100); // Start weiter links
        character.setY(Gamemap.getMapHeight() - character.getHeight()); // Direkt auf dem Boden starten


        Label label = new Label("BesenSurvivors");
        label.setStyle("-fx-font-size: 37px;");
        root.getChildren().add(label);

        Button startGameButton = new Button("Start Game");
        startGameButton.setPrefHeight(50);
        startGameButton.setPrefWidth(200);
        root.getChildren().add(startGameButton);

        Button settingsButton = new Button("Einstellungen");
        settingsButton.setPrefHeight(50);
        settingsButton.setPrefWidth(200);
        root.getChildren().add(settingsButton);

        Button endGameButton = new Button("Endgame");
        endGameButton.setPrefHeight(50);
        endGameButton.setPrefWidth(200);
        root.getChildren().add(endGameButton);

        endGameButton.setOnAction(event -> ((Stage) endGameButton.getScene().getWindow()).close());
        settingsButton.setOnAction(event -> openSettingsWindow());

        startGameButton.setOnAction(event -> {
            String currentDir = System.getProperty("user.dir");
            main.getMusik().stopMusic();
            main.getMusik().playMusic("file:///" + currentDir + "\\musik\\JavaxDaniel.mp3");
            Stage stage = (Stage) startGameButton.getScene().getWindow();
            stage.setScene(generateGameWindow());
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

        Label volumeLabel = new Label("Lautstärke:");
        volumeLabel.setStyle("-fx-font-size: 18px;");

        javafx.scene.control.Slider volumeSlider = new javafx.scene.control.Slider(0, 100, 50);
        volumeSlider.setShowTickLabels(true);
        volumeSlider.setShowTickMarks(true);
        volumeSlider.setMajorTickUnit(25);

        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double volume = newValue.doubleValue() / 100;
            System.out.println("Lautstärke: " + volume);
        });

        Button backButton = new Button("Zurück");
        backButton.setOnAction(event -> settingsStage.close());

        settingsRoot.getChildren().addAll(volumeLabel, volumeSlider, backButton);
        Scene settingsScene = new Scene(settingsRoot, 300, 200);
        settingsStage.setScene(settingsScene);
        settingsStage.show();
    }

    // Spielszene inklusive Timer, erweiterter Hindernisse und Game-Over-Logik
    public Scene generateGameWindow() {
        Pane gameRoot = new Pane();
        Scene gameScene = new Scene(gameRoot, gameWidth, gameHeight);

        // Hintergrund (Map) hinzufügen
        Gamemap map = new Gamemap();
        gameRoot.getChildren().add(map);

        // Spieler (blauer Block)
        Rectangle character = new Rectangle(50, 50, Color.BLUE);
        character.setX(375 / 2);
        character.setY(gameHeight - character.getHeight());

        // Gegner (roter Block)
        Rectangle enemy = new Rectangle(50, 50, Color.RED);
        enemy.setX(100 / 2);
        enemy.setY(gameHeight - enemy.getHeight());

        gameRoot.getChildren().addAll(character, enemy);

        // --- HINDERNISSE ---
        List<Rectangle> obstacles = new ArrayList<>();
        // Hindernis 1
        Rectangle obstacle1 = new Rectangle(300, gameHeight - 50, 50, 50);
        obstacle1.setFill(Color.BROWN);
        obstacles.add(obstacle1);
        // Hindernis 2 (doppelt hoch)
        Rectangle obstacle2 = new Rectangle(500, gameHeight - 100, 50, 100);
        obstacle2.setFill(Color.BROWN);
        obstacles.add(obstacle2);
        // Hindernis 3
        Rectangle obstacle3 = new Rectangle(700, gameHeight - 50, 50, 50);
        obstacle3.setFill(Color.BROWN);
        obstacles.add(obstacle3);
        // Hindernis 4 (doppelt hoch)
        Rectangle obstacle4 = new Rectangle(900, gameHeight - 100, 50, 100);
        obstacle4.setFill(Color.BROWN);
        obstacles.add(obstacle4);
        // Hindernis 5
        Rectangle obstacle5 = new Rectangle(1100, gameHeight - 50, 50, 50);
        obstacle5.setFill(Color.BROWN);
        obstacles.add(obstacle5);
        // Hindernis 6 (doppelt hoch)
        Rectangle obstacle6 = new Rectangle(1300, gameHeight - 100, 50, 100);
        obstacle6.setFill(Color.BROWN);
        obstacles.add(obstacle6);
        // Hindernis 7
        Rectangle obstacle7 = new Rectangle(1500, gameHeight - 50, 50, 50);
        obstacle7.setFill(Color.BROWN);
        obstacles.add(obstacle7);
        // Hindernis 8 (doppelt hoch)
        Rectangle obstacle8 = new Rectangle(1700, gameHeight - 100, 50, 100);
        obstacle8.setFill(Color.BROWN);
        obstacles.add(obstacle8);
        // Hindernis 9
        Rectangle obstacle9 = new Rectangle(1900, gameHeight - 50, 50, 50);
        obstacle9.setFill(Color.BROWN);
        obstacles.add(obstacle9);

        gameRoot.getChildren().addAll(obstacle1, obstacle2, obstacle3, obstacle4, obstacle5, obstacle6, obstacle7, obstacle8, obstacle9);

        // --- TIMER ---
        Label timerLabel = new Label("Time: 0 s");
        timerLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
        timerLabel.setLayoutX(10);
        timerLabel.setLayoutY(10);
        gameRoot.getChildren().add(timerLabel);

        final int[] seconds = {0};
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            seconds[0]++;
            timerLabel.setText("Time: " + seconds[0] + " s");
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        final boolean[] gameOver = {false};

        // --- SPIELER-STEUERUNG ---
        gameScene.setOnKeyPressed(event -> {
            if (gameOver[0]) return;
            double oldX = character.getX();
            double oldY = character.getY();
            double newX = oldX;
            double newY = oldY;

            // Sprung (SPACE oder W)
            if ((event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.W) && !isJumping) {
                isJumping = true;
                velocityY = JUMP_STRENGTH;
            }
            // Links-/Rechts-Bewegung (Schrittweite 10)
            if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
                newX = Math.max(0, character.getX() - 10);
            } else if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
                newX = Math.min(gameWidth - character.getWidth(), character.getX() + 10);
            }
            if (isJumping) {
                velocityY += GRAVITY;
                newY += velocityY;
                if (newY >= gameHeight - character.getHeight()) {
                    newY = gameHeight - character.getHeight();
                    isJumping = false;
                    velocityY = 0;
                }
            }
            character.setX(newX);
            character.setY(newY);
            if (collidesWithObstacles(character, obstacles)) {
                character.setX(oldX);
                character.setY(oldY);
            }
        });

        // --- GEGNER-KI (Kein Hindernis-Check, nur Verfolgung des Spielers) ---
        Thread enemyAI = new Thread(() -> {
            while (!gameOver[0]) {
                try {
                    Thread.sleep(16); // ca. 60 FPS
                    Platform.runLater(() -> {
                        double oldEnemyX = enemy.getX();
                        double oldEnemyY = enemy.getY();
                        double deltaX = character.getX() - enemy.getX();
                        double deltaY = character.getY() - enemy.getY();
                        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
                        if (distance != 0) {
                            double speed = 1; // Gegner folgt langsamer
                            double moveX = (deltaX / distance) * speed;
                            double moveY = (deltaY / distance) * speed;

                            // Gegner bewegt sich weiter ohne Hindernis-Check
                            enemy.setX(enemy.getX() + moveX);
                            enemy.setY(enemy.getY() + moveY);
                        }

                        // Kollision zwischen Gegner und Spieler → Game Over
                        if (enemy.getBoundsInParent().intersects(character.getBoundsInParent())) {
                            gameOver[0] = true;
                            timeline.stop();
                            Stage stage = (Stage) enemy.getScene().getWindow();
                            VBox gameOverRoot = new VBox(20);
                            gameOverRoot.setAlignment(Pos.CENTER);
                            Label gameOverLabel = new Label("Game Over!");
                            gameOverLabel.setStyle("-fx-font-size: 48px; -fx-text-fill: red;");
                            Label timeLabel = new Label("Time survived: " + seconds[0] + " s");
                            timeLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");
                            Button restartButton = new Button("Restart");
                            restartButton.setOnAction(e -> {
                                Scene newGameScene = generateGameWindow();
                                stage.setScene(newGameScene);
                            });
                            gameOverRoot.getChildren().addAll(gameOverLabel, timeLabel, restartButton);
                            Scene gameOverScene = new Scene(gameOverRoot, gameWidth, gameHeight);
                            stage.setScene(gameOverScene);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        enemyAI.setDaemon(true);
        enemyAI.start();

        return gameScene;
    }

    private boolean collidesWithObstacles(Rectangle rect, List<Rectangle> obstacles) {
        for (Rectangle obstacle : obstacles) {
            if (rect.getBoundsInParent().intersects(obstacle.getBoundsInParent())) {
                return true;
            }
        }
        return false;
    }
}
