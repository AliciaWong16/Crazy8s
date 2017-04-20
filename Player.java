/*
 * Megan Brock March 21 2017
 */

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player {
	
	//attributes
	private String name;
	private ArrayList<Card> hand;
	private ArrayList<ImageView> imageHand;
	private ArrayList<Button> buttonHand;
	
	//constructors
	public Player(String n) {
		hand = new ArrayList<Card>();
		name=n;
		imageHand = new ArrayList<ImageView>();
		buttonHand = new ArrayList<Button>();
	}
	public Player() {
		hand = new ArrayList<Card>();
		imageHand = new ArrayList<ImageView>();
		buttonHand = new ArrayList<Button>();
	}
	
	//get/set
	public String getName() {
		return name;
	}
	//get buttons (with images)
	public ArrayList<Button> getButtons()
	{
	//	setImages();
		return buttonHand;
	}
	//get button index - return int
	public int getButtonIndex(Button b)
	{
		int r=-1;
		for (int i=0; i<buttonHand.size(); i++)
		{
			if (b == buttonHand.get(i))
				return i;
		}
		System.out.println("Button not found");
		return r;
	}
	
	public void setName(String n) {
		name = n;
	}
	//play : takes card from player and return that card for discard pile (same as withdraw in deck class) -HB
	public Card playCard(int index)
	{
	//	System.out.println("TEST PLAY CARD");
			Card temp = new Card();
			temp = getHand().get(index); //creates placeholder card(hard copy)
			getHand().remove(index); //removes card from hand
			return temp; //returns copy of card to go in discard pile
	}
	
	//get cards in hand (returns array) - creating this to display number of cards each computer player has in hand
	//(in game manager toString method) - HB
	public ArrayList<Card> getHand()
	{
		return hand;
	}
	
	//add to hand (withdraw) - HB
	public void addToHand(Card c)
	{
		Card n = new Card();  //making hard copy
		n = c;
		hand.add(n);
	}
	
	//checks if there is a winner - HB
	public boolean isWinner()
	{
		if (hand.isEmpty())
			return true;
		else
			return false;
	}
	
	public String toString() {
		return name + "'s cards: " + hand.toString();
				
	}
}
