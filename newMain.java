/*
 * TODO : get rid of nesting if possible - try not to have button events nested under the enter event. How to achieve this? 
 * TODO : play computer player cards. after withdrawing, have option to play card
 */


import javafx.application.Application;
import static javafx.application.Application.launch;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class newMain extends Application {

	private GameManager gm; //game manager
//	private ArrayList<Button> hand; //array of buttons for player's hand
	private Button withdraw; //one button for withdraw pile (maybe 2 separate images for when pile has cards/no cards?)
//	private ImageView discardTop; //top of discard pile image (set image after every card play) 
	private String name; //player name
	private Button reset;
	private BorderPane main;
	private Stage prim;
	private TextField userTextField;
//	private ArrayList<Card> playerHand; //or do a player object?
	private FlowPane flow;
	private TilePane middle;
	private Label userName;
	
	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) 
    {
    	prim = new Stage(); //??
        primaryStage.setTitle("Crazy Eights");
        //creates grid pane (welcome screen)
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //adds components to grid pane
        Text scenetitle = new Text("Welcome to Crazy 8's!");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label playerName = new Label("Player Name:");
        grid.add(playerName, 0, 1);

        //types in name. press enter to start game and deal cards
       // TextField userTextField = new TextField();
        userTextField = new TextField();
        userTextField.setOnAction(this::textField);
        grid.add(userTextField, 1, 1);
    //    userTextField.addEventHandler(eventType, eventHandler);

        //sets primary scene to starting screen
        Scene scene = new Scene(grid, 600, 400);
        prim.setScene(scene);
        prim.show();
  //      primaryStage.setScene(scene);
  //      primaryStage.show();
        
        //this button doesn't do anything
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
        
        //shows rules when Rules button is pressed
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Stage ruleStage = new Stage();
                ruleStage.setTitle("How to Play");
                GridPane rulePane = new GridPane();
                Text txt = new Text("Rules"
                        + "\n\n\tTo begin, each player starts with 8 cards, and the goal is to be the first player to play all of the cards in their hand"
                        + "\n\tWhen It's your turn you must play a card from your hand that is either the same suit or value as the current top card"
                        + "\n\tIf you can't play any of your cards you must withdraw from the pile"
                        + "\n\tIf the withdrawn card is playable you can play it immediately"
                        + "\n\n\tThere are 3 kinds of special cards in the game"
                        + "\n\tIf a card with the value 2 is played the next player must pick up two cards before playing"
                        + "\n\tThis player can also play a 2 and meaning that the next player needs to pick up 4 cards"
                        + "\n\tThis continues up to 4 turns"
                        + "\n\n\tIf a Jack is played the next player's turn is skipped"
                        + "\n\n\tAn 8 can be played at any time in the game no matter what the top card is in the pile."
                        + "\n\tThe player who plays an eight then gets to decide what suit should be played next"
                        + "\n\n\tThe player who empties their hand is declared the winner!");
                txt.setFont(Font.font("Tahoma", FontWeight.LIGHT, 11));
                rulePane.getChildren().add(txt);
                
                
                Button closeBtn = new Button("Close");
                HBox hbcloseBtn = new HBox(10);
                hbcloseBtn.getChildren().add(closeBtn); 
                rulePane.getChildren().add(hbcloseBtn);
                
                closeBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                            public void handle(ActionEvent ev) {
                                ruleStage.close(); //closes stage
                            }
                });
                
                Scene ruleScene = new Scene(rulePane, 600, 400);
                ruleStage.setScene(ruleScene);
                ruleStage.show();
                
                
                //ruleStage.setFullScreen(true);
            }
        });
    }        
	
    //action event methods
	public void textField(ActionEvent event)
	{
		if (event.getSource()==userTextField)
			 name = userTextField.getText(); //gets user input (name)
		  gm = new GameManager(name); //creates game manager and deals cards
		  gm.dealCards();
		  
		  //creates new view of screen
	//	  BorderPane main = new BorderPane();
		  main = new BorderPane();
		  
		  //middle part
	//	  TilePane middle = new TilePane();
		  middle=new TilePane();
		  middle.setAlignment(Pos.CENTER);
		    middle.setPadding(new Insets(5, 0, 5, 0));
		    middle.setVgap(4);
		    middle.setHgap(4);
		   
		/*    Image getDiscardTop = new Image(gm.getDeck().getTopDiscard().getImgPath()); // get image path from top of discard pile
		    discardTop = new ImageView(getDiscardTop); //image view (displays image from path)
		    discardTop.setFitWidth(100);
		    discardTop.setPreserveRatio(true);*/
		    
		    //displays back of card image
		    Image getBackOfCard = new Image ("file:///C:/Users/Heather/workspace/Assignment/graphics/backOfCard.png");//create file path to image
		    ImageView backOfCard = new ImageView(getBackOfCard); //displays image
		    backOfCard.setFitWidth(100);
		    backOfCard.setPreserveRatio(true);
		    withdraw = new Button(); 
		    withdraw.setOnAction(this::processButtonPress);
		    withdraw.setGraphic(backOfCard); //sets button image
		    
		    middle.getChildren().addAll(gm.getDeck().getImageTop(), withdraw);
		  
		    //bottom part 
		  //  FlowPane flow = new FlowPane();
		    flow=new FlowPane();
		    flow.setAlignment(Pos.CENTER);
		    flow.setPadding(new Insets(5, 0, 5, 0));
		    flow.setVgap(4);
		    flow.setHgap(6);
		   
		    //label with player's name
		//    Label userName = new Label("\t"+gm.getPlayer().getName() +"'s Cards: \t");
		    userName = new Label("\t"+gm.getPlayer().getName() +"'s Cards: \t");
		    
		    flow.getChildren().add(userName);
		    for (int i=0; i < gm.getPlayer().getHand().size();i++)
		    {
		    	flow.getChildren().add(gm.getPlayer().getHand().get(i).getButton());
		    	gm.getPlayer().getHand().get(i).getButton().setOnAction(this::processButtonPress);
		    }
		  			
		  			//top part
		    TilePane top = new TilePane();
		    top.setAlignment(Pos.CENTER);
			   top.setPadding(new Insets(5, 0, 5, 0));
			   top.setVgap(4);
			   top.setHgap(75);
			    
			 //add cp number of cards in hand
			  Label cp1 = new Label("CP1 has "+gm.getCompPlayer(0).getHand().size()+" cards.");
			  Label cp2 = new Label("CP2 has "+gm.getCompPlayer(1).getHand().size()+" cards.");
			  Label cp3 = new Label("CP3 has "+gm.getCompPlayer(2).getHand().size()+" cards.");
			   reset = new Button("RESTART GAME");
			  reset.setOnAction(this::textField);
		
			  top.getChildren().addAll(cp1,cp2,cp3, reset);
		 
			  
		  main.setBottom(flow); //shows player's cards
		  main.setCenter(middle); //create center pane (top card picture and withdraw pile)
		  main.setTop(top); //shows number of cards in computer player's hands
		//  Stage secondStage = new Stage();
		  
		  Scene mainGameScene = new Scene(main, 800,500); 
		  prim.setScene(mainGameScene);
	//	  secondStage.setScene(mainGameScene);
	//	  secondStage.show();
		//  primaryStage.setScene(mainGameScene);
		  
		  
	}
	//new withdraw method
	public void processButtonPress(ActionEvent e)
	{
		if (e.getSource()==withdraw)
		{
			gm.play(-1, false, gm.getPlayer());
			flow.getChildren().add(gm.getPlayer().getHand().get(gm.getPlayer().getHand().size()-1).getButton());
			gm.getPlayer().getHand().get(gm.getPlayer().getHand().size()-1).getButton().setOnAction(this::processButtonPress);
		}
		else //if playing a card
		{	
			int index=-1;
			for (int i=0; i<gm.getPlayer().getHand().size();i++)
			{
				if(e.getSource().equals(gm.getPlayer().getHand().get(i).getButton()))
					index=i;
			}
					
		//	Button clickedButton = (Button) e.getTarget();
		//	int index= gm.getPlayer().getButtonIndex(clickedButton);
			System.out.println(index);
			if (index!=-1)
			{
				gm.play(index, false, gm.getPlayer());
				flow.getChildren().clear();
				flow.getChildren().add(userName);
				middle.getChildren().clear();
				middle.getChildren().addAll(gm.getDeck().getImageTop(), withdraw);
			}
			//changes cards
		    for (int i=0; i < gm.getPlayer().getHand().size();i++)
		    {
		    	flow.getChildren().add(gm.getPlayer().getHand().get(i).getButton());
		    	gm.getPlayer().getHand().get(i).getButton().setOnAction(this::processButtonPress);
		    }
		}
	}

}


		
	

