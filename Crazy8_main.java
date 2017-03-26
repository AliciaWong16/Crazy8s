/*
 * 
 */

import java.util.Scanner;

public class Crazy8_main {

	public static void main(String[]args)
	{
		//TODO : print rules option?	
		
		Scanner kb = new Scanner(System.in);
		System.out.print("Please enter your name: ");
		GameManager crazy = new GameManager(kb.next());
		crazy.dealCards();
	
		int turn=1; //player gets to go first TODO : update after play
		boolean winner =false; //TODO : update when winner
		
		
		//prints player cards and top display card
		System.out.println("Top of discard pile: " + crazy.getDeck().getTopDiscard());
		System.out.println("\nYour cards: " + crazy.getPlayer().getHand().toString());
		
		//while no winner (all players have more than 0 cards
		while (!winner)
		{			
			if (turn==1)//if it's player's turn
			{
				System.out.print("Pick a card to play (index, or -1 to withdraw): ");
				int index = kb.nextInt();
				crazy.play(index, false);
				
				if (index==-1) //if player withdrawing	
				{
					System.out.println("You withdrew a " + crazy.getPlayer().getHand().get(crazy.getPlayer().getHand().size()-1));
					if (crazy.isPlayLegal(crazy.getPlayer().getHand().get((crazy.getPlayer().getHand().size()-1))))// checks if withdrawn card can be played
					{
						System.out.println("Top of discard pile: " + crazy.getDeck().getTopDiscard());
						System.out.println("\nYour cards: " + crazy.getPlayer().getHand().toString());
						System.out.print("Do you want to play the card you just withdrew? (Y/N)");
						String playW = kb.next();
						if (playW.equals("Y"))
						{
							crazy.play(-1, true); //would this still be -1? I think think the int is used this time. 
						}
					}
				}
				//prints player cards and top display card
				System.out.println("Top of discard pile: " + crazy.getDeck().getTopDiscard());
				System.out.println("\nYour cards: " + crazy.getPlayer().getHand().toString());
				turn++; //TODO : check for jacks (SAMARA's code!!)
			} 
			else if (turn ==2 && !winner) //first computer player
			{
				
			}
			else if (turn ==3 && !winner) //second computer player
			{
				
			}
			else if (turn ==4 && !winner) //third computer player
			{
				
			}
				
			//for computer player, inputs next player's card number as int (cardCount)
		}	
	}	
}
