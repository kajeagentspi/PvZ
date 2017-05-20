package pvz;
import java.util.ArrayList;
import java.net.URL;
import java.io.Serializable;

public class Particle extends Sprite implements Runnable, Serializable{
	protected int damage;
	protected boolean isActive;
	protected int speed;
	protected Stage stage;
	public Particle(int xPos,int yPos, int width,int height, String location,int damage,int speed,Stage stage){
		super(xPos,yPos,width,height,location);
		this.isActive=true;
		this.damage=damage;
		this.speed=speed;
		this.stage=stage;
	}
	
	public int getDamage(){//return damge of particle
		return this.damage;
	}

	protected void move(){//move pea's xpos by 1
		if(this.isActive){
			this.xPos+=1;
		}	
	}

	public void setStatus(){//change the status of particle to be deleted
		this.isActive=false;
		stage.clearDeadParticle(this);
	}

	public synchronized boolean getStatus(){//para malaman kung buburahin na
		return this.isActive;
	}
	public synchronized void colissionCheck(ArrayList<Zombie> zombieList){//check if zombie is hit
		if(zombieList.size()!=0){
			for(int i=0;i<zombieList.size();i+=1){
				if(this.getRectangle().intersects(zombieList.get(i).getRectangle())){
					zombieList.get(i).damaged(this.getDamage());
					this.setStatus();
					break;
				}
			}
		}
	}
	
	@Override
	public void run(){
		while(this.isActive){
			this.updateRectangle();
			this.colissionCheck(stage.getZombieList());
			if(this.xPos>this.width+1000){
				this.setStatus();
			}	
			this.move();
			this.repaint();
			try{
				synchronized(this){
					while(suspendFlag) wait();
				}
			}catch (InterruptedException e){}
			
			try {
				Thread.sleep(speed);
			}catch (Exception e){}
		}
	}
}