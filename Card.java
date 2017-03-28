
public class Card {

	//attributes
	private String suit;
	private String value; //face card int or char
	
	//constructor
	public Card (String suit, String value)
	{
		this.suit=suit;
		this.value=value;
	}
	//empty constructor
	public Card ()
	{
		
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
		return value + " " + suit; 
	}	
}