import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * card class for crazy 8's - aggregate into deck
 * Heather Brunell March 21 2017
 */


public class Card {

	//attributes
	private String suit;
	private String value; //face card int or char
	private String imgPath;
	private Button cardButton;
	private int id;
	
	//constructor
	public Card (String suit, String value)
	{
		this.suit=suit;
		this.value=value;
	}
	//NEW constructor -default used now in deck
	public Card (String suit, String value, String img)
	{
		this.suit=suit;
		this.value=value;
		imgPath=img;
		setButton();
	}

	//NEW constructor - for card id
	public Card (String suit, String value, String img, int id)
	{
		this.suit=suit;
		this.value=value;
		imgPath=img;
		this.id=id;
		setButton();
	}
	
	//empty constructor
	public Card ()
	{
		
	}
	
	public int getId()
	{
		return id;
	}
	public Button getButton()
	{
		return cardButton;
	}
	
	//NEW
	public void setButton()
	{
        ImageView n = new ImageView(imgPath); //gets image path for each image; adds to imageview array
        n.setFitWidth(100);
        n.setPreserveRatio(true);
	    cardButton = new Button();
	    cardButton.setGraphic(n);
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
	public String getImgPath()
	{
		return imgPath;
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
	public void setImgPath(String path)
	{
		imgPath = path;
	}
	
	//toString method
	public String toString()
	{
		return value + " " + suit; 
	}	
}
