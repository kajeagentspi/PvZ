import java.net.URL;
import javax.swing.*;
import javax.sound.sampled.*;

public class PeaShooter extends Plant implements Runnable{
	public PeaShooter(int xPos, int yPos, Stage stage){
		super(xPos,yPos,96,99,"PeaShooter.gif",stage,TOUGHNESS_NORMAL);//int xPos, int yPos,int width,int height, String filename,Stage stage,int hp
		this.actionSpd=1500;
        // sound initialization
        try {
            this.url = new URL("file:peashooterFiring.wav");
            this.clip = AudioSystem.getClip();
            this.ais = AudioSystem.getAudioInputStream(this.url);
        } catch (Exception e) {}
        
	}

	public void shoot(){ //create peas until dead
		if(stage.zombieCheck(this.xPos,this.yPos)&&!this.suspendFlag) {
            stage.addParticle(new Pea(this.xPos,this.yPos+22,stage));
            this.soundComponent(this.clip);
        }
			
            
	}
    
    public void soundComponent(Clip clip) {
        try {
            clip.open(this.ais);
            clip.start();
        }   catch(Exception e) {}
        
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