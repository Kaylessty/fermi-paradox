package view;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Pos;


    public class FirstScreen {
        String title = "Space Game!";
        Button exit;
        Button next;
        Scene page1;

        /**
         * Initializes the first page. will initialize the same page every time, button setOnActions must be set up in main
         * page
         */
        public FirstScreen() {
            exit = new Button ("Exit Game");
            next = new Button("New Game");
            Label usedtitle = new Label(title);
            usedtitle.setTextFill(Color.WHITE);
            usedtitle.setFont(Font.font("Lao MN", FontWeight.BOLD, 40));
            VBox layoutPage1 = new VBox(1);
            layoutPage1.setStyle("-fx-background-image: url('resources/images/Background.png');");
            layoutPage1.setSpacing(50);
            layoutPage1.setAlignment(Pos.BASELINE_CENTER);
            page1 = new Scene(layoutPage1,800,800);
            layoutPage1.getChildren().addAll(usedtitle,next,exit);
        }

        /**
         * Returns the first Scene
         * @return the Scene which was initalized.
         */
        public Scene getScene() {
            return page1;
        }

        /**
         * Returns Exit button
         * @return The exit button on first screen.
         */
        public Button getExit() {return exit;}

        /**
         * Returns Next button
         * @return The next button on first screen.
         */
        public Button getNext() {return next;}
}
