package main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import javax.swing.*;

public class Shop {
    public static void display(Inventory inv, Item[] shop, RoomController con) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(470);
        window.setMaxWidth(470);
        window.setMaxHeight(800);
        window.setMinHeight(800);
        VBox shopv = new VBox(10);
        Label titel = new Label("Buy");
        shopv.getChildren().add(titel);
        for(int i = 0; i < shop.length; i++) {
            String imageURL = shop[i].getImageURL();
            Label line1 = new Label("--------");
            Image picture = new Image(imageURL, 64, 64, true, true);
            ImageView pictureView = new ImageView(picture);
            Integer k = shop[i].getBuyPrice();
            Button button = new Button(shop[i].getName() + ": " + k.toString());
            Item buyItem = shop[i];
            button.setOnAction(e -> {
                if(Player.getBalance().get() > k) {
                    con.pickUp(buyItem);
                    Player.setBalance(Player.getBalance().get() - k);
                }
            });
            shopv.getChildren().addAll(pictureView, button, line1);
        }
        VBox sell = new VBox(10);
        Label title2 = new Label("Sell");
        sell.getChildren().add(title2);
        for(int i = 0; i < inv.getContents().length; i++) {
            if(inv.getContents()[i] == null) {
                continue;
            } else {
                Label line1 = new Label("--------");
                String imageURL = inv.getContents()[i].getImageURL();
                Image picture = new Image(imageURL, 64, 64, true, true);
                ImageView pictureView = new ImageView(picture);
                Integer k = (Integer) inv.getContents()[i].getSellPrice();
                Button button = new Button(inv.getContents()[i].getName() + ": " + k.toString());
                Item toDrop = inv.getContents()[i];
                button.setOnAction(e -> {
                    con.remove(toDrop);
                    Player.setBalance(Player.getBalance().get() + k);
                    button.setText("SOLD");
                    button.setDisable(true);
                        });
                sell.getChildren().addAll(pictureView, button, line1);
            }
        }

        shopv.setAlignment(Pos.CENTER);
        HBox screen = new HBox(20);
        screen.getChildren().addAll(sell,shopv);
        ScrollPane scroll = new ScrollPane(screen);
        Scene scene = new Scene(scroll,470, 800);
        window.setScene(scene);
        window.showAndWait();
    }
}
