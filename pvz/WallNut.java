package pvz;
import java.net.*;
import java.io.Serializable;


public class WallNut extends Plant implements Runnable, Serializable{
	public WallNut(int xPos, int yPos, Stage stage){
		super(xPos,yPos,96,99,"sprites/plants/Tallnut_Idle.gif",stage,TOUGHNESS_HIGH);//int xPos, int yPos,int width,int height, String filename,Stage stage,int hp
		this.actionSpd=1500;
		this.plantvar=new PlantVar(hp,actionSpd,isAlive,xPos,yPos,width,height,location,"WallNut");

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