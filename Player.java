import java.util.ArrayList;
public class Player {
	private String name;
	private ArrayList<Card> hand;
	
	public Player() {
		hand = new ArrayList<Card>(8);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public void playCard() {
		
	}
	
	public String toString() {
		return name + "'s cards: " + hand.toString();
				
	}
}
