package pvz;
import java.util.ArrayList;

public class Particle extends Sprite implements Runnable{
	protected int damage;
	protected boolean isActive;
	protected int speed;
	protected Stage stage;
	public Particle(int xPos, int yPos, int width, int height, int damage, int speed, String imageLocation, String audiolocation, Stage stage){
		super(xPos, yPos, width, height, imageLocation, audiolocation);
		this.damage=damage;
		this.speed=speed;
		this.isActive=true;
		this.stage=stage;
	}
	
	public int getDamage(){//return damge of particle
		return this.damage;
	}
	public synchronized boolean getStatus(){//para malaman kung buburahin na
		return this.isActive;
	}
	protected void setStatus(){//change the status of particle to be deleted
		this.isActive=false;
		stage.clearDeadParticle(this);
	}

	protected void move(){//move pea's xpos by 1
		if(this.isActive){
			this.xPos+=1;
		}	
	}
	protected synchronized void colissionCheck(ArrayList<Zombie> zombieList){//check if zombie is hit
		if(zombieList.size()!=0){
			for(int i=0;i<zombieList.size();i+=1){
				if(this.getRectangle().intersects(zombieList.get(i).getRectangle())){
					this.soundComponent(this.clip);
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