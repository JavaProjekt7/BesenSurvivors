package besensurvivors.core;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class SceneGenerator {
  public Scene generateStartWindow() {
      // Die Objekte erstellen, in denen alle Elemente sind
      VBox root = new VBox();
      Scene scene = new Scene(root);

      // Label erstellen, der den Titel beinhaltet
      Label label = new Label("BesenSurvivors");
      label.setStyle("-fx-font-size: 37px;");
      root.getChildren().add(label);

      // Button für das Spiel starten erstellen
      Button startGameButton = new Button();
      startGameButton.setText("Start Game");
      startGameButton.setPrefHeight(50);
      startGameButton.setPrefWidth(200);
      root.getChildren().add(startGameButton);

      // Button für das beenden des Spiels
      Button endGameButton = new Button();
      endGameButton.setText("Endgame");
      endGameButton.setPrefHeight(50);
      endGameButton.setPrefWidth(200);
      root.getChildren().add(endGameButton);
      endGameButton.setOnAction(event -> ((Stage) endGameButton.getScene().getWindow()).close());




      // Den root (VBox) modifizieren
      root.setAlignment(Pos.CENTER);
      root.setStyle("-fx-background-color: #bb33ff");
    return scene;
    }
}
