package pvz;
import java.net.*;

public class WallNut extends Plant{
	public WallNut(int xPos, int yPos, Stage stage){
		super(xPos,yPos,100,100,TOUGHNESS_HIGH,"sprites/plants/Tallnut_Idle.gif",null,stage);
	}
	public void shoot(){}
}