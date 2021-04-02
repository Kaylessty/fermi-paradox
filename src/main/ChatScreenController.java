package main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Arrays;

public class ChatScreenController {
    public static void display(Monster mon, Inventory inv, Item[] shop, RoomController con) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(400);
        window.setMaxWidth(400);
        window.setMaxHeight(400);
        window.setMinHeight(400);
        VBox chat = new VBox(10);
        VBox responce = new VBox(10);
        HBox screen = new HBox(10);
        String imageUrl = mon.getImageURL();
        Image pic = new Image("resources/images/Prowler.png", 64, 64, true, true);
        ImageView pictureView = new ImageView(pic);
        Label res = new Label(mon.getIntro());
        responce.getChildren().addAll(pictureView, res);
        Button talk = new Button("Talk");
        Button sh = new Button("Shop");
        sh.setOnAction(e -> {
            Shop.display(inv, shop, con);
        });
        chat.getChildren().addAll(talk, sh);
        screen.getChildren().addAll(chat, res);
        Scene scene1 = new Scene(screen,400, 400);
        //scene 2
        VBox talking = new VBox();
        VBox responce1 = new VBox();
        HBox full = new HBox();
        Text respon = new Text(mon.getTalk());
        respon.setWrappingWidth(300);
        talking.getChildren().addAll(respon);
        for(int i = 0; i < mon.getSpeech().length; i++) {
            Button resp = new Button(mon.getTalks()[i]);
            String k = mon.getSpeech()[i][0];
            resp.setOnAction(e -> {
                respon.setText(k);
            });
            talking.getChildren().addAll(resp);
        }
        Button back = new Button("Return");
        back.setOnAction(e -> window.setScene(scene1));
        full.getChildren().addAll(talking,responce1);
        Scene scene2 = new Scene(full,400,400);
        //window
        talk.setOnAction(e -> {
            window.setScene(scene2);
        });
        window.setScene(scene1);
        window.showAndWait();
//        VBox shopv = new VBox(10);
//        Label titel = new Label("Buy");
//        shopv.getChildren().add(titel);
//        for(int i = 0; i < shop.length; i++) {
//            String imageURL = shop[i].getImageURL();
//            Label line1 = new Label("--------");
//            Image picture = new Image(imageURL, 64, 64, true, true);
//            ImageView pictureView = new ImageView(picture);
//            Integer k = shop[i].getBuyPrice();
//            Button button = new Button(shop[i].getName() + ": " + k.toString());
//            Item buyItem = shop[i];
//            button.setOnAction(e -> {
//                if(Player.getBalance().get() > k) {
//                    con.pickUp(buyItem);
//                    Player.setBalance(Player.getBalance().get() - k);
//                }
//            });
//            shopv.getChildren().addAll(pictureView, button, line1);
//        }
//        VBox sell = new VBox(10);
//        Label title2 = new Label("Sell");
//        sell.getChildren().add(title2);
//        for(int i = 0; i < inv.getContents().length; i++) {
//            if(inv.getContents()[i] == null) {
//                continue;
//            } else {
//                Label line1 = new Label("--------");
//                String imageURL = inv.getContents()[i].getImageURL();
//                Image picture = new Image(imageURL, 64, 64, true, true);
//                ImageView pictureView = new ImageView(picture);
//                Integer k = (Integer) inv.getContents()[i].getSellPrice();
//                Button button = new Button(inv.getContents()[i].getName() + ": " + k.toString());
//                Item toDrop = inv.getContents()[i];
//                button.setOnAction(e -> {
//                    con.remove(toDrop);
//                    Player.setBalance(Player.getBalance().get() + k);
//                    button.setText("SOLD");
//                    button.setDisable(true);
//                        });
//                sell.getChildren().addAll(pictureView, button, line1);
//            }
//        }
//
//        shopv.setAlignment(Pos.CENTER);
//        HBox screen = new HBox(20);
//        screen.getChildren().addAll(sell,shopv);
//        ScrollPane scroll = new ScrollPane(screen);
//        Scene scene = new Scene(scroll,470, 800);
//        window.setScene(scene);
//        window.showAndWait();
    }
}
