package crazyeights;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartPage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Crazy Eights");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label playerName = new Label("Player Name:");
        grid.add(playerName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Button btn = new Button("Start");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 3);

        Button btn2 = new Button("Rules");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(btn2);
        grid.add(hbBtn2, 1, 3);
        
        btn2.setOnAction(new EventHandler<ActionEvent>() {
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
                txt.setFont(Font.font("Tahoma", FontWeight.LIGHT, 11));
                rulePane.getChildren().add(txt);
                
                Button closeBtn = new Button("Close");
                HBox hbcloseBtn = new HBox(10);
                hbcloseBtn.getChildren().add(closeBtn);
                rulePane.getChildren().add(hbcloseBtn);
                
                closeBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                            public void handle(ActionEvent ev) {
                                ruleStage.close();
                            }
                });
                
                Scene ruleScene = new Scene(rulePane, 600, 400);
                ruleStage.setScene(ruleScene);
                ruleStage.show();
                
                
                //ruleStage.setFullScreen(true);
            }
        });
        
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
            }
        });
        
        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
        //primaryStage.setFullScreen(true);
    }
}