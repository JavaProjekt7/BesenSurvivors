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
public class SceneGenerator {

    public Scene generateStartWindow() {
        VBox root = new VBox(20);
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
        Scene gameScene = new Scene(gameRoot, 800, 600); // Größe des Spiels

        // Erstelle einen Charakter (blaues Rechteck)
        Rectangle character = new Rectangle(50, 50, Color.BLUE);
        character.setX(375); // Startposition X
        character.setY(275); // Startposition Y

        gameRoot.getChildren().add(character);

        // Steuerung des Charakters
        gameScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                character.setY(character.getY() - 5); // nach oben bewegen
            } else if (event.getCode() == KeyCode.DOWN) {
                character.setY(character.getY() + 5); // nach unten bewegen
            } else if (event.getCode() == KeyCode.LEFT) {
                character.setX(character.getX() - 5); // nach links bewegen
            } else if (event.getCode() == KeyCode.RIGHT) {
                character.setX(character.getX() + 5); // nach rechts bewegen
            }
        });



        return gameScene;
    }
}
