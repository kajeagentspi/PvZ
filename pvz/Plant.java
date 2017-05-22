package pvz;
import java.net.URL;
import javax.swing.*;
import javax.sound.sampled.*;

public abstract class Plant extends Sprite{
	protected int hp;
	protected int actionSpd;
	protected boolean isAlive;
	protected PlantVar plantvar;
	protected Stage stage;
	protected Timer timer;
	//normal plant constructor
	public Plant(int xPos, int yPos,int width,int height, int hp,String imageLocation,String audioLocation,Stage stage){
		super(xPos,yPos,width,height,imageLocation,audioLocation);
		this.stage=stage;
		this.isAlive=true;
		this.hp=hp;
	}
	//loaded plant constructor
	public Plant(PlantVar plantvar,Stage stage){
		super(plantvar.getXPos(),plantvar.getYPos(),plantvar.getWidth(),plantvar.getHeight(),plantvar.getImageLocation(),plantvar.getAudioLocation());
		this.hp=plantvar.getHP();
		this.actionSpd=plantvar.getActionSpd();
		this.isAlive=plantvar.getIsAlive();
		this.plantvar=plantvar;
		this.stage=stage;
		this.cliptime=plantvar.getClipTime();
		this.suspendFlag=true;//paused during creation
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
	public PlantVar getPlantVar(){
		return this.plantvar;
	}
	//sets damage
	public void setHP(int dmg){
		this.hp-=dmg;
		System.out.println("P HP: "+this.hp);
		plantvar.setHP(this.hp);
		if(this.hp<=0){
			this.setStatus();
			System.out.println("Plant dead");
		}
	}
	//changes status then removes the dead plant
	private void setStatus(){
		this.isAlive=false;
		plantvar.setisAlive(this.isAlive);
		this.stage.clearDeadPlants(this);
	}
	public abstract void shoot();
	
}