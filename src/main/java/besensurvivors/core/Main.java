package besensurvivors.core;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    public static Stage windowRoot;

    private Musik musik;

    // --module-path "C:\Program Files\Java\javafx-sdk-23.0.1\lib" --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.media

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        this.musik = new Musik();

        // Das Start-Fenster konfigurieren
        windowRoot = primaryStage;

        primaryStage.setWidth(1600/2);
        primaryStage.setHeight(900/2);
        primaryStage.setTitle("BesenSurvivors");
        primaryStage.show();
        // Den Scene-Generator initalisieren und die StartWindowScene generieren
        SceneGenerator generator = new SceneGenerator();
        Scene startWindowScene = generator.generateStartWindow(this);

        // Das Fenster auf auf StartWindowScene Scene stellen
        primaryStage.setScene(startWindowScene);

        String currentDir = System.getProperty("user.dir");
        getMusik().playMusic("file:///" + currentDir + "\\musik\\IntroUwU.mp3");
    }
//a
    public Musik getMusik() {
        return musik;
    }
}