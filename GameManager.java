/*
 * game manager class. aggregates all other classes. 
 * this is the class to be called from the main method.
 * Heather Brunell March 22 2017
 */


public class GameManager {

	//attributes
	private Player player; 
	private ComputerPlayer[] compPlayer; 
	private Deck deck;
	
	//constructor
	public GameManager()
	{
		player = new Player();
		deck = new Deck();
		
		//will this work to initialize array? MUST TEST
		compPlayer = new ComputerPlayer[] {new ComputerPlayer(),
					new ComputerPlayer(), new ComputerPlayer()};
	}
	
	//deal cards (withdraws 8) - only use in beginning of game
	public void dealCards()
	{
		for (int i=0; i <8; i++)
		{
			player.addToHand(deck.withdraw());
			compPlayer[0].addToHand(deck.withdraw());
			compPlayer[1].addToHand(deck.withdraw());
			compPlayer[2].addToHand(deck.withdraw());
		}	
	}
	
	//IS PLAY LEGAL : checks for same card suit, same value, OR if it's an eight.
	public boolean isPlayLegal(Card c)
	{
		//always playable if card is an 8
		if (c.getValue().equals("8"))
			return true;
		//playable is suits or values of played card match top card (of discard pile)
		else if (c.getSuit().equals(deck.getTopDiscard().getSuit()) || c.getValue().equals(deck.getTopDiscard().getSuit()))
			return true;
		else 
			return false;
	}
	
	//toString method 
	public String toString()
	{
		String r;
		//computer player card number
		r= "cp1 has " + compPlayer[0].getHand().size() + " cards.\n";
		r+="cp2 has " + compPlayer[1].getHand().size() + " cards.\n";
		r+="cp3 has " + compPlayer[2].getHand().size() + " cards.\n";
		
		//card on top of deck
		r+="\n\nThe top card is " + deck.getTopDiscard().toString(); //(calls card toString)
		
		//player's cards
		r+="\n\n" + player.toString();
		
		return r;
	}
	
}
