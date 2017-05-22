package pvz;
import java.net.URL;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.Serializable;


public class SnowPea extends Plant implements Runnable, Serializable{
	public SnowPea(int xPos, int yPos, Stage stage){
		super(xPos,yPos,96,99,"sprites/plants/SnowPea.png",stage,TOUGHNESS_NORMAL);//int xPos, int yPos,int width,int height, String filename,Stage stage,int hp
		this.actionSpd=1500;
		this.plantvar=new PlantVar(hp,actionSpd,isAlive,xPos,yPos,width,height,location,"SnowPea");

		// sound initialization
		try {
			this.url = new URL("file:audio/peashooterFiring.wav");
			this.clip = AudioSystem.getClip();
			this.ais = AudioSystem.getAudioInputStream(this.url);
			this.clip.open(this.ais);

		} catch (Exception e) {}
		
	}
	public SnowPea(PlantVar plantvar,Stage stage){
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
			stage.addParticle(new IcePea(this.xPos,this.yPos+22,stage));
			this.soundComponent(this.clip);
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
		}
		
	}

}