package besensurvivors.core;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Gamemap extends Pane {
    public Gamemap() {
        // Hintergrundbild laden
        Image backgroundImage = new Image(getClass().getResource("/pictures/map.png").toString()); // Pfad zur Karte anpassen
        ImageView backgroundView = new ImageView(backgroundImage);

        // Bild skalieren, um das Fenster auszufüllen
        backgroundView.setFitWidth(800); // Breite des Fensters
        backgroundView.setFitHeight(600); // Höhe des Fensters
        backgroundView.setPreserveRatio(false);

        // Bild als Hintergrund hinzufügen
        this.getChildren().add(backgroundView);
    }
}
