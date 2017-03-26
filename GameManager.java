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
	private static int turn; //to track whose turn it is : 0 for player , 1,2,3 for compPlayers
	private String oSuit; //for 8 to override suit
	
	//constructor
	public GameManager(String n)
	{
		player = new Player(n);
		deck = new Deck();
		turn =0;
		//will this work to initialize array? MUST TEST
		compPlayer = new ComputerPlayer[] {new ComputerPlayer(),
					new ComputerPlayer(), new ComputerPlayer()};
	}
	//get methods
	public String getOSuit()
	{
		return oSuit;
	}
	public Player getPlayer()
	{
		return player;
	}
	public ComputerPlayer getCompPlayer(int index)
	{
		return compPlayer[index]; //return entire array of players
	}
	public Deck getDeck()
	{
		return deck;
	}
	public static int getTurn()
	{
		return turn;
	}
	//set
	public void setOSuit(String s)
	{
		oSuit=s;
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
		deck.addToDiscard(deck.withdraw());
	}
	
	//IS PLAY LEGAL : checks for same card suit, same value, OR if it's an eight. returns true if play is legal
	public boolean isPlayLegal(Card c)
	{
		//always playable if card is an 8
		if (c.getValue().equals("8"))
			return true;
		//playable is suits or values of played card match top card (of discard pile)
		else if (c.getSuit().equals(oSuit) || c.getValue().equals(deck.getTopDiscard().getValue()))
			return true;
		else 
			return false;
	}
	
	//play class TODO: make 8's, 2's and jacks special cards
	//NOTE: boolean p should be always set to false when calling class from main method, unless the player chooses to play the card they just withdrew
	public boolean play (int index, boolean p) //takes in card index (from hand - "-1" will be withdraw for now)
	{

		//if playing card that was just withdrawn
		if (p && isPlayLegal(player.getHand().get(player.getHand().size()-1)))
		{
			deck.addToDiscard(player.playCard(player.getHand().size()-1)); //plays last card picked up
			turn++;
			return true;
		}		
		// if card index does not exist in player's hand (or not withdrawing), return false - play not valid
		else if (index<-1 && index > player.getHand().size())
		{
			System.out.println("You can't play that card!");
			return false;
		}
		else if(index==-1) //if player wants to withdraw
		{
			//if draw pile is empty, moves discard pile into draw pile(except card 0 of discard)
			if(deck.isDrawPileEmpty());
			{
				deck.moveDiscard();
			}
			player.addToHand(deck.withdraw()); 
			return true;
		}
		else if (isPlayLegal(player.getHand().get(index))) //if playing a card
		{
			deck.addToDiscard(player.playCard(index));//take card from player, give to discard(add to top of discard);
			turn++; //goes to next player's turn. This could be updated in main method loop instead (with a method to incrementTurn)
			return true;
		}
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
