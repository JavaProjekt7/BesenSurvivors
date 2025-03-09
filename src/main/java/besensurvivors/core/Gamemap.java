package besensurvivors.core;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Gamemap extends Pane {
    private static final double MAP_WIDTH = 3000;  // Erweiterte Breite für mehr Bewegungsfreiheit
    private static final double MAP_HEIGHT = 1000; // Höhe bleibt gleich

    public Gamemap() {
        // Hintergrundbild laden
        String currentDir = System.getProperty("user.dir");
        Image backgroundImage = new Image("file:///" + currentDir + "\\pictures\\map.png");
        ImageView backgroundView = new ImageView(backgroundImage);

        // Map-Größe anpassen
        backgroundView.setFitWidth(MAP_WIDTH);
        backgroundView.setFitHeight(MAP_HEIGHT);
        backgroundView.setPreserveRatio(false);

        // Hintergrundbild hinzufügen
        this.getChildren().add(backgroundView);
    }

    public static double getMapWidth() {
        return MAP_WIDTH;
    }

    public static double getMapHeight() {
        return MAP_HEIGHT;
    }

    public static void enableFullscreen(Stage stage) {
        stage.setFullScreen(true);
    }
}
