public class Zombie extends Sprite implements Runnable{
	protected int hp;
	protected int damage;
	protected boolean isAlive;
	protected int speed;
	protected boolean isEating;
	protected Stage stage;
	public Zombie(int xPos, int yPos,Stage stage){
		super(xPos, yPos,100, 100, "Zombie.gif");
		this.isAlive=true;
		this.hp=100;
		this.damage=10;
		this.speed=50;
		this.stage=stage;
	}
	public Zombie(int xPos,int yPos, int width,int height, String filename, int damage,int speed, int hp){
		super(xPos, yPos,width, height, filename);
		this.isAlive=true;
		this.hp=hp;
		this.damage=damage;
		this.speed=speed;
	}
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
	protected void checkColission(){
		this.stage.zombiePlantColissionCheck(this);
	}
	protected void eat(Plant plant){
		plant.setHP(this.damage);
		
	}
	
	@Override
	public void run(){
		while(this.isAlive){
			this.updateRectangle();
			this.checkColission();
			if(this.xPos<0) 
				this.setStatus();
			
			if(this.stage.zombiePlantColissionCheck(this)!=null){
				this.isEating=true;
				this.eat(this.stage.zombiePlantColissionCheck(this));
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