package pvz;
import java.util.*;
import java.net.*;

public class Zombie extends Sprite implements Runnable{
	protected int hp;
	protected int damage;
	protected int speed;
	protected boolean isAlive;
	protected boolean isEating;
	protected Stage stage;
	public Zombie(int xPos, int yPos,Stage stage){
		super(xPos, yPos,100, 100, "sprites/zombies/Reg_Zombie.gif",null);
		this.hp=10;
		this.damage=1;
		this.speed=50;
		this.isAlive=true;
		this.stage=stage;
	}

	//USE THIS FOR OTHER ZOMBIES
	// public Zombie(int xPos,int yPos, int width,int height, String filename, int damage,int speed, int hp){
	// 	super(xPos, yPos,width, height, filename);
	// 	this.isAlive=true;
	// 	this.hp=hp;
	// 	this.damage=damage;
	// 	this.speed=speed;
	// }
	public int getDamage(){
		return this.damage;
	}
	public boolean getStatus(){
		return this.isAlive;
	}
	private void move(){
		if(this.isAlive){
			this.xPos-=50;
		}
	}
	//applies the damage from the zombie
	public void damaged(int damage){
		this.hp-=damage;
		System.out.println("Z HP: "+this.hp);
		if(this.hp<=0){	
			this.stage.getGamePanel().setScore(1);
			this.setStatus();
			System.out.println("Zombie dead");
		}
	}
	//sets the status to false then removes the zombie
	public void setStatus(){
		this.isAlive=false;
		this.stage.clearDeadZombie(this);
	}
	//zombie eats the plant
	protected void eat(Plant plant){
		plant.setHP(this.damage);
	}
	//checks if zombie collide with a plant
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
	@Override
	public void run(){
		while(this.isAlive){
			this.updateRectangle();
			if(this.xPos<=0){
				this.isAlive=false;
				System.out.println("Game Over");
				this.stage.terminate();
				this.setStatus();
			}
			if(this.checkColission(stage.getPlantList())!=null){
				this.isEating=true;
				this.eat(this.checkColission(stage.getPlantList()));
				try{
					Thread.sleep(1000);
				}catch(Exception e){}
			}else {
				this.isEating=false;
			}
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