public class Particle extends Sprite implements Runnable{
	protected int damage;
	protected boolean isActive;
	protected int speed;
	protected Stage stage;
	public Particle(int xPos,int yPos, int width,int height, String filename,int damage,int speed,Stage stage){
		super(xPos,yPos,width,height,filename);
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
	public void colissionCheck(){//check if zombie is hit
		stage.particleZombieColissionCheck(this);
	}
	
	@Override
	public void run(){
		while(this.isActive){
			this.updateRectangle();
			this.colissionCheck();
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