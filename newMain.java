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
	private String name; //player name
	private Button reset, withdraw, next; //reset to restart game, next to let computer players go
	private BorderPane main, win, lose;
	private Stage prim;
	private TextField userTextField;
	private FlowPane flow;
	private TilePane middle;
	private Label userName, cp1, cp2, cp3;
	private TilePane top;
	private boolean firstPlay, eight;
	private int two; //how many two's have been played in a row
	private GridPane suitPicker;
//	private Label userName2, Heatherscool;
	
	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) 
    {
    	firstPlay=true; eight=false;
    	win=new BorderPane();
    	Label winLabel = new Label("YOU WIN!!");
    	win.setCenter(winLabel);
    	lose=new BorderPane();
    	Label loseLabel= new Label("YOU LOSE");
    	lose.setCenter(loseLabel);
    	
    	prim = new Stage(); //??
    	prim.setTitle("Crazy Eights");
    //    primaryStage.setTitle("Crazy Eights");
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
		    top = new TilePane();
		    top.setAlignment(Pos.CENTER);
			   top.setPadding(new Insets(5, 0, 5, 0));
			   top.setVgap(4);
			   top.setHgap(75);
			    
			 //add cp number of cards in hand
			  next = new Button("Next Player Go"); //TODO add this button when player plays; remove when it's their turn
			  next.setOnAction(this::computerPlayerTurn); //add turn count in gm
			   
			  cp1 = new Label("CP1 has "+gm.getCompPlayer(0).getHand().size()+" cards.");
			  cp2 = new Label("CP2 has "+gm.getCompPlayer(1).getHand().size()+" cards.");
			  cp3 = new Label("CP3 has "+gm.getCompPlayer(2).getHand().size()+" cards.");
			   reset = new Button("RESTART GAME");
			  reset.setOnAction(this::textField);
		
			  top.getChildren().addAll(cp1,cp2,cp3, reset);
		 
			  
		  main.setBottom(flow); //shows player's cards
		  main.setCenter(middle); //create center pane (top card picture and withdraw pile)
		  main.setTop(top); //shows number of cards in computer player's hands
		  
		  Scene mainGameScene = new Scene(main, 800,500); 
		  prim.setScene(mainGameScene);
		  
	}
	//new player play method
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
		
		//TESTING 123
		if (!firstPlay) //checks for special cards as long as a player has placed a card
		{
			//8 update suit
			if(!eight){ //what is this boolean for again?
				if (gm.getDeck().getTopDiscard().getValue().equals("8"))
				{
					suitPicker = new GridPane();
					//TODO update to images
					Button clubs = new Button("Clubs");
					suitPicker.add(clubs, 0, 0);
					Button hearts = new Button("Hearts");
					suitPicker.add(hearts, 1, 0);
					Button diamonds = new Button("Diamonds");
					suitPicker.add(diamonds, 0, 1);
					Button spades = new Button("Spades");
					suitPicker.add(spades, 1, 1);
					Scene SPicker = new Scene(suitPicker);
					Stage SP = new Stage();
					SP.setTitle("Pick a Suit");
					SP.setScene(SPicker);
					SP.show();
					
					eight = true;
				}
			}
			else
				gm.setOSuit(gm.getDeck().getTopDiscard().getSuit());
					
			//test if jack
			if (gm.getDeck().getTopDiscard().getValue().equals("J"))
				gm.setTurn(2);
			else
				gm.setTurn(1);
			//two's update
			if(gm.getDeck().getTopDiscard().getValue().equals("2"))
				two++;
			else
				two=0;
		}	 			
		
		
		top.getChildren().add(0, next); //TODO only add once(do not add again if playing card after withdrawing)
		//TODO test this one (must win)
		if (gm.getPlayer().getHand().isEmpty())
		{
			win.setBottom(reset); //test
			Scene winScene = new Scene(win,800,500);
			prim.setScene(winScene);
		}
	}

	public void computerPlayerTurn(ActionEvent e)
	{
		int turn = gm.getTurn();
		if (turn==1)
		{
			//TESTING
			if (two>=1 && !firstPlay)
			{
				gm.play(-1, false, gm.getCompPlayer(0));
				gm.play(-1, false, gm.getCompPlayer(0));
			}
			if (two>=2)
			{
				gm.play(-1, false, gm.getCompPlayer(0));
				gm.play(-1, false, gm.getCompPlayer(0));
			}
			if (two>=3)
			{
				gm.play(-1, false, gm.getCompPlayer(0));
				gm.play(-1, false, gm.getCompPlayer(0));
			}
			if (two>=4)
			{
				gm.play(-1, false, gm.getCompPlayer(0));
				gm.play(-1, false, gm.getCompPlayer(0));
			}
			//END TEST
			
			
			
			int chosen= gm.compDecide(gm.getCompPlayer(0),gm.getCompPlayer(1));
			System.out.println("index testing1: " + chosen);
			if (chosen==-1)
			{
				gm.getCompPlayer(0).addToHand(gm.getDeck().withdraw());
				System.out.println("CP3 withdrew a " + gm.getCompPlayer(0).getHand().get(gm.getCompPlayer(0).getHand().size()-1));
			}
			else
			{
				System.out.println(gm.getCompPlayer(0).getHand().get(chosen));
				gm.play(chosen,false,gm.getCompPlayer(0));
			}
			
			//TESTING 123
			if (!firstPlay) //checks for special cards as long as a player has placed a card
			{
				//8 update suit
				if(!eight){
					if (gm.getDeck().getTopDiscard().getValue().equals("8"))
					{
						gm.setOSuit(gm.pickSuit(gm.getCompPlayer(0)));
						eight = true;
					}
				}
				else
					gm.setOSuit(gm.getDeck().getTopDiscard().getSuit());
						
				//test if jack
				if (gm.getDeck().getTopDiscard().getValue().equals("J"))
					gm.setTurn(3);
				else
					gm.setTurn(2);
				//two's update
				if(gm.getDeck().getTopDiscard().getValue().equals("2"))
					two++;
				else
					two=0;
			}	 			
			
			
			
			gm.setTurn(2);
			middle.getChildren().clear();
			middle.getChildren().addAll(gm.getDeck().getImageTop(), withdraw);
			cp1.setText("CP1 has "+gm.getCompPlayer(0).getHand().size()+" cards."); //label
		}
		else if (turn==2)
		{
			//TESTING
			if (two>=1 && !firstPlay)
			{
				gm.play(-1, false, gm.getCompPlayer(1));
				gm.play(-1, false, gm.getCompPlayer(1));
			}
			if (two>=2)
			{
				gm.play(-1, false, gm.getCompPlayer(1));
				gm.play(-1, false, gm.getCompPlayer(1));
			}
			if (two>=3)
			{
				gm.play(-1, false, gm.getCompPlayer(1));
				gm.play(-1, false, gm.getCompPlayer(1));
			}
			if (two>=4)
			{
				gm.play(-1, false, gm.getCompPlayer(1));
				gm.play(-1, false, gm.getCompPlayer(1));
			}
			//END TEST
			
			int chosen= gm.compDecide(gm.getCompPlayer(1),gm.getCompPlayer(2));
			System.out.println("index testing2: " + chosen);
			if (chosen==-1)
			{
				gm.getCompPlayer(1).addToHand(gm.getDeck().withdraw());
				System.out.println("CP1 withdrew a " + gm.getCompPlayer(1).getHand().get(gm.getCompPlayer(1).getHand().size()-1));
			}
			else
			{
				gm.play(chosen,false,gm.getCompPlayer(1));
				firstPlay=false;
			}
			
			//TESTING 123
			if (!firstPlay) //checks for special cards as long as a player has placed a card
			{
				//8 update suit
				if(!eight){
					if (gm.getDeck().getTopDiscard().getValue().equals("8"))
					{
						gm.setOSuit(gm.pickSuit(gm.getCompPlayer(1)));
						eight = true;
					}
				}
				else
					gm.setOSuit(gm.getDeck().getTopDiscard().getSuit());
						
				//test if jack
				if (gm.getDeck().getTopDiscard().getValue().equals("J"))
				{
					gm.setTurn(1);
					top.getChildren().remove(next);
				}
				else
					gm.setTurn(3); //TODO if first card has not been played, will not increment turn (fix)
				//two's update
				if(gm.getDeck().getTopDiscard().getValue().equals("2"))
					two++;
				else
					two=0;
			}	 		
			//END TEST
		
			//	gm.setTurn(3);
			middle.getChildren().clear();
			middle.getChildren().addAll(gm.getDeck().getImageTop(), withdraw);
			cp2.setText("CP2 has "+gm.getCompPlayer(1).getHand().size()+" cards."); //label
		}
		else if (turn==3)
		{
			//TESTING
			if (two>=1 && !firstPlay)
			{
				gm.play(-1, false, gm.getCompPlayer(2));
				gm.play(-1, false, gm.getCompPlayer(2));
			}
			if (two>=2)
			{
				gm.play(-1, false, gm.getCompPlayer(2));
				gm.play(-1, false, gm.getCompPlayer(2));
			}
			if (two>=3)
			{
				gm.play(-1, false, gm.getCompPlayer(2));
				gm.play(-1, false, gm.getCompPlayer(2));
			}
			if (two>=4)
			{
				gm.play(-1, false, gm.getCompPlayer(2));
				gm.play(-1, false, gm.getCompPlayer(2));
			}
			//END TEST
			
			
			int chosen= gm.compDecide(gm.getCompPlayer(2),gm.getPlayer());
			System.out.println("index testing3: " + chosen);
			if (chosen==-1)
			{
				gm.getCompPlayer(2).addToHand(gm.getDeck().withdraw());
				System.out.println("CP3 withdrew a " + gm.getCompPlayer(2).getHand().get(gm.getCompPlayer(2).getHand().size()-1));
			}
			else
				gm.play(chosen,false,gm.getCompPlayer(2));
			
			//TESTING 123
			if (!firstPlay) //checks for special cards as long as a player has placed a card
			{
				//8 update suit
				if(!eight){
					if (gm.getDeck().getTopDiscard().getValue().equals("8"))
					{
						gm.setOSuit(gm.pickSuit(gm.getCompPlayer(2)));
						eight = true;
					}
				}
				else
					gm.setOSuit(gm.getDeck().getTopDiscard().getSuit());
						
				//test if jack
				if (gm.getDeck().getTopDiscard().getValue().equals("J"))
				{
				//	turn=1;
					gm.setTurn(2);
				}
				else
				{
					gm.setTurn(1); //TODO if first card has not been played, will not increment turn (fix)
					top.getChildren().remove(next);
				}
				//two's update
				if(gm.getDeck().getTopDiscard().getValue().equals("2"))
					two++;
				else
					two=0;
			}	 		
			//END TEST
			
			
	//		if(gm.getTurn()!=)
	//			top.getChildren().remove(next);
			middle.getChildren().clear();
			middle.getChildren().addAll(gm.getDeck().getImageTop(), withdraw);
			cp3.setText("CP3 has "+gm.getCompPlayer(2).getHand().size()+" cards."); //label
			gm.setTurn(1);
		}
		
		//if a computer player wins, changes screen to LOSE scene
		if (gm.getCompPlayer(0).getHand().isEmpty() || gm.getCompPlayer(1).getHand().isEmpty() || gm.getCompPlayer(2).getHand().isEmpty())
		{
			lose.setBottom(reset); //test
			Scene loseScene = new Scene(lose,800,500);
			prim.setScene(loseScene);
		}
	}
	
}


		
	

