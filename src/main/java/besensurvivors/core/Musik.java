package besensurvivors.core;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;

public class Musik {

    private MediaPlayer mediaPlayer;

    public void playMusic(String filePath) {
        try {
            filePath = filePath.replace('\\', '/');
            Media media = new Media(filePath);
            mediaPlayer = new MediaPlayer(media);

            //mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

            // Musik starten
            mediaPlayer.play();
            System.out.println("Musik gestartet: " + filePath);
        } catch (Exception e) {
            System.err.println("Fehler" + e.getMessage());
        }
    }
//aa

    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            System.out.println("Musik gestoppt.");
        }
    }
}