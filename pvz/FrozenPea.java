package pvz;
import java.util.ArrayList;

public class FrozenPea extends Particle{
	public FrozenPea(int xPos, int yPos,Stage stage){
		super(xPos, yPos, 28, 28, DAMAGE_NORMAL, 4,"sprites/projectiles/FrozenPea.png" ,"audio/FrozenPea.wav","FrozenPea",stage);
	}
	public FrozenPea(ParticleVar particlevar, Stage stage){
		super(particlevar, stage);
	}
	@Override
	public synchronized void colissionCheck(ArrayList<Zombie> zombieList){//check if zombie is hit
		if(zombieList.size()!=0){
			for(int i=zombieList.size()-1;i>=0;i-=1){
				if(this.getRectangle().intersects(zombieList.get(i).getRectangle())){
					zombieList.get(i).slowZombie();
					zombieList.get(i).damaged(this.damage);
					this.setStatus();
					break;
				}
			}
		}
	}
}