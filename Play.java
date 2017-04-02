package crazyeights;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Play extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Crazy Eights");
        
        Pane pane = new Pane();
        
        Image image = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSQnvgaYWuPAVn2phm5JprU1OLEX1QJCLpNL0HqtyJQHi6GmCCT-Q");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        
        Image image1 = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSQnvgaYWuPAVn2phm5JprU1OLEX1QJCLpNL0HqtyJQHi6GmCCT-Q");
        ImageView imageView1 = new ImageView();
        imageView1.setImage(image1);
        
        Image image2 = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSQnvgaYWuPAVn2phm5JprU1OLEX1QJCLpNL0HqtyJQHi6GmCCT-Q");
        ImageView imageView2 = new ImageView();
        imageView2.setImage(image2);
        
        //top of discard pile
        HBox discard = new HBox();
        discard.setPadding(new Insets(50, 0, 0, 0));
        discard.getChildren().add(imageView2);
        
        //row for player cards
        HBox box = new HBox();
        box.setSpacing(10);
        box.setPadding(new Insets(0, 0, 50, 0));
        Text text = new Text("Player hand");
        text.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        box.getChildren().add(text);
        box.getChildren().add(imageView);
        box.getChildren().add(imageView1);
        box.getChildren().add(imageView2);
        box.setAlignment(Pos.BOTTOM_CENTER);
        
        Button button = new Button("How to Play");
        HBox hbButton = new HBox();
        hbButton.getChildren().add(button);
        
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Stage ruleStage = new Stage();
                ruleStage.setTitle("How to Play");
                GridPane rulePane = new GridPane();
                Text txt = new Text("Rules"
                        + "\n\nTo begin, each player starts with 8 cards, and the goal is to be the first player to play all of the cards in their hand"
                        + "\nWhen It's your turn you must play a card from your hand that is either the same suit or value as the current top card"
                        + "\nIf you can't play any of your cards you must withdraw from the pile"
                        + "\nIf the withdrawn card is playable you can play it immediately"
                        + "\n\nThere are 3 kinds of special cards in the game"
                        + "\nIf a card with the value 2 is played the next player must pick up two cards before playing"
                        + "\nThis player can also play a 2 and meaning that the next player needs to pick up 4 cards"
                        + "\nThis continues up to 4 turns"
                        + "\n\nIf a Jack is played the next player's turn is skipped"
                        + "\n\nAn 8 can be played at any time in the game no matter what the top card is in the pile."
                        + "\nThe player who plays an eight then gets to decide what suit should be played next"
                        + "\n\nThe player who empties their hand is declared the winner!");
                txt.setFont(Font.font("Tahoma", FontWeight.LIGHT, 20));
                rulePane.getChildren().add(txt);
                
                Scene ruleScene = new Scene(rulePane, 300, 300);
                ruleStage.setScene(ruleScene);
                ruleStage.show();
                ruleStage.setFullScreen(true);
            }
        });
        
        pane.getChildren().add(text);
        pane.getChildren().add(discard);
        pane.getChildren().add(box);
        pane.getChildren().add(hbButton);
        
        hbButton.setAlignment(Pos.BOTTOM_CENTER);
        
        final Scene scene = new Scene(pane, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        primaryStage.setFullScreen(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}