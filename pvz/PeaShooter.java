package pvz;
import java.net.URL;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.Serializable;


public class PeaShooter extends Plant implements Runnable, Serializable{
	public PeaShooter(int xPos, int yPos, Stage stage){
		super(xPos,yPos,96,99,"sprites/plants/Peashooter_Idle.gif",stage,TOUGHNESS_NORMAL);//int xPos, int yPos,int width,int height, String filename,Stage stage,int hp
		this.actionSpd=1500;
		this.plantvar=new PlantVar(hp,actionSpd,isAlive,xPos,yPos,width,height,location,"PeaShooter");
		// sound initialization
		try {
			this.url = new URL("file:audio/peashooterFiring.wav");
			this.clip = AudioSystem.getClip();
			this.ais = AudioSystem.getAudioInputStream(this.url);
			this.clip.open(this.ais);
		} catch (Exception e) {}
	}
	public PeaShooter(PlantVar plantvar,Stage stage){
		super(plantvar,stage);
		try {
			this.url = new URL("file:audio/peashooterFiring.wav");
			this.clip = AudioSystem.getClip();
			this.ais = AudioSystem.getAudioInputStream(this.url);
			this.clip.open(this.ais);

		} catch (Exception e) {}
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