package pvz;
import java.awt.Rectangle;
import java.net.*;

public class CherryBomb extends Plant implements Runnable{
	public CherryBomb(int xPos, int yPos, Stage stage){
		super(xPos,yPos,100,100,TOUGHNESS_INFINITE,"sprites/plants/CherryBomb_Idle.gif","audio/CherryBomb.wav",stage);
		this.actionSpd=2000;
		this.plantvar=new PlantVar(hp, actionSpd, xPos, yPos, width, height, isAlive, cliptime, "CherryBomb", imageLocation ,audioLocation);
	}

	public CherryBomb(PlantVar plantvar,Stage stage){
		super(plantvar,stage);
	}
	public void shoot(){ 
		Rectangle explosion=new Rectangle(this.xPos-100,this.yPos-100,300,300);
		for(int i=stage.getZombieList().size()-1; i>=0;i-=1){
			if(explosion.intersects(stage.getZombieList().get(i).getRectangle()))
				stage.getZombieList().get(i).damaged(DAMAGE_HEAVY);
			this.soundComponent(this.clip);
		}this.setHP(TOUGHNESS_INFINITE);
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
			try {
				Thread.sleep(this.actionSpd);
			}catch (Exception e){}
			this.shoot();
		}
		
	}

}