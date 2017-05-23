package pvz;
import java.net.URL;
import javax.swing.*;
import javax.sound.sampled.*;

public abstract class Plant extends Sprite{
	protected int hp;
	protected int actionSpd;
	protected boolean isAlive;
	protected Stage stage;
	protected Timer timer;
	//normal plant constructor
	public Plant(int xPos, int yPos,int width,int height, int hp,String imageLocation,String audioLocation,Stage stage){
		super(xPos,yPos,width,height,imageLocation,audioLocation);
		this.stage=stage;
		this.isAlive=true;
		this.hp=hp;
	}

	public int getHP(){
		return this.hp;
	}
	public int getActionSpd(){
		return this.actionSpd;
	}
	public boolean getStatus(){
		return this.isAlive;
	}

	//sets damage
	public void setHP(int dmg){
		this.hp-=dmg;
		System.out.println("P HP: "+this.hp);
		if(this.hp<=0){
			this.setStatus();
			System.out.println("Plant dead");
		}
	}
	//changes status then removes the dead plant
	private void setStatus(){
		this.isAlive=false;
		this.timer.stop();
		this.stage.clearDeadPlants(this);
	}
	public abstract void shoot();
	
}