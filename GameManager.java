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
	private int turn; //will be 1,2,or 3
	private String oSuit; //for 8 to override suit
	
	//constructor
	public GameManager(String n)
	{
		player = new Player(n);
		deck = new Deck();
		compPlayer = new ComputerPlayer[] {new ComputerPlayer(),
					new ComputerPlayer(), new ComputerPlayer()};
		turn = 1; 
	}
	public GameManager() //without name of player
	{
		player = new Player();
		deck = new Deck();
		compPlayer = new ComputerPlayer[] {new ComputerPlayer(),
					new ComputerPlayer(), new ComputerPlayer()};
	}
	//get methods
	public String getOSuit()
	{
		return oSuit;
	}
	public int getTurn()
	{
		return turn;
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

	//set
	public void setOSuit(String s)
	{
		oSuit=s;
	}
	public void setTurn(int t)
	{
		turn=t;
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
		deck.addToDiscard(deck.withdraw());//starting top card
	}
	
	//IS PLAY LEGAL : checks for same card suit, same value, OR if it's an eight. returns true if play is legal
	public boolean isPlayLegal(Card c)
	{
		//always playable if card is an 8
		if (c.getValue().equals("8"))
			return true;
		//playable is suits or values of played card match top card (of discard pile) TODO change back to osuit
		else if (c.getSuit().equals(oSuit) || c.getValue().equals(deck.getTopDiscard().getValue()))
			return true;
		else 
			return false;
	}
	
	//play class
	//NOTE: boolean b should be always set to false when calling class from main method, unless the player chooses to play the card they just withdrew
	public boolean play (int index, boolean b, Player p) //takes in card index (from hand - "-1" will be withdraw for now)
	{

		//if playing card that was just withdrawn
		if (b && isPlayLegal(p.getHand().get(p.getHand().size()-1)))
		{
			deck.addToDiscard(p.playCard(p.getHand().size()-1)); //plays last card picked up
			oSuit=deck.getTopDiscard().getSuit();
			return true;
		}		
		// if card index does not exist in player's hand (or not withdrawing), return false - play not valid
		else if (index<-1 || index > p.getHand().size())
		{
			System.out.println("You can't play that card!");
			return false;
		}
		else if(index==-1) //if player wants to withdraw
		{
			//if draw pile is empty, moves discard pile into draw pile(except card 0 of discard)
			if(deck.getDrawPile().size()==0);
			{
				System.out.println("DRAW PILE EMPTY " +deck.getDrawPile().size());
				deck.moveDiscard();
			}
			p.addToHand(deck.withdraw()); 
			return true;
		}
		else if (isPlayLegal(p.getHand().get(index))) //if playing a card
		{
			deck.addToDiscard(p.playCard(index));//take card from player, give to discard(add to top of discard);
			oSuit = deck.getTopDiscard().getSuit();
			return true;
		}
		return false;
	}
	
	//computer picking card, returns index of their hand of hards
	public int compDecide(ComputerPlayer p, Player next)
	{
		int chosen = -1;//index of chosen card
		boolean picked = false; //true when picked a card??
		//if next player has few cards, play a 2 if there is one
		if(next.getHand().size()<= 5)
		{
			for(int i = 0; i < p.getHand().size() & !picked; i++)
			{
				//TODO change to compare to oSuit when implemented
				if(p.getHand().get(i).getValue().equals("2") && p.getHand().get(i).getSuit().equals(oSuit))
				{
					chosen = i;
					picked = true;
				}
			}
		}
		
		//play card that matches top discard card and is not an 8
		if(!picked){
			for(int i = 0; i<p.getHand().size() & !picked; i++){
				if(p.getHand().get(i).getValue().equals(deck.getTopDiscard().getValue())||
					p.getHand().get(i).getSuit().equals(oSuit) &&
					!p.getHand().get(i).getValue().equals("8"))
				{
					chosen = i;
					picked = true;
				}
			}
		}
		//play jack
		if(!picked)
		{	//play jack if held
			for(int i = 0; i<p.getHand().size() & !picked; i++)
			{
				if(p.getHand().get(i).getValue().equals("J") && p.getHand().get(i).getSuit().equals(oSuit))
				{
					chosen = i;
					picked = true;
				}
			}
		}
		//play 8 as last resort if no other cards to play
		if(!picked){
			for(int i = 0; i<p.getHand().size() & !picked; i++){
				if(p.getHand().get(i).getValue().equals("8")){
					chosen = i;
					picked = true;
				}
			}
		}
		//withdraws if cannot play a card
		if(!picked)
			chosen=-1;
		
		return chosen;
	}
	//class for computer player to pick a suit once 8 is played
	public String pickSuit(Player p)
	{
		int clubs = 0,hearts = 0,diamonds = 0,spades = 0;
		//count total number of each suit in hand
		for(int i = 0; i<p.getHand().size(); i++)
		{
			if(p.getHand().get(i).getSuit().equals("C"))
			{
				clubs++;
			}
			else if(p.getHand().get(i).getSuit().equals("H"))
			{
				hearts++;
			}
			else if(p.getHand().get(i).getSuit().equals("D"))
			{
				diamonds++;
			}
			else //card is spade
			{
				spades++;
			}
		}
		//picks suit that has the higest number of hard in hand
		String suit;//variable for suit to choose
		if(clubs >= spades && clubs >= diamonds && clubs >= hearts)//hand has most clubs
			suit = "C";
		else if(spades >= clubs && spades >= diamonds && spades >= hearts)//hand has most spades
			suit = "S";
		else if(diamonds >= spades && diamonds >= clubs && diamonds >= hearts)//hand has most diamonds
			suit = "D";
		else//most of own cards are hearts
			suit = "H";
		return suit; //returns String (chosen suit)
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
