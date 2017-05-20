package pvz;
import java.util.ArrayList;
import java.net.URL;
import java.io.Serializable;

public class IcePea extends Particle implements Serializable{
	public IcePea(int xPos, int yPos,Stage stage){
		super(xPos, yPos,28, 28, "sprites/particles/IcePea.png", DAMAGE_NORMAL,4,stage);//int xPos,int yPos, int width,int height, String filename,int damage,int speed,Stage stage
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