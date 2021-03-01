package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static javafx.geometry.Pos.CENTER_LEFT;

public class ConfigScreen {
    private int screenWidth;
    private int screenHeight;
    private TextField nameEntry;

    private Button proceed;

    private RadioButton easy;
    private RadioButton amateur;
    private RadioButton hard;
    private RadioButton blaster;
    private RadioButton spaceSword;
    private RadioButton sonarGun;

    private ToggleGroup difficulties;
    private ToggleGroup weapons;

    private Label nameLabel;
    private Label difficultyLabel;
    private Label weaponLabel;
    private HBox row1;
    private HBox row2;
    private HBox row3;
    private VBox pillar;
    private BorderPane frame;
    private Scene configScreen;

    /**
     * By default, this constructor initializes the game screen to size 800 by 800
     */
    public ConfigScreen() {
        screenHeight = 600;
        screenWidth = 800;
    }

    /**
     * This is a constructor that allows for changing of screen size
     * @param width the width of the screen
     * @param height the height of the screen
     */
    public ConfigScreen(int width, int height) {
        screenWidth = width;
        screenHeight = height;
    }

    /**
     * This method sets the configuration screen. It places all the nodes and the scene,
     * and sets the scene on the stage.
     * Functionality is implemented in Config_Screen_Controller.java
     */
    public void setConfigScreen() {
        nameEntry = new TextField();
        nameEntry.setPrefColumnCount(30);
        nameLabel = new Label("Enter your name: ");
        row1 = new HBox(10, nameLabel, nameEntry); // Placing label beside corresponding text entry

        // Creating difficulty options and adding them to the toggle group
        difficultyLabel = new Label("Select the difficulty: ");
        easy = new RadioButton("easy");
        amateur = new RadioButton("amateur");
        hard = new RadioButton("hard");
        difficulties = new ToggleGroup();
        easy.setToggleGroup(difficulties);
        amateur.setToggleGroup(difficulties);
        hard.setToggleGroup(difficulties);

        // Creating weapon options and adding them to a toggle group
        weaponLabel = new Label("Select your starting weapon: ");
        blaster = new RadioButton("Blaster");
        spaceSword = new RadioButton("Space Sword");
        sonarGun = new RadioButton("Sonar Gun");
        weapons = new ToggleGroup();
        blaster.setToggleGroup(weapons);
        spaceSword.setToggleGroup(weapons);
        sonarGun.setToggleGroup(weapons);

        // Creating the continue button providing its functionality
        proceed = new Button("Continue");

        // Adding radio buttons to corresponding labels, 
        // and adding those bunches together into a VBox
        row2 = new HBox(10, difficultyLabel, easy, amateur, hard);
        row3 = new HBox(10, weaponLabel, blaster, spaceSword, sonarGun);
        pillar = new VBox(20, row1, row2, row3);

        // Decorating the buttons


        // Adding boxes to the scene and the scene to the stage
        frame = new BorderPane(pillar);
        frame.setAlignment(pillar, CENTER_LEFT);
        frame.setBottom(proceed);
        configScreen = new Scene(frame, screenWidth, screenHeight);
        configScreen.setFill(Color.MEDIUMORCHID);
    }

    /**
     * This is a getter for the proceed button
     * @return the button to proceed
     */
    public Button getProceed() {
        return proceed;
    }

    /**
     * This is a getter that allows for the retrieval of the user's name
     * @return the TextField where the user enters his/her name
     */
    public TextField getNameEntry() {
        return nameEntry;
    }

    /**
     * This is a getter for the ToggleGroup that controls difficulty options
     * @return the ToggleGroup with difficulty radio buttons
     */
    public ToggleGroup getDifficulties() {
        return difficulties;
    }

    /**
     * This is a getter for the ToggleGroup that controls weapon options
     * @return The Togglegroup with weapon radio buttons
     */
    public ToggleGroup getWeapons() {
        return weapons;
    }

    /**
     * This method is a getter for the scene
     * @return the configuration screen
     */
    public Scene getScene() { 
        return configScreen;
    };

}
