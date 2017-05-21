package pvz;
import java.net.URL;
import javax.swing.*;
import java.io.Serializable;

import javax.sound.sampled.*;

public abstract class Plant extends Sprite implements Runnable, Serializable{
	protected int hp;
	protected int actionSpd;
	protected Stage stage;
	protected boolean isAlive;
	protected PlantVar plantvar;
    // for sound
    protected transient URL url;
    protected transient Clip clip;
    protected transient AudioInputStream ais;
	public Plant(int xPos, int yPos,int width,int height, String location,Stage stage,int hp){
		super(xPos,yPos,width,height,location);
		this.stage=stage;
		this.isAlive=true;
		this.hp=hp;
	}
	public Plant(PlantVar plantvar,Stage stage){
		super(plantvar.getXPos(),plantvar.getYPos(),plantvar.getWidth(),plantvar.getHeight(),plantvar.getLocation());
		this.plantvar=plantvar;
		this.stage=stage;
		this.isAlive=plantvar.getIsAlive();
		this.hp=plantvar.getHP();
		this.actionSpd=plantvar.getActionSpd();
		this.suspendFlag=true;
	}
	public PlantVar getPlantVar(){
		return this.plantvar;
	}
	public int getHP(){//returns hp
		return this.hp;
	}

	public void setHP(int dmg){//sets damage
		this.hp-=dmg;
		System.out.println("P HP: "+this.hp);
		plantvar.setHP(this.hp);
		if(this.hp<=0){
			this.setStatus();
			System.out.println("Plant dead");
		}
	}
	public boolean getStatus(){
		return this.isAlive;
	}
	public void setStatus(){
		this.isAlive=false;
		plantvar.setisAlive(this.isAlive);
		this.stage.clearDeadPlants(this);
	}
	public int getActionSpd(){
		return this.actionSpd;
	}
	
}