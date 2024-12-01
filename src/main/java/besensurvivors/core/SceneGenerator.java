package besensurvivors.core;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


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

        //Einstellungs button
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
}