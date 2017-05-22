package pvz;
import java.net.*;

public class WallNut extends Plant implements Runnable{
	public WallNut(int xPos, int yPos, Stage stage){
		super(xPos,yPos,100,100,TOUGHNESS_HIGH,"sprites/plants/Tallnut_Idle.gif",null,stage);//int xPos, int yPos,int width,int height, String filename,Stage stage,int hp
		this.actionSpd=1500;
		this.plantvar=new PlantVar(hp, actionSpd, xPos, yPos, width, height, isAlive, cliptime, "Wallnut", imageLocation ,audioLocation);
	}
	public WallNut(PlantVar plantvar,Stage stage){
		super(plantvar,stage);
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
		}
		
	}

}