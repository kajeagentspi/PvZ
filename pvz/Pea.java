package pvz;
import java.net.*;
import java.io.Serializable;


public class Pea extends Particle implements Serializable{
	public Pea(int xPos, int yPos,Stage stage){
		super(xPos, yPos,28, 28, "sprites/projectiles/Pea.png", DAMAGE_NORMAL,4,stage);//int xPos,int yPos, int width,int height, String filename,int damage,int speed,Stage stage
	}
}