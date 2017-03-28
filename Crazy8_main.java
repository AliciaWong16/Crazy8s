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
		boolean firstPlay=true;
		int two=0;
		
		//prints player cards and top display card

		
		//while no winner (all players have more than 0 cards
		while (!winner)
		{			
			crazy.setOSuit(crazy.getDeck().getTopDiscard().getSuit());
			if (turn==1)//if it's player's turn
			{
				System.out.println("Top of discard pile: " + crazy.getDeck().getTopDiscard());
				System.out.println("\nYour cards: " + crazy.getPlayer().getHand().toString());
				//withdraws twos
				if (two>=1 && !firstPlay)
				{
					crazy.getPlayer().addToHand(crazy.getDeck().withdraw()); //withdraw 1 card
					crazy.getPlayer().addToHand(crazy.getDeck().withdraw());
				}
				if (two>=2)
				{
					crazy.getPlayer().addToHand(crazy.getDeck().withdraw()); //withdraw 1 card
					crazy.getPlayer().addToHand(crazy.getDeck().withdraw());
				}
				if (two>=3)
				{
					crazy.getPlayer().addToHand(crazy.getDeck().withdraw()); //withdraw 1 card
					crazy.getPlayer().addToHand(crazy.getDeck().withdraw());
				}
				if (two>=4)
				{
					crazy.getPlayer().addToHand(crazy.getDeck().withdraw()); //withdraw 1 card
					crazy.getPlayer().addToHand(crazy.getDeck().withdraw());
				}
					
				System.out.print("Pick a card to play (index, or -1 to withdraw): ");
				int index = kb.nextInt();
		//		crazy.play(index, false, crazy.getPlayer());
				
				if(index!=-1) //playing a card
				{	
					while(!crazy.isPlayLegal(crazy.getPlayer().getHand().get(index))) //if play is invalid
					{
						System.out.println("TEST: " + crazy.getPlayer().getHand().get(index));
						System.out.println("TEST: " + crazy.getPlayer().getHand());
						System.out.print("That move is invalid. Pick a card to play (index, or -1 to witdraw): ");
						index=kb.nextInt();
						//crazy.play(index, false, crazy.getPlayer());
					}
					crazy.play(index, false, crazy.getPlayer());
					firstPlay=false;
				}
				else //if player withdrawing	
				{
					System.out.println("You withdrew a " + crazy.getPlayer().getHand().get(crazy.getPlayer().getHand().size()-1));
					if (crazy.isPlayLegal(crazy.getPlayer().getHand().get((crazy.getPlayer().getHand().size()-1))))//TODO: fix. checks if withdrawn card can be played
					{
						System.out.println("Top of discard pile: " + crazy.getDeck().getTopDiscard());
						System.out.println("\nYour cards: " + crazy.getPlayer().getHand().toString());
						System.out.print("Do you want to play the card you just withdrew? (Y/N)");
						String playW = kb.next();
						if (playW.equals("Y"))
						{
							crazy.play(-1, true, crazy.getPlayer()); //would this still be -1? I think think the int is used this time. 
							firstPlay=false;
						}
					}
				}
				//prints player cards and top display card
				System.out.println("Top of discard pile: " + crazy.getDeck().getTopDiscard());
				System.out.println("\nYour cards: " + crazy.getPlayer().getHand().toString());
				
				if(!firstPlay) //ensures top card was played by a player before applying special effects
				{
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
						turn=1;
					else
						turn=2;
					//two's update
					if(crazy.getDeck().getTopDiscard().getValue().equals("2"))
							two++;
					else
						two=0;
				}
				//checks if player has won
				if (crazy.getPlayer().isWinner())
				{
					winner=true;
					System.out.println("Congratulations, " + crazy.getPlayer().getName() + " you won!!");
				}
			} 
			else if (turn ==2 && !winner) //first computer player
			{
				System.out.println("\ncp1 cards"+crazy.getCompPlayer(0).getHand()); //TESTING - shows computer's hand
				//withdraws twos
				if (two>=1 && !firstPlay)
				{
					crazy.getCompPlayer(0).addToHand(crazy.getDeck().withdraw());
					crazy.getCompPlayer(0).addToHand(crazy.getDeck().withdraw());
				}
				if (two>=2)
				{
					crazy.getCompPlayer(0).addToHand(crazy.getDeck().withdraw());
					crazy.getCompPlayer(0).addToHand(crazy.getDeck().withdraw());
				}
				if (two>=3)
				{
					crazy.getCompPlayer(0).addToHand(crazy.getDeck().withdraw());	
					crazy.getCompPlayer(0).addToHand(crazy.getDeck().withdraw());
				}
				if (two>=4)
				{
					crazy.getCompPlayer(0).addToHand(crazy.getDeck().withdraw());
					crazy.getCompPlayer(0).addToHand(crazy.getDeck().withdraw());
				}
				
				
				System.out.println("\ncp1 cards"+crazy.getCompPlayer(0).getHand()); //TESTING - shows computer's hand
				int chosen = crazy.compDecide(crazy.getCompPlayer(0),crazy.getCompPlayer(1));
				System.out.println("index testing: " + chosen);
			//	crazy.play(chosen,false,crazy.getCompPlayer(0));
				if (chosen==-1) //if player withdrawing	
				{
					crazy.getCompPlayer(0).addToHand(crazy.getDeck().withdraw()); //-1 for cp not working?
					System.out.println("CP0 withdrew a " + crazy.getCompPlayer(0).getHand().get(crazy.getCompPlayer(0).getHand().size()-1));
					if (crazy.isPlayLegal(crazy.getCompPlayer(0).getHand().get((crazy.getCompPlayer(0).getHand().size()-1))))// checks if withdrawn card can be played
					{
						crazy.play(-1, true,crazy.getCompPlayer(0)); //would this still be -1? I think think the int is used this time.
						firstPlay=false;
					}
				}	
				else
				{
					crazy.play(chosen,false,crazy.getCompPlayer(0));
					System.out.println("CP1 has played a " + crazy.getDeck().getTopDiscard() + " card.");
					firstPlay=false;
				}
				if (!firstPlay) //checks for special cards as long as a player has placed a card
				{
					//8 update suit
					if (crazy.getDeck().getTopDiscard().getValue().equals("8"))
					{
						crazy.setOSuit(crazy.pickSuit(crazy.getCompPlayer(0)));
						System.out.println("The suit is now: " + crazy.getOSuit());
					}
					else
						crazy.setOSuit(crazy.getDeck().getTopDiscard().getSuit());
							
					//TODO : test if jack
					if (crazy.getDeck().getTopDiscard().getValue().equals("J"))
						turn=2;//TODO:change back to 4
					else
						turn=1;//TODO: CHANGE BACK TO 3
					//two's update
					if(crazy.getDeck().getTopDiscard().getValue().equals("2"))
						two++;
					else
						two=0;
				}	 			
				if(chosen != -1)
				{
					System.out.println("CP1 has played a " + crazy.getDeck().getTopDiscard() + " card.");
				}
				System.out.println("\nCP1 has " + crazy.getCompPlayer(0).getHand().size() + " cards left.");
				System.out.println("Top of discard pile: " + crazy.getDeck().getTopDiscard());
			//	System.out.println("\ncp1 cards"+crazy.getCompPlayer(0).getHand());
			//	System.out.println("done");
				 	
				//checks if player won
				if (crazy.getCompPlayer(0).isWinner())
				{
					winner=true;
					System.out.println("Congratulations, " + crazy.getCompPlayer(0).getName() + " you won!!");
					System.exit(0);
				}
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
