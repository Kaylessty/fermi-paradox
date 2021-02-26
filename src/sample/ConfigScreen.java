import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import static javafx.geometry.Pos.CENTER_LEFT;
import static javafx.geometry.Pos.TOP_LEFT;

public class ConfigScreen extends Application {
    private final int SCREENWIDTH = 800;
    private final int SCREENHEIGHT = 800;

    public void setConfigurationScreen(Stage primaryStage) {
        TextField nameEntry = new TextField();
        nameEntry.setPrefColumnCount(30);
        Label nameLabel = new Label("Enter your name: ");
        HBox row1 = new HBox(10, nameLabel, nameEntry); // Placing label beside corresponding text entry

        // Creating difficulty options and adding them to the toggle group
        Label difficultyLabel = new Label("Select the difficulty: ");
        RadioButton easy = new RadioButton("easy");
        RadioButton amateur = new RadioButton("amateur");
        RadioButton hard = new RadioButton("hard");
        ToggleGroup difficulties = new ToggleGroup();
        easy.setToggleGroup(difficulties);
        amateur.setToggleGroup(difficulties);
        hard.setToggleGroup(difficulties);

        // Creating weapon options and adding them to a toggle group
        Label weaponLabel = new Label("Select your starting weapon: ");
        RadioButton blaster = new RadioButton("Blaster");
        RadioButton spaceSword = new RadioButton("Space Sword");
        RadioButton sonarGun = new RadioButton("Sonar Gun");
        ToggleGroup weapons = new ToggleGroup();
        blaster.setToggleGroup(weapons);
        spaceSword.setToggleGroup(weapons);
        sonarGun.setToggleGroup(weapons);

        // Creating the continue button providing its functionality
        Button proceed = new Button("Continue");
        proceed.setOnAction((ActionEvent e) -> {
            /*
            I am unable to close the dialog box that pops up, so for now I have commented out this section of code.


            if ((nameEntry.getText()).trim().equals("")) {
                Dialog warning = new Dialog();
                warning.setContentText("Name must have 1 non-space character");
                warning.showAndWait();
            } else {
                /*
                player.setName("nameEntry.getText()");
                playerInventory.add()
                 */
            }
        });

        // Adding radio buttons to corresponding labels, and adding those bunches together into a VBox
        HBox row2 = new HBox(10, difficultyLabel, easy, amateur, hard);
        HBox row3 = new HBox(10, weaponLabel, blaster, spaceSword, sonarGun);
        VBox pillar = new VBox(20, row1, row2, row3);



        // Adding boxes to the scene and the scene to the stage
        BorderPane frame = new BorderPane(pillar);
        frame.setAlignment(pillar, CENTER_LEFT);
        frame.setBottom(proceed);
        Scene configScreen = new Scene(frame, 500, 500);
        configScreen.setFill(Color.CORAL);
        primaryStage.setScene((configScreen));
        primaryStage.setTitle("Player Configuration Screen");
        primaryStage.show();
    }

}