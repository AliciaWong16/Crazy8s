/*
 * 
 */

import java.util.Scanner;

public class Crazy8_main {

	public static void main(String[]args)
	{
	
		//TODO : create deck, player (ask name), computer players. Should we have a "game manager" class?
		//turns 
		
		
		Scanner kb = new Scanner(System.in);
		System.out.print("Please enter your name: ");
		GameManager crazy = new GameManager(kb.next());
		int turn=0;
		
		//while no winner (all players have more than 0 cards
		while (crazy.getPlayer().getHand().size()<1 || crazy.getCompPlayer(0).getHand().size() < 1 || crazy.getCompPlayer(1).getHand().size() < 1 || crazy.getCompPlayer(2).getHand().size() < 1)
		{
			//TODO: print player cards
			if (turn%4==0)//if it's player's turn
			{
				System.out.print("Pick a card to play (index, or -1 to withdraw): ");
				int index = kb.nextInt();
				crazy.play(index, false);
				if (index==-1)
				{
					//TODO - check if card can be played
					crazy.getPlayer().getHand().get(index);
					System.out.print("Do you want to play the card you just withdrew? (Y/N)");
					String playW = kb.next();
					
				}
			} 
			//for computer player, inputs next player's card number as int (cardCount)
		}
		
		//TODO : print rules
		
	/*	Deck deck = new Deck(); //deck is called deck
		
		//creates player
		Player player = new Player();
		player.setName(kb.next());
		
		ComputerPlayer cp1 = new ComputerPlayer();
		ComputerPlayer cp2 = new ComputerPlayer();
		ComputerPlayer cp3 = new ComputerPlayer(); */
		
		//TODO: loop for plays; count to track whose turn it is. 1- check if winner. 2 - 
	
	
	
	
	}	
}
