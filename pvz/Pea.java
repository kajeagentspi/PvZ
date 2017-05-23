package pvz;

public class Pea extends Particle{
	public Pea(int xPos, int yPos,Stage stage){
		super(xPos, yPos, 28, 28, DAMAGE_NORMAL, 4,"sprites/projectiles/Pea.png" ,"audio/Pea.wav","Pea",stage);
	}
}