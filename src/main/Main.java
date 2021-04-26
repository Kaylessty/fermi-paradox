package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Main extends Application {

    private Parent rootNode;
    private static Stage stage;
    private MediaPlayer mediaPlayer;



    public static void main(final String[] args) {
        Application.launch(args);
    }

    public static Stage getPrimaryStage() {
        return Main.stage;
    }


    @Override
    public void init() { //throws Exception {
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(getClass().getResource("/view/firstscreen.fxml"));
            rootNode = fxmlLoader.load();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("Fermi Paradox");
        stage.setScene(new Scene(rootNode, 800, 600));
        music();
        stage.show();
    }

    public void music() throws URISyntaxException {
        String musicFile = getClass().getResource("/resources/sounds/song.mp3").toURI().toString();
        Media sound = new Media(musicFile);
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
}