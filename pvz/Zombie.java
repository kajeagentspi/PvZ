package pvz;
import java.util.ArrayList;
import java.net.*;
import java.io.Serializable;


public class Zombie extends Sprite implements Runnable, Serializable{
	protected int hp;
	protected int damage;
	protected boolean isAlive;
	protected int speed;
	protected boolean isEating;
	protected Stage stage;
	protected int slowtime;
	protected boolean isSlowed;
	public Zombie(int xPos, int yPos,Stage stage){
		super(xPos, yPos,100, 100, "sprites/zombies/Zombie.gif");
		this.isAlive=true;
		this.hp=10;
		this.damage=1;
		this.speed=50;
		this.stage=stage;
		this.isSlowed=false;
	}
	// public Zombie(int xPos,int yPos, int width,int height, String filename, int damage,int speed, int hp){
	// 	super(xPos, yPos,width, height, filename);
	// 	this.isAlive=true;
	// 	this.hp=hp;
	// 	this.damage=damage;
	// 	this.speed=speed;
	// }
	private void move(){
		if(this.isAlive){
			this.xPos-=1;
		}	
	}
	public int getDamage(){
		return this.damage;
	}
	public boolean getStatus(){
		return this.isAlive;
	}
	public void setStatus(){
		this.isAlive=false;
		this.stage.clearDeadZombie(this);
	}
	public void damaged(int damage){
		this.hp-=damage;
		System.out.println("Z HP: "+this.hp);
		if(this.hp<=0){
			this.setStatus();
			System.out.println("Zombie dead");
		}
	}
	protected Plant checkColission(ArrayList<Plant> plantList){
		if(plantList.size()!=0){
			for(int i=0;i<plantList.size();i+=1){
				if(this.getRectangle().intersects(plantList.get(i).getRectangle())&&this.getStatus()){
					if(this.xPos>plantList.get(i).getXPos()+25){
						return plantList.get(i);
					}
				}
			}
		}return null;
	}

	protected void eat(Plant plant){
		plant.setHP(this.damage);
	}
	protected void slowZombie(){
		this.isSlowed=true;
		System.out.println("Slowed");
	}
	
	@Override
	public void run(){
		while(this.isAlive){
			this.updateRectangle();
			if(this.xPos<0) 
				this.setStatus();
			if(this.isSlowed==true){
				if(this.checkColission(stage.getPlantList())!=null){
					this.isEating=true;
					this.eat(this.checkColission(stage.getPlantList()));
					try{
						Thread.sleep(1000*2); 
					}catch(Exception e){}
				}else{
					this.isEating=false;
					for(int i=0;i<3;i++){
						if(!this.isEating) 
							this.move();
						this.repaint();
						try{
							synchronized(this){
								while(suspendFlag) wait();
							}
						}catch (InterruptedException e){}
						try {
							Thread.sleep(speed*3);
						} catch (Exception e){}
					}
				}
				this.isSlowed=false;	
			}else{
				if(this.checkColission(stage.getPlantList())!=null){
					this.isEating=true;
					this.eat(this.checkColission(stage.getPlantList()));
					try{
						Thread.sleep(1000);
					}catch(Exception e){}
				}else this.isEating=false;
				if(!this.isEating) 
					this.move();
				this.repaint();
				try{
					synchronized(this){
						while(suspendFlag) wait();
					}
				}catch (InterruptedException e){}
				try {
					Thread.sleep(speed);
				} catch (Exception e){}
			}	
		}
	}
}