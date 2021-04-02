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

public class Shop {
    public static void display(Inventory inv, Item[] shop, RoomController con) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(18);
        VBox shopv = new VBox(10);
        Label titel = new Label("Buy");
        shopv.getChildren().add(titel);
        for(int i = 0; i < shop.length; i++) {
            Integer k = shop[i].getBuyPrice();
            Label name = new Label(k.toString());
            Button button = new Button(shop[i].getName());
            Item buyItem = shop[i];
            button.setOnAction(e -> {
                if(Player.getBalance().get() > k) {
                    con.pickUp(buyItem);
                    Player.setBalance(Player.getBalance().get() - k);
                }
            });
            shopv.getChildren().addAll(name, button);
        }
        VBox sell = new VBox(10);
        Label title2 = new Label("Sell");
        sell.getChildren().add(title2);
        for(int i = 0; i < inv.getContents().length; i++) {
            if(inv.getContents()[i] == null) {
                continue;
            } else {
                Integer k = (Integer) inv.getContents()[i].getSellPrice();
                Label name = new Label(k.toString());
                Button button = new Button(inv.getContents()[i].getName());
                Item toDrop = inv.getContents()[i];
                button.setOnAction(e -> {
                    con.remove(toDrop);
                    Player.setBalance(Player.getBalance().get() + k);
                    window.close();
                    Shop.display(inv, shop, con);
                        });
                sell.getChildren().addAll(name,button);
            }
        }
        shopv.setAlignment(Pos.CENTER);
        HBox screen = new HBox(20);
        screen.getChildren().addAll(sell,shopv);
        Scene scene = new Scene(screen);
        window.setScene(scene);
        window.showAndWait();
    }
}
