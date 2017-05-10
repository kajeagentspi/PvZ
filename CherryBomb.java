import java.awt.Rectangle;
public class CherryBomb extends Plant implements Runnable{
	public CherryBomb(int xPos, int yPos, Stage stage){
		super(xPos,yPos,96,99,"CherryBomb.png",stage,TOUGHNESS_INFINITE);//int xPos, int yPos,int width,int height, String filename,Stage stage,int hp
		this.actionSpd=1500;
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