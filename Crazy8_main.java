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
	
		int turn=1; //player gets to go first 
		boolean winner =false; //TODO : update when winner
		int two=0;
		
		//prints player cards and top display card
		System.out.println("Top of discard pile: " + crazy.getDeck().getTopDiscard());
		System.out.println("\nYour cards: " + crazy.getPlayer().getHand().toString());
		
		//while no winner (all players have more than 0 cards
		while (!winner)
		{			
		//	if (turn==1)//if it's player's turn
			{
				//withdraws twos
				if (two==1)
				{
					crazy.getPlayer().addToHand(crazy.getDeck().withdraw()); //withdraw 1 card
					crazy.getPlayer().addToHand(crazy.getDeck().withdraw());
				}
				else if (two==2)
				{
					crazy.getPlayer().addToHand(crazy.getDeck().withdraw()); //withdraw 1 card
					crazy.getPlayer().addToHand(crazy.getDeck().withdraw());
				}
				else if (two==3)
				{
					crazy.getPlayer().addToHand(crazy.getDeck().withdraw()); //withdraw 1 card
					crazy.getPlayer().addToHand(crazy.getDeck().withdraw());
				}
				else if (two==4)
				{
					crazy.getPlayer().addToHand(crazy.getDeck().withdraw()); //withdraw 1 card
					crazy.getPlayer().addToHand(crazy.getDeck().withdraw());
				}
					
				System.out.print("Pick a card to play (index, or -1 to withdraw): ");
				int index = kb.nextInt();
				crazy.play(index, false, crazy.getPlayer());
				
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
							crazy.play(-1, true, crazy.getPlayer()); //would this still be -1? I think think the int is used this time. 
						}
					}
				}
				//prints player cards and top display card
				System.out.println("Top of discard pile: " + crazy.getDeck().getTopDiscard());
				System.out.println("\nYour cards: " + crazy.getPlayer().getHand().toString());
				
				//8 update suit
				if (crazy.getDeck().getTopDiscard().getValue().equals("8"))
				{
					System.out.println("Which suit do you want to change it to?");
					crazy.setOSuit(kb.next());
				}
				else
					crazy.setOSuit(crazy.getDeck().getTopDiscard().getSuit());
				
				//TODO : test if jack
				if (crazy.getDeck().getTopDiscard().getValue().equals("J"))
					turn=3;
				else
					turn=2;
				//two's update
				if(crazy.getDeck().getTopDiscard().getValue().equals("2"))
						two++;
				else
					two=0;
				//checks if player won
				if (crazy.getPlayer().isWinner())
				{
					winner=true;
					System.out.println("Congratulations, " + crazy.getPlayer().getName() + " you won!!");
				}
			} 
		//	else if (turn ==2 && !winner) //first computer player
			{
				
			}
	//		else if (turn ==3 && !winner) //second computer player
			{
				
			}
	//		else if (turn ==4 && !winner) //third computer player
			{
				
			}
				
			//for computer player, inputs next player's card number as int (cardCount)
		}	
	}	
}
