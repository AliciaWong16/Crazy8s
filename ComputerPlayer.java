
public class ComputerPlayer extends Player{

	public ComputerPlayer(){
		super();
	}
	public int decide(){
		int chosen = -1;//index of chosen card
		boolean picked = false;
		if(1==1 ){//TODO: next player has less than or equal to 5 cards, choose 2 if possible
			for(int i = 0; i<getHand().size() & !picked; i++){
				if(getHand().get(i).getValue().equals("2")){
					chosen = i;
					picked = true;
				}
			}
		}
		if(!picked){
			for(int i = 0; i<getHand().size() & !picked; i++){//play card that matches deck
				if(getHand().get(i).getValue().equals("TODO")){//TODO: CHANGE TODO TO MATCH TOP OF DECK
					chosen = i;
					picked = true;
				}
			}
		}
		if(!picked){//play jack
			for(int i = 0; i<getHand().size() & !picked; i++){//play jack if held
				if(getHand().get(i).getValue().equals("J")){
					chosen = i;
					picked = true;
				}
			}
		}
		if(!picked){//play 8
			for(int i = 0; i<getHand().size() & !picked; i++){//play 8 as last resort
				if(getHand().get(i).getValue().equals("8")){
					chosen = i;
					picked = true;
				}
			}
		}
		return chosen;
	}
	public String pickSuit(){//class for picking suit once 8 is played
		int clubs = 0,hearts = 0,diamonds = 0,spades = 0;
		for(int i = 0; i<getHand().size(); i++){//count total number of cards in
			if(getHand().get(i).getSuit().equals("C")){
				clubs++;
			}
			else if(getHand().get(i).getSuit().equals("H")){
				hearts++;
			}
			else if(getHand().get(i).getSuit().equals("D")){
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
	//TODO : needs to have methods to decide which card to play,
	//or to withdraw. Probably the last piece to code before main method.
}