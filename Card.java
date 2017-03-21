/*
 * card class for crazy 8's - aggregate into deck
 * Heather Brunell March 21 2017
 */


public class Card {

	//attributes
	private String suit;
	private String value; //face card int or char
	
	//constructor
	//TODO : add a check to make sure value and suit are viable,
	//or a create deck class will take care of that?
	public Card (String suit, String value)
	{
		this.suit=suit;
		this.value=value;
	}
	
	//get methods
	public String getSuit ()
	{
		return suit;
	}
	public String getValue()
	{
		return value;
	}
	
	//set methods
	public void setSuit(String suit)
	{
		this.suit= suit;
	}
	public void setValue(String value)
	{
		this.value=value;
	}
	
	//toString method
	public String toString()
	{
		return value + suit; 
	}	
}
