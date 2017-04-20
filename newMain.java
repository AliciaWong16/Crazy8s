/*
 * Crazy 8's main method with gui. Alicia, Megan, Samara and Heather. 
 */


import javafx.application.Application;
import static javafx.application.Application.launch;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
	private Button reset, withdraw, next, yes, no, scores; //reset to restart game, next to let computer players go
	private Button hearts, clubs, diamonds, spades; //for changing suits
	private BorderPane main;
	private Stage prim, playWithdraw, SP;
	private TextField userTextField;
	private Text endText;
	private FlowPane flow;
	private TilePane middle;
	private Label userName, cp1, cp2, cp3, s;
	private TilePane top;
	private boolean played;
	private int two; //how many two's have been played in a row
	private GridPane suitPicker;
	private Scene scoresScene;
	private Stage scoreStage;
	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
    	scoreStage= new Stage();
 	
    	endText = new Text("Game Over");
    	scores = new Button("Win Counts");
    	scores.setOnAction(this::scoresButton);
		reset = new Button("RESTART GAME");
		reset.setOnAction(this::textField);
    	
    	//setup for later
    	hearts = new Button(); clubs = new Button(); diamonds = new Button(); spades = new Button();
    	hearts.setGraphic(new ImageView(new Image("file:///C:/Users/Heather/Documents/School/Crazy%208/cards/icons/heart.jpg")));
    	clubs.setGraphic(new ImageView(new Image("file:///C:/Users/Heather/Documents/School/Crazy%208/cards/icons/club.jpg")));
    	diamonds.setGraphic(new ImageView(new Image("file:///C:/Users/Heather/Documents/School/Crazy%208/cards/icons/diamond.jpg")));
    	spades.setGraphic(new ImageView(new Image("file:///C:/Users/Heather/Documents/School/Crazy%208/cards/icons/spade.jpg")));
    	
    	two=0; //counter for number of twos stacked
    	s = new Label(""); //8 suit change notification
    	//setup end
    	
    	prim = new Stage(); //??
    	prim.setTitle("Crazy Eights");
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
        userTextField = new TextField();
        userTextField.setOnAction(this::textField);
        grid.add(userTextField, 1, 1);
     //   grid.setStyle("-fx-background-color:pink");
        
        //sets primary scene to starting screen
        Scene scene = new Scene(grid, 600, 400);
        prim.setScene(scene);
        prim.show();
        
        //this button doesn't do anything
        Label enter = new Label("Press Enter to Start");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(enter);
        grid.add(hbBtn, 1, 3);
        
        Button btn2 = new Button("Rules");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(btn2);
        grid.add(hbBtn2, 1, 3);   
        
        grid.add(scores,2,3);
        
        //shows rules when Rules button is pressed
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Stage ruleStage = new Stage();
                ruleStage.setTitle("How to Play");
                GridPane rulePane = new GridPane();
                Text txt = new Text("Rules"
                        + "\n\n\tTo begin, each player starts with 8 cards, and the goal is to be the first player to play all of the cards in their hand."
                        + "\n\tWhen It's your turn, you must play a card from your hand that is either the same suit or value as the current top card."
                        + "\n\tIf you can't play any of your cards you must withdraw from the pile."
                        + "\n\tIf the withdrawn card is playable you can play it immediately."
                        + "\n\n\tThere are 3 kinds of special cards in the game:\n"
                        + "\n\tIf a card with the value 2 is played the next player must pick up two cards before playing"
                        + "\n\tThis player can also play a 2, meaning that the next player needs to pick up 4 cards."
                        + "\n\tThis continues up to 4 turns (max pick up from 2s is 8 cards)."
                        + "\n\n\tIf a Jack is played the next player's turn is skipped."
                        + "\n\n\tAn 8 can be played at any time in the game no matter what the top card is in the pile."
                        + "\n\tThe player who plays an eight then gets to decide what suit should be played next."
                        + "\n\n\tThe player who empties their hand is declared the winner!"
                        +"\n\n\tWhen it is not your turn, press the \"Next Player Go\" button until it is your turn again.");
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
		{
			if (userTextField.getText().equals(""))
				name = "ANONYMOUS";
			else
				 name = userTextField.getText(); //gets user input (name)
		}
		
		  gm = new GameManager(name); //creates game manager and deals cards
		  gm.dealCards();
		  gm.setOSuit(gm.getDeck().getTopDiscard().getSuit());
		  
		  //creates new view of screen
		  main = new BorderPane();
		  
		  //middle part
		  middle=new TilePane();
		  middle.setAlignment(Pos.CENTER);
		  middle.setPadding(new Insets(5, 0, 5, 0));
		  middle.setVgap(4);
		  middle.setHgap(4);
		    
		  //displays back of card image
		  Image getBackOfCard = new Image ("file:///C:/Users/Heather/workspace/Assignment/graphics/backOfCard.jpg");//create file path to image
		  ImageView backOfCard = new ImageView(getBackOfCard); //displays image
		  backOfCard.setFitWidth(100);
		  backOfCard.setPreserveRatio(true);
		  withdraw = new Button(); 
		  withdraw.setOnAction(this::processButtonPress);
		  withdraw.setGraphic(backOfCard); //sets button image
		    
		  middle.getChildren().addAll(gm.getDeck().getImageTop(), withdraw, s);
		  
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
		    
		  //@megan
		  Image hand = new Image("file:///C:/Users/Heather/Documents/School/Crazy%208/graphics/hand.png");
		  ImageView handView = new ImageView(hand);
		  handView.setFitWidth(50);
		  handView.setPreserveRatio(true);
		                      
		  ImageView handView0 = new ImageView(hand);
		  handView0.setFitWidth(50);
		  handView0.setPreserveRatio(true);
		                           
		  ImageView handView1 = new ImageView(hand);
		  handView1.setFitWidth(50);
		  handView1.setPreserveRatio(true);
		                      
		  ImageView handView2 = new ImageView(hand);
		  handView2.setFitWidth(50);
		  handView2.setPreserveRatio(true);
		                    
		  flow.getChildren().addAll(handView2, userName);
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
		  next = new Button("Next Player Go"); 
		  next.setOnAction(this::computerPlayerTurn); //add turn count in gm
			   
		  cp1 = new Label("CP1 has "+gm.getCompPlayer(0).getHand().size()+" cards.");
		  cp2 = new Label("CP2 has "+gm.getCompPlayer(1).getHand().size()+" cards.");
		  cp3 = new Label("CP3 has "+gm.getCompPlayer(2).getHand().size()+" cards.");
		
		  top.getChildren().addAll(handView, cp1, handView0, cp2, handView1, cp3, reset);
		 
		 // main.setRight(s);
		  main.setBottom(flow); //shows player's cards
		  main.setCenter(middle); //create center pane (top card picture and withdraw pile)
		  main.setTop(top); //shows number of cards in computer player's hands
		  
		  Scene mainGameScene = new Scene(main, 1000, 600); 
		  prim.setScene(mainGameScene);
	//	  prim.setFullScreen(true);
		  
	}
	//new player play method
	public void processButtonPress(ActionEvent e)
	{
		played=false; //must be false to play
		
		if(gm.getTurn()==0) //WITHDRAW
		{
			top.getChildren().remove(next);
			//if withdrawing a card
			if (e.getSource()==withdraw && !played)
			{
				gm.play(-1, gm.getPlayer());
				played=true;
				flow.getChildren().add(gm.getPlayer().getHand().get(gm.getPlayer().getHand().size()-1).getButton());
				gm.getPlayer().getHand().get(gm.getPlayer().getHand().size()-1).getButton().setOnAction(this::processButtonPress);
				//check if card is playable
				if (gm.isPlayLegal(gm.getPlayer().getHand().get(gm.getPlayer().getHand().size()-1)))
				{
					//asks player if they want to play the card
					yes = new Button("PLAY CARD");
					no = new Button("DO NOT PLAY CARD");
					yes.setPadding(new Insets(13));
					no.setPadding(new Insets(13));
					BorderPane wpCard = new BorderPane();
					wpCard.setTop(yes);
					wpCard.setBottom(no);
					wpCard.setPadding(new Insets(10));
					Scene wp = new Scene(wpCard,155,110);
					playWithdraw = new Stage();
					playWithdraw.setScene(wp);
					playWithdraw.setTitle("Play card?");
					playWithdraw.show();
					
					//actions for buttons to play withdraw'd card
					yes.setOnAction(this::processButtonPress);
					no.setOnAction(this::processButtonPress);
				}
				else
				{
					gm.setTurn(1);
					top.getChildren().add(0, next);
				}
			}//end withdraw
			//if not playing withdrawn card
			else if (e.getSource()==no)
			{
				two=0;
				gm.setTurn(1);
				playWithdraw.close();
				top.getChildren().add(0, next);
			}
			//if playing withdrawn card
			
			else  //if playing a card
			{	
				int index=-1;
				//if card was just withdrawn
				if (e.getSource()==yes)
				{
					index = gm.getPlayer().getHand().size()-1;
					playWithdraw.close();
					played=false;
				}
				else //if playing normal card (not withdrawn)
				{
					//finds button
					for (int i=0; i<gm.getPlayer().getHand().size();i++)
					{
						if(e.getSource().equals(gm.getPlayer().getHand().get(i).getButton()))
							index=i;				
					}
				}
				
				//plays card
				//if it's an 8
				if (gm.getPlayer().getHand().get(index).getValue().equals("8"))
				{
					suitPicker = new GridPane();
					suitPicker.add(clubs, 0, 0);
					suitPicker.add(hearts, 1, 0);
					suitPicker.add(diamonds, 0, 1);
					//sets buttons to change suit method
					clubs.setOnAction(this::changeSuit);
					hearts.setOnAction(this::changeSuit);
					diamonds.setOnAction(this::changeSuit);
					spades.setOnAction(this::changeSuit);
					suitPicker.add(spades, 1, 1);
					Scene SPicker = new Scene(suitPicker);
					SP = new Stage();
					SP.setTitle("Pick a Suit");
					SP.setScene(SPicker);
					SP.show();
				}
				//TWOS
				if (gm.getPlayer().getHand().get(index).getValue().equals("2")) //TWO
					two++;
				else
					two=0;
				//JACK
				if (gm.getPlayer().getHand().get(index).getValue().equals("J"))
					gm.setTurn(2);
				else
					gm.setTurn(1);
				
				//plays card if card is legal
				if (gm.isPlayLegal(gm.getPlayer().getHand().get(index)))
				{
					gm.play(index, gm.getPlayer());
					if (!gm.getDeck().getTopDiscard().getValue().equals("8"))
						gm.setOSuit(gm.getDeck().getTopDiscard().getSuit()); //updates suit
					played=true;
					flow.getChildren().clear();
					flow.getChildren().add(userName);
					middle.getChildren().clear();
					middle.getChildren().addAll(gm.getDeck().getImageTop(), withdraw, s);
					top.getChildren().add(0, next); 
				}
				else
				{
					played=false;
					gm.setTurn(0);
					top.getChildren().remove(next);
				}
				//changes cards (adds back in)
			    for (int i=0; i < gm.getPlayer().getHand().size() && played;i++)
			    {
			    	flow.getChildren().add(gm.getPlayer().getHand().get(i).getButton());
			    	gm.getPlayer().getHand().get(i).getButton().setOnAction(this::processButtonPress);
			    }
			}
			//win screen
			if (gm.getPlayer().getHand().isEmpty())
			{
				endText.setText("You Win!");
				winScene(true);	
			}
		}
	}
	
	public void computerPlayerTurn(ActionEvent e)
	{
		if (gm.getTurn()==0)
			top.getChildren().remove(next);
		if (gm.getTurn()==1)
		{
			//pick up two
			if (two>=1)
			{
				gm.play(-1, gm.getCompPlayer(0));
				gm.play(-1, gm.getCompPlayer(0));
			}
			if (two>=2)
			{
				gm.play(-1, gm.getCompPlayer(0));
				gm.play(-1, gm.getCompPlayer(0));
			}
			if (two>=3)
			{
				gm.play(-1, gm.getCompPlayer(0));
				gm.play(-1, gm.getCompPlayer(0));
			}
			if (two>=4)
			{
				gm.play(-1, gm.getCompPlayer(0));
				gm.play(-1, gm.getCompPlayer(0));
			}
			//END 2s

			int chosen= gm.compDecide(gm.getCompPlayer(0),gm.getCompPlayer(1));
			if (chosen==-1)
			{
				s.setText("");
				gm.getCompPlayer(0).addToHand(gm.getDeck().withdraw());
				//if possible cp will play withdrawn card
				if (gm.isPlayLegal(gm.getCompPlayer(0).getHand().get(gm.getCompPlayer(0).getHand().size()-1)))
					chosen=gm.getCompPlayer(0).getHand().size()-1;
				gm.setTurn(2);
				two=0;
			}
			if (chosen!=-1)
			{
				if (gm.getCompPlayer(0).getHand().get(chosen).getValue().equals("8"))
				{
					gm.setOSuit(gm.pickSuit(gm.getCompPlayer(0)));
					//tells user which suit is the newest
					if (gm.getOSuit().equals("C"))
						s.setText("Suit was changed to Clubs");
					else if (gm.getOSuit().equals("D"))
						s.setText("Suit was changed to Diamonds");
					else if (gm.getOSuit().equals("H"))
						s.setText("Suit was changed to Hearts");
					else
						s.setText("Suit was changed to Spades");
					gm.play(chosen, gm.getCompPlayer(0));
					two=0;
					gm.setTurn(2);
				}
				else if (gm.getCompPlayer(0).getHand().get(chosen).getValue().equals("2"))
				{
					s.setText("");
					gm.play(chosen, gm.getCompPlayer(0));
					gm.setOSuit(gm.getDeck().getTopDiscard().getSuit());
					two++;
					gm.setTurn(2);
					
				}
				else if (gm.getCompPlayer(0).getHand().get(chosen).getValue().equals("J"))
				{
					two=0;
					s.setText("");
					gm.play(chosen,gm.getCompPlayer(0));
					gm.setOSuit(gm.getDeck().getTopDiscard().getSuit());
					gm.setTurn(3);
				}
				else
				{
					two=0;
					s.setText("");
					gm.play(chosen, gm.getCompPlayer(0));
					gm.setOSuit(gm.getDeck().getTopDiscard().getSuit());
					gm.setTurn(2);
				}
			}
			
			middle.getChildren().clear();
			middle.getChildren().addAll(gm.getDeck().getImageTop(), withdraw, s);
			cp1.setText("CP1 has "+gm.getCompPlayer(0).getHand().size()+" cards."); //label
		
			//if a computer player wins, changes screen to LOSE scene
			if (gm.getCompPlayer(0).getHand().isEmpty())
			{
				endText.setText("You Lose.");
				winScene(false);	
			}
			
		}
		//PLAYER2
		else if (gm.getTurn()==2)
		{
			//withdraw for twos
			if (two>=1) //got rid of !firstPlay boolean 
			{
				gm.play(-1, gm.getCompPlayer(1));
				gm.play(-1, gm.getCompPlayer(1));
			}
			if (two>=2)
			{
				gm.play(-1, gm.getCompPlayer(1));
				gm.play(-1, gm.getCompPlayer(1));
			}
			if (two>=3)
			{
				gm.play(-1, gm.getCompPlayer(1));
				gm.play(-1, gm.getCompPlayer(1));
			}
			if (two>=4)
			{
				gm.play(-1, gm.getCompPlayer(1));
				gm.play(-1, gm.getCompPlayer(1));
			}
			
			int chosen= gm.compDecide(gm.getCompPlayer(1),gm.getCompPlayer(2));
			if (chosen==-1)
			{
				s.setText("");
				gm.getCompPlayer(1).addToHand(gm.getDeck().withdraw());
				//plays withdrawn card if possible
				if (gm.isPlayLegal(gm.getCompPlayer(1).getHand().get(gm.getCompPlayer(1).getHand().size()-1)))
					chosen=gm.getCompPlayer(1).getHand().size()-1;
				gm.setTurn(3);
				two=0;
			}
			if (chosen!=-1)
			{
				if (gm.getCompPlayer(1).getHand().get(chosen).getValue().equals("8"))
				{
					gm.setOSuit(gm.pickSuit(gm.getCompPlayer(1)));
					//tells player what suit is
					if (gm.getOSuit().equals("C"))
						s.setText("Suit was changed to Clubs");
					else if (gm.getOSuit().equals("D"))
						s.setText("Suit was changed to Diamonds");
					else if (gm.getOSuit().equals("H"))
						s.setText("Suit was changed to Hearts");
					else
						s.setText("Suit was changed to Spades");
					gm.play(chosen,gm.getCompPlayer(1));
					two=0;
					gm.setTurn(3);
				}
				else if (gm.getCompPlayer(1).getHand().get(chosen).getValue().equals("2"))
				{
					s.setText("");
					gm.play(chosen,gm.getCompPlayer(1));
					gm.setOSuit(gm.getDeck().getTopDiscard().getSuit());
					two++;
					gm.setTurn(3);
				}
				else if (gm.getCompPlayer(1).getHand().get(chosen).getValue().equals("J")) //if playing a jack
				{
					two=0;
					s.setText("");
					gm.play(chosen,gm.getCompPlayer(1));
					gm.setOSuit(gm.getDeck().getTopDiscard().getSuit());
					gm.setTurn(0);
					top.getChildren().remove(next);
				}
				else //if playing normal card
				{
					two=0;
					s.setText("");
					gm.play(chosen, gm.getCompPlayer(1));
					gm.setOSuit(gm.getDeck().getTopDiscard().getSuit());
					gm.setTurn(3);
				}
			}
		
			//	gm.setTurn(3);
			middle.getChildren().clear();
			middle.getChildren().addAll(gm.getDeck().getImageTop(), withdraw, s);
			cp2.setText("CP2 has "+gm.getCompPlayer(1).getHand().size()+" cards."); //label
			
			//if a computer player wins, changes screen to LOSE scene
			if (gm.getCompPlayer(1).getHand().isEmpty())
			{
				endText.setText("You Lose.");
				winScene(false);	
			}
		}
		
		//PLAYER 3
		else if (gm.getTurn()==3)
		{
			//pick up for twos
			if (two>=1)
			{
				gm.play(-1, gm.getCompPlayer(2));
				gm.play(-1, gm.getCompPlayer(2));
			}
			if (two>=2)
			{
				gm.play(-1, gm.getCompPlayer(2));
				gm.play(-1, gm.getCompPlayer(2));
			}
			if (two>=3)
			{
				gm.play(-1, gm.getCompPlayer(2));
				gm.play(-1, gm.getCompPlayer(2));
			}
			if (two>=4)
			{
				gm.play(-1, gm.getCompPlayer(2));
				gm.play(-1, gm.getCompPlayer(2));
			}
			
			//PLAYER 3
			int chosen= gm.compDecide(gm.getCompPlayer(2),gm.getPlayer());
			if (chosen==-1) 
			{
				s.setText("");
				gm.getCompPlayer(2).addToHand(gm.getDeck().withdraw());
				//allows cp to play card after withdrawing it
				if (gm.isPlayLegal(gm.getCompPlayer(2).getHand().get(gm.getCompPlayer(2).getHand().size()-1)))
					chosen=gm.getCompPlayer(2).getHand().size()-1;
				gm.setTurn(0);
				two=0;
			}
			if (chosen!=-1) //cannot be else b/c of withdraw - new chosen value
			{
				if (gm.getCompPlayer(2).getHand().get(chosen).getValue().equals("8"))
				{
					gm.setOSuit(gm.pickSuit(gm.getCompPlayer(2)));
					//tells player current suit 
					if (gm.getOSuit().equals("C"))
						s.setText("Suit was changed to Clubs");
					else if (gm.getOSuit().equals("D"))
						s.setText("Suit was changed to Diamonds");
					else if (gm.getOSuit().equals("H"))
						s.setText("Suit was changed to Hearts");
					else
						s.setText("Suit was changed to Spades");
					gm.play(chosen,gm.getCompPlayer(2));
					two=0;
					gm.setTurn(0);
					top.getChildren().remove(next);
				}
				else if (gm.getCompPlayer(2).getHand().get(chosen).getValue().equals("2"))
				{
					s.setText("");
					gm.play(chosen,gm.getCompPlayer(2));
					gm.setOSuit(gm.getDeck().getTopDiscard().getSuit());
					two++;
					gm.setTurn(0);
					top.getChildren().remove(next);
					
					//makes player withdraw before their turn
					for (int i=0; i<two; i++)
					{
						gm.play(-1, gm.getPlayer());
						flow.getChildren().add(gm.getPlayer().getHand().get(gm.getPlayer().getHand().size()-1).getButton());
						gm.getPlayer().getHand().get(gm.getPlayer().getHand().size()-1).getButton().setOnAction(this::processButtonPress);
					}
				}
				else if (gm.getCompPlayer(2).getHand().get(chosen).getValue().equals("J"))
				{
					two=0;
					s.setText("");
					gm.play(chosen,gm.getCompPlayer(2));
					gm.setOSuit(gm.getDeck().getTopDiscard().getSuit());
					gm.setTurn(1);
				}
				else
				{
					gm.setTurn(0);
					two=0;
					s.setText("");
					gm.play(chosen, gm.getCompPlayer(2));
					gm.setOSuit(gm.getDeck().getTopDiscard().getSuit());
					top.getChildren().remove(next);
				}
			}
			
			//resets image of top card, and number of cards player2 has
			middle.getChildren().clear();
			middle.getChildren().addAll(gm.getDeck().getImageTop(), withdraw, s);
			cp3.setText("CP3 has "+gm.getCompPlayer(2).getHand().size()+" cards."); //label
			
			//if a computer player wins, changes screen to LOSE scene
			if (gm.getCompPlayer(2).getHand().isEmpty())
			{
				endText.setText("You Lose.");
				winScene(false);	
			}	
		}
	}

	public void changeSuit(ActionEvent e)
	{
		if (e.getSource()==hearts)
			gm.setOSuit("H");
		else if (e.getSource()==clubs)
			gm.setOSuit("C");
		else if (e.getSource()==diamonds)
			gm.setOSuit("D");
		else if (e.getSource()==spades)
			gm.setOSuit("S");
		
		SP.close(); //closes suit picker stage
	}
	
	//scans file to display and opens window - Alicia W
	public void scanFile() throws IOException
	{		
		 GridPane pane = new GridPane();
		 pane.setPadding(new Insets(20,20,20,20));
		 pane.setHgap(20);
		 pane.setVgap(10);
		//adds labels to pane
		 Label name = new Label("Name:");
		 pane.add(name, 0, 0);
		 Label wins = new Label("# of Wins:");
		 pane.add(wins, 2, 0);
		 
		 File winners = new File("winnersP.txt"); //ordered version of file
		 Scanner win = new Scanner(winners);//new Scanner to read file
		 //adds number of wins to pane
		 for(int i = 1; win.hasNext(); i++){
			 Text nameN = new Text(win.nextLine());
			 pane.add(nameN, 0, i);
			 Text winsN = new Text(win.nextLine());
			 pane.add(winsN, 2, i);
		 }
		 win.close();
		 
		 scoresScene = new Scene(pane, 300, 300);
		 scoreStage.setScene(scoresScene);		 
	}
	
	public void scoresButton(ActionEvent e)
	{
		try {
			scanFile();
		} catch (IOException e1) {
			//  Auto-generated catch block
			e1.printStackTrace();
		}
		
		scoreStage.setTitle("Winners!");
		scoreStage.setScene(scoresScene);
		scoreStage.show();
	}
	
	//win/lose scene - Megan B
	public void winScene(boolean b)
	{
		try {
			setFile(b);
		} catch (IOException e){
			e.printStackTrace();
		}
		
	    GridPane grid = new GridPane();
	    grid.setAlignment(Pos.CENTER);
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(25, 25, 25, 25));
	    
	    Image hearts = new Image ("file:///C:/Users/Heather/Documents/School/Crazy%208/cards/icons/heart.jpg");//create file path to image
	ImageView heartsIcon = new ImageView(hearts); //displays image
	    heartsIcon.setFitWidth(60);
	    heartsIcon.setPreserveRatio(true);
	    
	    Image diamonds = new Image ("file:///C:/Users/Heather/Documents/School/Crazy%208/cards/icons/diamond.jpg");//create file path to image
	ImageView diamondsIcon = new ImageView(diamonds); //displays image
	    diamondsIcon.setFitWidth(60);
	    diamondsIcon.setPreserveRatio(true);
	    
	    Image spades = new Image ("file:///C:/Users/Heather/Documents/School/Crazy%208/cards/icons/spade.jpg");//create file path to image
	ImageView spadesIcon = new ImageView(spades); //displays image
	    spadesIcon.setFitWidth(60);
	    spadesIcon.setPreserveRatio(true);
	    
	    Image clubs = new Image ("file:///C:/Users/Heather/Documents/School/Crazy%208/cards/icons/club.jpg");//create file path to image
	ImageView clubsIcon = new ImageView(clubs); //displays image
	    clubsIcon.setFitWidth(60);
	    clubsIcon.setPreserveRatio(true);
	
	    grid.add(heartsIcon, 0, 0);
	    grid.add(spadesIcon, 0, 2);
	    grid.add(endText, 1, 1);
	    grid.add(clubsIcon, 2, 0);
	    grid.add(diamondsIcon, 2, 2);
	    
	    grid.add(reset,0,3);
	    grid.add(scores,1,3);
	    
	    Scene scene2 = new Scene(grid, 600, 400);
	    prim.setTitle("End of Game");
	    prim.setScene(scene2);
	}
	//saves player win - Alicia W
	public void setFile(boolean b) throws IOException
	{
		
		ArrayList<String> users = new ArrayList<String>(); //users in order
		ArrayList<Integer>wins = new ArrayList<Integer>();
		ArrayList<String> usersOrd = new ArrayList<String>();
		ArrayList<Integer>winsOrd = new ArrayList<Integer>();
		
		String winners2 = "winners.txt"; //create file if does not exist
		PrintWriter outputFile = new PrintWriter(new FileWriter(winners2, true));
		outputFile.close();
		
		File winners = new File("winners.txt");
		Scanner win = new Scanner(winners);//new Scanner to read file
		int gamesW = -1;
		String name = gm.getPlayer().getName();
		
		//adds to arraylists while there is data in files
		while(win.hasNext()){
			users.add(win.nextLine());
			wins.add(Integer.parseInt((win.nextLine())));
		}
		win.close();
		
		//if user does not exist, adds user 
		if(!users.contains(name)){
			users.add(name);
			wins.add(0);
		}
		if(b){ //if user won, increments win count
			for (int i = 0; i<users.size(); i++)
			{
				if(users.get(i).equalsIgnoreCase(name)){
					gamesW = wins.get(i);
					wins.remove(i);
					wins.add(i, gamesW+1);
				}
			}
		}
		
		for(int i = 0; i<wins.size(); i++){
			if(winsOrd.isEmpty()) //if there are no values in the arrayList
			{
				winsOrd.add(wins.get(i));
				usersOrd.add(users.get(i));
			}
			else{//if there are values in the arrayList
				boolean set = false;//variable to check if current has been added
				for(int j = 0; j<winsOrd.size() & !set; j++){//loop continues until arrayList is gone through or current is added
					if(winsOrd.get(j)<wins.get(i)){//if the number at the index is less than the current number
						winsOrd.add(j,wins.get(i));//add the current number at that index
						usersOrd.add(j,users.get(i));
						set = true;//current has been added
					}
					else if(j == winsOrd.size()-1 && !set){//if the entire array list has been gone through and current is not added
						winsOrd.add(wins.get(i));
						usersOrd.add(users.get(i));
						set = true;//current is added
					}			
				}
			}
		}
		
		PrintWriter winOT = new PrintWriter("winners.txt");//create file
		for(int i = 0; i<users.size(); i++){
			winOT.println(users.get(i));
			winOT.println(wins.get(i));
		}
		winOT.close();//close file
		
		PrintWriter winP = new PrintWriter("winnersP.txt");//create file
		//winP.println("Win Count: \n");
		for(int i = 0; i<usersOrd.size(); i++){
			winP.println(usersOrd.get(i));
			winP.println(winsOrd.get(i));
		}
		winP.close();//close file
	}
}

	
	



		
	

