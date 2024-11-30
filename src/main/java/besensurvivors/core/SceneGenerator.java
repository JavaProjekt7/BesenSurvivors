package besensurvivors.core;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;


public class SceneGenerator {
  public Scene generateStartWindow() {
      StackPane stackPane = new StackPane();
      Scene scene = new Scene(stackPane);

      Label label = new Label("BesenSurvivors");
      label.setStyle("-fx-font-size: 40px;");
      stackPane.getChildren().add(label);


    return scene;
    }
}
