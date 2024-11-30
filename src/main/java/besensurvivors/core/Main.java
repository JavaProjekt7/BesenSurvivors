package besensurvivors.core;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application  {
    public static void main(String [] args) {
       launch(args);
    }

    public void start(Stage primaryStage)  {
      primaryStage.setWidth(1600);
      primaryStage.setHeight(900);
      primaryStage.setTitle("BesenSurvivors");
      primaryStage.show();

      SceneGenerator generator = new SceneGenerator();
      Scene startWindowScene = generator.generateStartWindow();
      primaryStage.setScene(startWindowScene);

    }


}