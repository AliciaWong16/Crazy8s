/*
 * deck object. aggregated to player (player has a deck)
 * assumes top card of discard is 0 index. (discarded cards are added to front of array list)
 * Heather Brunell Mar 21 2017
 */

import java.util.ArrayList;
import java.util.Random;

public class Deck {

	//attributes
	private ArrayList<Card> fullDeck; // holds all cards; not necessary
	private ArrayList<Card> discard; //played cards, "on table"
	private ArrayList<Card> drawPile; //incomplete version of fullDeck
	
	//constructor
	public Deck()
	{
		fullDeck = new ArrayList<Card>();
		populateDeck(); //populates fullDeck
		discard = new ArrayList<Card>();
		drawPile = new ArrayList<Card>();
		drawPile = fullDeck; //hardcopy of deck
		
	}
	
	//withdraw random card from drawPile
	public Card withdraw()
	{
	/*	if (isDrawPileEmpty());
		{
			System.out.println("The draw pile ran out! The discard pile has been added to the draw pile.");
			moveDiscard();
		} */
		int index = random(); //index of card to withdraw from pile
		Card temp = new Card(); //requires the new empty constructor
		temp = drawPile.get(index); //duplicates card to withdraw
		drawPile.remove(index); //removes card from drawPile
		return temp; //returns copy of card 
	}
	
	//add to top of discard
	public void addToDiscard(Card c)
	{
		discard.add(0, c);
	}
	
	//is pile empty (drawPile) : returns true is empty
	public boolean isDrawPileEmpty()
	{
		if (drawPile.isEmpty())
			return true;
		else
			return false;
	}
	
	//get methods - gets whole piles 
	public ArrayList<Card> getDrawPile()
	{
		return drawPile;
	}
	public ArrayList<Card> getDiscardPile()
	{
		return discard;
	}
	//gets top card from discard to validate plays and display to player
	public Card getTopDiscard()
	{
		return discard.get(0);
	}
	
