package gui;

import be.Movie;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class Window2Controller {

    @FXML
    private MediaView mediaViewWatch;

    private MediaPlayer mediaPlayer;

    public void setMovieToWatch(Movie movie) {
        File file = new File(movie.getFileLink());
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaViewWatch.setMediaPlayer(mediaPlayer);
    }

    @FXML
    private void buttonPlayMovie() {
        mediaPlayer.play();
    }

    @FXML
    private void buttonPauseMovie() {
        mediaPlayer.pause();
    }
}
