package pvz;
import java.net.URL;
import javax.swing.*;
import javax.sound.sampled.*;

public class PeaShooter extends Plant implements Runnable{
	public PeaShooter(int xPos, int yPos, Stage stage){
		super(xPos,yPos,100,100,TOUGHNESS_NORMAL,"sprites/plants/Peashooter_Idle.gif","audio/peaShooter.wav",stage);
		this.actionSpd=1500;
		this.plantvar=new PlantVar(hp, actionSpd, xPos, yPos, width, height, isAlive, cliptime, "PeaShooter",imageLocation ,audioLocation);
	}
	public PeaShooter(PlantVar plantvar,Stage stage){
		super(plantvar,stage);
	}

	public void shoot(){ //create peas until dead
		if(stage.zombieCheck(this.xPos,this.yPos)&&!this.suspendFlag) {
			this.soundComponent(this.clip);
			this.changeIcon("sprites/plants/Peashooter_Fire.gif");
			stage.addParticle(new Pea(this.xPos+25,this.yPos+12,stage));
		}  
	}

	@Override
	public void run(){
		while(this.isAlive){
			try{
				synchronized(this){
					while(this.suspendFlag){
						wait();
					}
				}
			}catch (InterruptedException e){}
			this.shoot();
			try {
				Thread.sleep(this.actionSpd);
			}catch (Exception e){}
			this.changeIcon("sprites/plants/Peashooter_Idle.gif");
		}	
		
	}

}