	//populate deck / drawPile
	public void populateDeck()
	{
	/*	String suit = "ERROR";
		for(int s= 0; s<4; s++)
		{
			//adds 13 cards each time, changes suits here. 
			if (s == 0)
				suit ="H";
			else if (s == 1)
				suit = "D";
			else if (s == 2)
				suit = "C";
			else
				suit="S"; */
			//hearts
			String suit ="H";
			fullDeck.add(new Card (suit, "A", "file:///C:/Users/Heather/workspace/Assignment/graphics/ace_hearts.png"));
			fullDeck.add(new Card (suit, "2", "file:///C:/Users/Heather/workspace/Assignment/graphics/two_hearts.png"));
			fullDeck.add(new Card (suit, "3", "file:///C:/Users/Heather/workspace/Assignment/graphics/three_hearts.png"));
			fullDeck.add(new Card (suit, "4", "file:///C:/Users/Heather/workspace/Assignment/graphics/four_hearts.png"));
			fullDeck.add(new Card (suit, "5", "file:///C:/Users/Heather/workspace/Assignment/graphics/five_hearts.png"));
			fullDeck.add(new Card (suit, "6", "file:///C:/Users/Heather/workspace/Assignment/graphics/six_hearts.png"));
			fullDeck.add(new Card (suit, "7", "file:///C:/Users/Heather/workspace/Assignment/graphics/seven_hearts.png"));
			fullDeck.add(new Card (suit, "8", "file:///C:/Users/Heather/workspace/Assignment/graphics/eight_hearts.png"));
			fullDeck.add(new Card (suit, "9", "file:///C:/Users/Heather/workspace/Assignment/graphics/nine_hearts.png"));
			fullDeck.add(new Card (suit, "10", "file:///C:/Users/Heather/workspace/Assignment/graphics/ten_hearts.png"));
			fullDeck.add(new Card (suit, "J", "file:///C:/Users/Heather/workspace/Assignment/graphics/jack_hearts.png"));
			fullDeck.add(new Card (suit, "Q", "file:///C:/Users/Heather/workspace/Assignment/graphics/queen_hearts.png"));
			fullDeck.add(new Card (suit, "K", "file:///C:/Users/Heather/workspace/Assignment/graphics/king_hearts.png"));		
			//diamonds
			suit = "D"; 
			fullDeck.add(new Card (suit, "A", "file:///C:/Users/Heather/workspace/Assignment/graphics/ace_diamonds.png"));
			fullDeck.add(new Card (suit, "2", "file:///C:/Users/Heather/workspace/Assignment/graphics/two_diamonds.png"));
			fullDeck.add(new Card (suit, "3", "file:///C:/Users/Heather/workspace/Assignment/graphics/three_diamonds.png"));
			fullDeck.add(new Card (suit, "4", "file:///C:/Users/Heather/workspace/Assignment/graphics/four_diamonds.png"));
			fullDeck.add(new Card (suit, "5", "file:///C:/Users/Heather/workspace/Assignment/graphics/five_diamonds.png"));
			fullDeck.add(new Card (suit, "6", "file:///C:/Users/Heather/workspace/Assignment/graphics/six_diamonds.png"));
			fullDeck.add(new Card (suit, "7", "file:///C:/Users/Heather/workspace/Assignment/graphics/seven_diamonds.png"));
			fullDeck.add(new Card (suit, "8", "file:///C:/Users/Heather/workspace/Assignment/graphics/eight_diamonds.png"));
			fullDeck.add(new Card (suit, "9", "file:///C:/Users/Heather/workspace/Assignment/graphics/nine_diamonds.png"));
			fullDeck.add(new Card (suit, "10", "file:///C:/Users/Heather/workspace/Assignment/graphics/ten_diamonds.png"));
			fullDeck.add(new Card (suit, "J", "file:///C:/Users/Heather/workspace/Assignment/graphics/jack_diamonds.png"));
			fullDeck.add(new Card (suit, "Q", "file:///C:/Users/Heather/workspace/Assignment/graphics/queen_diamonds.png"));
			fullDeck.add(new Card (suit, "K", "file:///C:/Users/Heather/workspace/Assignment/graphics/king_diamonds.png"));	
			//clubs
			suit = "C"; 
			fullDeck.add(new Card (suit, "A", "file:///C:/Users/Heather/workspace/Assignment/graphics/ace_clubs.png"));
			fullDeck.add(new Card (suit, "2", "file:///C:/Users/Heather/workspace/Assignment/graphics/two_clubs.png"));
			fullDeck.add(new Card (suit, "3", "file:///C:/Users/Heather/workspace/Assignment/graphics/three_clubs.png"));
			fullDeck.add(new Card (suit, "4", "file:///C:/Users/Heather/workspace/Assignment/graphics/four_clubs.png"));
			fullDeck.add(new Card (suit, "5", "file:///C:/Users/Heather/workspace/Assignment/graphics/five_clubs.png"));
			fullDeck.add(new Card (suit, "6", "file:///C:/Users/Heather/workspace/Assignment/graphics/six_clubs.png"));
			fullDeck.add(new Card (suit, "7", "file:///C:/Users/Heather/workspace/Assignment/graphics/seven_clubs.png"));
			fullDeck.add(new Card (suit, "8", "file:///C:/Users/Heather/workspace/Assignment/graphics/eight_clubs.png"));
			fullDeck.add(new Card (suit, "9", "file:///C:/Users/Heather/workspace/Assignment/graphics/nine_clubs.png"));
			fullDeck.add(new Card (suit, "10", "file:///C:/Users/Heather/workspace/Assignment/graphics/ten_clubs.png"));
			fullDeck.add(new Card (suit, "J", "file:///C:/Users/Heather/workspace/Assignment/graphics/jack_clubs.png"));
			fullDeck.add(new Card (suit, "Q", "file:///C:/Users/Heather/workspace/Assignment/graphics/queen_clubs.png"));
			fullDeck.add(new Card (suit, "K", "file:///C:/Users/Heather/workspace/Assignment/graphics/king_clubs.png"));	
			//spades
			suit="S";
			fullDeck.add(new Card (suit, "A", "file:///C:/Users/Heather/workspace/Assignment/graphics/ace_spades.png"));
			fullDeck.add(new Card (suit, "2", "file:///C:/Users/Heather/workspace/Assignment/graphics/two_spades.png"));
			fullDeck.add(new Card (suit, "3", "file:///C:/Users/Heather/workspace/Assignment/graphics/three_spades.png"));
			fullDeck.add(new Card (suit, "4", "file:///C:/Users/Heather/workspace/Assignment/graphics/four_spades.png"));
			fullDeck.add(new Card (suit, "5", "file:///C:/Users/Heather/workspace/Assignment/graphics/five_spades.png"));
			fullDeck.add(new Card (suit, "6", "file:///C:/Users/Heather/workspace/Assignment/graphics/six_spades.png"));
			fullDeck.add(new Card (suit, "7", "file:///C:/Users/Heather/workspace/Assignment/graphics/seven_spades.png"));
			fullDeck.add(new Card (suit, "8", "file:///C:/Users/Heather/workspace/Assignment/graphics/eight_spades.png"));
			fullDeck.add(new Card (suit, "9", "file:///C:/Users/Heather/workspace/Assignment/graphics/nine_spades.png"));
			fullDeck.add(new Card (suit, "10", "file:///C:/Users/Heather/workspace/Assignment/graphics/ten_spades.png"));
			fullDeck.add(new Card (suit, "J", "file:///C:/Users/Heather/workspace/Assignment/graphics/jack_spades.png"));
			fullDeck.add(new Card (suit, "Q", "file:///C:/Users/Heather/workspace/Assignment/graphics/queen_spades.png"));
			fullDeck.add(new Card (suit, "K", "file:///C:/Users/Heather/workspace/Assignment/graphics/king_spades.png"));	
				
	}
	
	//to move discard pile into draw pile, and delete all of discard pile except first index (top card)
	//call when drawPile is empty (must check if empty before every withdraw)
	public void moveDiscard()
	{
		while (discard.size()>1)
		{
			Card temp = new Card();
			temp=discard.get(1); //creates hard copy to add to draw pile
			drawPile.add(temp); 
			discard.remove(1); //removes card from discard	 
		}
	}
	
	//random generator (for cards in withdraw pile; to deal cards out of default deck order)
	public int random()
	{
		Random r = new Random();
		int num = r.nextInt(drawPile.size());
		return num;
	}
}
