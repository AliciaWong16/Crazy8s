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
		boolean winner = false; //update when winner
		boolean firstPlay=true;
		boolean eight = false;//if an 8 is the top of the deck but not played by current player
		int two=0;
		
		//prints player cards and top display card

		
		//while no winner (all players have more than 0 cards
		while (!winner)
		{		
			if(crazy.getPlayer().getHand().size() >=26){
				System.out.println("\n" + crazy.getPlayer().getName() + "You Lost! You have at least half of the deck.");
				winner = true;
			}
			crazy.setOSuit(crazy.getDeck().getTopDiscard().getSuit());
			if (turn==1)//if it's player's turn
			{
				System.out.println("\nTop of discard pile: " + crazy.getDeck().getTopDiscard());
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
				String input = kb.next();
				int index = -2;//will change to correct index
				if(((input.equals("-1")))){
					index = Integer.parseInt(input);
				}
				else{
					boolean numbers = false;
					boolean indexCorr = false;
					while(!numbers || !indexCorr){
						int count =0;
						for(int i =0; i<input.length(); i++){
							char a = input.charAt(i);
							if(!Character.isDigit(a)){
								count++;
							}
						}
						if(count==0){
							numbers = true;
						}
						if(numbers){
							if(Integer.parseInt(input)>=crazy.getPlayer().getHand().size()){
								indexCorr = false;
							}
							else{
								indexCorr = true;
							}
						}
						if(!numbers || !indexCorr){
							System.out.print("Invalid input. Pick a card to play(index of card or -1 to withdraw): ");
							input = kb.next();
							numbers = false;
							indexCorr=false;
						}
					}
					if(numbers && indexCorr){
						index = Integer.parseInt(input);
					}
				}
				//crazy.play(index, false, crazy.getPlayer());
				if(index != -2){
					if(index!=-1) //playing a card
					{	
						while(!crazy.isPlayLegal(crazy.getPlayer().getHand().get(index))) //if play is invalid
						{
							//System.out.println("TEST: " + crazy.getPlayer().getHand().get(index));
							//System.out.println("TEST: " + crazy.getPlayer().getHand());
							System.out.print("That move is invalid. Pick a card to play (index, or -1 to withdraw): ");
							index=kb.nextInt();
							//crazy.play(index, false, crazy.getPlayer());
						}
						crazy.play(index, false, crazy.getPlayer());
						firstPlay=false;
					}
					else //if player withdrawing	
					{
						crazy.play(index, false, crazy.getPlayer());
						System.out.println("You withdrew a " + crazy.getPlayer().getHand().get(crazy.getPlayer().getHand().size()-1));
						if (crazy.isPlayLegal(crazy.getPlayer().getHand().get((crazy.getPlayer().getHand().size()-1))))
						{
							System.out.println("Top of discard pile: " + crazy.getDeck().getTopDiscard());
							System.out.println("\nYour cards: " + crazy.getPlayer().getHand().toString());
							System.out.print("Do you want to play the card you just withdrew? (Y/N) ");
							String playW = kb.next();
							while(!playW.equalsIgnoreCase("N") && !playW.equalsIgnoreCase("Y")){								
                                System.out.print("Incorrect input. Please enter \"Y\" or \"N\": ");
                                playW = kb.next();								
							}
							if (playW.equalsIgnoreCase("Y"))
							{
								crazy.play(-1, true, crazy.getPlayer()); //would this still be -1? I think think the int is used this time. 
								firstPlay=false;
							}
						}
					}
				}
				//prints player cards and top display card
				System.out.println("Your cards: " + crazy.getPlayer().getHand().toString());
				System.out.println("\nTop of discard pile: " + crazy.getDeck().getTopDiscard());
				
				if(!firstPlay) //ensures top card was played by a player before applying special effects
				{
					//8 update suit
					if(!eight){
						if (crazy.getDeck().getTopDiscard().getValue().equals("8"))
						{
							System.out.print("Which suit do you want to change it to? (S or C or D or H) ");
							String suit = kb.next();
							while(!(suit.equalsIgnoreCase("H")||suit.equalsIgnoreCase("C")||suit.equalsIgnoreCase("D")||suit.equalsIgnoreCase("S"))){
								System.out.println("Error. please input a valid suit letter: ");
								suit = kb.next();
							}
							crazy.setOSuit(suit);
							System.out.println("\nThe new suit is " + crazy.getOSuit());
							eight = true;
						}
					}
					else
						crazy.setOSuit(crazy.getDeck().getTopDiscard().getSuit());
					//test if jack
					if (crazy.getDeck().getTopDiscard().getValue().equals("J")){
						System.out.println("\nCP2 has lost their turn!");
						turn=3;
					}
					else
						turn=2;
					//two's update
					if(crazy.getDeck().getTopDiscard().getValue().equals("2"))
							two++;
					else
						two=0;
				}
				if(turn==1)
					turn=2;
				//checks if player has won
				if (crazy.getPlayer().isWinner())
				{
					winner=true;
					System.out.println("Congratulations, " + crazy.getPlayer().getName() + ", you won!!");
				}
			} 
			else if (turn ==2 && !winner) //first computer player
			{
				//withdraws twos
				if (two>=1 && !firstPlay)
				{
					crazy.play(-1, false, crazy.getCompPlayer(0));
					crazy.play(-1, false, crazy.getCompPlayer(0));
				}
				if (two>=2)
				{
					crazy.play(-1, false, crazy.getCompPlayer(0));
					crazy.play(-1, false, crazy.getCompPlayer(0));
				}
				if (two>=3)
				{
					crazy.play(-1, false, crazy.getCompPlayer(0));
					crazy.play(-1, false, crazy.getCompPlayer(0));
				}
				if (two>=4)
				{
					crazy.play(-1, false, crazy.getCompPlayer(0));
					crazy.play(-1, false, crazy.getCompPlayer(0));
				}
				
				
				//System.out.println("\ncp1 cards"+crazy.getCompPlayer(0).getHand()); //TESTING - shows computer's hand
				int chosen = crazy.compDecide(crazy.getCompPlayer(0),crazy.getCompPlayer(1));
				//System.out.println("index testing: " + chosen);
			//	crazy.play(chosen,false,crazy.getCompPlayer(0));
				if (chosen==-1) //if player withdrawing	
				{
					crazy.getCompPlayer(0).addToHand(crazy.getDeck().withdraw()); //-1 for cp not working?
					//System.out.println("CP0 withdrew a " + crazy.getCompPlayer(0).getHand().get(crazy.getCompPlayer(0).getHand().size()-1));
					if (crazy.isPlayLegal(crazy.getCompPlayer(0).getHand().get((crazy.getCompPlayer(0).getHand().size()-1))))// checks if withdrawn card can be played
					{
						crazy.play(-1, true,crazy.getCompPlayer(0)); //would this still be -1? I think think the int is used this time.
						firstPlay=false;
					}
				}	
				else
				{
					crazy.play(chosen,false,crazy.getCompPlayer(0));
					//System.out.println("CP1 has played a " + crazy.getDeck().getTopDiscard() + " card.");
					firstPlay=false;
				}
				if (!firstPlay) //checks for special cards as long as a player has placed a card
				{
					//8 update suit
					if(!eight){
						if (crazy.getDeck().getTopDiscard().getValue().equals("8"))
						{
							crazy.setOSuit(crazy.pickSuit(crazy.getCompPlayer(0)));
							System.out.println("\nThe new suit is " + crazy.getOSuit());
							eight = true;
						}
					}
					else
						crazy.setOSuit(crazy.getDeck().getTopDiscard().getSuit());
							
					//test if jack
					if (crazy.getDeck().getTopDiscard().getValue().equals("J")){
						System.out.println("\nCP3 has lost their turn!");
						turn=4;
					}
					else
						turn=3;
					//two's update
					if(crazy.getDeck().getTopDiscard().getValue().equals("2"))
						two++;
					else
						two=0;
				}	 			
				if(chosen != -1)
				{
					System.out.println("\nCP1 has played a " + crazy.getDeck().getTopDiscard() + " card.");
				}
				System.out.println("CP1 has " + crazy.getCompPlayer(0).getHand().size() + " cards left.");
				System.out.println("\nTop of discard pile: " + crazy.getDeck().getTopDiscard());
			//	System.out.println("\ncp1 cards"+crazy.getCompPlayer(0).getHand());
			//	System.out.println("done");
				 	
				//checks if player won
				if (crazy.getCompPlayer(0).isWinner())
				{
					winner=true;
					System.out.println("\n" + crazy.getPlayer().getName() + ", You Lost! CP1 Won!");
					//System.exit(0);
				}
			}
			else if (turn ==3 && !winner) //second computer player
			{
				//System.out.println("\ncp2 cards"+crazy.getCompPlayer(1).getHand()); //TESTING - shows computer's hand
				//withdraws twos
				if (two>=1 && !firstPlay)
				{
					crazy.play(-1, false, crazy.getCompPlayer(1));
					crazy.play(-1, false, crazy.getCompPlayer(1));
				}
				if (two>=2)
				{
					crazy.play(-1, false, crazy.getCompPlayer(1));
					crazy.play(-1, false, crazy.getCompPlayer(1));
				}
				if (two>=3)
				{
					crazy.play(-1, false, crazy.getCompPlayer(1));
					crazy.play(-1, false, crazy.getCompPlayer(1));
				}
				if (two>=4)
				{
					crazy.play(-1, false, crazy.getCompPlayer(1));
					crazy.play(-1, false, crazy.getCompPlayer(1));
				}
				//System.out.println("\ncp2 cards"+crazy.getCompPlayer(1).getHand()); //TESTING - shows computer's hand
				int chosen = crazy.compDecide(crazy.getCompPlayer(1),crazy.getCompPlayer(2));
				//System.out.println("index testing: " + chosen);
			//	crazy.play(chosen,false,crazy.getCompPlayer(0));
				if (chosen==-1) //if player withdrawing	
				{
					crazy.getCompPlayer(1).addToHand(crazy.getDeck().withdraw()); //-1 for cp not working?
					//System.out.println("\nCP2 withdrew a " + crazy.getCompPlayer(1).getHand().get(crazy.getCompPlayer(1).getHand().size()-1));
					if (crazy.isPlayLegal(crazy.getCompPlayer(1).getHand().get((crazy.getCompPlayer(1).getHand().size()-1))))// checks if withdrawn card can be played
					{
						crazy.play(-1, true,crazy.getCompPlayer(1)); //would this still be -1? I think think the int is used this time.
						firstPlay=false;
					}
				}	
				else
				{
					crazy.play(chosen,false,crazy.getCompPlayer(1));
					//System.out.println("CP2 has played a " + crazy.getDeck().getTopDiscard() + " card.");
					firstPlay=false;
				}
				if (!firstPlay) //checks for special cards as long as a player has placed a card
				{
					//8 update suit
					if(!eight){
						if (crazy.getDeck().getTopDiscard().getValue().equals("8"))
						{
							crazy.setOSuit(crazy.pickSuit(crazy.getCompPlayer(1)));
							System.out.println("\nThe new suit is " + crazy.getOSuit());
							eight = true;
						}
					}
					else
						crazy.setOSuit(crazy.getDeck().getTopDiscard().getSuit());
							
					//test if jack
					if (crazy.getDeck().getTopDiscard().getValue().equals("J"))
						turn=1;
					else
						turn=4;
					//two's update
					if(crazy.getDeck().getTopDiscard().getValue().equals("2"))
						two++;
					else
						two=0;
				}	 			
				if(chosen != -1)
				{
					System.out.println("\nCP2 has played a " + crazy.getDeck().getTopDiscard() + " card.");
				}
				System.out.println("CP2 has " + crazy.getCompPlayer(1).getHand().size() + " cards left.");
				System.out.println("\nTop of discard pile: " + crazy.getDeck().getTopDiscard()); 	
				//checks if player won
				if (crazy.getCompPlayer(1).isWinner())
				{
					winner=true;
					System.out.println("\n" + crazy.getPlayer().getName() + ", You Lost! CP2 Won!");
					//System.exit(0);
				}
			}
			else if (turn ==4 && !winner) //third computer player
			{
				//System.out.println("\ncp3 cards"+crazy.getCompPlayer(2).getHand()); //TESTING - shows computer's hand
				//withdraws twos
				if (two>=1 && !firstPlay)
				{
					crazy.play(-1, false, crazy.getCompPlayer(2));
					crazy.play(-1, false, crazy.getCompPlayer(2));
				}
				if (two>=2)
				{
					crazy.play(-1, false, crazy.getCompPlayer(2));
					crazy.play(-1, false, crazy.getCompPlayer(2));
				}
				if (two>=3)
				{
					crazy.play(-1, false, crazy.getCompPlayer(2));
					crazy.play(-1, false, crazy.getCompPlayer(2));
				}
				if (two>=4)
				{
					crazy.play(-1, false, crazy.getCompPlayer(2));
					crazy.play(-1, false, crazy.getCompPlayer(2));
				}
				//System.out.println("\ncp3 cards"+crazy.getCompPlayer(2).getHand()); //TESTING - shows computer's hand
				int chosen = crazy.compDecide(crazy.getCompPlayer(2),crazy.getPlayer());
				//System.out.println("index testing: " + chosen);
			//	crazy.play(chosen,false,crazy.getCompPlayer(0));
				if (chosen==-1) //if player withdrawing	
				{
					crazy.getCompPlayer(2).addToHand(crazy.getDeck().withdraw()); //-1 for cp not working?
					//System.out.println("CP3 withdrew a " + crazy.getCompPlayer(2).getHand().get(crazy.getCompPlayer(2).getHand().size()-1));
					if (crazy.isPlayLegal(crazy.getCompPlayer(2).getHand().get((crazy.getCompPlayer(2).getHand().size()-1))))// checks if withdrawn card can be played
					{
						crazy.play(-1, true,crazy.getCompPlayer(2)); //would this still be -1? I think think the int is used this time.
						firstPlay=false;
					}
				}	
				else
				{
					crazy.play(chosen,false,crazy.getCompPlayer(2));
					//System.out.println("CP3 has played a " + crazy.getDeck().getTopDiscard() + " card.");
					firstPlay=false;
				}
				if (!firstPlay) //checks for special cards as long as a player has placed a card
				{
					//8 update suit
					if(!eight){
						if (crazy.getDeck().getTopDiscard().getValue().equals("8"))
						{
							crazy.setOSuit(crazy.pickSuit(crazy.getCompPlayer(2)));
							System.out.println("\nThe new suit is " + crazy.getOSuit());
							eight = true;
						}
					}
					else
						crazy.setOSuit(crazy.getDeck().getTopDiscard().getSuit());
							
					//test if jack
					if (crazy.getDeck().getTopDiscard().getValue().equals("J")){
						turn=2;
						System.out.println("Player has lost their turn!");
					}
					else
						turn=1;
					//two's update
					if(crazy.getDeck().getTopDiscard().getValue().equals("2"))
						two++;
					else
						two=0;
				}	 			
				if(chosen != -1)
				{
					System.out.println("\nCP3 has played a " + crazy.getDeck().getTopDiscard() + " card.");
				}
				System.out.println("CP3 has " + crazy.getCompPlayer(2).getHand().size() + " cards left.");
				//System.out.println("Top of discard pile: " + crazy.getDeck().getTopDiscard()); 	
				//checks if player won
				if (crazy.getCompPlayer(2).isWinner())
				{
					winner=true;
					System.out.println("\n" + crazy.getPlayer().getName() + ", You Lost! CP3 Won!");
				}
			}
		}	
	}
}