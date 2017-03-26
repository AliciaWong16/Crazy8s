public class GameManager {

	//attributes
	private Player player; 
	private ComputerPlayer[] compPlayer; 
	private Deck deck;
	private String oSuit; //for 8 to override suit
	
	//constructor
	public GameManager(String n)
	{
		player = new Player(n);
		deck = new Deck();
		//will this work to initialize array? MUST TEST
		compPlayer = new ComputerPlayer[] {new ComputerPlayer("cp1"),
					new ComputerPlayer("cp2"), new ComputerPlayer("cp3")};
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
	
	//play class
	//NOTE: boolean p should be always set to false when calling class from main method, unless the player chooses to play the card they just withdrew
	public boolean play (int index, boolean b, Player p) //takes in card index (from hand - "-1" will be withdraw for now)
	{

		//if playing card that was just withdrawn
		if (b && isPlayLegal(p.getHand().get(p.getHand().size()-1)))
		{
			deck.addToDiscard(p.playCard(p.getHand().size()-1)); //plays last card picked up
			return true;
		}		
		// if card index does not exist in player's hand (or not withdrawing), return false - play not valid
		else if (index<-1 && index > p.getHand().size())
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
			p.addToHand(deck.withdraw()); 
			return true;
		}
		else if (isPlayLegal(p.getHand().get(index))) //if playing a card
		{
			deck.addToDiscard(p.playCard(index));//take card from player, give to discard(add to top of discard);
			return true;
		}
		return false;
	}
	public int compDecide(ComputerPlayer p, ComputerPlayer next){
		int chosen = -1;//index of chosen card
		boolean picked = false;
		if(next.getHand().size()<= 5){
			for(int i = 0; i<p.getHand().size() & !picked; i++){
				if(p.getHand().get(i).getValue().equals("2")){
					chosen = i;
					picked = true;
				}
			}
		}
		if(!picked){
			for(int i = 0; i<p.getHand().size() & !picked; i++){//play card that matches deck
				if(p.getHand().get(i).getValue().equals(deck.getTopDiscard().getValue())||
					p.getHand().get(i).getSuit().equals(deck.getTopDiscard().getSuit()) &&
					!p.getHand().get(i).getValue().equals("8")){
					chosen = i;
					picked = true;
				}
			}
		}
		if(!picked){//play jack
			for(int i = 0; i<p.getHand().size() & !picked; i++){//play jack if held
				if(p.getHand().get(i).getValue().equals("J")){
					chosen = i;
					picked = true;
				}
			}
		}
		if(!picked){//play 8
			for(int i = 0; i<p.getHand().size() & !picked; i++){//play 8 as last resort
				if(p.getHand().get(i).getValue().equals("8")){
					chosen = i;
					picked = true;
				}
			}
		}
		return chosen;
	}
	public String pickSuit(Player p){//class for picking suit once 8 is played
		int clubs = 0,hearts = 0,diamonds = 0,spades = 0;
		for(int i = 0; i<p.getHand().size(); i++){//count total number of cards in
			if(p.getHand().get(i).getSuit().equals("C")){
				clubs++;
			}
			else if(p.getHand().get(i).getSuit().equals("H")){
				hearts++;
			}
			else if(p.getHand().get(i).getSuit().equals("D")){
				diamonds++;
			}
			else{//card is spade
				spades++;
			}
		}
		String suit;//variable for suit to choose
		if(clubs >= spades && clubs >= diamonds && clubs >= hearts)//hand has most clubs
			suit = "C";
		else if(spades >= clubs && spades >= diamonds && spades >= hearts)//hand has most spades
			suit = "S";
		else if(diamonds >= spades && diamonds >= clubs && diamonds >= hearts)//hand has most diamonds
			suit = "D";
		else//most of own cards are hearts
			suit = "H";
		return suit;
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