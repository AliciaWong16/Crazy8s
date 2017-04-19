/*
 * TODO : after withdrawing, have option to play card. cannot play cards when it's cp player's turn
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
	private Button reset, withdraw, next, yes, no; //reset to restart game, next to let computer players go
	private Button hearts, clubs, diamonds, spades; //for changing suits
//	private ImageView heartsI, clubsI, diamondsI, spadesI; //images for buttons to change suits (above)
	private BorderPane main, win, lose;
	private Stage prim, playWithdraw, SP;
	private TextField userTextField;
	private FlowPane flow;
	private TilePane middle;
	private Label userName, cp1, cp2, cp3, s;
	private TilePane top;
	private boolean played;
	private int two; //how many two's have been played in a row
	private GridPane suitPicker;
	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) 
    {
    	//setup for later
    //	heartsI = new ImageView(new Image("file:///C:/Users/Heather/Documents/School/Crazy%208/cards/icons/heart.jpg"));
    	hearts = new Button(); clubs = new Button(); diamonds = new Button(); spades = new Button();
    	hearts.setGraphic(new ImageView(new Image("file:///C:/Users/Heather/Documents/School/Crazy%208/cards/icons/heart.jpg")));
    	clubs.setGraphic(new ImageView(new Image("file:///C:/Users/Heather/Documents/School/Crazy%208/cards/icons/club.jpg")));
    	diamonds.setGraphic(new ImageView(new Image("file:///C:/Users/Heather/Documents/School/Crazy%208/cards/icons/diamond.jpg")));
    	spades.setGraphic(new ImageView(new Image("file:///C:/Users/Heather/Documents/School/Crazy%208/cards/icons/spade.jpg")));
    	
    	two=0;
    	s = new Label("");
    	
    	win=new BorderPane();
    	Label winLabel = new Label("YOU WIN!!");
    	win.setCenter(winLabel);
    	lose=new BorderPane();
    	Label loseLabel= new Label("YOU LOSE");
    	lose.setCenter(loseLabel);
    	
    	//setup end
    	
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
		  Image getBackOfCard = new Image ("file:///C:/Users/Heather/workspace/Assignment/graphics/backOfCard.png");//create file path to image
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
		  Image hand = new Image("file:///C:/Users/Megan/OneDrive/cards/hand.png");
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
		
		  userName = new Label("\t"+gm.getPlayer().getName() +"'s Cards: \t");
		    
		  flow.getChildren().addAll(handView2,userName);
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
		  reset = new Button("RESTART GAME");
		  reset.setOnAction(this::textField);
		
		  top.getChildren().addAll(handView,cp1,handView0,cp2,handView1,cp3, reset);
		 
			
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
		played=false;
		
		//TESTING
		if (two>=1)
		{
		//	gm.getCompPlayer(0).addToHand(gm.getDeck().withdraw());
		//	gm.getCompPlayer(0).addToHand(gm.getDeck().withdraw());
			gm.play(-1, gm.getPlayer());
			flow.getChildren().add(gm.getPlayer().getHand().get(gm.getPlayer().getHand().size()-1).getButton());
			gm.getPlayer().getHand().get(gm.getPlayer().getHand().size()-1).getButton().setOnAction(this::processButtonPress);
			gm.play(-1, gm.getPlayer());
			flow.getChildren().add(gm.getPlayer().getHand().get(gm.getPlayer().getHand().size()-1).getButton());
			gm.getPlayer().getHand().get(gm.getPlayer().getHand().size()-1).getButton().setOnAction(this::processButtonPress);
		}
		if (two>=2)
		{
			gm.play(-1, gm.getPlayer());
			gm.play(-1, gm.getPlayer());
		}
		if (two>=3)
		{
			gm.play(-1, gm.getPlayer());
			gm.play(-1, gm.getPlayer());
		}
		if (two>=4)
		{
			gm.play(-1, gm.getPlayer());
			gm.play(-1, gm.getPlayer());
		}
		//END TEST
		
		if(gm.getTurn()==0) //WITHDRAW
		{
			top.getChildren().remove(next);
			//if withdrawing a card
			if (e.getSource()==withdraw)
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
					GridPane wpCard = new GridPane();
					wpCard.add(yes, 1, 0);
					wpCard.add(no, 0, 0);
					Scene wp = new Scene(wpCard);
					playWithdraw = new Stage();
					playWithdraw.setScene(wp);
					playWithdraw.setTitle("Play the card you just Withdrew?");
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
				System.out.println(index);
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
				//	System.out.println("test");
					gm.setOSuit(gm.getPlayer().getHand().get(index).getSuit()); //updates suit
					gm.play(index, gm.getPlayer());
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
			
			//test this one (must win a game)
			if (gm.getPlayer().getHand().isEmpty())
			{
				win.setBottom(reset); //test
				Scene winScene = new Scene(win,800,500);
				prim.setScene(winScene);
			}
		}
	}
	
	
	public void computerPlayerTurn(ActionEvent e)
	{
		if (gm.getTurn()==0)
			top.getChildren().remove(next);
		System.out.println("gmTurn: " +gm.getTurn() +"\nSuit: " + gm.getOSuit());
		if (gm.getTurn()==1)
		{
			//TESTING
			if (two>=1)
			{
			//	gm.getCompPlayer(0).addToHand(gm.getDeck().withdraw());
			//	gm.getCompPlayer(0).addToHand(gm.getDeck().withdraw());
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
			//END TEST

			
			int chosen= gm.compDecide(gm.getCompPlayer(0),gm.getCompPlayer(1));
	//		System.out.println("index testing1: " + chosen);
	//		System.out.println("chosen card: " + gm.getCompPlayer(0).getHand().get(chosen));
			if (chosen==-1) //TODO add option for cp to play withdrawn card
			{
				System.out.println("withdraw");
				s.setText("");
				gm.getCompPlayer(0).addToHand(gm.getDeck().withdraw());
				System.out.println("CP3 withdrew a " + gm.getCompPlayer(0).getHand().get(gm.getCompPlayer(0).getHand().size()-1));
				gm.setTurn(2);
				two=0;
			}
			else if (gm.getCompPlayer(0).getHand().get(chosen).getValue().equals("8"))
			{
				System.out.println("eight");
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
				System.out.println("two");
				s.setText("");
				gm.play(chosen, gm.getCompPlayer(0));
				two++;
				gm.setTurn(2);
			}
			else if (gm.getCompPlayer(0).getHand().get(chosen).getValue().equals("J"))
			{
				System.out.println("jack");
				two=0;
				s.setText("");
				gm.play(chosen,gm.getCompPlayer(0));
				gm.setTurn(3);
			}
			else
			{
				System.out.println("other");
				two=0;
				s.setText("");
				gm.play(chosen, gm.getCompPlayer(0));
				gm.setTurn(2);
			}
			

	//		gm.setTurn(2);
			System.out.println("test");
			middle.getChildren().clear();
			middle.getChildren().addAll(gm.getDeck().getImageTop(), withdraw, s);
			cp1.setText("CP1 has "+gm.getCompPlayer(0).getHand().size()+" cards."); //label
		
			//if a computer player wins, changes screen to LOSE scene
			if (gm.getCompPlayer(1).getHand().isEmpty())
			{
				lose.setBottom(reset); //test
				Scene loseScene = new Scene(lose,800,500);
				prim.setScene(loseScene);
			}
			
		}
		//PLAYER2
		else if (gm.getTurn()==2)
		{
			//TESTING
			if (two>=1) //got rid of !firstPlay boolean 
			{
				gm.getCompPlayer(1).addToHand(gm.getDeck().withdraw());
				gm.getCompPlayer(1).addToHand(gm.getDeck().withdraw());
			//	gm.play(-1, gm.getCompPlayer(1));
			//	gm.play(-1, gm.getCompPlayer(1));
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
			//END TEST
			
			int chosen= gm.compDecide(gm.getCompPlayer(1),gm.getCompPlayer(2));
			System.out.println("index testing2: " + chosen);
			if (chosen==-1) //TODO add option for cp to play withdrawn card
			{
				s.setText("");
				gm.getCompPlayer(1).addToHand(gm.getDeck().withdraw());
				System.out.println("CP2 withdrew a " + gm.getCompPlayer(1).getHand().get(gm.getCompPlayer(1).getHand().size()-1));
				gm.setTurn(3);
				two=0;
			}
			else if (gm.getCompPlayer(1).getHand().get(chosen).getValue().equals("8"))
			{
				gm.setOSuit(gm.pickSuit(gm.getCompPlayer(1)));
				s.setText("Suit was changed to " + gm.getOSuit());
				gm.play(chosen,gm.getCompPlayer(1));
				two=0;
				gm.setTurn(3);
			}
			else if (gm.getCompPlayer(1).getHand().get(chosen).getValue().equals("2"))
			{
				s.setText("");
				gm.play(chosen,gm.getCompPlayer(1));
				two++;
				gm.setTurn(3);
			}
			else if (gm.getCompPlayer(1).getHand().get(chosen).getValue().equals("J"))
			{
				two=0;
				s.setText("");
				gm.play(chosen,gm.getCompPlayer(1));
				gm.setTurn(0);
				top.getChildren().remove(next);
			}
			else
			{
				two=0;
				s.setText("");
				gm.play(chosen, gm.getCompPlayer(1));
				gm.setTurn(3);
			}
		
			//	gm.setTurn(3);
			middle.getChildren().clear();
			middle.getChildren().addAll(gm.getDeck().getImageTop(), withdraw, s);
			cp2.setText("CP2 has "+gm.getCompPlayer(1).getHand().size()+" cards."); //label
			
			//if a computer player wins, changes screen to LOSE scene
			if (gm.getCompPlayer(1).getHand().isEmpty())
			{
				lose.setBottom(reset); //test
				Scene loseScene = new Scene(lose,800,500);
				prim.setScene(loseScene);
			}
		
		}
		
		
		//PLAYER 3
		else if (gm.getTurn()==3)
		{
			//TESTING
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
			//END TEST
			
			//PLAYER 3
			int chosen= gm.compDecide(gm.getCompPlayer(2),gm.getPlayer());
			System.out.println("index testing3: " + chosen);
			if (chosen==-1) //TODO add option for cp to play withdrawn card
			{
				s.setText("");
				gm.getCompPlayer(2).addToHand(gm.getDeck().withdraw());
				System.out.println("CP3 withdrew a " + gm.getCompPlayer(2).getHand().get(gm.getCompPlayer(2).getHand().size()-1));
				gm.setTurn(0);
				two=0;
			}
			else if (gm.getCompPlayer(2).getHand().get(chosen).getValue().equals("8"))
			{
				gm.setOSuit(gm.pickSuit(gm.getCompPlayer(2)));
				s.setText("Suit was changed to " + gm.getOSuit());
				gm.play(chosen,gm.getCompPlayer(2));
				two=0;
				gm.setTurn(0);
				top.getChildren().remove(next);
			}
			else if (gm.getCompPlayer(2).getHand().get(chosen).getValue().equals("2"))
			{
				s.setText("");
				gm.play(chosen,gm.getCompPlayer(2));
				two++;
				gm.setTurn(0);
				top.getChildren().remove(next);
			}
			else if (gm.getCompPlayer(2).getHand().get(chosen).getValue().equals("J"))
			{
				two=0;
				s.setText("");
				gm.play(chosen,gm.getCompPlayer(2));
				gm.setTurn(1);
			}
			else
			{
				gm.setTurn(0);
				two=0;
				s.setText("");
				gm.play(chosen, gm.getCompPlayer(2));
				top.getChildren().remove(next);
			}
			
			//resets image of top card, and number of cards player2 has
			middle.getChildren().clear();
			middle.getChildren().addAll(gm.getDeck().getImageTop(), withdraw, s);
			cp3.setText("CP3 has "+gm.getCompPlayer(2).getHand().size()+" cards."); //label
			
			//if a computer player wins, changes screen to LOSE scene
			if (gm.getCompPlayer(2).getHand().isEmpty())
			{
				lose.setBottom(reset); //test
				Scene loseScene = new Scene(lose,800,500);
				prim.setScene(loseScene);
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
}


		
	

