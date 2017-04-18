/*
 * deck object. aggregated to player (player has a deck)
 * assumes top card of discard is 0 index. (discarded cards are added to front of array list)
 * Heather Brunell Mar 21 2017
 */

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Deck {

	//attributes
	private ArrayList<Card> fullDeck; // holds all cards; not necessary
	private ArrayList<Card> discard; //played cards, "on table"
	private ArrayList<Card> drawPile; //incomplete version of fullDeck
	private ImageView discardTop;
	
	
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
		Image getDiscardTop = new Image(c.getImgPath()); // get image path from top of discard pile
	    discardTop = new ImageView(getDiscardTop); //image view (displays image from path)
	    discardTop.setFitWidth(100);
	    discardTop.setPreserveRatio(true);
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
	//gets image of top card
	public ImageView getImageTop()
	{
		return discardTop;
	}
	
	//populate deck / drawPile
	public void populateDeck()
	{
			//hearts
			String suit ="H";
			fullDeck.add(new Card (suit, "A", "file:///C:/Users/Heather/workspace/Assignment/graphics/ace_hearts.png", 1));
			fullDeck.add(new Card (suit, "2", "file:///C:/Users/Heather/workspace/Assignment/graphics/two_hearts.png", 2));
			fullDeck.add(new Card (suit, "3", "file:///C:/Users/Heather/workspace/Assignment/graphics/three_hearts.png", 3));
			fullDeck.add(new Card (suit, "4", "file:///C:/Users/Heather/workspace/Assignment/graphics/four_hearts.png", 4));
			fullDeck.add(new Card (suit, "5", "file:///C:/Users/Heather/workspace/Assignment/graphics/five_hearts.png",5));
			fullDeck.add(new Card (suit, "6", "file:///C:/Users/Heather/workspace/Assignment/graphics/six_hearts.png",6));
			fullDeck.add(new Card (suit, "7", "file:///C:/Users/Heather/workspace/Assignment/graphics/seven_hearts.png",7));
			fullDeck.add(new Card (suit, "8", "file:///C:/Users/Heather/workspace/Assignment/graphics/eight_hearts.png",8));
			fullDeck.add(new Card (suit, "9", "file:///C:/Users/Heather/workspace/Assignment/graphics/nine_hearts.png",9));
			fullDeck.add(new Card (suit, "10", "file:///C:/Users/Heather/workspace/Assignment/graphics/ten_hearts.png",10));
			fullDeck.add(new Card (suit, "J", "file:///C:/Users/Heather/workspace/Assignment/graphics/jack_hearts.png",11));
			fullDeck.add(new Card (suit, "Q", "file:///C:/Users/Heather/workspace/Assignment/graphics/queen_hearts.png",12));
			fullDeck.add(new Card (suit, "K", "file:///C:/Users/Heather/workspace/Assignment/graphics/king_hearts.png",13));		
			//diamonds
			suit = "D"; 
			fullDeck.add(new Card (suit, "A", "file:///C:/Users/Heather/workspace/Assignment/graphics/ace_diamonds.png",14));
			fullDeck.add(new Card (suit, "2", "file:///C:/Users/Heather/workspace/Assignment/graphics/two_diamonds.png",15));
			fullDeck.add(new Card (suit, "3", "file:///C:/Users/Heather/workspace/Assignment/graphics/three_diamonds.png",16));
			fullDeck.add(new Card (suit, "4", "file:///C:/Users/Heather/workspace/Assignment/graphics/four_diamonds.png",17));
			fullDeck.add(new Card (suit, "5", "file:///C:/Users/Heather/workspace/Assignment/graphics/five_diamonds.png",18));
			fullDeck.add(new Card (suit, "6", "file:///C:/Users/Heather/workspace/Assignment/graphics/six_diamonds.png",19));
			fullDeck.add(new Card (suit, "7", "file:///C:/Users/Heather/workspace/Assignment/graphics/seven_diamonds.png",20));
			fullDeck.add(new Card (suit, "8", "file:///C:/Users/Heather/workspace/Assignment/graphics/eight_diamonds.png",21));
			fullDeck.add(new Card (suit, "9", "file:///C:/Users/Heather/workspace/Assignment/graphics/nine_diamonds.png",22));
			fullDeck.add(new Card (suit, "10", "file:///C:/Users/Heather/workspace/Assignment/graphics/ten_diamonds.png",23));
			fullDeck.add(new Card (suit, "J", "file:///C:/Users/Heather/workspace/Assignment/graphics/jack_diamonds.png",24));
			fullDeck.add(new Card (suit, "Q", "file:///C:/Users/Heather/workspace/Assignment/graphics/queen_diamonds.png",25));
			fullDeck.add(new Card (suit, "K", "file:///C:/Users/Heather/workspace/Assignment/graphics/king_diamonds.png",26));	
			//clubs
			suit = "C"; 
			fullDeck.add(new Card (suit, "A", "file:///C:/Users/Heather/workspace/Assignment/graphics/ace_clubs.png",27));
			fullDeck.add(new Card (suit, "2", "file:///C:/Users/Heather/workspace/Assignment/graphics/two_clubs.png",28));
			fullDeck.add(new Card (suit, "3", "file:///C:/Users/Heather/workspace/Assignment/graphics/three_clubs.png",29));
			fullDeck.add(new Card (suit, "4", "file:///C:/Users/Heather/workspace/Assignment/graphics/four_clubs.png",30));
			fullDeck.add(new Card (suit, "5", "file:///C:/Users/Heather/workspace/Assignment/graphics/five_clubs.png",31));
			fullDeck.add(new Card (suit, "6", "file:///C:/Users/Heather/workspace/Assignment/graphics/six_clubs.png",32));
			fullDeck.add(new Card (suit, "7", "file:///C:/Users/Heather/workspace/Assignment/graphics/seven_clubs.png",33));
			fullDeck.add(new Card (suit, "8", "file:///C:/Users/Heather/workspace/Assignment/graphics/eight_clubs.png",34));
			fullDeck.add(new Card (suit, "9", "file:///C:/Users/Heather/workspace/Assignment/graphics/nine_clubs.png",35));
			fullDeck.add(new Card (suit, "10", "file:///C:/Users/Heather/workspace/Assignment/graphics/ten_clubs.png",36));
			fullDeck.add(new Card (suit, "J", "file:///C:/Users/Heather/workspace/Assignment/graphics/jack_clubs.png",37));
			fullDeck.add(new Card (suit, "Q", "file:///C:/Users/Heather/workspace/Assignment/graphics/queen_clubs.png",38));
			fullDeck.add(new Card (suit, "K", "file:///C:/Users/Heather/workspace/Assignment/graphics/king_clubs.png",39));	
			//spades
			suit="S";
			fullDeck.add(new Card (suit, "A", "file:///C:/Users/Heather/workspace/Assignment/graphics/ace_spades.png",40));
			fullDeck.add(new Card (suit, "2", "file:///C:/Users/Heather/workspace/Assignment/graphics/two_spades.png",41));
			fullDeck.add(new Card (suit, "3", "file:///C:/Users/Heather/workspace/Assignment/graphics/three_spades.png",42));
			fullDeck.add(new Card (suit, "4", "file:///C:/Users/Heather/workspace/Assignment/graphics/four_spades.png",43));
			fullDeck.add(new Card (suit, "5", "file:///C:/Users/Heather/workspace/Assignment/graphics/five_spades.png",44));
			fullDeck.add(new Card (suit, "6", "file:///C:/Users/Heather/workspace/Assignment/graphics/six_spades.png",45));
			fullDeck.add(new Card (suit, "7", "file:///C:/Users/Heather/workspace/Assignment/graphics/seven_spades.png",46));
			fullDeck.add(new Card (suit, "8", "file:///C:/Users/Heather/workspace/Assignment/graphics/eight_spades.png",47));
			fullDeck.add(new Card (suit, "9", "file:///C:/Users/Heather/workspace/Assignment/graphics/nine_spades.png",48));
			fullDeck.add(new Card (suit, "10", "file:///C:/Users/Heather/workspace/Assignment/graphics/ten_spades.png",49));
			fullDeck.add(new Card (suit, "J", "file:///C:/Users/Heather/workspace/Assignment/graphics/jack_spades.png",50));
			fullDeck.add(new Card (suit, "Q", "file:///C:/Users/Heather/Documents/School/Crazy%208/graphics/queen_of_spades.jpg",51));
			fullDeck.add(new Card (suit, "K", "file:///C:/Users/Heather/workspace/Assignment/graphics/king_spades.png",52));	
				
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
