package besensurvivors.core;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application  {
    public static void main(String [] args) {
       launch(args);
    }

    public void start(Stage primaryStage)  {
      // Das Start-Fenster konfigurieren
      primaryStage.setWidth(1600);
      primaryStage.setHeight(900);
      primaryStage.setTitle("BesenSurvivors");
      primaryStage.show();

      // Den Scene-Generator initalisieren und die StartWindowScene generieren
      SceneGenerator generator = new SceneGenerator();
      Scene startWindowScene = generator.generateStartWindow();

      // Das Fenster auf auf StartWindowScene Scene stellen
      primaryStage.setScene(startWindowScene);

    }


}