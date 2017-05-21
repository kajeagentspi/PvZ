package pvz;
import java.awt.Rectangle;
import java.net.*;
import java.io.Serializable;

public class CherryBomb extends Plant implements Runnable, Serializable{
	public CherryBomb(int xPos, int yPos, Stage stage){
		super(xPos,yPos,96,99,"sprites/plants/CherryBomb.png",stage,TOUGHNESS_INFINITE);//int xPos, int yPos,int width,int height, String filename,Stage stage,int hp
		this.actionSpd=1500;
		this.plantvar=new PlantVar(hp,actionSpd,isAlive,xPos,yPos,width,height,location,"CherryBomb");

	}

	public CherryBomb(PlantVar plantvar,Stage stage){
		super(plantvar,stage);
	}
	public void shoot(){ 
		Rectangle explosion=new Rectangle(this.xPos-100,this.yPos-100,300,300);
		for(int i=stage.getZombieList().size()-1; i>=0;i-=1){
			if(explosion.intersects(stage.getZombieList().get(i).getRectangle()))
				stage.getZombieList().get(i).damaged(DAMAGE_HEAVY);
			//if zombie hp <=-20 burn
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