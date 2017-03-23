/*
 * Megan Brock March 21 2017
 */

import java.util.ArrayList;
public class Player {
	private String name;
	private ArrayList<Card> hand;
	
	public Player() {
		hand = new ArrayList<Card>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
	}
	//play : takes card from player and return that card for discard pile (same as withdraw in deck class) -HB
	public Card playCard(int index) {
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
