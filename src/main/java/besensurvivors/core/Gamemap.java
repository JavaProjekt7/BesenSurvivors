package besensurvivors.core;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Path;

public class Gamemap extends Pane {
    public Gamemap() {
        // Hintergrundbild laden
        String currentDir = System.getProperty("user.dir");
        //System.out.println(currentDir);

        Image backgroundImage = new Image("file:///" + currentDir + "\\pictures\\map.png"); // Pfad zur Karte anpassen
        ImageView backgroundView = new ImageView(backgroundImage);

        // Bild skalieren, um das Fenster auszufüllen
        backgroundView.setFitWidth(Main.windowRoot.getWidth()); // Breite des Fensters
        backgroundView.setFitHeight(Main.windowRoot.getHeight()); // Höhe des Fensters
        backgroundView.setPreserveRatio(false);

        // Bild als Hintergrund hinzufügen
        this.getChildren().add(backgroundView);
    }
}
